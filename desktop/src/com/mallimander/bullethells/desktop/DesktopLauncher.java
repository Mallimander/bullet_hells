package com.mallimander.bullethells.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mallimander.bullethells.BulletHells;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		
		int res = 0;
		
		switch (res){
		case 0: config.width = 1280; config.height = 720; config.fullscreen = false;
			break;
		case 1: config.width = 1440; config.height = 900; config.fullscreen = true;
			break;
		
		
		}
		
		
		new LwjglApplication(new BulletHells(), config);
	}
}