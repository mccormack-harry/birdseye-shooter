package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {

    public TransformComponent(Vector2 position) {
        this.position = position;
    }

    public TransformComponent(Vector2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public TransformComponent(Vector2 position, Vector2 origin) {
        this.position = position;
//        this.origin = origin;
    }

    public TransformComponent(Vector2 position, Vector2 origin, float rotation) {
        this.position = position;
//        this.origin = origin;
        this.rotation = rotation;
    }

    public TransformComponent(Vector2 position, Vector2 origin, Vector2 scale, float rotation) {
        this.position = position;
//        this.origin = origin;
        this.scale = scale;
        this.rotation = rotation;
    }

    public Vector2 position = new Vector2();
//    public Vector2 origin = new Vector2();
    public Vector2 scale = new Vector2(1, 1);
    public float rotation = 0;

}
