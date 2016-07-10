/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.levels;

import com.badlogic.gdx.files.FileHandle;
import java.io.Reader;

/**
 * Contains levels.
 *
 * @author Walter
 */
public class Campaign {

    private final String name;
    private final Level[] levelArray;
    private int current;

    public Campaign(FileHandle file) {
        name = file.nameWithoutExtension();
        levelArray = loadLevels(file);
        current = -1;
    }

    private Level[] loadLevels(FileHandle file) {
        Reader r = file.reader();

        return new Level[1];
    }

    public Level nextLevel() {
        current++;
        if (current < 0 || current >= levelArray.length) {
            return null;
        } else {
            return levelArray[current];
        }
    }

}
