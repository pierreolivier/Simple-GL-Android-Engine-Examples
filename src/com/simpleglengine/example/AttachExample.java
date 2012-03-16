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

public class AttachExample extends GLActivity {
	private Texture mTexture;
	private TextureRegion mSpriteTextureRegion;

	public AttachExample() {
		// Config
		this.name = "Attach/Detach an Entity";
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
		Scene scene = new Scene() {
			public boolean onTouch(MotionEvent event) {
				final Scene mScene = this;
				boolean result = super.onTouch(event);
				if(!result && event.getAction() == MotionEvent.ACTION_DOWN) {
					// Create the sprite
					final Sprite sprite = new Sprite(mSpriteTextureRegion, (int) event.getX(), (int) event.getY()) {
						final Sprite mSprite = this;
						{
							// Create the physic handler
							PhysicsHandler physicsHandler = new PhysicsHandler(this);
							physicsHandler.setVelocity(200.0f, 200.0f);
							setPhysicsHandler(physicsHandler);
						}
						public void onManagedUpdate(float alpha) {
							super.onManagedUpdate(alpha);
							
							if(mX+getScaledWidth() > ScreenTools.getWidth())
								mPhysicsHandler.setVelocityX(-200);
							if( mX < 0)
								mPhysicsHandler.setVelocityX(200);
							if(mY+getScaledHeight() > ScreenTools.getHeight())
								mPhysicsHandler.setVelocityY(-280);
							if( mY < 0)
								mPhysicsHandler.setVelocityY(280);

						}
						public boolean onTouch(MotionEvent event) {
							if(event.getAction() == MotionEvent.ACTION_DOWN) {
								
								// Detach
								Runnable detach = new Runnable() {						
									@Override
									public void run() {
										mScene.detachChild(mSprite);				
									}
								};
								AttachExample.this.runOnUpdateThread(detach);
								
								return true;
							}
							return false;
						}
					};
					
					// Attach
					Runnable attach = new Runnable() {						
						@Override
						public void run() {
							mScene.attachChild(sprite);
							sprite.setScale(2.0f);
						}
					};
					AttachExample.this.runOnUpdateThread(attach);
				}

				return result;
			}
		};	    


		// Attach your sprite to the scene
		scene.setBackground(new ColorBackground(1.0f, 1.0f, 1.0f, 1.0f));

		// Return the created scene to the engine
		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

}
