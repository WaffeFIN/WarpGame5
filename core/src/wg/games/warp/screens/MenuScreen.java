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
public class MenuScreen implements com.badlogic.gdx.Screen {

    private final WarpGame game;

    public MenuScreen(WarpGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        //create menu buttons
    }

    @Override
    public void render(float delta) {
        game.world.setDelta(delta);
        game.world.process();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        //nothing
    }

    @Override
    public void resume() {
        //nothing
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        //disable menu entities
    }

}
