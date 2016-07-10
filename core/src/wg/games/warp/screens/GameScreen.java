/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.screens;

import wg.games.warp.WarpGame;

/**
 *
 * @author Walter
 */
public class GameScreen implements com.badlogic.gdx.Screen {

    private final WarpGame game;

    private float time_scalar;

    public GameScreen(WarpGame game) {
        this.game = game;
        this.time_scalar = 1.0f;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        game.world.setDelta(delta * time_scalar);
        game.world.process();
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

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
//        dispose all loaded game resources? Is this necessary?
//        game.manager.clear();
//        disable all game entities
    }
}
