package com.simpleglengine.example;


import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.widget.Toast;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.scene.background.ColorBackground;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.ScreenTools;

public class OnTouchEventExample extends GLActivity {
	private Texture mTexture;
	private TextureRegion mSpriteTextureRegion;
	
	public OnTouchEventExample() {
		// Config
		this.name = "\"onTouch\"";
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
	    Sprite sprite = new Sprite(mSpriteTextureRegion, 0, 0) {
	    	public void onManagedUpdate(float alpha) {
	    		super.onManagedUpdate(alpha);
	    		
	    		if(mX+getScaledWidth() > ScreenTools.getWidth() || mX < 0)
	    			mPhysicsHandler.setVelocityX(-mPhysicsHandler.getVelocityX());
	    		if(mY+getScaledHeight() > ScreenTools.getHeight() || mY < 0)
	    			mPhysicsHandler.setVelocityY(-mPhysicsHandler.getVelocityY());
	    		
	    	}
	    	public boolean onTouch(MotionEvent event) {
	    		if(event.getAction() == MotionEvent.ACTION_DOWN) {
	    			Toast.makeText(OnTouchEventExample.this, "Good Game", Toast.LENGTH_SHORT).show();
	    			return true;
	    		}
	    		return false;
	    	}
	    };
	    
	    // Create the physic handler
	    PhysicsHandler physicsHandler = new PhysicsHandler(sprite);
	    physicsHandler.setVelocity(200.0f, 200.0f);
	    physicsHandler.setAcceleration(270.0f, 280.0f);
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
