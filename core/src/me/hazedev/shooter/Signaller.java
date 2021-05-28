package me.hazedev.shooter;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Signal;
import me.hazedev.shooter.event.CollisionEvent;

public class Signaller {

    public final Signal<CollisionEvent> collisionSignal = new Signal<>();
    public final Signal<Entity> enemyDeathSignal = new Signal<>();

}
