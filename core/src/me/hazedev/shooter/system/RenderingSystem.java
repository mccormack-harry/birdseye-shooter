package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectIntMap;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.component.ParticleEffectComponent;
import me.hazedev.shooter.component.PolygonSpriteComponent;
import me.hazedev.shooter.component.SpriteComponent;

import java.util.Comparator;

public class RenderingSystem extends EntitySystem implements Disposable {

    public final PolygonSpriteBatch batch;
    public final OrthographicCamera camera;

    private final Comparator<Component> layerComparator;
    private final ObjectIntMap<Component> componentLayerMap = new ObjectIntMap<>();

    public RenderingSystem(OrthographicCamera camera) {
        this.batch = new PolygonSpriteBatch();
        this.camera = camera;
        layerComparator = (c1, c2) -> (int) Math.signum(componentLayerMap.get(c1, 0) - componentLayerMap.get(c2, 0));
    }

    @Override
    public void update(float delta) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.one(SpriteComponent.class, PolygonSpriteComponent.class, ParticleEffectComponent.class).get());
        Array<Component> renderQueue = new Array<>();
        for (Entity entity: entities) {
            PolygonSpriteComponent polygonSprite = Mapper.POLYGON_SPRITE.get(entity);
            if (polygonSprite != null) {
                renderQueue.add(polygonSprite);
                componentLayerMap.put(polygonSprite, polygonSprite.layer);
            }
            SpriteComponent sprite = Mapper.SPRITE.get(entity);
            if (sprite != null) {
                renderQueue.add(sprite);
                componentLayerMap.put(sprite, sprite.layer);
            }
            ParticleEffectComponent particleEffect = Mapper.PARTICLE_EFFECT.get(entity);
            if (particleEffect != null) {
                renderQueue.add(particleEffect);
                componentLayerMap.put(particleEffect, particleEffect.layer);
            }
        }
        renderQueue.sort(layerComparator);
        componentLayerMap.clear();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Component renderable: renderQueue) {
            if (renderable instanceof SpriteComponent) {
                Sprite sprite = ((SpriteComponent) renderable).sprite;
//                batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
                sprite.draw(batch);
            } else if (renderable instanceof PolygonSpriteComponent) {
                ((PolygonSpriteComponent) renderable).sprite.draw(batch);
            } else if (renderable instanceof ParticleEffectComponent) {
                ((ParticleEffectComponent) renderable).effect.draw(batch, delta);
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}