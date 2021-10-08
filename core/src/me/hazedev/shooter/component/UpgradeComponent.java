package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;
import me.hazedev.shooter.UpgradeType;

public class UpgradeComponent implements Component {

    public final UpgradeType type;
    public final int amount;

    public UpgradeComponent(UpgradeType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

}
