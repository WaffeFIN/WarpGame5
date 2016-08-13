/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import wg.games.warp.components.PositionComponent;
import wg.games.warp.components.SizeComponent;
import wg.games.warp.components.ui.ClickableComponent;
import wg.games.warp.util.Pair;

/**

 @author Walter
 */
public class ClickableSystem extends BaseEntitySystem {

    private ComponentMapper<PositionComponent> positionM;
    private ComponentMapper<SizeComponent> sizeM;
    private ComponentMapper<ClickableComponent> clickableM;

    public ClickableSystem() {
        super(Aspect.all(PositionComponent.class, SizeComponent.class, ClickableComponent.class));
    }

    public boolean click(Pair<Integer, Integer> click) {
        IntBag actives = subscription.getEntities();
        int[] ids = actives.getData();
        boolean rv = false;
        for (int i = 0, s = actives.size(); s > i; i++) {
            if (clickCheck(ids[i], click))
                rv = true;
        }

        return rv;
    }

    private boolean clickCheck(int e, Pair<Integer, Integer> click) {
        PositionComponent positionC = positionM.get(e);
        SizeComponent sizeC = sizeM.get(e);

        if (positionC == null || sizeC == null)
            return false;

        if (positionC.y <= click.y && positionC.y + sizeC.h > click.y
                && positionC.x <= click.x && positionC.x + sizeC.w > click.x) {
            ClickableComponent clickableC = clickableM.get(e);
            if (clickableC.listener != null)
                clickableC.listener.run();
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkProcessing() {
        return false;
    }

    @Override
    protected void processSystem() {
    }

}
