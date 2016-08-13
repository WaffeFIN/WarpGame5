/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import wg.games.warp.components.AlarmComponent;
import wg.games.warp.components.TimerComponent;

/**

 @author Walter
 */
public class TimerSystem extends IteratingSystem {

    private ComponentMapper<AlarmComponent> alarmM;
    private ComponentMapper<TimerComponent> timerM;

    public TimerSystem() {
        super(Aspect.one(AlarmComponent.class, TimerComponent.class));
    }

    @Override
    protected void process(int e) {
        AlarmComponent alarmC = alarmM.get(e);
        if (alarmC != null) {
            alarmC.accumulated += world.delta;
            if (alarmC.accumulated >= alarmC.end)
                alarmM.remove(e);
        }

        TimerComponent timerC = timerM.get(e);
        if (timerC != null)
            timerC.accumulated += world.delta;
    }

}
