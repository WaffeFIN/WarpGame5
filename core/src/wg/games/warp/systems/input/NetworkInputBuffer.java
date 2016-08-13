/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.input;

import wg.games.warp.util.Pair;

/**
 TODO for future update!
 @author Walter
 */
public class NetworkInputBuffer implements InputEventBuffer{

    private final InputEvent[] defaultContinuousInputs;
    private final InputEvent[] continuousInputs;

    private static final int BUFFER_SIZE = 256;
    private final InputEvent[] buffer;
    private int bufferIndex;

    private final Pair<Integer, Integer> mouse;

    public NetworkInputBuffer() {
        defaultContinuousInputs = InputEvent.getContinuous();
        continuousInputs = new InputEvent[defaultContinuousInputs.length];
        buffer = new InputEvent[BUFFER_SIZE];
        bufferIndex = 0;

        mouse = new Pair(0, 0);
    }

    @Override
    public Pair<Integer, Integer> getMouse() {
        return mouse; //:D
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
    
}
