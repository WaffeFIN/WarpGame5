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
import wg.games.warp.components.gfx.BackgroundComponent;
import wg.games.warp.components.gfx.HUDComponent;
import wg.games.warp.components.gfx.LoadingBarComponent;
import wg.games.warp.components.PositionComponent;
import wg.games.warp.components.gfx.RenderableComponent;
import wg.games.warp.components.SizeComponent;
import wg.games.warp.components.gfx.TextureComponent;
import wg.games.warp.entities.archetypes.Archetypes;
import wg.games.warp.entities.systems.*;

/**
 * Does initialisation and disposing. Also handles pausing and resuming.
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

    //Entity constants
    private static final int LOADING_BAR_WIDTH = 256;
    private static final int LOADING_BAR_HEIGHT = 13;
    private static final int LOADING_BAR_EDGE = 2;
    private static final int LOADING_BAR_Y_POSITION = 96;

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
     * Blueprints for default entities.
     */
    private Archetypes archetypes;
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
        archetypes = new Archetypes(world);

        System.out.println("...Done");

        switchState(GameState.INTRO);
    }

    private void initArtemis() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        new AssetManagerSystem(manager),
                        new ClearingRenderSystem(),
                        new TextureRenderSystem(batch),
                        new TimerSystem(),
                        new LogicSystem(),
                        new AISystem(),
                        new PhysicsSystem(engine),
                        new PrimitiveMovementSystem()
                ).build();
        world = new com.artemis.World(config);
    }

    private void initIntro() {
        //when loading bar reaches 100%, signal
        world.createEntity().edit()
                .add(new RenderableComponent())
                .add(new HUDComponent())
                .add(new BackgroundComponent())
                .add(new PositionComponent(
                        (VIEW_WIDTH - LOADING_BAR_WIDTH) / 2,
                        LOADING_BAR_Y_POSITION))
                .add(new SizeComponent(LOADING_BAR_WIDTH, LOADING_BAR_HEIGHT))
                .add(new TextureComponent(manager.get("blank.png", Texture.class)));
        world.createEntity().edit()
                .add(new RenderableComponent())
                .add(new HUDComponent())
                .add(new PositionComponent(
                        (VIEW_WIDTH - LOADING_BAR_WIDTH) / 2 + LOADING_BAR_EDGE,
                        LOADING_BAR_Y_POSITION + LOADING_BAR_EDGE))
                .add(new SizeComponent(
                        LOADING_BAR_WIDTH - 2 * LOADING_BAR_EDGE,
                        LOADING_BAR_HEIGHT - 2 * LOADING_BAR_EDGE))
                .add(new TextureComponent(manager.get("blank.png", Texture.class)))
                .add(new LoadingBarComponent());

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

    public void switchState(GameState gameState) {
        state = gameState;
        System.out.println("State switched to '" + state + "'");

    }
}
