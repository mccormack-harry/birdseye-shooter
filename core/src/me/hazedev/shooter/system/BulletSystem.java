package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.World;
import me.hazedev.shooter.component.BulletComponent;
import me.hazedev.shooter.component.HealthComponent;
import me.hazedev.shooter.component.TransformComponent;

public class BulletSystem extends IteratingSystem {

    private final World world;

    public BulletSystem(World world) {
        super(Family.all(BulletComponent.class, TransformComponent.class).get());
        this.world = world;

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        BulletComponent bullet = Mapper.BULLET.get(entity);

        HealthComponent health = Mapper.HEALTH.get(entity);
        boolean dead = health.health <= 0;

        if (!dead) {
            TransformComponent transform = Mapper.TRANSFORM.get(entity);
            TransformComponent shooterTransform = Mapper.TRANSFORM.get(bullet.shooter);
            if (shooterTransform.position.dst(transform.position) > Math.max(world.viewport.getWorldWidth(), world.viewport.getWorldHeight())) {
                dead = true;
            }
        }

        if (dead) {
            Mapper.SHOOTER.get(bullet.shooter).kills += bullet.kills;
            world.removeEntity(entity);
        }

    }

}
