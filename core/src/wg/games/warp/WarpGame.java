
package wg.games.warp;

import wg.games.warp.systems.spawning.EntityManager;
import wg.games.warp.systems.render.PreRenderSystem;
import wg.games.warp.systems.render.PostRenderSystem;
import wg.games.warp.systems.render.BackgroundHUDRenderSystem;
import wg.games.warp.systems.render.ForegroundHUDRenderSystem;
import wg.games.warp.systems.render.BackgroundRenderSystem;
import wg.games.warp.systems.render.ForegroundRenderSystem;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import wg.games.warp.systems.*;
import wg.games.warp.systems.input.InputSystem;

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

    //Versions
    /** YEAR * 2^19 + MONTH * 2^15 + DAY * 2^10 + BUILD. */
    public static final int VERSION = (2016 << 19) + (8 << 15) + (9 << 10) + 1;
    public static final int MAPMAKER_VERSION = 0;
    /** Oldest supported mapmaker version. */
    public static final int MAPMAKER_LEGACY_SUPPORT_VERSION = 0;

    //Ticks, intervals
    public static final int TICKRATE = 144;
    private static final float AI_SYSTEM_INTERVAL = 1.0f / 5;
    private static final float LOGIC_SYSTEM_INTERVAL = 1.0f / 30;
    private static final float PHYSICS_SYSTEM_INTERVAL = 1.0f / 60;
    
    private DeltaHandler deltaHandler;

    //Disposable stuff
    private com.artemis.World world;
    private World engine;
    private AssetManager assetManager;
    private SpriteBatch batch;
    
    @Override
    public void create() {
        System.out.println("Warp v." + VERSION
                + "\nInitializing...");
        
        Box2D.init();
        engine = new World(new Vector2(0.0f, 0.0f), true); //gravity is assigned later

        batch = new SpriteBatch();
        OrthographicCamera cam = new OrthographicCamera();
        OrthographicCamera hudCam = new OrthographicCamera();
        cam.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);
        hudCam.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);
        
        assetManager = new AssetManager();
        
        deltaHandler = new DeltaHandler(TICKRATE);
        
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with( //Load
                        new AssetManagerSystem(assetManager),
                        new LoadingBarSystem(),
                        //Render
                        new PreRenderSystem(batch, new Color(Color.BLACK)),
                        new BackgroundRenderSystem(batch),
                        new ForegroundRenderSystem(batch),
                        new BackgroundHUDRenderSystem(batch),
                        new ForegroundHUDRenderSystem(batch),
                        new PostRenderSystem(batch),
                        //Input
                        new InputSystem(),
                        new ClickableSystem(),
                        //Misc.
                        new TimerSystem(),
                        new LogicSystem(LOGIC_SYSTEM_INTERVAL),
                        new AISystem(AI_SYSTEM_INTERVAL),
                        //Physics/Movement
                        new PhysicsSystem(engine, PHYSICS_SYSTEM_INTERVAL),
                        new PrimitiveMovementSystem(),
                        //Camera
                        new CameraSystem(cam, hudCam),
                        //Entity manager
                        new EntityManager(assetManager, engine),
                        //Game state, last
                        new GameStateManager()
                ).build();
        System.out.println("...Initialization complete"
                + "\nCreating world");
        world = new com.artemis.World(config);
    }
    
    @Override
    public void render() {
        world.setDelta(deltaHandler.getDelta());
        world.process();
    }
    
    @Override
    public void dispose() {
        world.dispose();
        engine.dispose();
        batch.dispose();
        //finally, dispose assets
        assetManager.dispose();
        //https://github.com/libgdx/libgdx/wiki/Memory-management
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
