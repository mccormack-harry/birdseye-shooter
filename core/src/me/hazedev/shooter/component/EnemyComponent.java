package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;

public class EnemyComponent implements Component {

    public float gracePeriod;
    public float damageDelay = 0.3f;
    public int damage;

}
