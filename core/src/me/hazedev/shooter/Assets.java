package me.hazedev.shooter;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetProvider {

    public static class Paths {
        public static final String TEXTURE_DIR = "texture/";
        public static final String BACKGROUND = TEXTURE_DIR + "background.png";
        public static final String ARROW = TEXTURE_DIR + "arrow.png";
        public static final String OCTAGON = TEXTURE_DIR + "octagon.png";
        public static final String BULLET = TEXTURE_DIR + "bullet.png";
        public static final String HEALTH = TEXTURE_DIR + "health.png";
        public static final String REGENERATION = TEXTURE_DIR + "regeneration.png";

        public static final String PARTICLE_DIR = "particle/";
        public static final String ENEMY_BLAST = PARTICLE_DIR + "blast.p";
        public static final String SHOOTER_BLAST = PARTICLE_DIR + "death.p";

        public static final String MUSIC_DIR = "music/";
        public static final String MUSIC = MUSIC_DIR + "techno_stargazev2.1loop.ogg";

        public static final String SFX_DIR = "sfx/";
        public static final String FIRE = SFX_DIR + "fire2.mp3";
        public static final String HIT = SFX_DIR + "hit.mp3";
        public static final String GAME_OVER = SFX_DIR + "game-over.mp3";

    }

    public static class Descriptors {
        public static final AssetDescriptor<Texture> BACKGROUND_TILE = new AssetDescriptor<>(Paths.BACKGROUND, Texture.class);
        public static final AssetDescriptor<Texture> ARROW = new AssetDescriptor<>(Paths.ARROW, Texture.class);
        public static final AssetDescriptor<Texture> OCTAGON = new AssetDescriptor<>(Paths.OCTAGON, Texture.class);
        public static final AssetDescriptor<Texture> BULLET = new AssetDescriptor<>(Paths.BULLET, Texture.class);
        public static final AssetDescriptor<Texture> HEALTH = new AssetDescriptor<>(Paths.HEALTH, Texture.class);
        public static final AssetDescriptor<Texture> REGENERATION = new AssetDescriptor<>(Paths.REGENERATION, Texture.class);
        public static final AssetDescriptor<ParticleEffect> ENEMY_BLAST = new AssetDescriptor<>(Paths.ENEMY_BLAST, ParticleEffect.class);
        public static final AssetDescriptor<ParticleEffect> SHOOTER_BLAST = new AssetDescriptor<>(Paths.SHOOTER_BLAST, ParticleEffect.class);
        public static final AssetDescriptor<Music> MUSIC = new AssetDescriptor<>(Paths.MUSIC, Music.class);
        public static final AssetDescriptor<Sound> FIRE = new AssetDescriptor<>(Paths.FIRE, Sound.class);
        public static final AssetDescriptor<Sound> HIT = new AssetDescriptor<>(Paths.HIT, Sound.class);
        public static final AssetDescriptor<Sound> GAME_OVER = new AssetDescriptor<>(Paths.GAME_OVER, Sound.class);
    }

    public final AssetManager manager = new AssetManager();

    public void loadAll() {
        manager.load(Descriptors.BACKGROUND_TILE);
        manager.load(Descriptors.ARROW);
        manager.load(Descriptors.OCTAGON);
        manager.load(Descriptors.BULLET);
        manager.load(Descriptors.HEALTH);
        manager.load(Descriptors.REGENERATION);
        manager.load(Descriptors.ENEMY_BLAST);
        manager.load(Descriptors.SHOOTER_BLAST);
        manager.load(Descriptors.MUSIC);
        manager.load(Descriptors.FIRE);
        manager.load(Descriptors.HIT);
        manager.load(Descriptors.GAME_OVER);
        manager.finishLoading();
    }

    @Override
    public Texture getBackgroundTile() {
        return manager.get(Descriptors.BACKGROUND_TILE);
    }

    @Override
    public Texture getArrow() {
        return manager.get(Descriptors.ARROW);
    }

    @Override
    public Texture getOctagon() {
        return manager.get(Descriptors.OCTAGON);
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
    public ParticleEffect getShooterBlast() {
        return new ParticleEffect(manager.get(Descriptors.SHOOTER_BLAST));
    }

    @Override
    public Music getMusic() {
        return manager.get(Descriptors.MUSIC);
    }

    @Override
    public Sound getFire() {
        return manager.get(Descriptors.FIRE);
    }

    @Override
    public Sound getHit() {
        return manager.get(Descriptors.HIT);
    }

    @Override
    public Sound getGameOver() {
        return manager.get(Descriptors.GAME_OVER);
    }

    @Override
    public Texture getHealthUpgrade() {
        return manager.get(Descriptors.HEALTH);
    }

    @Override
    public Texture getRegenerationUpgrade() {
        return manager.get(Descriptors.REGENERATION);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

}
