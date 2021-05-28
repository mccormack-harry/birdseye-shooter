package me.hazedev.shooter.event;

import com.badlogic.ashley.core.Entity;

public class CollisionEvent {

    public final Entity entity1;
    public final Entity entity2;

    public CollisionEvent(Entity entity1, Entity entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

}
