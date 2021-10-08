package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.World;
import me.hazedev.shooter.component.BackgroundComponent;
import me.hazedev.shooter.component.SpriteComponent;
import me.hazedev.shooter.component.TransformComponent;

public class BackgroundSystem extends IteratingSystem {

    private final World world;

    public BackgroundSystem(World world) {
        super(Family.all(BackgroundComponent.class, TransformComponent.class, SpriteComponent.class).get());
        this.world = world;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getEntities().size() == 0) {
            createBackground();
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpriteComponent sprite = Mapper.SPRITE.get(entity);
        int tileWidth = sprite.sprite.getTexture().getWidth();
        int tileHeight = sprite.sprite.getTexture().getHeight();
        TransformComponent transform = Mapper.TRANSFORM.get(entity);
        transform.position.set(
                Math.max(0, Math.min(world.size, (int) (world.camera.position.x/tileWidth) * tileWidth)),
                Math.max(0, Math.min(world.size, (int) (world.camera.position.y/tileHeight) * tileHeight)));
    }

    public void createBackground() {
        Entity entity = getEngine().createEntity();


        Texture texture = world.assets.getBackgroundTile();
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        Sprite sprite = new Sprite(texture, world.size + texture.getWidth() * 2, world.size + texture.getHeight() * 2);

        entity.add(new TransformComponent(new Vector2()));
        entity.add(new SpriteComponent(-1, sprite));
        entity.add(new BackgroundComponent());

        world.addEntity(entity);
    }

}
