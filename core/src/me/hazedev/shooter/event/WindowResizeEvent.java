package me.hazedev.shooter.event;

public class WindowResizeEvent {

    public final int width;
    public final int height;

    public WindowResizeEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
