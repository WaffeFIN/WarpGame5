/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.input;

import wg.games.warp.util.Pair;

/**
 Collects and buffers InputEvents, pointers and other input data for retrieval.

 @author Walter
 */
interface InputEventBuffer {

    Pair<Integer, Integer> getMouse();

    InputEvent[] getContinuousInputs();

    void resetContinuousInputs();

    InputEvent[] getBuffer();

    void resetBuffer();

}
