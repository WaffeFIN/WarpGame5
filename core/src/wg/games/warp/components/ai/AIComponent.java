/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.components.ai;

import com.artemis.Component;
import wg.games.warp.ai.AIActionState;
import wg.games.warp.ai.AIMood;

/**
 *
 * @author Walter
 */
public class AIComponent extends Component {

    public AIMood mood = AIMood.NULL;
    public AIActionState state = AIActionState.NULL;
}
