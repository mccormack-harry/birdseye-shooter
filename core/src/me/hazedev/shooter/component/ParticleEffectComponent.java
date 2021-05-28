package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class ParticleEffectComponent implements Component {

    public int layer;
    public final ParticleEffect effect;

    public ParticleEffectComponent(int layer, ParticleEffect effect) {
        this.layer = layer;
        this.effect = effect;
    }

    public ParticleEffectComponent(ParticleEffect effect) {
        this(0, effect);
    }
}
