package com.simpleglengine.example;

import com.simpleglengine.SimpleGLEngineActivity;
import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.scene.background.ColorBackground;
import com.simpleglengine.entity.scene.background.TextureBackground;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.sprite.SpriteBatch;
import com.simpleglengine.tools.BitmapTools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

public class SimpleGLEngineExampleActivity extends SimpleGLEngineActivity {
	
	private Texture mTexture;
	private Sprite mSprite;
	
	private Texture mBackground;
	
	@Override
	public void onLoadRessources() {
		Bitmap bmp = BitmapTools.loadBitmapFromRessource(R.drawable.heros, Color.rgb(255, 0, 255));		
		this.mTexture = getTextureManager().loadTextureFromBitmap(bmp);
		
		Bitmap bmp2 = BitmapTools.loadBitmapFromRessource(R.drawable.logo);
		this.mBackground = getTextureManager().loadTextureRegionFromBitmap(bmp2, 0, 0, 256, 256);
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
		//physicsHandler.setAngularVelocity(100);		
		physicsHandler.setVelocityY(25);
		mSprite.setPhysicsHandler(physicsHandler);		
		
		scene.attachChild(mSprite);
		
		Sprite mSprite2 = new Sprite(this.mTexture, 500, 200);
		
		
		PhysicsHandler physicsHandler2 = new PhysicsHandler(mSprite2);
		physicsHandler2.setVelocityX(-100);
		physicsHandler2.setAngularVelocity(-100);		
		physicsHandler2.setVelocityY(-5);
		mSprite2.setPhysicsHandler(physicsHandler2);	
		
		scene.attachChild(mSprite2);
		
		/*
		float [] x = {0,30,100};
		float [] y = {0,30,100};
		SpriteBatch sb = new SpriteBatch(mTexture, x, y);
		
		//scene.attachChild(sb);
		//scene.setBackground(new ColorBackground(0.8f, 0.5f, 0.1f, 1.0f));
		scene.setBackground(sb);
		*/
		TextureBackground tb = new TextureBackground(this.mTexture, 10, 854, 10, 480, true, true);
		scene.setBackground(tb);
		TextureBackground tb2 = new TextureBackground(this.mBackground, 0, 1024, 0, 512, true, true);
		//scene.setBackground(tb2);
		
		scene.setScale(4);
		
		return scene;
	}

	

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

}