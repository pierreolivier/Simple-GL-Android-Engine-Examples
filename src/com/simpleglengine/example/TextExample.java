package com.simpleglengine.example;


import java.io.IOException;

import android.graphics.Bitmap;

import com.simpleglengine.engine.opengl.Font;
import com.simpleglengine.engine.opengl.Texture;
import com.simpleglengine.engine.opengl.TextureRegion;
import com.simpleglengine.entity.scene.Scene;
import com.simpleglengine.entity.scene.background.ColorBackground;
import com.simpleglengine.entity.sprite.Sprite;
import com.simpleglengine.entity.text.Text;
import com.simpleglengine.tools.BitmapTools;
import com.simpleglengine.tools.ScreenTools;

public class TextExample extends GLActivity {
	private Font mVerdana;
	
	public TextExample() {
		// Config
		this.name = "Write a text";
	}
	
	@Override
	public void onLoadRessources() {		
	    // Create the font
	    try {
			mVerdana = getFontManager().createFont("Verdana.bff");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Scene onLoadScene() {
		// Create your scene
	    Scene scene = new Scene();
	    
	    // Create your text
	    Text text = new Text(mVerdana, "HI !", 100, 100);
	    mVerdana.setSize(20.0f);
	    
	    // Attach your text to the scene
	    scene.setBackground(new ColorBackground(1.0f, 1.0f, 1.0f, 1.0f));
	    scene.attachChild(text);

	    // Return the created scene to the engine
	    return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
		
}
