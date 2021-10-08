package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.UpgradeType;
import me.hazedev.shooter.World;
import me.hazedev.shooter.component.BoundsComponent;
import me.hazedev.shooter.component.ShooterComponent;
import me.hazedev.shooter.component.SpriteComponent;
import me.hazedev.shooter.component.TransformComponent;
import me.hazedev.shooter.component.UpgradeComponent;
import me.hazedev.shooter.event.listener.CollisionListener;

import java.util.Optional;

public class UpgradeSystem extends IteratingSystem {

    private final World world;

    public UpgradeSystem(World world) {
        super(Family.all(UpgradeComponent.class).get());
        this.world = world;
        world.signaller.enemyDeathSignal.add(new EnemyDeathListener());
        world.signaller.collisionSignal.add(new UpgradePickupListener());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

    public void spawnUpgrade(Vector2 pos) {
        Entity entity = world.createEntity();

        UpgradeType[] upgradeTypes = UpgradeType.values();
        UpgradeType upgradeType = upgradeTypes[world.random.nextInt(upgradeTypes.length)];

        Texture texture = world.assets.getOctagon();

//        switch (upgradeType) {
//            case HEALTH:
//                texture = world.assets.getHealthUpgrade();
//                break;
//            case REGENERATION:
//                texture = world.assets.getRegenerationUpgrade();
//                break;
//            default:
//                texture = world.assets.getOctagon();
//                break;
//        }

        Sprite sprite = new Sprite(texture);
        sprite.setColor(Color.CYAN);

        entity.add(new UpgradeComponent(upgradeType, 1));
        entity.add(new TransformComponent(pos, new Vector2(0.5f, 0.5f), 45 * world.random.nextFloat()));
        entity.add(new SpriteComponent(2, sprite));
        entity.add(new BoundsComponent(sprite.getWidth(), sprite.getHeight()));

        world.addEntity(entity);
    }

    private class UpgradePickupListener extends CollisionListener {

        public UpgradePickupListener() {
            super(Family.all(UpgradeComponent.class).get(), Family.all(ShooterComponent.class).get());
        }

        @Override
        public void onCollide(Entity upgradeEntity, Entity shooterEntity) {
            UpgradeComponent upgrade = Mapper.UPGRADE.get(upgradeEntity);
            ShooterComponent shooter = Mapper.SHOOTER.get(shooterEntity);
            switch (upgrade.type) {
                case HEALTH:
                    Optional.ofNullable(Mapper.HEALTH.get(shooterEntity)).ifPresent(health -> health.maxHealth += 1);
                    break;
                case REGENERATION:
                    Optional.ofNullable(Mapper.HEALTH.get(shooterEntity)).ifPresent(health -> health.health += 1);
                    break;
                case DAMAGE:
                    shooter.damage += upgrade.amount;
                    break;
                case FIRE_RATE:
                    shooter.fireRate += upgrade.amount;
                    break;
                case SPEED:
                    Optional.ofNullable(Mapper.MOVEMENT.get(shooterEntity)).ifPresent(movement -> movement.maxVelocity += 16);
                    break;
                case PENETRATION:
                    shooter.penetration += upgrade.amount;
                    break;
            }
            world.removeEntity(upgradeEntity);
        }

    }

    private class EnemyDeathListener implements Listener<Entity> {

        @Override
        public void receive(Signal<Entity> signal, Entity enemy) {
            TransformComponent transform = Mapper.TRANSFORM.get(enemy);
            if (transform != null) {
                if (world.random.nextFloat() < 0.05f) {
                    spawnUpgrade(transform.position.cpy());
                }
            }
        }

    }

}
