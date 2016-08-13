/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/**
 Handles the delta value used in the Artemis world. Used to achieve slowmotion
 effect.

 @author Walter
 */
public class DeltaHandler {

    private final int tickrate;
    private float deltaMult;

    public DeltaHandler(int rate) {
        tickrate = rate;
        deltaMult = 1.0f;
    }

    /** @param deltaMultiplier is clamped to be between 0.0f and 1.0f */
    public void setDeltaMultiplier(float deltaMultiplier) {
        this.deltaMult = MathUtils.clamp(deltaMultiplier, 0.0f, 1.0f);
    }

    public float getDelta() {
        return deltaMult * MathUtils.clamp(Gdx.graphics.getDeltaTime(), 0.0f, 1.0f / tickrate);
    }

}
