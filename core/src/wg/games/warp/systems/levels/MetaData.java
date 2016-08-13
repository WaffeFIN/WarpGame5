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
public class MetaData {

    /** Bronze, silver, gold, (hidden) platinum */
    private static final int MEDAL_TYPES = 4;

    public int levelVersion;
    public int mapMakerVersion;

    public String levelName;
    public String authorName;
    public Color background;
    /** Default inventory and more. */
    public int stateFlag;
    /** Miscellaneous stuff. */
    public int[] levelParams;

    /** In milliseconds. */
    public int[] parTimes;
    public int[] parPortals;

    public MetaData() {
        parTimes = new int[MEDAL_TYPES];
        parPortals = new int[MEDAL_TYPES];
    }

}
