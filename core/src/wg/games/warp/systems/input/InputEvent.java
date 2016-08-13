/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.input;

import java.util.Arrays;

/**

 @author Walter
 */
public enum InputEvent {

    WALK_LEFT, WALK_RIGHT,
    JUMP, DASH, TOGGLE_SLOWMO, HOLD_SLOWMO,
    AIM, PRIMARY_CLICK, SECONDARY_CLICK,
    CHANGE_CAMERA, CAMERA_1, CAMERA_2, CAMERA_3,
    NEXT_WEAPON, PREVIOUS_WEAPON, WEAPON_1, WEAPON_2, WEAPON_3;

    private static final InputEvent[] CONTINUOUS_TYPES_ARRAY = new InputEvent[]
    {AIM, WALK_LEFT, WALK_RIGHT, JUMP, DASH, HOLD_SLOWMO};

    static InputEvent[] getContinuous() {
        return Arrays.copyOf(CONTINUOUS_TYPES_ARRAY, CONTINUOUS_TYPES_ARRAY.length);
    }

    static int getContinuousIndexOf(InputEvent type) {
        int index = 0;
        for (InputEvent r : CONTINUOUS_TYPES_ARRAY) {
            if (r == type)
                return index;
            index++;
        }
        return -1;
    }
}
