/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.input;

import com.badlogic.gdx.Gdx;
import wg.games.warp.util.Pair;

/**
 The LocalInputProcessor used in the Desktop app. Collects and buffers
 InputEvents, pointers and other input data for retrieval.

 @author Walter
 */
class DesktopInputProcessor implements BufferedInputProcessor {

    private final InputEvent[] defaultContinuousInputs;
    private final InputEvent[] continuousInputs;

    private static final int BUFFER_SIZE = 16;
    private final InputEvent[] buffer;
    private int bufferIndex;

    private final InputMapper mapper;

    private final Pair<Integer, Integer> mouse;

    public DesktopInputProcessor() {
        defaultContinuousInputs = InputEvent.getContinuous();
        continuousInputs = new InputEvent[defaultContinuousInputs.length];
        buffer = new InputEvent[BUFFER_SIZE];
        bufferIndex = 0;

        mapper = new InputMapper();

        mouse = new Pair(0, 0);
    }

    @Override
    public Pair<Integer, Integer> getMouse() {
        return mouse;
    }

    @Override
    public InputEvent[] getContinuousInputs() {
        return continuousInputs;
    }

    @Override
    public void resetContinuousInputs() {
        for (int i = 0; i < continuousInputs.length; i++) {
            continuousInputs[i] = null;
        }
    }

    @Override
    public InputEvent[] getBuffer() {
        return buffer;
    }

    @Override
    public void resetBuffer() {
        while (bufferIndex > 0) {
            buffer[--bufferIndex] = null;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        InputEvent event = mapper.getKey(keycode);
        if (event != null) {
            int i = InputEvent.getContinuousIndexOf(event);
            if (i != -1)
                continuousInputs[i] = defaultContinuousInputs[i];
            else if (bufferIndex < BUFFER_SIZE)
                buffer[bufferIndex++] = event;
        }
        return true; //:D
    }

    @Override
    public boolean keyUp(int keycode) {
        InputEvent event = mapper.getKey(keycode);
        if (event != null) {
            int i = InputEvent.getContinuousIndexOf(event);
            if (i != -1)
                continuousInputs[i] = null;
        }
        return true; //:D
    }

    @Override
    public boolean keyTyped(char character) {
        return false; //:D
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (bufferIndex < BUFFER_SIZE) {
            InputEvent event = mapper.getTouch(button);
            if (event != null)
                buffer[bufferIndex++] = event;
        }
        return true; //:D
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouse.x = screenX;
        mouse.y = Gdx.graphics.getHeight() - 1 - screenY;
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        if (bufferIndex < BUFFER_SIZE) {
            InputEvent event = mapper.getScroll(amount);
            if (event != null)
                buffer[bufferIndex++] = event;
        }
        return true; //:D
    }
}
