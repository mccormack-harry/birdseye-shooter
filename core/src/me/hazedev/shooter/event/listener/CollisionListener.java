package me.hazedev.shooter.event.listener;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import me.hazedev.shooter.event.CollisionEvent;

public abstract class CollisionListener implements Listener<CollisionEvent> {

    public final Family family1;
    public final Family family2;

    public CollisionListener(Family family, Family family2) {
        this.family1 = family;
        this.family2 = family2;
    }

    @Override
    public void receive(Signal<CollisionEvent> signal, CollisionEvent event) {
        if (family1.matches(event.entity1) && family2.matches(event.entity2)) {
            onCollide(event.entity1, event.entity2);
        } else if (family1.matches(event.entity2) && family2.matches(event.entity1)) {
            onCollide(event.entity2, event.entity1);
        }
    }

    public abstract void onCollide(Entity entity1, Entity entity2);

}
