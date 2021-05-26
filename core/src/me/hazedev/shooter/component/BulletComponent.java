package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public final class BulletComponent implements Component {

    public BulletComponent(Entity shooter) {
        this.shooter = shooter;
    }

    public Entity shooter;
    public int kills;
    public int damage;

}
