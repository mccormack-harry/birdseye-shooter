package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {

    public float maxHealth;
    public float health;
    public float regeneration;

    public HealthComponent(float health) {
        this(health, health, 0);
    }

    public HealthComponent(float health, float maxHealth) {
        this(health, maxHealth, 0);
    }

    public HealthComponent(float health, float maxHealth, float regeneration) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.regeneration = regeneration;
    }

}
