package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;

public class ShooterComponent implements Component {

    public int fireRate = 6;
    public int penetration = 2;
    public float cooldown = 0;

}
