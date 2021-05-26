package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.component.BoundsComponent;
import me.hazedev.shooter.component.BulletComponent;
import me.hazedev.shooter.component.EnemyComponent;
import me.hazedev.shooter.component.HealthComponent;
import me.hazedev.shooter.component.ObstacleComponent;
import me.hazedev.shooter.component.ShooterComponent;

public class CollisionSystem extends EntitySystem {

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> enemies = getEngine().getEntitiesFor(Family.all(EnemyComponent.class, BoundsComponent.class, HealthComponent.class).get());
        ImmutableArray<Entity> bullets = getEngine().getEntitiesFor(Family.all(BulletComponent.class, BoundsComponent.class, HealthComponent.class).get());
        ImmutableArray<Entity> shooters = getEngine().getEntitiesFor(Family.all(ShooterComponent.class, BoundsComponent.class, HealthComponent.class).get());
        ImmutableArray<Entity> obstacles = getEngine().getEntitiesFor(Family.all(ObstacleComponent.class, BoundsComponent.class).get());

        for (Entity enemyEntity: enemies) {
            BoundsComponent enemyBounds = Mapper.BOUNDS.get(enemyEntity);
            HealthComponent enemyHealth = Mapper.HEALTH.get(enemyEntity);

            if (enemyHealth.health > 0) {

                for (Entity bulletEntity : bullets) {
                    BoundsComponent bulletBounds = Mapper.BOUNDS.get(bulletEntity);
                    HealthComponent bulletHealth = Mapper.HEALTH.get(bulletEntity);

                    if (bulletHealth.health <= 0) continue;

                    if (Intersector.overlapConvexPolygons(enemyBounds.bounds, bulletBounds.bounds)) {
                        enemyHealth.health -= 1;
                        bulletHealth.health -= 1;
                    }

                }

            }

            if (enemyHealth.health > 0) {
                for (Entity shooterEntity: shooters) {
                    BoundsComponent shooterBounds = Mapper.BOUNDS.get(shooterEntity);
                    HealthComponent shooterHealth = Mapper.HEALTH.get(shooterEntity);

                    if (shooterHealth.health <= 0) continue;

                    if (Intersector.overlapConvexPolygons(enemyBounds.bounds, shooterBounds.bounds)) {
                        shooterHealth.health -= 1;
                        enemyHealth.health = 0;
                    }

                }
            }

            if (enemyHealth.health > 0) {
                for (Entity obstacleEntity: obstacles) {
                    BoundsComponent obstacleBounds = Mapper.BOUNDS.get(obstacleEntity);

                    if (Intersector.overlapConvexPolygons(enemyBounds.bounds, obstacleBounds.bounds)) {
                        enemyHealth.health = 0;
                    }
                }
            }

        }

        for (Entity shooterEntity: shooters) {
            BoundsComponent shooterBounds = Mapper.BOUNDS.get(shooterEntity);
            HealthComponent shooterHealth = Mapper.HEALTH.get(shooterEntity);

            if (shooterHealth.health > 0) {
                for (Entity obstacleEntity: obstacles) {
                    BoundsComponent obstacleBounds = Mapper.BOUNDS.get(obstacleEntity);
                    if (Intersector.overlapConvexPolygons(shooterBounds.bounds, obstacleBounds.bounds)) {
                        shooterHealth.health = 0;
                    }
                }
            }

        }

    }

}
