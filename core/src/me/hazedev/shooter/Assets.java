package me.hazedev.shooter;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetProvider {

    public static class Paths {
        public static final String TEXTURE_DIR = "texture/";
        public static final String BACKGROUND = TEXTURE_DIR + "background.png";
        public static final String SHOOTER = TEXTURE_DIR + "shooter.png";
        public static final String ENEMY = TEXTURE_DIR + "enemy.png";
        public static final String BULLET = TEXTURE_DIR + "bullet.png";
        public static final String BLANK = TEXTURE_DIR + "white.png";

        public static final String PARTICLE_DIR = "particle/";
        public static final String ENEMY_BLAST = PARTICLE_DIR + "blast.p";
        public static final String SHOOTER_BLAST = PARTICLE_DIR + "death.p";
        public static final String BLUE_BLAST = PARTICLE_DIR + "blue-blast.p";

        public static final String MUSIC_DIR = "music/";
        public static final String MUSIC = MUSIC_DIR + "techno_stargazev2.1loop.ogg";
    }

    public static class Descriptors {
        public static final AssetDescriptor<Texture> BACKGROUND_TILE = new AssetDescriptor<>(Paths.BACKGROUND, Texture.class);
        public static final AssetDescriptor<Texture> SHOOTER = new AssetDescriptor<>(Paths.SHOOTER, Texture.class);
        public static final AssetDescriptor<Texture> ENEMY = new AssetDescriptor<>(Paths.ENEMY, Texture.class);
        public static final AssetDescriptor<Texture> BULLET = new AssetDescriptor<>(Paths.BULLET, Texture.class);
        public static final AssetDescriptor<Texture> BLANK = new AssetDescriptor<>(Paths.BLANK, Texture.class);
        public static final AssetDescriptor<ParticleEffect> ENEMY_BLAST = new AssetDescriptor<>(Paths.ENEMY_BLAST, ParticleEffect.class);
        public static final AssetDescriptor<ParticleEffect> SHOOTER_BLAST = new AssetDescriptor<>(Paths.SHOOTER_BLAST, ParticleEffect.class);
        public static final AssetDescriptor<ParticleEffect> BLUE_BLAST = new AssetDescriptor<>(Paths.BLUE_BLAST, ParticleEffect.class);
        public static final AssetDescriptor<Music> MUSIC = new AssetDescriptor<>(Paths.MUSIC, Music.class);
    }

    public final AssetManager manager = new AssetManager();

    public void loadAll() {
        manager.load(Descriptors.BACKGROUND_TILE);
        manager.load(Descriptors.SHOOTER);
        manager.load(Descriptors.ENEMY);
        manager.load(Descriptors.BULLET);
        manager.load(Descriptors.BLANK);
        manager.load(Descriptors.ENEMY_BLAST);
        manager.load(Descriptors.SHOOTER_BLAST);
        manager.load(Descriptors.BLUE_BLAST);
        manager.load(Descriptors.MUSIC);
        manager.finishLoading();
    }

    @Override
    public Texture getBackgroundTile() {
        return manager.get(Descriptors.BACKGROUND_TILE);
    }

    @Override
    public Texture getShooter() {
        return manager.get(Descriptors.SHOOTER);
    }

    @Override
    public Texture getEnemy() {
        return manager.get(Descriptors.ENEMY);
    }

    @Override
    public Texture getBullet() {
        return manager.get(Descriptors.BULLET);
    }

    @Override
    public ParticleEffect getEnemyBlast() {
        return new ParticleEffect(manager.get(Descriptors.ENEMY_BLAST));
    }

    @Override
    public ParticleEffect getBlueBlast() {
        return manager.get(Descriptors.BLUE_BLAST);
    }

    @Override
    public ParticleEffect getShooterBlast() {
        return new ParticleEffect(manager.get(Descriptors.SHOOTER_BLAST));
    }

    @Override
    public Music getMusic() {
        return manager.get(Descriptors.MUSIC);
    }

    @Override
    public Texture getBlankTexture() {
        return manager.get(Descriptors.BLANK);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

}
