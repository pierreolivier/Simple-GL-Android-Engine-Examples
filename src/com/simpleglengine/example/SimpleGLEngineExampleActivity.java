package com.simpleglengine.example;

import java.io.IOException;

import com.simpleglengine.SimpleGLEngineActivity;
import com.simpleglengine.audio.Music;
import com.simpleglengine.audio.Sound;
import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.handler.PhysicsJumpHandler;
import com.simpleglengine.engine.handler.modifier.ColorEntityModifier;
import com.simpleglengine.engine.handler.modifier.DoubleValueEntityModifier;
import com.simpleglengine.engine.handler.modifier.IEntityModifierListener;
import com.simpleglengine.engine.handler.modifier.ease.EaseBounceOut;
import com.simpleglengine.engine.handler.modifier.ease.EaseLinear;
import com.simpleglengine.engine.opengl.ColorBuffer;
import com.simpleglengine.engine.opengl.Font;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.Shape;
import com.simpleglengine.entity.primitive.GradientColorRectangle;
import com.simpleglengine.entity.primitive.Rectangle;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.scene.background.AutoParallaxBackground;
import com.simpleglengine.entity.scene.background.ColorBackground;
import com.simpleglengine.entity.scene.background.TextureBackground;
import com.simpleglengine.entity.scene.menu.Menu;
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
	private Texture mTexture2;
	
	private TextureRegion mRail1, mRail2;
	private TextureRegion mTrainTop, mTrain;
	
	private TextureRegion mBackgroundColor;
	private TextureRegion mBackgroundAnim;	
	private TextureRegion [] mBirds;	
	private TextureRegion mBalance;
	
	private Font mFont;
	private Music mMusic;
	private Sound mSound;

	@Override
	public void onLoadRessources() {
		Bitmap bitmap = BitmapTools.loadBitmapFromRessource(R.drawable.texture);
		this.mTexture = getTextureManager().loadTextureFromBitmap(bitmap);
		
		Bitmap bitmap2 = BitmapTools.loadBitmapFromRessource(R.drawable.texture2);
		this.mTexture2 = getTextureManager().loadTextureFromBitmap(bitmap2);
		
		this.mRail1 = new TextureRegion(mTexture, 700, 291, 316, 345);
		this.mRail2 = new TextureRegion(mTexture, 408, 1, 316, 280);
		
		this.mTrainTop = new TextureRegion(mTexture, 969, 196, 33, 35);
		this.mTrain = new TextureRegion(mTexture, 875, 193, 29, 34);
		
		this.mBackgroundColor = new TextureRegion(mTexture2, 0, 0, 854, 480);
		this.mBackgroundAnim = new TextureRegion(mTexture2, 0, 512, 1024, 150);
		
		this.mBalance = new TextureRegion(mTexture2, 386, 665, 44, 43);
		/*
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
		*/
		
		
		try {
			mMusic = getAudioManager().loadMusic(R.raw.music);
			mSound = getAudioManager().loadSound(R.raw.perfect);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

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
		
		ColorBuffer colorBuffer = new ColorBuffer();
		colorBuffer.setColor1(10f/255f, 136f/255f, 165f/255f, 1);
		colorBuffer.setColor3(10f/255f, 136f/255f, 165f/255f, 1);
		colorBuffer.setColor2(240f/255f, 1, 1, 1);
		colorBuffer.setColor4(240f/255f, 1, 1, 1);
		colorBuffer.generate();
		GradientColorRectangle gcr = new GradientColorRectangle(0, 0, 854, 200, colorBuffer);	
		AutoParallaxBackground apb = new AutoParallaxBackground(this.mBackgroundAnim, 180, SPEED);
		apb.setBackground(gcr);
		
		apb.setColor(1, 1, 1, 1);
		Balance b = new Balance(mBalance, 0, 0);
		apb.addFollower(b, 1031, 182);
		
		Sprite mRail_1 = new Sprite(mRail1, 0, 250);
		Sprite mRail_2 = new Sprite(mRail2, (int) (mRail_1.getWidth()*1.5f), 250+98);
		Sprite mTrain_Top = new Sprite(mTrainTop, 300, 100) {
			private PhysicsJumpHandler jmp;
			
			{
				jmp = new PhysicsJumpHandler(this);
				jmp.settingWorld(480-4*32-60, 3);
				jmp.settingJump(0, -650, 0, 2000);
				this.setPhysicsHandler(jmp);
			}
			public boolean onTouch(MotionEvent event) {	
				if(event.getAction() == MotionEvent.ACTION_DOWN) {			
					jmp.startJump();
					return true;
				} else {
					return false;
				}
			}
		};
		mFont.setSize(2);
		Text t = new Text(mFont, "FPS: "+ 0, 0, 0) {
			public void onManagedUpdate(float alpha) {
				super.onManagedUpdate(alpha);
				this.setText("FPS: "+getFPS());
			}
		};
		Rectangle rec = new Rectangle(0, 0, 10, 10, 1, 1, 1, 1);
		
		scene.setBackground(apb);
		scene.attachChild(mRail_1);
		scene.attachChild(mRail_2);
		scene.attachChild(mTrain_Top);
		scene.attachChild(t);
		scene.attachChild(rec);
		
		
		t.setPause(true);
		
		scene.setScale(1.5f);
		apb.setScale(2.0f);
		mTrain_Top.setScale(2.0f);
		/*
		TextureBackground tb2 = new TextureBackground(this.mBackgroundColor, 0, 0);		
		AutoParallaxBackground apb = new AutoParallaxBackground(this.mBackgroundAnim, 180, SPEED);
		apb.setTextureBackground(tb2);


		Rectangle rec = new Rectangle(0, 0, 1000, 1000, 0.2f, 0.1f, 0, 0.8f);
		final Menu menu = new Menu();
		Text t2 = new Text(mFont, "QUIT MENU", 200, 220) {
			public boolean onTouch(MotionEvent event) {
				menu.hide();
				return true;
			}
		};
		menu.attachChild(rec);
		menu.attachChild(t2);		
		//menu.setAutoPause(true);		
		scene.setMenu(menu);

		final Rectangle rec2 = new Rectangle(0, 0, 1000, 500, 1.0f, 1.0f, 1.0f, 1.0f);
		IEntityModifierListener lis = new IEntityModifierListener() {			
			@Override
			public void onModifierStarted(Shape shape) {
				
			}
			
			@Override
			public void onModifierFinished(Shape shape) {
				runOnUpdateThread(new Runnable() {
					
					@Override
					public void run() {
						scene.detachChild(rec2);
						
					}
				});
			}
		};
		rec2.addEntityModifier(new ColorEntityModifier(0.3f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, new EaseLinear(), lis));
		

		Sprite mSprite = new Sprite(this.mTexture, 100, 100) {
			private PhysicsJumpHandler jmp;
			{
				jmp = new PhysicsJumpHandler(this);
				jmp.settingWorld(480-4*32, 3);
				jmp.settingJump(0, -650, 0, 2000);
				this.setPhysicsHandler(jmp);
			}
			public boolean onTouch(MotionEvent event) {				
				//menu.show();
				if(event.getAction() == MotionEvent.ACTION_DOWN) {			
					jmp.startJump();
					return true;
				} else {
					return false;
				}
			}
		};

		//mSprite.addEntityModifier(new DoubleValueEntityModifier(2, 200, 200, 0, 0, new EaseBounceOut()));

		AnimatedSprite as = new AnimatedSprite(mBirds, 50, 450, 90.0f) {
			public void onUpdate(float alpha) {
				super.onUpdate(alpha);
				final AnimatedSprite sprite = this;
				if(this.getY() < -50) {
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
		physicsHandler2.setAngularVelocity(-40);
		as.setPhysicsHandler(physicsHandler2);

		Balance b = new Balance(mBalance, 0, 0);
		apb.addFollower(b, 1031, 182);

		mFont.setSize(2);
		Text t = new Text(mFont, "FPS: "+ 0, 0, 0) {
			public void onUpdate(float alpha) {
				super.onUpdate(alpha);
				this.setText("FPS: "+getFPS());
			}
			public boolean onTouch(MotionEvent event) {				
				menu.hide();
				return true;
			}
		};



		scene.setBackground(apb);

		scene.attachChild(as);
		scene.attachChild(t);
		scene.attachChild(mSprite);
		scene.attachChild(rec2);

		scene.setScale(4);
		apb.setScale(2.0f);
		as.setScale(2.0f);

		*/
		return scene;
	}



	@Override
	public void onLoadComplete() {

	}

}