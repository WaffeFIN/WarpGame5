/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.levels;

import java.io.Reader;
import wg.games.warp.systems.EntityManager;

/**
 * Handles campaigns and level loading.
 *
 * @author Walter
 */
public class LevelLoader {

    private Campaign currentCampaign;
    private Level currentLevel;
    private Level nextLevel;
    private EntityManager spawner;

    public void setCampaign(Campaign campaign) {
        if (campaign == null) {
            return;
        }
        currentCampaign = campaign;
        nextLevel = campaign.next();
    }

    public boolean loadLevel(Level level) {
        if (level == null || level.data == null || !level.data.exists()) {
            return false;
        }

        int b;
        try {
            Reader reader = level.data.reader();
            while ((b = reader.read()) != -1) {
                //read bytes, create entities?
            }
            reader.close();
        } catch (Exception ex) {
            return false;
        }
        //initialize level timers, variables
        return true;
    }

    public boolean loadNextLevel() {
        clearLevel();
        if (nextLevel == null) {
            return false;
        }
        currentLevel = nextLevel;
        nextLevel = currentCampaign.next();
        return loadLevel(nextLevel);
    }

    public boolean restartLevel() {
        clearLevel();
        return loadLevel(currentLevel);
    }

    public void clearLevel() {
        //clear all current level entities
    }
}
