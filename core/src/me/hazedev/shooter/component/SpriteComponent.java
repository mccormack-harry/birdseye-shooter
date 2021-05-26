package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component {

    public Sprite sprite;
    public int layer;

    public SpriteComponent(Sprite sprite) {
        this.sprite = sprite;
    }

    public SpriteComponent(int layer, Sprite sprite) {
        this.sprite = sprite;
        this.layer = layer;
    }

}
