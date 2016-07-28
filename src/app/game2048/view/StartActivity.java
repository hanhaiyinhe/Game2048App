package app.game2048.view;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import app.game2048.R;
import app.game2048.config.Config;

public class StartActivity extends Activity {
	private Button btn_enter,btn_load,btn_exit,btn_achievement;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		btn_enter=(Button)this.findViewById(R.id.bt_enter);
		btn_load=(Button)this.findViewById(R.id.bt_load);
		btn_exit=(Button)this.findViewById(R.id.bt_exit);
		btn_achievement=(Button)this.findViewById(R.id.bt_achievement);

		btn_enter.setTypeface(Config.typeFace);
		btn_load.setTypeface(Config.typeFace);
		btn_exit.setTypeface(Config.typeFace);
		btn_achievement.setTypeface(Config.typeFace);
		
		btn_enter.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(StartActivity.this,LevelActivity.class);
				startActivity(intent);
				//overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}});
		
		btn_load.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int saveLevel=Config.preferences.getInt("save_id", 0);
				if(saveLevel==0)
					Toast.makeText(getApplicationContext(), "没有保存的游戏数据", Toast.LENGTH_SHORT).show();
				else if((saveLevel>=13)&&(saveLevel<=16)){
					Intent intent=new Intent(StartActivity.this,GameImageActivity.class);
					intent.putExtra("mode", 1);
					intent.putExtra("id", saveLevel);
					startActivity(intent);
				}
				else{
					Intent intent=new Intent(StartActivity.this,GameActivity.class);
					intent.putExtra("mode", 1);
					intent.putExtra("id", saveLevel);
					startActivity(intent);
				}
			}});
		
		btn_achievement.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(StartActivity.this,AchieveActivity.class);
				startActivity(intent);
				//overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}});
		
		btn_exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(StartActivity.this)
				.setIcon(R.drawable.icon)		
				.setTitle("退出游戏")
				.setMessage("是否退出2048闯关游戏？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						int nPid=android.os.Process.myPid();
						android.os.Process.killProcess(nPid);
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).show();
				
			}});
	}

}
