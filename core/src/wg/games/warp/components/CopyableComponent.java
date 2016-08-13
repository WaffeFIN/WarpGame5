/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.components;

import com.artemis.Component;

/**

 @author Walter
 */
public abstract class CopyableComponent extends Component {

    public abstract <T extends Component> void copyFrom(T original);
}
