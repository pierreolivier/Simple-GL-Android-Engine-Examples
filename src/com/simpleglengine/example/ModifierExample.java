package com.simpleglengine.example;


import android.graphics.Bitmap;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.handler.modifier.DoubleValueEntityModifier;
import com.simpleglengine.engine.handler.modifier.ease.EaseBounceOut;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.scene.background.ColorBackground;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.ScreenTools;

public class ModifierExample extends GLActivity {
	private Texture mTexture;
	private TextureRegion mSpriteTextureRegion;
	
	public ModifierExample() {
		// Config
		this.name = "Apply modifier (color, motion)";
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

	    // Attach your sprite to the scene
	    scene.setBackground(new ColorBackground(1.0f, 1.0f, 1.0f, 1.0f));
	    scene.attachChild(sprite);
	    sprite.setScale(2.0f);
	    
	    // Fonction associated to our entity
	    EaseBounceOut ease = new EaseBounceOut();
	    // Add our modifier
	    sprite.addEntityModifier(new DoubleValueEntityModifier(20, 0, 0, 650, 300, ease));

	    // Return the created scene to the engine
	    return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
		
}
