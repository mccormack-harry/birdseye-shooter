package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.component.BoundsComponent;
import me.hazedev.shooter.component.SpriteComponent;

public class BoundsSystem extends IteratingSystem {

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public BoundsSystem() {
        super(Family.all(BoundsComponent.class, SpriteComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
// Render bounding boxes:
//        shapeRenderer.setProjectionMatrix(((World) getEngine()).camera.combined);
//        shapeRenderer.setAutoShapeType(true);
//        shapeRenderer.begin();
//        shapeRenderer.setColor(Color.RED);
//        for (Entity entity: getEntities()) {
//            BoundsComponent boundsComponent = Mapper.BOUNDS.get(entity);
//            shapeRenderer.polygon(boundsComponent.bounds.getTransformedVertices());
//        }
//        shapeRenderer.end();
    }

    @Override
    protected void processEntity(Entity entity, float delta) {
        BoundsComponent bounds = Mapper.BOUNDS.get(entity);
        SpriteComponent sprite = Mapper.SPRITE.get(entity);
        if (sprite != null) {
            bounds.bounds.setOrigin(sprite.sprite.getOriginX(), sprite.sprite.getOriginY());
            bounds.bounds.setPosition(sprite.sprite.getX(), sprite.sprite.getY());
            bounds.bounds.setRotation(sprite.sprite.getRotation());
            bounds.bounds.setScale(sprite.sprite.getScaleX(), sprite.sprite.getScaleY());
        }
    }

}
