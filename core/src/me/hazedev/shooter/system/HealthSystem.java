package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.component.HealthComponent;

public class HealthSystem extends IteratingSystem {

    public HealthSystem() {
        super(Family.all(HealthComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float delta) {
        HealthComponent health = Mapper.HEALTH.get(entity);
        health.health = Math.min(health.maxHealth, health.health + health.regeneration * delta);
    }

}
