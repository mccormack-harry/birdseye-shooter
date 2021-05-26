package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.component.MovementComponent;
import me.hazedev.shooter.component.TransformComponent;

public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(Family.all(MovementComponent.class, TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float delta) {
        MovementComponent movement = Mapper.MOVEMENT.get(entity);
        movement.velocity.add(movement.acceleration.cpy().scl(delta)).clamp(movement.minVelocity, movement.maxVelocity);
        TransformComponent transform = Mapper.TRANSFORM.get(entity);
        transform.position.add(new Vector2(movement.velocity).scl(delta));
    }

}
