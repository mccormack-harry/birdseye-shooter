package me.hazedev.shooter;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public interface AssetProvider {

    Texture getBackgroundTile();

    Texture getShooter();

    Texture getEnemy();

    Texture getBullet();

    ParticleEffect getEnemyBlast();

    ParticleEffect getBlueBlast();

    ParticleEffect getShooterBlast();

    Music getMusic();

    Texture getBlankTexture();

}
