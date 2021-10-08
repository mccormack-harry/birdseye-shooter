package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.World;
import me.hazedev.shooter.component.CameraTargetComponent;
import me.hazedev.shooter.component.SpriteComponent;

public class CameraSystem extends IteratingSystem {

    private final World world;
    private Entity target;

    public CameraSystem(World world) {
        super(Family.all(CameraTargetComponent.class, SpriteComponent.class).get());
        this.world = world;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (target != null) {
            SpriteComponent sprite = Mapper.SPRITE.get(target);
            if (sprite != null) {
                world.camera.position.set(
                        Math.max(0, Math.min(world.size, sprite.sprite.getX())),
                        Math.max(0, Math.min(world.size, sprite.sprite.getY())), 0);
                world.viewport.setWorldSize(sprite.sprite.getWidth() * 50, sprite.sprite.getHeight() * 50);
                world.viewport.apply();
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
