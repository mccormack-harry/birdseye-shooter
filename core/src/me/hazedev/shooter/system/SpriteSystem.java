package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.component.SpriteComponent;
import me.hazedev.shooter.component.TransformComponent;

public class SpriteSystem extends IteratingSystem {

    public SpriteSystem() {
        super(Family.all(TransformComponent.class).one(SpriteComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float delta) {
        TransformComponent transform = Mapper.TRANSFORM.get(entity);

        SpriteComponent sprite = Mapper.SPRITE.get(entity);
        if (sprite != null) {
            sprite.sprite.setOriginBasedPosition(transform.position.x, transform.position.y);
            sprite.sprite.setScale(transform.scale.x, transform.scale.y);
            sprite.sprite.setRotation(transform.rotation);
        }



    }

}
