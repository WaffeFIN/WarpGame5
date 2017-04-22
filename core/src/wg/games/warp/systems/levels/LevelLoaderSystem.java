/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.systems.levels;

import com.artemis.BaseSystem;
import java.io.IOException;
import java.io.Reader;

/**
 * Handles campaigns and level loading.
 *
 * @author Walter
 */
public class LevelLoaderSystem extends BaseSystem {

	private Campaign currentCampaign;
	private Level currentLevel;
	private Level nextLevel;

	public LevelLoaderSystem() {
		super();
	}

	public void setCampaign(Campaign campaign) {
		if (campaign == null) {
			return;
		}
		currentCampaign = campaign;
		nextLevel = campaign.next();
	}

	public boolean loadLevel() {
		return loadLevel(currentLevel);
	}

	public boolean loadLevel(Level level) {
		if (level == null || level.data == null || !level.data.exists()) {
			return false;
		}

		try {
			Reader reader = level.data.reader();
			while (true) {
				int b = reader.read();
				if (b == -1)
					break;
			}
			reader.close();
		} catch (IOException ex) {
			return false;
		}
		// initialize level timers, variables
		return true;
	}

	public boolean loadNextLevel() {
		currentLevel = nextLevel;
		nextLevel = currentCampaign.next();
		return loadLevel(nextLevel);
	}

	@Override
	protected void processSystem() {
	}
}
