package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {

    public Vector2 position = new Vector2();
    public Vector2 scale = new Vector2(1, 1);
    public float rotation = 0;

    public TransformComponent() {}

    public TransformComponent(Vector2 position) {
        this.position = position;
    }

    public TransformComponent(Vector2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }
    public TransformComponent(Vector2 position, Vector2 scale) {
        this.position = position;
        this.scale = scale;
    }

    public TransformComponent(Vector2 position, Vector2 scale, float rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

}
