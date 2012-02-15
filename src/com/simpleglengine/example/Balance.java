package com.simpleglengine.example;

import android.util.Log;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.sprite.Sprite;

public class Balance extends Sprite {
	PhysicsHandler physicsHandler2;
	public Balance(TextureRegion textureRegion, int x, int y) {
		super(textureRegion, x, y);		
				
		this.setRotationCenter(40, 10);
		
		
		physicsHandler2 = new PhysicsHandler(this);
		physicsHandler2.setAngularVelocity(-150);
		//physicsHandler2.setVelocityX(SimpleGLEngineExampleActivity.SPEED);
		this.setPhysicsHandler(physicsHandler2);
		
	}

	@Override
	public void onUpdate(float alpha) {
		super.onUpdate(alpha);
		
		
		//if(getX()+getScaledWidth() < 0)
		//	setX(1919);
		
		if(getRotation() > 20)
			physicsHandler2.setAngularVelocity(-150);
		if (getRotation() < -140)
			physicsHandler2.setAngularVelocity(100);
			
	}
	
	

}
