
package wg.games.warp;

import wg.games.warp.systems.TimerSystem;
import wg.games.warp.systems.gfx.PreRenderSystem;
import wg.games.warp.systems.AssetManagerSystem;
import wg.games.warp.systems.LogicSystem;
import wg.games.warp.systems.PhysicsSystem;
import wg.games.warp.systems.EntityManager;
import wg.games.warp.systems.LoadingBarSystem;
import wg.games.warp.systems.PrimitiveMovementSystem;
import wg.games.warp.systems.AISystem;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import wg.games.warp.systems.GameStateManager;
import wg.games.warp.systems.gfx.BackgroundHUDRenderSystem;
import wg.games.warp.systems.gfx.BackgroundRenderSystem;
import wg.games.warp.systems.gfx.ForegroundHUDRenderSystem;
import wg.games.warp.systems.gfx.ForegroundRenderSystem;
import wg.games.warp.systems.gfx.PostRenderSystem;

/**
 Does initialization and disposing. Also handles pausing and resuming.

 @author waxwax
 */
public class WarpGame extends ApplicationAdapter {

    //Remember, statics are evil. Use only static final for constants
    //Application
    public static final String TITLE = "Warp Game";
    public static final int VIEW_WIDTH = 800;
    public static final int VIEW_HEIGHT = 540;
    public static final float VIEW_SCALE = 1.0f;
    public static final int DISPLAY_WIDTH = (int) (VIEW_WIDTH * VIEW_SCALE);
    public static final int DISPLAY_HEIGHT = (int) (VIEW_HEIGHT * VIEW_SCALE);

    //Ticks, intervals
    public static final int TICKRATE = 60;
    public static final float AI_SYSTEM_INTERVAL = 12.0f;
    public static final float LOGIC_SYSTEM_INTERVAL = 3.0f;
    public static final float PHYSICS_SYSTEM_INTERVAL = 1.0f;

    //Physics
    private static final float GRAVITY = -10.0f;

    //Versions
    public static final int VERSION = 201607200; //YYYYMMDDV
    public static final int MAPMAKER_VERSION = 0;
    /**
     Oldest supported mapmaker version.
     */
    public static final int MAPMAKER_SUPPORT_VERSION = 0;

    //The following objects are shared and implements Disposable.
    //These are disposed here in this class.
    private com.artemis.World world;
    private World engine;
    private AssetManager assetManager;
    private SpriteBatch batch;

    @Override
    public void create() {
        System.out.println("Initializing...");

        Box2D.init();
        engine = new World(new Vector2(0.0f, GRAVITY), true);

        batch = new SpriteBatch();
        OrthographicCamera cam = new OrthographicCamera();
        OrthographicCamera hudCam = new OrthographicCamera();
        cam.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);
        hudCam.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);

        assetManager = new AssetManager();

        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new AssetManagerSystem(assetManager),
                        new PreRenderSystem(batch),
                        new BackgroundRenderSystem(batch),
                        new ForegroundRenderSystem(batch),
                        new BackgroundHUDRenderSystem(batch),
                        new ForegroundHUDRenderSystem(batch),
                        new PostRenderSystem(batch),
                        new TimerSystem(),
                        new LogicSystem(),
                        new AISystem(),
                        new PhysicsSystem(engine),
                        new PrimitiveMovementSystem(),
                        new LoadingBarSystem(assetManager),
                        new GameStateManager(),
                        new EntityManager(assetManager, engine)
                ).build();
        world = new com.artemis.World(config);

        System.out.println("...Done");
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
        //finally, dispose assets
        assetManager.dispose();

        /*
         https://github.com/libgdx/libgdx/wiki/Memory-management
         */
    }

    @Override
    public void resize(int width, int height) {
    }

    /**
     On Android this method is called when the Home button is pressed or an
     incoming call is received. On desktop this is called just before dispose()
     when exiting the application. A good place to save the game state.
     */
    @Override
    public void pause() {
    }

    /**
     This method is only called on Android, when the application resumes from a
     paused state.
     */
    @Override
    public void resume() {
    }
}
