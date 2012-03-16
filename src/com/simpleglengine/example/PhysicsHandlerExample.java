package com.simpleglengine.example;


import android.graphics.Bitmap;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.scene.background.ColorBackground;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.ScreenTools;

public class PhysicsHandlerExample extends GLActivity {
	private Texture mTexture;
	private TextureRegion mSpriteTextureRegion;
	
	public PhysicsHandlerExample() {
		// Config
		this.name = "Simple physic handler";
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
	    Sprite sprite = new Sprite(mSpriteTextureRegion, 0, 0);
	    
	    // Create the physic handler
	    PhysicsHandler physicsHandler = new PhysicsHandler(sprite);
	    physicsHandler.setVelocity(60.0f, 40.0f);
	    sprite.setPhysicsHandler(physicsHandler);

	    // Attach your sprite to the scene
	    scene.setBackground(new ColorBackground(1.0f, 1.0f, 1.0f, 1.0f));
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
