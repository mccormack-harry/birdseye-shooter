package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.PolygonFactory;
import me.hazedev.shooter.World;
import me.hazedev.shooter.component.BoundsComponent;
import me.hazedev.shooter.component.BulletComponent;
import me.hazedev.shooter.component.CameraTargetComponent;
import me.hazedev.shooter.component.EnemyComponent;
import me.hazedev.shooter.component.HealthComponent;
import me.hazedev.shooter.component.InputComponent;
import me.hazedev.shooter.component.MovementComponent;
import me.hazedev.shooter.component.ParticleEffectComponent;
import me.hazedev.shooter.component.ShooterComponent;
import me.hazedev.shooter.component.SpriteComponent;
import me.hazedev.shooter.component.TransformComponent;
import me.hazedev.shooter.event.listener.CollisionListener;

public class ShooterSystem extends IteratingSystem {

    public final World world;

    public ShooterSystem(World world) {
        super(Family.all(ShooterComponent.class).get());
        this.world = world;
        world.signaller.collisionSignal.add(new ShooterCollisionListener());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getEntities().size() == 0) {
            spawnPlayer();
        }
    }

    @Override
    protected void processEntity(Entity entity, float delta) {
        ShooterComponent shooter = Mapper.SHOOTER.get(entity);
        HealthComponent health = Mapper.HEALTH.get(entity);
        if (health.health > 0) {

            shooter.fireCooldown -= delta;
            TransformComponent transform = Mapper.TRANSFORM.get(entity);
            MovementComponent movement = Mapper.MOVEMENT.get(entity);

            if (Mapper.INPUT.has(entity)) {
                // https://stackoverflow.com/questions/20315566/libgdx-vector2-shooting-ball-at-an-angle
                Vector3 mousePos = world.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                transform.rotation = MathUtils.radDeg * MathUtils.atan2(mousePos.y - transform.position.y, mousePos.x - transform.position.x);

                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    if (shooter.fireCooldown <= 0) {
                        spawnBullet(entity);
                        shooter.fireCooldown = 1f / shooter.fireRate;
                    }
                }
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    movement.acceleration.y = 1;
                } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    movement.acceleration.y = -1;
                } else {
                    movement.acceleration.y = 0;
                    movement.velocity.y *= 1 - delta;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    movement.acceleration.x = 1;
                } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    movement.acceleration.x = -1;
                } else {
                    movement.acceleration.x = 0;
                    movement.velocity.x *= 1 - delta;
                }
                movement.acceleration.scl(256).clamp(0, 256);
                movement.maxVelocity = 90;
            }
        } else { // DEAD
            entity.remove(SpriteComponent.class);
            entity.remove(InputComponent.class);
            entity.remove(CameraTargetComponent.class);
            entity.remove(MovementComponent.class);
            ParticleEffectComponent particleEffectComponent = Mapper.PARTICLE_EFFECT.get(entity);
            if (particleEffectComponent == null) {
                world.assets.getGameOver().play();
                ParticleEffect effect = world.assets.getShooterBlast();
                Vector2 pos = Mapper.TRANSFORM.get(entity).position;
                effect.setPosition(pos.x, pos.y);
                effect.start();
                entity.add(new ParticleEffectComponent(0, effect));
            } else {
                if (particleEffectComponent.effect.isComplete()) {
                    Gdx.app.exit();
                }
            }
        }

    }

    private static class ShooterCollisionListener extends CollisionListener {

        public ShooterCollisionListener() {
            super(Family.all(ShooterComponent.class).get(), Family.all(EnemyComponent.class).get());
        }

        @Override
        public void onCollide(Entity shooterEntity, Entity enemyEntity) {
            HealthComponent enemyHealth = Mapper.HEALTH.get(enemyEntity);
            HealthComponent shooterHealth = Mapper.HEALTH.get(shooterEntity);
            if (enemyHealth.health > 0 && shooterHealth.health > 0) {
                shooterHealth.health -= 1;
                enemyHealth.health = 0;
            }
        }

    }

    public void spawnPlayer() {
        Entity entity = world.createEntity();

        Sprite sprite = new Sprite(world.assets.getArrow());
        sprite.setColor(Color.GREEN);

        entity.add(new ShooterComponent());
        entity.add(new SpriteComponent(2, sprite));
        entity.add(new TransformComponent(new Vector2(world.size / 2f, world.size / 2f)));
        entity.add(new MovementComponent());
        entity.add(new InputComponent());
        entity.add(new CameraTargetComponent());
        entity.add(new BoundsComponent(PolygonFactory.getArrow()));
        entity.add(new HealthComponent(3));

        world.addEntity(entity);
    }

    public void spawnBullet(Entity shooterEntity) {
        Entity entity = getEngine().createEntity();

        Sprite sprite = new Sprite(world.assets.getBullet());

        TransformComponent shooterTransform = Mapper.TRANSFORM.get(shooterEntity);
        ShooterComponent shooter = Mapper.SHOOTER.get(shooterEntity);

        BulletComponent bullet = new BulletComponent(shooterEntity);
        bullet.damage = shooter.damage;

        entity.add(bullet);
        entity.add(new TransformComponent(shooterTransform.position.cpy(), new Vector2(shooter.damage, shooter.damage), shooterTransform.rotation));
        entity.add(new MovementComponent(new Vector2(300, 0).rotateDeg(shooterTransform.rotation)));
        entity.add(new SpriteComponent(3, sprite));
        entity.add(new BoundsComponent(sprite.getWidth(), sprite.getHeight()));
        entity.add(new HealthComponent(shooter.penetration));

        world.addEntity(entity);

        world.assets.getFire().play(0.5f);
    }

}
