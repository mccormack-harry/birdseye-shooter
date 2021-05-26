package me.hazedev.shooter.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import me.hazedev.shooter.Assets;
import me.hazedev.shooter.BirdseyeShooter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Birdseye Shooter");
		config.setWindowIcon(Assets.Paths.SHOOTER);
		new Lwjgl3Application(new BirdseyeShooter(), config);
	}
}
