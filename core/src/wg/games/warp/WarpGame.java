package wg.games.warp;

import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import wg.games.warp.entities.systems.*;
import wg.games.warp.screens.IntroScreen;

public class WarpGame extends Game {

    public static final String TITLE = "Warp Game";
    public static final int VIEW_WIDTH = 800;
    public static final int VIEW_HEIGHT = 540;
    public static final float VIEW_SCALE = 1.0f;

    public static final int TICKRATE = 60;
    public static final float AI_SYSTEM_INTERVAL = 10.0f;
    public static final float LOGIC_SYSTEM_INTERVAL = 2.0f;
    public static final float PHYSICS_SYSTEM_INTERVAL = 1.0f;

    public static final int RELEASE_VERSION = 0;
    public static final int LATEST_MAPMAKER_VERSION = 0;

    /**
     * Artemis entity management world.
     */
    public com.artemis.World world;

    /**
     * Box2D Physics engine.
     */
    private World engine;

    /**
     * Resource manager.
     */
    private AssetManager manager;

    private SpriteBatch batch;
    private OrthographicCamera cam;

    @Override
    public void create() {
        Box2D.init();
        engine = new World(new Vector2(0.0f, -10.0f), true);

        manager = new AssetManager();
        manager.load("data/texture.png", Texture.class);

        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, VIEW_WIDTH, VIEW_HEIGHT);

        initWorld();

        this.setScreen(new IntroScreen(this));

    }

    private void initWorld() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .dependsOn()
                .with(
                        new RenderSystem(),
                        new LogicSystem(),
                        new AISystem(),
                        new PhysicsSystem(engine),
                        new PrimitiveMovementSystem()
                ).build();
        world = new com.artemis.World(config);
    }

    @Override
    public void render() {
        super.render();
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
    
    public void waitForMenuAssets() {
        manager.finishLoadingAsset("");
    }
    
    public void waitForGameAssets() {
        manager.finishLoadingAsset("");
    }
}
