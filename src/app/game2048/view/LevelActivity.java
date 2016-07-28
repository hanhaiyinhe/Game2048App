package app.game2048.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import app.game2048.R;
import app.game2048.config.Config;


public class LevelActivity extends Activity implements OnClickListener{
	private Button level1,level2,level3,level4,level5,level6,level7,level8,level9,level10,level11,level12,level13,level14,level15,level16,level17,level18,level19,level20;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level);
		
		level1=(Button)this.findViewById(R.id.level1);
		level2=(Button)this.findViewById(R.id.level2);
		level3=(Button)this.findViewById(R.id.level3);
		level4=(Button)this.findViewById(R.id.level4);
		level5=(Button)this.findViewById(R.id.level5);
		level6=(Button)this.findViewById(R.id.level6);
		level7=(Button)this.findViewById(R.id.level7);
		level8=(Button)this.findViewById(R.id.level8);
		level9=(Button)this.findViewById(R.id.level9);
		level10=(Button)this.findViewById(R.id.level10);
		level11=(Button)this.findViewById(R.id.level11);
		level12=(Button)this.findViewById(R.id.level12);
		level13=(Button)this.findViewById(R.id.level13);
		level14=(Button)this.findViewById(R.id.level14);
		level15=(Button)this.findViewById(R.id.level15);
		level16=(Button)this.findViewById(R.id.level16);
		level17=(Button)this.findViewById(R.id.level17);
		level18=(Button)this.findViewById(R.id.level18);
		level19=(Button)this.findViewById(R.id.level19);
		level20=(Button)this.findViewById(R.id.level20);
		
		level1.setTypeface(Config.typeFace);
		level2.setTypeface(Config.typeFace);
		level3.setTypeface(Config.typeFace);
		level4.setTypeface(Config.typeFace);
		level5.setTypeface(Config.typeFace);
		level6.setTypeface(Config.typeFace);
		level7.setTypeface(Config.typeFace);
		level8.setTypeface(Config.typeFace);
		level9.setTypeface(Config.typeFace);
		level10.setTypeface(Config.typeFace);
		level11.setTypeface(Config.typeFace);
		level12.setTypeface(Config.typeFace);
		level13.setTypeface(Config.typeFace);
		level14.setTypeface(Config.typeFace);
		level15.setTypeface(Config.typeFace);
		level16.setTypeface(Config.typeFace);
		level17.setTypeface(Config.typeFace);
		level18.setTypeface(Config.typeFace);
		level19.setTypeface(Config.typeFace);
		level20.setTypeface(Config.typeFace);
		
		level1.setOnClickListener(this);
		level2.setOnClickListener(this);
		level3.setOnClickListener(this);
		level4.setOnClickListener(this);
		level5.setOnClickListener(this);
		level6.setOnClickListener(this);
		level7.setOnClickListener(this);
		level8.setOnClickListener(this);
		level9.setOnClickListener(this);
		level10.setOnClickListener(this);
		level11.setOnClickListener(this);
		level12.setOnClickListener(this);
		//level13.setOnClickListener(this);
		//level14.setOnClickListener(this);
		//level15.setOnClickListener(this);
		//level16.setOnClickListener(this);
		level17.setOnClickListener(this);
		level18.setOnClickListener(this);
		level19.setOnClickListener(this);
		level20.setOnClickListener(this);
		
		level13.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LevelActivity.this,GameImageActivity.class);
				intent.putExtra("mode", 0);
				intent.putExtra("id", 13);
				startActivity(intent);
			}
			
		});
		
		level14.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LevelActivity.this,GameImageActivity.class);
				intent.putExtra("mode", 0);
				intent.putExtra("id", 14);
				startActivity(intent);
			}
			
		});
		
		level15.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LevelActivity.this,GameImageActivity.class);
				intent.putExtra("mode", 0);
				intent.putExtra("id", 15);
				startActivity(intent);
			}
			
		});
		
		level16.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LevelActivity.this,GameImageActivity.class);
				intent.putExtra("mode", 0);
				intent.putExtra("id", 16);
				startActivity(intent);
			}
			
		});
	}
	
	


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//Config.preferences=getSharedPreferences("level_id",MODE_PRIVATE);
		int nowLevel=Config.preferences.getInt("level_id", 1);
		
		Button[] buttons={level1,level2,level3,level4,level5,level6,level7,level8,level9,level10,level11,level12,level13,level14,level15,level16,level17,level18,level19,level20};
		
		for(int i=0;i<nowLevel;i++){
			buttons[i].setEnabled(true);
		}
		for(int i=nowLevel;i<buttons.length;i++){
			buttons[i].setEnabled(false);
		}
		
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(LevelActivity.this,GameActivity.class);
		intent.putExtra("mode", 0);
		switch(v.getId()){
		case R.id.level1:
			intent.putExtra("id", 1);
			startActivity(intent);
			break;
		case R.id.level2:
			intent.putExtra("id", 2);
			startActivity(intent);
			break;
		case R.id.level3:
			intent.putExtra("id", 3);
			startActivity(intent);
			break;
		case R.id.level4:
			intent.putExtra("id", 4);
			startActivity(intent);
			break;
		case R.id.level5:
			intent.putExtra("id", 5);
			startActivity(intent);
			break;
		case R.id.level6:
			intent.putExtra("id", 6);
			startActivity(intent);
			break;
		case R.id.level7:
			intent.putExtra("id", 7);
			startActivity(intent);
			break;
		case R.id.level8:
			intent.putExtra("id", 8);
			startActivity(intent);
			break;
		case R.id.level9:
			intent.putExtra("id", 9);
			startActivity(intent);
			break;
		case R.id.level10:
			intent.putExtra("id", 10);
			startActivity(intent);
			break;
		case R.id.level11:
			intent.putExtra("id", 11);
			startActivity(intent);
			break;
		case R.id.level12:
			intent.putExtra("id", 12);
			startActivity(intent);
			break;

		case R.id.level17:
			intent.putExtra("id", 17);
			startActivity(intent);
			break;
		case R.id.level18:
			intent.putExtra("id", 18);
			startActivity(intent);
			break;
		case R.id.level19:
			intent.putExtra("id", 19);
			startActivity(intent);
			break;
		case R.id.level20:
			intent.putExtra("id", 20);
			startActivity(intent);
			break;
		default:
			break;
		}
	}	
	
}
