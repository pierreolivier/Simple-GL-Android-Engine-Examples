package com.simpleglengine.example;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class SimpleGLEngineExampleActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		GLActivity [] activities = new GLActivity[10];
		activities[0] = new SpriteExample();
		activities[1] = new PhysicsHandlerExample();
		activities[2] = new OnManagedUpdateExample();
		activities[3] = new OnTouchEventExample();
		activities[4] = new AttachExample();
		activities[5] = new BackgroundExample();
		activities[6] = new ModifierExample();
		activities[7] = new TextExample();
		activities[8] = new MenuExample();
		activities[9] = new MusicSoundExample();
		
		ListView list = (ListView) findViewById(R.id.list);
		final ArrayAdapter<GLActivity> aa = new ArrayAdapter<GLActivity>(this, R.layout.list_activity, activities);
		list.setAdapter(aa);
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(SimpleGLEngineExampleActivity.this, SpriteExample.class);;
				switch(position) {
					case 0: intent = new Intent(SimpleGLEngineExampleActivity.this, SpriteExample.class); break;
					case 1: intent = new Intent(SimpleGLEngineExampleActivity.this, PhysicsHandlerExample.class); break;
					case 2: intent = new Intent(SimpleGLEngineExampleActivity.this, OnManagedUpdateExample.class); break;
					case 3: intent = new Intent(SimpleGLEngineExampleActivity.this, OnTouchEventExample.class); break;
					case 4: intent = new Intent(SimpleGLEngineExampleActivity.this, AttachExample.class); break;
					case 5: intent = new Intent(SimpleGLEngineExampleActivity.this, BackgroundExample.class); break;
					case 6: intent = new Intent(SimpleGLEngineExampleActivity.this, ModifierExample.class); break;
					case 7: intent = new Intent(SimpleGLEngineExampleActivity.this, TextExample.class); break;
					case 8: intent = new Intent(SimpleGLEngineExampleActivity.this, MenuExample.class); break;
					case 9: intent = new Intent(SimpleGLEngineExampleActivity.this, MusicSoundExample.class); break;
				}
				startActivity(intent);
			}
		});
	}
}