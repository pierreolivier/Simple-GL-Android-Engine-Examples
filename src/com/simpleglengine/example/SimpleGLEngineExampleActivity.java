package com.simpleglengine.example;

import com.simpleglengine.SimpleGLEngineActivity;
import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.scene.background.AutoParallaxBackground;
import com.simpleglengine.entity.scene.background.ColorBackground;
import com.simpleglengine.entity.scene.background.TextureBackground;
import com.simpleglengine.entity.sprite.AnimatedSprite;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.sprite.SpriteBatch;
import com.simpleglengine.tools.BitmapTools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.opengl.Matrix;
import android.os.Bundle;

public class SimpleGLEngineExampleActivity extends SimpleGLEngineActivity {
	
	private Texture mTexture;
	private Sprite mSprite;
	
	private Texture mBackgroundColor;
	private Texture mBackgroundAnim;
	
	private Texture [] mBirds;
	
	private Texture mBalance;
	
	@Override
	public void onLoadRessources() {
		Bitmap bmp = BitmapTools.loadBitmapFromRessource(R.drawable.heros, Color.rgb(255, 0, 255));		
		this.mTexture = getTextureManager().loadTextureFromBitmap(bmp);
		
		Bitmap backColor = BitmapTools.loadBitmapFromRessource(R.drawable.back_color);
		this.mBackgroundColor = getTextureManager().loadTextureRegionFromBitmap(backColor, 0, 0, 1024, 512);
		
		Bitmap backAnim = BitmapTools.loadBitmapFromRessource(R.drawable.back);
		this.mBackgroundAnim = getTextureManager().loadTextureRegionFromBitmap(backAnim, 0, 0, 1024, 256);
		
		mBirds = new Texture[2];
		
		Bitmap b1 = BitmapTools.loadBitmapFromRessource(R.drawable.birds_anim1);
		Bitmap b2 = BitmapTools.loadBitmapFromRessource(R.drawable.birds_anim2);
		mBirds[0] = getTextureManager().loadTextureFromBitmap(b1);
		mBirds[1] = getTextureManager().loadTextureFromBitmap(b2);
		
		Bitmap balance = BitmapTools.loadBitmapFromRessource(R.drawable.balance);		
		this.mBalance = getTextureManager().loadTextureFromBitmap(balance);
	}
	
	@Override
	public Scene onLoadScene() {
		Scene scene = new Scene();
		
		TextureBackground tb2 = new TextureBackground(this.mBackgroundColor, 0, 0);		
		AutoParallaxBackground apb = new AutoParallaxBackground(this.mBackgroundAnim, 180, -200);
		apb.setTextureBackground(tb2);
		
		
		this.mSprite = new Sprite(this.mTexture, 0, 0);
		mSprite.setScale(2f);		
		mSprite.setRotationCenter(32, 32);	
		PhysicsHandler physicsHandler = new PhysicsHandler(mSprite);
		physicsHandler.setVelocityX(100);		
		physicsHandler.setVelocityY(25);
		//mSprite.setPhysicsHandler(physicsHandler);
		
		AnimatedSprite as = new AnimatedSprite(mBirds, 50, 450, 90.0f);
		PhysicsHandler physicsHandler2 = new PhysicsHandler(as);
		physicsHandler2.setVelocityX(200);		
		physicsHandler2.setVelocityY(-20);
		physicsHandler2.setAccelerationY(-300);
		physicsHandler2.setAngularVelocity(-60);
		as.setPhysicsHandler(physicsHandler2);
		
		Balance b = new Balance(mBalance, 10, 10);
		mSprite.setPosition(b.getX()+40*4, b.getY()+10*4);
		
		
		scene.setBackground(apb);			
		scene.attachChild(mSprite);
		scene.attachChild(as);
		scene.attachChild(b);
		
		scene.setScale(4);
		apb.setScale(2.0f);
		as.setScale(2.0f);
		
		return scene;
	}

	

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

}