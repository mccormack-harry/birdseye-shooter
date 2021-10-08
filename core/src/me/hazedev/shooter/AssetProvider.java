package me.hazedev.shooter;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public interface AssetProvider {

    Texture getBackgroundTile();

    Texture getArrow();

    Texture getOctagon();

    Texture getBullet();

    ParticleEffect getEnemyBlast();

    ParticleEffect getShooterBlast();

    Music getMusic();

    Sound getFire();

    Sound getHit();

    Sound getGameOver();

    Texture getHealthUpgrade();

    Texture getRegenerationUpgrade();

    BitmapFont getFont();

}
