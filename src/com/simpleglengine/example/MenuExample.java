package com.simpleglengine.example;


import java.io.IOException;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Font;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.scene.background.ColorBackground;
import com.simpleglengine.entity.scene.menu.Menu;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.text.Text;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.ScreenTools;

public class MenuExample extends GLActivity {
	private Font mVerdana;

	private Texture mTexture;
	private TextureRegion mSpriteTextureRegion;

	public MenuExample() {
		// Config
		this.name = "Make a menu";
	}

	@Override
	public void onLoadRessources() {		
		// Create the font
		try {
			mVerdana = getFontManager().createFont("Verdana.bff");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Load bitmap
		Bitmap bitmap = BitmapTools.loadBitmapFromRessource(R.drawable.texture);

		// Create the texture
		this.mTexture = getTextureManager().loadTextureFromBitmap(bitmap);

		// Create the texture region
		this.mSpriteTextureRegion = getTextureManager().createTextureRegion(mTexture, 640, 0, 127, 77);
	}

	@Override
	public Scene onLoadScene() {
		// Create your scene
		final Scene scene = new Scene();

		// Create your menu
		final Menu menu = new Menu();
		menu.setAutoPause(true);
		

		// Create your text visible only on your menu
		Text text = new Text(mVerdana, "PAUSE", 100, 100) {
			public boolean onTouch(MotionEvent event) {
				menu.setBackground(new ColorBackground(1.0f, 1.0f, 1.0f, 1.0f));
				scene.setBackground(new ColorBackground(1.0f, 1.0f, 1.0f, 1.0f));
				menu.hide();
				return true;
			}
		};
		mVerdana.setSize(10.0f);
		mVerdana.setColor(1.0f, 1.0f, 1.0f);
		menu.attachChild(text);

		// Create the sprite
		Sprite sprite = new Sprite(mSpriteTextureRegion, 0, 0) {
			public boolean onTouch(MotionEvent event) {
				menu.setBackground(new ColorBackground(0.0f, 0.0f, 0.0f, 0.8f));
				menu.show();
				return true;
			}
		};

		// Create the physic handler
		PhysicsHandler physicsHandler = new PhysicsHandler(sprite);
		physicsHandler.setVelocity(60.0f, 40.0f);
		sprite.setPhysicsHandler(physicsHandler);

		// Attach your text to the scene
		scene.setBackground(new ColorBackground(1.0f, 1.0f, 1.0f, 1.0f));
		scene.setMenu(menu);
		scene.attachChild(sprite);

		// Return the created scene to the engine
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

}
