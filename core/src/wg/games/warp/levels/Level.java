/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.levels;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.InputStream;

/**
 * Contains level data file and metadata file. Data-only class
 *
 * @author Walter
 */
public class Level {

    public final FileHandle data;

    //METADATA
    public long levelVersion;
    private long mapMakerVersion;

    public String levelName;
    public String authorName;
    public float bg_r;
    public float bg_g;
    public float bg_b;
    public int initialState;

    public float[] parTimes;
    public int[] parPortals;

    public Level(FileHandle data) {
        this.data = data;
        readMetaData();
    }

    private void readMetaData() {
        parTimes = new float[4];
        parPortals = new int[4];
    }

}
