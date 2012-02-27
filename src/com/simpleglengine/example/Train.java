package com.simpleglengine.example;

import android.util.Log;
import android.view.MotionEvent;

import com.simpleglengine.engine.handler.PhysicsJumpHandler;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.sprite.Sprite;

public class Train extends Sprite {
	private PhysicsJumpHandler jmp;
	public PhysicsJumpHandler getJmp() {
		return jmp;
	}

	public Train(TextureRegion textureRegion, int x, int y) {
		super(textureRegion, x, y);


		jmp = new PhysicsJumpHandler(this);
		jmp.settingWorld(480-4*32-60, 1);
		jmp.settingJump(0, -1250, 0, 3500);

		this.setPhysicsHandler(jmp);
	}
	/*
	public boolean onTouch(MotionEvent event) {	
		if (event.getAction() == MotionEvent.ACTION_UP) {
			jmp.resetYVelocity();
			Log.e("jmp", "stop");
			return true;
		} else if(event.getAction() == MotionEvent.ACTION_DOWN) {			
			jmp.startJump();
			Log.e("jmp","start");
			return true;
		} else {
			return false;
		}
	}*/

}
