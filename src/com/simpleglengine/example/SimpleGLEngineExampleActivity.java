package com.simpleglengine.example;

import java.io.IOException;

import com.simpleglengine.SimpleGLEngineActivity;
import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Font;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.scene.background.AutoParallaxBackground;
import com.simpleglengine.entity.scene.background.ColorBackground;
import com.simpleglengine.entity.scene.background.TextureBackground;
import com.simpleglengine.entity.sprite.AnimatedSprite;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.sprite.SpriteBatch;
import com.simpleglengine.entity.text.Text;
import com.simpleglengine.tools.BitmapTools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.opengl.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


public class SimpleGLEngineExampleActivity extends SimpleGLEngineActivity {
	public static final float SPEED = -200.0f;
	
	
	private Texture mTexture;	
	private Texture mBackgroundColor;
	private Texture mBackgroundAnim;	
	private Texture [] mBirds;	
	private Texture mBalance;
	private Font mFont;
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
		
		
		try {
			mFont = getFontManager().createFont("Verdana.bff");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Scene onLoadScene() {
		final Scene scene = new Scene();
		
		TextureBackground tb2 = new TextureBackground(this.mBackgroundColor, 0, 0);		
		AutoParallaxBackground apb = new AutoParallaxBackground(this.mBackgroundAnim, 180, SPEED);
		apb.setTextureBackground(tb2);
		
		
		Sprite mSprite = new Sprite(this.mTexture, 200, 200) {
			public boolean onTouch(MotionEvent event) {
				Log.e("ddd", "sdsdqsdsdqsd");
				return false;
			}
		};
		
		AnimatedSprite as = new AnimatedSprite(mBirds, 50, 450, 90.0f) {
			public void onUpdate(float alpha) {
				super.onUpdate(alpha);
				final AnimatedSprite sprite = this;
				if(this.getY() < 100) {
					runOnUpdateThread(new Runnable() {
						public void run() {
							scene.detachChild(sprite);
						}
					});					
				}
			}
		};
		PhysicsHandler physicsHandler2 = new PhysicsHandler(as);
		physicsHandler2.setVelocityX(200);		
		physicsHandler2.setVelocityY(-20);
		physicsHandler2.setAccelerationY(-300);
		physicsHandler2.setAngularVelocity(-60);
		as.setPhysicsHandler(physicsHandler2);
		
		Balance b = new Balance(mBalance, 0, 0);
		apb.addFollower(b, 1031, 182);
		
		mFont.setSize(2);
		Text t = new Text(mFont, "FPS: "+ 0, 0, 0) {
			public void onUpdate(float alpha) {
				super.onUpdate(alpha);
				this.setText("FPS: "+getFPS());
			}
		};
		
		

		scene.setBackground(apb);
		
		scene.attachChild(as);
		scene.attachChild(t);
		scene.attachChild(mSprite);
		
		scene.setScale(4);
		apb.setScale(2.0f);
		as.setScale(2.0f);
		
		return scene;
	}

	

	@Override
	public void onLoadComplete() {
		
	}

}