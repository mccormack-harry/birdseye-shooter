package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;

public class BoundsComponent implements Component {

    public Polygon bounds;

    public BoundsComponent(Polygon bounds) {
        this.bounds = bounds;
    }

    public BoundsComponent(float width, float height) {
        float[] vertices = new float[]{
                0, 0,
                0, height,
                width, height,
                width, 0};
        this.bounds = new Polygon(vertices);
    }

}
