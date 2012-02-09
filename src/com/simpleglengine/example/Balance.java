package com.simpleglengine.example;

import android.util.Log;

import com.simpleglengine.engine.handler.PhysicsHandler;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.entity.sprite.Sprite;

public class Balance extends Sprite {

	public Balance(Texture texture, int x, int y) {
		super(texture, x, y);		
				
		this.setRotationCenter(40, 10);
		
		PhysicsHandler physicsHandler2 = new PhysicsHandler(this);
		physicsHandler2.setAngularVelocity(-300);
		this.setPhysicsHandler(physicsHandler2);
	}

	@Override
	public void onUpdate(float alpha) {
		super.onUpdate(alpha);
		
		Log.e("RO", ""+getRotation());
	}
	
	

}
