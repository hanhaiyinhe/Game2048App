package app.game2048.config;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Typeface;

public class Config extends Application {
	public static Typeface typeFace;
	public static SharedPreferences preferences;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		typeFace=Typeface.createFromAsset(getAssets(), "fonts/fakt.ttf");
		preferences=getSharedPreferences("level_id", MODE_PRIVATE);
		preferences=getSharedPreferences("save_id",MODE_PRIVATE);
		
	}
	
	
}
