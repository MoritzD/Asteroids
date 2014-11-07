package Ass;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;


public class DesktopStarter
{
	public static void main(String[] args)
	{
		new LwjglApplication(new AsteroidsBase(), "Asteroids", 1280, 720, false);
	}
}
