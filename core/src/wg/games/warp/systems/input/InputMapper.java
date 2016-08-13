/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.input;

import com.badlogic.gdx.Input;
import java.util.HashMap;
import java.util.Map;

/**
 Converts raw input codes into InputEvents.
 @author Walter
 */
class InputMapper {

    private final Map<Integer, InputEvent> keyboardMap;
    private final Map<Integer, InputEvent> mouseMap;
    private InputEvent scrollUp;
    private InputEvent scrollDown;

    public InputMapper() {
        this.keyboardMap = new HashMap();
        this.mouseMap = new HashMap();
        init();
    }

    private void init() {
        keyboardMap.put(Input.Keys.W, InputEvent.JUMP);
        keyboardMap.put(Input.Keys.SPACE, InputEvent.JUMP);
        keyboardMap.put(Input.Keys.UP, InputEvent.JUMP);

        keyboardMap.put(Input.Keys.A, InputEvent.WALK_LEFT);
        keyboardMap.put(Input.Keys.LEFT, InputEvent.WALK_LEFT);
        keyboardMap.put(Input.Keys.D, InputEvent.WALK_RIGHT);
        keyboardMap.put(Input.Keys.RIGHT, InputEvent.WALK_RIGHT);

        keyboardMap.put(Input.Keys.SHIFT_LEFT, InputEvent.DASH);
        keyboardMap.put(Input.Keys.NUMPAD_0, InputEvent.DASH);

        keyboardMap.put(Input.Keys.Q, InputEvent.TOGGLE_SLOWMO);
        keyboardMap.put(Input.Keys.NUMPAD_1, InputEvent.TOGGLE_SLOWMO);

        keyboardMap.put(Input.Keys.E, InputEvent.CHANGE_CAMERA);
        keyboardMap.put(Input.Keys.NUMPAD_2, InputEvent.CHANGE_CAMERA);

        keyboardMap.put(Input.Keys.NUM_1, InputEvent.WEAPON_1);
        keyboardMap.put(Input.Keys.NUM_2, InputEvent.WEAPON_2);
        keyboardMap.put(Input.Keys.NUM_3, InputEvent.WEAPON_3);
        keyboardMap.put(Input.Keys.NUMPAD_7, InputEvent.WEAPON_1);
        keyboardMap.put(Input.Keys.NUMPAD_8, InputEvent.WEAPON_2);
        keyboardMap.put(Input.Keys.NUMPAD_9, InputEvent.WEAPON_3);

        mouseMap.put(Input.Buttons.LEFT, InputEvent.PRIMARY_CLICK);
        mouseMap.put(Input.Buttons.RIGHT, InputEvent.SECONDARY_CLICK);

        scrollUp = InputEvent.NEXT_WEAPON;
        scrollDown = InputEvent.PREVIOUS_WEAPON;
    }

    InputEvent getKey(int n) {
        return (!keyboardMap.containsKey(n)) ? null : keyboardMap.get(n);
    }

    InputEvent getTouch(int n) {
        return (!mouseMap.containsKey(n)) ? null : mouseMap.get(n);
    }

    InputEvent getScroll(int amount) {
        if (amount > 0)
            return scrollUp;
        else if (amount == 0)
            return null;
        else
            return scrollDown;
    }
}
