package wg.games.warp.components;

import com.artemis.Component;

import wg.games.warp.systems.GameState;

public class LoadingComponent extends Component {

    public float loadingProgress = 0.0f;
    public GameState nextState = GameState.MENU;
}
