/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import wg.games.warp.components.CameraFocusComponent;
import wg.games.warp.components.PositionComponent;

/**

 @author Walter
 */
public class CameraSystem extends IteratingSystem {

    private final OrthographicCamera cam;
    private final OrthographicCamera hudCam;

    private ComponentMapper<PositionComponent> positionM;

    public CameraSystem(OrthographicCamera cam, OrthographicCamera hudCam) {
        super(Aspect.all(PositionComponent.class, CameraFocusComponent.class));
        this.cam = cam;
        this.hudCam = hudCam;
    }

    public Matrix4 getCameraMatrix() {
        return cam.combined;
    }

    @Override
    protected void process(int e) {
        Vector3 pos = cam.position;
        PositionComponent positionC = positionM.get(e);
        pos.set(positionC.x, positionC.y, 0);
    }
}
