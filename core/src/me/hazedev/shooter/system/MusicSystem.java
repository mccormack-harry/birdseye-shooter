package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.hazedev.shooter.World;
import me.hazedev.shooter.component.MusicComponent;

public class MusicSystem extends IteratingSystem {

    public MusicSystem() {
        super(Family.all(MusicComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (getEntities().size() == 0) {
            Entity entity = getEngine().createEntity();
            entity.add(new MusicComponent(((World) getEngine()).assets.getMusic()));
            getEngine().addEntity(entity);
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

}
