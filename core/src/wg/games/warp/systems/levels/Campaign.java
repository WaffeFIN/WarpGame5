/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.systems.levels;

/**
 * Contains levels. Campaign files contains metadata and paths to each level in
 * the correct order. Data-only
 *
 * @author Walter
 */
public class Campaign {

    public String campaignName;

    private final Level[] levels;
    private int index;

    public Campaign(Level[] levels) {
        this.levels = levels;
        index = 0;
    }

    public boolean hasNext() {
        return (index < levels.length);
    }

    public Level next() {
        return (hasNext() ? levels[index++] : null);
    }

}
