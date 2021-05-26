package me.hazedev.shooter.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Music;

public class MusicComponent implements Component {

    public MusicComponent(Music music) {
        this.music = music;
    }

    public Music music;

}
