package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;

public class PolygonSpriteComponent implements Component {

    public int layer; // render layer
    public PolygonSprite sprite;

    public PolygonSpriteComponent(PolygonSprite sprite) {
        this.sprite = sprite;
    }

    public PolygonSpriteComponent(int layer, PolygonSprite sprite) {
        this.layer = layer;
        this.sprite = sprite;
    }
}
