package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class ParticleEffectComponent implements Component {

    public final ParticleEffect effect;
    public int layer;

    public ParticleEffectComponent(ParticleEffect effect, int layer) {
        this.effect = effect;
        this.layer = layer;
    }

    public ParticleEffectComponent(ParticleEffect effect) {
        this(effect, 0);
    }
}
