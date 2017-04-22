/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.systems.levels;

/**
 * Contains levels. Campaign files contains metadata and paths to each level in
 * the correct order.
 *
 * @author Walter
 */
public class Campaign {

	private final Level[] levels;
	private int index;

	public CampaignMetaData metaData;

	public Campaign(Level[] levels) {
		this.levels = levels;
		index = 0;
		initMetaData();
	}

	private void initMetaData() {
		metaData = new CampaignMetaData();
		// get authors sorted by occurrence
	}

	public boolean hasNext() {
		return (index < levels.length);
	}

	public Level next() {
		return (hasNext() ? levels[index++] : null);
	}

}
