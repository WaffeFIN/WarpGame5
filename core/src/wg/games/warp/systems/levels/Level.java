/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.levels;

import com.badlogic.gdx.files.FileHandle;

/**
 Contains level data file and metadata. Metadata should be loaded on Level
 instance creation for the menu. Data-only class

 @author Walter
 */
public class Level {

    public final FileHandle data;
    public final LevelMetaData metaData;

    public Level(FileHandle data) {
        this.data = data;
        this.metaData = new LevelMetaData();
    }
}
