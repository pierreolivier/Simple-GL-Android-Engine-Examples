package com.simpleglengine.example;


import android.graphics.Bitmap;

import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.scene.background.ColorBackground;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.ScreenTools;

public class SpriteExample extends GLActivity {
	private Texture mTexture;
	private TextureRegion mSpriteTextureRegion;
	
	public SpriteExample() {
		// Config
		this.name = "Draw a sprite";
	}
	
	@Override
	public void onLoadRessources() {		
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
	    Scene scene = new Scene();

	    // Create the sprite
	    Sprite sprite = new Sprite(mSpriteTextureRegion, ScreenTools.getWidth()/2-127, ScreenTools.getHeight()/2-77);	    

	    // Attach your sprite to the scene
	    scene.setBackground(new ColorBackground(1, 1, 1, 1));
	    scene.attachChild(sprite);
	    sprite.setScale(2.0f);

	    // Return the created scene to the engine
	    return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
		
}
