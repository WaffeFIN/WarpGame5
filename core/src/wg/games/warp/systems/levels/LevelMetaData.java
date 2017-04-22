/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.levels;

import com.badlogic.gdx.graphics.Color;

/**

 @author Walter
 */
public class LevelMetaData {

    /** Bronze, silver, gold, (hidden) platinum */
    private static final int MEDAL_TYPES = 4;
    private static final int PARAMS = 4;

    private int levelVersion;
    private int mapMakerVersion;

    private String name;
    private String author;
    private Color background;
    /** Default inventory and more. */
    private int stateFlags;
    /** Miscellaneous stuff. */
    private int[] params;

    /** In milliseconds. */
    private int[] parTimes;
    private int[] parPortals;

    public LevelMetaData() {
    	params = new int[PARAMS];
    	
        parTimes = new int[MEDAL_TYPES];
        parPortals = new int[MEDAL_TYPES];
    }

}
