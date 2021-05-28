package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;

public class UpgradeComponent implements Component {

    public final UpgradeType type;
    public final int amount;

    public UpgradeComponent(UpgradeType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public enum UpgradeType {

        HEALTH,
        FIRE_RATE,
        DAMAGE,
        PENETRATION,
        REGENERATION;

    }

}
