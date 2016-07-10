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
public class IntroScreen implements com.badlogic.gdx.Screen {
    
    private static final float WAITING_TIME = 1.7f;
    private final WarpGame game;

    public IntroScreen(WarpGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        
    }

    private void update(float delta) {
        //load assets, timer?
        game.waitForMenuAssets();
    }

    @Override
    public void render(float delta) {
        update(delta);
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        
    }
}
