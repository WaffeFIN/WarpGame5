/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.input;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import wg.games.warp.systems.ClickableSystem;
import wg.games.warp.util.Pair;

/**


 @author Walter
 */
public class InputSystem extends BaseSystem {

    private final BufferedInputProcessor inputProcessor;
    /**
     This is used to allow multiplayer in the future.
     */
    private final InputEventBuffer[] inputBufferers;
    private Pair<Integer, Integer> currentMouse;

    public InputSystem() {
        inputProcessor = new DesktopInputProcessor();
        inputBufferers = new InputEventBuffer[]{inputProcessor};
    }

    @Override
    protected void initialize() {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    protected void processSystem() {
        for (InputEventBuffer inputBuffer : inputBufferers) {
            if (inputBuffer == null)
                continue;
            
            InputEvent[] inputs;
            currentMouse = inputBuffer.getMouse();

            inputs = inputBuffer.getContinuousInputs();
            for (InputEvent input : inputs) {
                processInput(input);
            }

            inputs = inputBuffer.getBuffer();
            for (InputEvent input : inputs) {
                if (input == null)
                    break;
                processInput(input);
            }

            inputBuffer.resetBuffer();
        }
    }

    private void processInput(InputEvent event) {
        if (event == null)
            return;
        System.out.println(event);
        switch (event) {
            case AIM:
                //player sprite angles, camera?, firing angle
                return;
            case WALK_RIGHT:
                //player
                return;
            case WALK_LEFT:
                //player
                return;
            case JUMP:
                //player
                return;
            case DASH:
                //player
                return;
            case HOLD_SLOWMO:
                //player
                return;
            case PRIMARY_CLICK:
                if (!world.getSystem(ClickableSystem.class).click(currentMouse))
                    System.out.println("player click");
                //click XOR player
                return;
            case SECONDARY_CLICK:
                //player
                return;
            case CHANGE_CAMERA:
                //camera
                return;
            case NEXT_WEAPON:
                //player
                return;
            case PREVIOUS_WEAPON:
                //player
                return;
            case TOGGLE_SLOWMO:
                //player
                return;
            case WEAPON_1:
                //player
                return;
            case WEAPON_2:
                //player
                return;
            case WEAPON_3:
                //player
                return;
            case CAMERA_1:
                //camera
                return;
            case CAMERA_2:
                //camera
                return;
            case CAMERA_3:
                //camera
                return;
        }
    }

}
