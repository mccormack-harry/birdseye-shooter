package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.World;
import me.hazedev.shooter.component.BoundsComponent;
import me.hazedev.shooter.component.PolygonSpriteComponent;
import me.hazedev.shooter.component.SpriteComponent;

public class BoundsSystem extends IteratingSystem {

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public BoundsSystem() {
        super(Family.all(BoundsComponent.class).one(SpriteComponent.class, PolygonSpriteComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        shapeRenderer.setProjectionMatrix(((World) getEngine()).camera.combined);
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.RED);
        for (Entity entity: getEntities()) {
            BoundsComponent boundsComponent = Mapper.BOUNDS.get(entity);
            shapeRenderer.polygon(boundsComponent.bounds.getTransformedVertices());
        }
        shapeRenderer.end();
    }

    @Override
    protected void processEntity(Entity entity, float delta) {
        BoundsComponent bounds = Mapper.BOUNDS.get(entity);

        PolygonSpriteComponent polygonSprite = Mapper.POLYGON_SPRITE.get(entity);
        if (polygonSprite != null) {
            bounds.bounds.setVertices(polygonSprite.sprite.getRegion().getVertices());
            bounds.bounds.setOrigin(polygonSprite.sprite.getOriginX(), polygonSprite.sprite.getScaleY());
            bounds.bounds.setPosition(polygonSprite.sprite.getX(), polygonSprite.sprite.getY());
            bounds.bounds.setRotation(polygonSprite.sprite.getRotation());
            bounds.bounds.setScale(polygonSprite.sprite.getScaleX(), polygonSprite.sprite.getScaleY());
        }

        SpriteComponent sprite = Mapper.SPRITE.get(entity);
        if (sprite != null) {
            bounds.bounds.setPosition(sprite.sprite.getX(), sprite.sprite.getY());
            bounds.bounds.setRotation(sprite.sprite.getRotation());
            float x1 = 0;
            float x2 = x1 + sprite.sprite.getWidth();
            float y1 = 0;
            float y2 = y1 + sprite.sprite.getHeight();
            float[] vertices = new float[]{x1, y1, x1, y2, x2, y2, x2, y1};
            bounds.bounds.setVertices(vertices);
            bounds.bounds.setOrigin(sprite.sprite.getOriginX(), sprite.sprite.getOriginY());
            bounds.bounds.setScale(sprite.sprite.getScaleX(), sprite.sprite.getScaleY());
        }
    }

}
