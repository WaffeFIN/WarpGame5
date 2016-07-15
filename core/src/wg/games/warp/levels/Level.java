/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.levels;

import com.badlogic.gdx.files.FileHandle;

/**
 * Contains level data file and metadata file. Metadata should be loaded on
 * Level instance creation for the menu. Data-only class
 *
 * @author Walter
 */
public class Level {

    public final FileHandle data;

    //METADATA
    public long levelVersion;
    public long mapMakerVersion;

    public String levelName;
    public String authorName;
    public float bg_r;
    public float bg_g;
    public float bg_b;
    public int levelParam;
    public int stateParam;

    public float[] parTimes;
    public int[] parPortals;

    public Level(FileHandle data) {
        this.data = data;
        parTimes = new float[4];
        parPortals = new int[4];
    }
}
