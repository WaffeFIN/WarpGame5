/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.levels;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.InputStream;
import wg.games.warp.WarpGame;

/**
 * Handles campaigns and level loading.
 *
 * @author Walter
 */
public class LevelManager {

    private final WarpGame game;

    private Campaign currentCampaign;
    private Level currentLevel;
    private Level nextLevel;

    public LevelManager(WarpGame game) {
        this.game = game;
    }

    public void setCampaign(Campaign campaign) {
        if (campaign == null) {
            return;
        }
        currentCampaign = campaign;
        nextLevel = campaign.nextLevel();
    }

    public boolean loadLevel(Level level) {
        InputStream stream;
        try {
            if ((stream = level.data.read()) == null) {
                return false;
            }
        } catch (GdxRuntimeException ex) {
            return false;
        }
        int b;
        try {
            while ((b = stream.read()) != -1) {
                //read bytes
            }
            stream.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public void nextLevel() {
        clearLevel();
        if (nextLevel != null) {
            currentLevel = nextLevel;
            nextLevel = currentCampaign.nextLevel();
            if (loadLevel(nextLevel)) {
                return;
            }
        }
        //stop campaign
    }

    public void restartLevel() {
        clearLevel();
        loadLevel(currentLevel);
    }

    public void clearLevel() {

    }
}
