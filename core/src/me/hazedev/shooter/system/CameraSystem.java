package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.component.CameraTargetComponent;
import me.hazedev.shooter.component.TransformComponent;

public class CameraSystem extends IteratingSystem {

    public final Camera camera;
    private Entity target;

    public CameraSystem(Camera camera) {
        super(Family.all(CameraTargetComponent.class, TransformComponent.class).get());
        this.camera = camera;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (target != null) {
            TransformComponent transform = Mapper.TRANSFORM.get(target);
            if (transform != null) {
                camera.position.set(transform.position.cpy(), 0);
            }
            target = null;
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (target == null) {
            target = entity;
        } else {
            CameraTargetComponent cameraTarget = Mapper.CAMERA_TARGET.get(entity);
            CameraTargetComponent currentTarget = Mapper.CAMERA_TARGET.get(entity);
            if (cameraTarget.priority > currentTarget.priority) {
                target = entity;
            }
        }
    }

}
