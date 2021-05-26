package me.hazedev.shooter;

import com.badlogic.ashley.core.ComponentMapper;
import me.hazedev.shooter.component.*;

public class Mapper {
        public static final ComponentMapper<BackgroundComponent> BACKGROUND = ComponentMapper.getFor(BackgroundComponent.class);
        public static final ComponentMapper<BoundsComponent> BOUNDS = ComponentMapper.getFor(BoundsComponent.class);
        public static final ComponentMapper<BulletComponent> BULLET = ComponentMapper.getFor(BulletComponent.class);
        public static final ComponentMapper<CameraTargetComponent> CAMERA_TARGET = ComponentMapper.getFor(CameraTargetComponent.class);
        public static final ComponentMapper<EnemyComponent> ENEMY = ComponentMapper.getFor(EnemyComponent.class);
        public static final ComponentMapper<HealthComponent> HEALTH = ComponentMapper.getFor(HealthComponent.class);
        public static final ComponentMapper<InputComponent> INPUT = ComponentMapper.getFor(InputComponent.class);
        public static final ComponentMapper<MovementComponent> MOVEMENT = ComponentMapper.getFor(MovementComponent.class);
        public static final ComponentMapper<ParticleEffectComponent> PARTICLE_EFFECT = ComponentMapper.getFor(ParticleEffectComponent.class);
        public static final ComponentMapper<PolygonSpriteComponent> POLYGON_SPRITE = ComponentMapper.getFor(PolygonSpriteComponent.class);
        public static final ComponentMapper<ShooterComponent> SHOOTER = ComponentMapper.getFor(ShooterComponent.class);
        public static final ComponentMapper<SpriteComponent> SPRITE = ComponentMapper.getFor(SpriteComponent.class);
        public static final ComponentMapper<TransformComponent> TRANSFORM = ComponentMapper.getFor(TransformComponent.class);
}
