/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.input;

import com.badlogic.gdx.InputProcessor;

/**
 Combines InputProcessor with InputEventBufferer. This interface is used by all
 "local" input processors, that is, all except network controlled
 InputEventBufferers.

 @author Walter
 */
public interface BufferedInputProcessor extends InputProcessor, InputEventBuffer {

}
