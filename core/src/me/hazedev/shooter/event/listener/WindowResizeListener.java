package me.hazedev.shooter.event.listener;

import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.hazedev.shooter.event.WindowResizeEvent;

public class WindowResizeListener implements Listener<WindowResizeEvent> {

    public final Viewport viewport;

    public WindowResizeListener(Viewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public void receive(Signal<WindowResizeEvent> signal, WindowResizeEvent event) {
        viewport.update(event.width, event.height);
    }

}
