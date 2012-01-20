package com.simpleglengine.example;

import com.simpleglengine.SimpleGLEngineActivity;
import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.tools.BitmapTools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

public class SimpleGLEngineExampleActivity extends SimpleGLEngineActivity {
	
	private Texture mTexture;
	private Sprite mSprite;
	
	
	@Override
	public void onLoadRessources() {
		Bitmap bmp = BitmapTools.loadBitmapFromRessource(R.drawable.heros, Color.rgb(255, 0, 255));		
		this.mTexture = getTextureManager().loadTextureFromBitmap(bmp);
		
	}
	
	@Override
	public Scene onLoadScene() {
		Scene scene = new Scene();
		
		this.mSprite = new Sprite(this.mTexture, 0, 0);
		mSprite.setScale(2f);		
		mSprite.setRotationCenter(32, 32);
		mSprite.translate(10, 10);
		
		PhysicsHandler physicsHandler = new PhysicsHandler(mSprite);
		physicsHandler.setVelocityX(100);
		physicsHandler.setAngularVelocity(1000);
		physicsHandler.setVelocityY(25);
		mSprite.setPhysicsHandler(physicsHandler);
		
		scene.attachChild(mSprite);		
		
		return scene;
	}

	

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

}