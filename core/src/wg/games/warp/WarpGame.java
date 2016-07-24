package wg.games.warp;

import com.artemis.Aspect;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import wg.games.warp.entities.archetypes.Archetypes;
import wg.games.warp.entities.systems.*;

/**
 * Does initialization and disposing. Also handles pausing and resuming.
 *
 * @author waxwax
 */
public class WarpGame extends ApplicationAdapter {

    //Application
    public static final String TITLE = "Warp Game";
    public static final int VIEW_WIDTH = 800;
    public static final int VIEW_HEIGHT = 540;
    public static final float VIEW_SCALE = 1.0f;
    public static final int DISPLAY_WIDTH = (int) (VIEW_WIDTH * VIEW_SCALE);
    public static final int DISPLAY_HEIGHT = (int) (VIEW_HEIGHT * VIEW_SCALE);

    //Ticks, intervals
    public static final int TICKRATE = 60;
    public static final float AI_SYSTEM_INTERVAL = 10.0f;
    public static final float LOGIC_SYSTEM_INTERVAL = 2.0f;
    public static final float PHYSICS_SYSTEM_INTERVAL = 1.0f;

    //Physics
    private static final float GRAVITY = -10.0f;

    //Versions
    public static final int RELEASE_VERSION = 0;
    public static final int LATEST_MAPMAKER_VERSION = 0;

    //Remember, statics are evil. Use only static final for constants
    /**
     * Box2D Physics engine.
     */
    private com.badlogic.gdx.physics.box2d.World engine;
    /**
     * Resource/asset manager.
     */
    private AssetManager manager;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    /**
     * Artemis entity management world.
     */
    private com.artemis.World world;
    /**
     * Spawns entities on launch.
     */
    private EntitySpawner spawner;
    private GameState state;

    @Override
    public void create() {
        System.out.println("Initializing...");

        Box2D.init();
        engine = new com.badlogic.gdx.physics.box2d.World(new Vector2(0.0f, GRAVITY), true);

        manager = new AssetManager();
//        manager.load("assets/blank.png", Texture.class);
        manager.load(new AssetDescriptor("blank.png", Texture.class));
        manager.finishLoading();

        batch = new SpriteBatch();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);
//        CameraInputController?

        initArtemis();
        spawner.setArchetypes(new Archetypes(world));

        System.out.println("...Done");

        setState(GameState.INTRO);
        spawner.createIntro();
    }

    private void initArtemis() {
        spawner = new EntitySpawner(manager);
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        new AssetManagerSystem(manager),
                        spawner,
                        new ClearingRenderSystem(),
                        new TextureRenderSystem(batch),
                        new TimerSystem(),
                        new LogicSystem(),
                        new AISystem(),
                        new PhysicsSystem(engine),
                        new PrimitiveMovementSystem(),
                        new LoadingBarSystem()
                ).build();
        world = new com.artemis.World(config);
    }

    @Override
    public void render() {
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
    }

    @Override
    public void dispose() {
        world.dispose();
        engine.dispose();
        batch.dispose();
        //finally, dispose all resources, images, sounds, music etc.
        manager.dispose();

        //https://github.com/libgdx/libgdx/wiki/Memory-management
    }

    public void clearAllEntities() {
        IntBag ents = world.getAspectSubscriptionManager().get(Aspect.all()).getEntities();
        int[] ids = ents.getData();

        for (int i = 0; i < ids.length; i++) {
            world.delete(ids[i]);
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    /**
     * On Android this method is called when the Home button is pressed or an
     * incoming call is received. On desktop this is called just before
     * dispose() when exiting the application. A good place to save the game
     * state.
     */
    @Override
    public void pause() {
        //you could clear assets here?
    }

    /**
     * This method is only called on Android, when the application resumes from
     * a paused state.
     */
    @Override
    public void resume() {
    }

//    public GameState getState() {
//        return state;
//    }
    public void setState(GameState gameState) {
        state = gameState;
        System.out.println("State switched to '" + state + "'");
    }
}
