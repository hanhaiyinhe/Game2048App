package app.game2048.view;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import app.game2048.R;
import app.game2048.business.AchieveAdapter;
import app.game2048.config.Config;
import app.game2048.database.GameOpenHelper;

public class AchieveActivity extends Activity{
	
	private GameOpenHelper db;
	private AchieveAdapter achiAdapter;
	private ListView dbListView;
	private Cursor cursor;
	
	private List<String> LevelID;
	private List<String> LevelScore;
	AlertDialog dialog;
	
	private Button btn_enter,btn_exit,btn_clear;
	private TextView achieve_title,achieve_id,achieve_score;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve);
        achieve_title=(TextView)findViewById(R.id.achieve_title);
        achieve_id=(TextView)findViewById(R.id.achieve_id);
        achieve_score=(TextView)findViewById(R.id.achieve_score);
        dbListView = (ListView)findViewById(R.id.achievelist);
        btn_enter=(Button)this.findViewById(R.id.btn_achienter);
        btn_exit=(Button)this.findViewById(R.id.btn_achiexit);
        btn_clear=(Button)this.findViewById(R.id.btn_achiclear);
        
        achieve_title.setTypeface(Config.typeFace);
        achieve_id.setTypeface(Config.typeFace);
        achieve_score.setTypeface(Config.typeFace);
        btn_enter.setTypeface(Config.typeFace);
        btn_exit.setTypeface(Config.typeFace);
		btn_clear.setTypeface(Config.typeFace);
		
        
        btn_enter.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(AchieveActivity.this,LevelActivity.class);
				startActivity(intent);
				AchieveActivity.this.finish();
			}});
        
        btn_exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AchieveActivity.this.finish();
			}
        	
        });
        
        
        btn_clear.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(AchieveActivity.this).setTitle("重置游戏")
				.setMessage("是否清空游戏记录?")
				.setPositiveButton("清空", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						db=new GameOpenHelper(AchieveActivity.this);
						db.ClearScore();
						db.close();
						refreshDBOpenHelper(); 
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				})
				.show();							
			}
        	
        });
        
	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		refreshDBOpenHelper(); 
	}

	public void refreshDBOpenHelper(){
		db=new GameOpenHelper(this);
        cursor = db.selectRecord();
        LevelID = new ArrayList<String>();
        LevelScore = new ArrayList<String>();   
        int count = cursor.getCount();
        if(count>0){
	        for(int i=0;i<count;i++)
	        {
	        	cursor.moveToPosition(i);
	        	LevelID.add(cursor.getString(0).toString());
	        	int record=Integer.parseInt(cursor.getString(2));
	        	if(record==0){
	        		LevelScore.add("未通关");
	        	}
	        	else{
	        		LevelScore.add(String.valueOf(record));
	        	}
	        }
        }       
        achiAdapter = new AchieveAdapter(this,LevelID,LevelScore);
        dbListView.setAdapter(achiAdapter);   
        cursor.close();
        db.close();
    }
}
