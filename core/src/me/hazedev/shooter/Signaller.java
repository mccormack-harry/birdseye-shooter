package me.hazedev.shooter;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Signal;
import me.hazedev.shooter.event.CollisionEvent;
import me.hazedev.shooter.event.WindowResizeEvent;

public class Signaller {

    public final Signal<CollisionEvent> collisionSignal = new Signal<>();
    public final Signal<Entity> enemyDeathSignal = new Signal<>();
    public final Signal<WindowResizeEvent> windowResizeSignal = new Signal<>();

}
