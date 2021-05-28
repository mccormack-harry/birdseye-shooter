package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.World;
import me.hazedev.shooter.component.BoundsComponent;
import me.hazedev.shooter.event.CollisionEvent;

public class CollisionSystem extends EntitySystem {

    private final World world;
    public Family boundsFamily = Family.all(BoundsComponent.class).get();

    public CollisionSystem(World world) {
        this.world = world;
    }

    @Override
    public void update(float delta) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(boundsFamily);
        Array<Entity> others = new Array<>(entities.toArray(Entity.class));
        for (Entity entity: entities) {
            others.removeIndex(0);
            for (Entity other: others) {
                BoundsComponent bounds = Mapper.BOUNDS.get(entity);
                BoundsComponent otherBounds = Mapper.BOUNDS.get(other);
                if (Intersector.overlapConvexPolygons(bounds.bounds, otherBounds.bounds)) {
                    world.signaller.collisionSignal.dispatch(new CollisionEvent(entity, other));
                }
            }
        }
    }

}
