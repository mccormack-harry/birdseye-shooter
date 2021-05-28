package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;

public class ShooterComponent implements Component {

    public int fireRate = 6;
    public int penetration = 1;
    public int damage = 1;
    public int kills = 0;

    public float fireCooldown = 0;
    public float damageCooldown = 0;

}
