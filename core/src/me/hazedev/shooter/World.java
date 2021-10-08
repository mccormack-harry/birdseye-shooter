package me.hazedev.shooter;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.hazedev.shooter.event.listener.WindowResizeListener;
import me.hazedev.shooter.system.BackgroundSystem;
import me.hazedev.shooter.system.BoundsSystem;
import me.hazedev.shooter.system.BulletSystem;
import me.hazedev.shooter.system.CameraSystem;
import me.hazedev.shooter.system.CollisionSystem;
import me.hazedev.shooter.system.EnemySystem;
import me.hazedev.shooter.system.HealthSystem;
import me.hazedev.shooter.system.MovementSystem;
import me.hazedev.shooter.system.ObstacleSystem;
import me.hazedev.shooter.system.RenderingSystem;
import me.hazedev.shooter.system.ShooterSystem;
import me.hazedev.shooter.system.SpriteSystem;
import me.hazedev.shooter.system.UISystem;
import me.hazedev.shooter.system.UpgradeSystem;

import java.util.Random;

public class World extends PooledEngine implements Disposable {

    public final AssetProvider assets;
    public final int size;
    public final Random random;
    public final Signaller signaller;
    public final Camera camera;
    public final Viewport viewport;

    public World(AssetProvider assets, int size) {
        this.assets = assets;
        this.size = size;
        random = new Random();
        signaller = new Signaller();
        camera = new OrthographicCamera();
        viewport = new FillViewport(size, size, camera);
        signaller.windowResizeSignal.add(new WindowResizeListener(viewport));

        addSystem(new MovementSystem());
        addSystem(new SpriteSystem());
        addSystem(new CameraSystem(this));
        addSystem(new BoundsSystem());
        addSystem(new CollisionSystem(this));
        addSystem(new ShooterSystem(this));
        addSystem(new EnemySystem(this));
        addSystem(new BulletSystem(this));
        addSystem(new ObstacleSystem(this));
        addSystem(new UpgradeSystem(this));
        addSystem(new BackgroundSystem(this));
        addSystem(new HealthSystem());
        addSystem(new RenderingSystem(this));
        addSystem(new UISystem(this));
        Music music = assets.getMusic();
        music.setLooping(true);
        music.play();
    }

    @Override
    public void dispose() {
        for (EntitySystem system: getSystems()) {
            if (system instanceof Disposable) {
                ((Disposable) system).dispose();
            }
        }
    }

}
