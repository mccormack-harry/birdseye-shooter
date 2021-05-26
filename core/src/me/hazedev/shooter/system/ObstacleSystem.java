package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.World;
import me.hazedev.shooter.component.BoundsComponent;
import me.hazedev.shooter.component.ObstacleComponent;
import me.hazedev.shooter.component.PolygonSpriteComponent;
import me.hazedev.shooter.component.ShooterComponent;
import me.hazedev.shooter.component.TransformComponent;

public class ObstacleSystem extends EntitySystem {

    World world;
    Family family = Family.all(ObstacleComponent.class).get();

    public ObstacleSystem(World world) {
        this.world = world;
    }

    public void spawnObstacle(Vector2 pos) {
        Entity entity = world.createEntity();

        Polygon polygon = new Polygon(new float[]{6,0, 0,10, 6,20, 18,20, 24,10, 18,0});

        PolygonSprite sprite = new PolygonSprite(new PolygonRegion(new TextureRegion(world.assets.getBlankTexture()), polygon.getVertices(), new EarClippingTriangulator().computeTriangles(polygon.getVertices()).toArray()));
        sprite.setColor(Color.PURPLE);

        entity.add(new ObstacleComponent());
        entity.add(new PolygonSpriteComponent(1, sprite));
        entity.add(new TransformComponent(pos, new Vector2(sprite.getWidth()/2, sprite.getHeight()/2)));
        entity.add(new BoundsComponent());

        world.addEntity(entity);
    }

    @Override
    public void update(float delta) {
        ImmutableArray<Entity> shooters = getEngine().getEntitiesFor(Family.all(TransformComponent.class, ShooterComponent.class).get());
        for (Entity shooter : shooters) {
            TransformComponent shooterTransform = Mapper.TRANSFORM.get(shooter);
            if (getEngine().getEntitiesFor(family).size() < 5) {
                Vector2 offset = new Vector2((world.random.nextFloat() * 0.6f + 0.4f) * world.size / 2f, (world.random.nextFloat() * 0.6f + 0.4f) * world.size / 2f).scl(world.random.nextBoolean() ? 1 : -1, world.random.nextBoolean() ? 1 : -1);
                spawnObstacle(offset.add(shooterTransform.position));
            }
        }
    }
}
