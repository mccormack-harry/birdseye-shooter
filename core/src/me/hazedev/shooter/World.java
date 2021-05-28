package me.hazedev.shooter;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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

import java.util.Random;

public class World extends PooledEngine implements Disposable {

    public final AssetProvider assets;
    public final int size;
    public final Random random;
    public final OrthographicCamera camera;
    public final Viewport viewport;
    public final Signaller signaller;

    public World(AssetProvider assets, int size) {
        this.assets = assets;
        this.size = size;
        random = new Random();
        camera = new OrthographicCamera();
        viewport = new FitViewport(size, size, camera);
        signaller = new Signaller();

        addSystem(new MovementSystem());
        addSystem(new SpriteSystem());
        addSystem(new CameraSystem(camera));
        addSystem(new BoundsSystem());
        addSystem(new CollisionSystem());
        addSystem(new ShooterSystem(this));
        addSystem(new EnemySystem(this));
        addSystem(new BulletSystem(this));
        addSystem(new ObstacleSystem(this));
        addSystem(new BackgroundSystem(this));
        addSystem(new HealthSystem());
        addSystem(new RenderingSystem(camera));

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
