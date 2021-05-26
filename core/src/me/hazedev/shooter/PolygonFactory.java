package me.hazedev.shooter;

import com.badlogic.gdx.math.Polygon;

public class PolygonFactory {

    public static Polygon getHexagon() {
        return new Polygon(new float[]{6,0, 0,10, 6,20, 18,20, 24,10, 18,0});
    }

    public static Polygon getArrow() {
        return new Polygon(new float[]{0,0, 0,5, 3,8, 0,11, 0,16, 3,16, 16,8, 3,0});
    }

}
