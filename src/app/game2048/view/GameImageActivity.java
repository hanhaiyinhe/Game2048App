package app.game2048.view;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.game2048.R;
import app.game2048.business.GameImageView;
import app.game2048.config.Config;
import app.game2048.database.GameOpenHelper;
import app.game2048.model.Level;

public class GameImageActivity extends Activity implements OnClickListener{
private static GameImageActivity mGame;
	
	private TextView level_title,score_title,record_title;
	private TextView tvGoal;
	private TextView tvScore;
	private TextView tvRecord;
	
	
	private int mode=0;
	private int level_id=1;
	private String level_name;
	private int Goal;
	private int Score;
	private int Record;
	
	private Button mBtnRestart;
	private Button mBtnRevert;
	private Button mBtnExit;
	private GameImageView mGameImageView;
	
	private GameOpenHelper db;
	private Cursor cursor;	
	
	private Level level;
	
	public GameImageActivity(){
		mGame=this;
	}
	
	public static GameImageActivity getGameImageActivity(){
		return mGame;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		level_title=(TextView)findViewById(R.id.level_title);
		score_title=(TextView)findViewById(R.id.score_title);
		record_title=(TextView)findViewById(R.id.record_title);
		tvGoal=(TextView)findViewById(R.id.tv_level);
		tvScore=(TextView)findViewById(R.id.tv_score);
		tvRecord=(TextView)findViewById(R.id.tv_record);

		mBtnRestart=(Button)this.findViewById(R.id.btn_restart);
		mBtnRevert=(Button)this.findViewById(R.id.btn_revert);
		mBtnExit=(Button)this.findViewById(R.id.btn_exit);
		
		level_title.setTypeface(Config.typeFace);
		score_title.setTypeface(Config.typeFace);
		record_title.setTypeface(Config.typeFace);
		tvGoal.setTypeface(Config.typeFace);
		tvScore.setTypeface(Config.typeFace);
		tvRecord.setTypeface(Config.typeFace);
		mBtnRestart.setTypeface(Config.typeFace);
		mBtnRevert.setTypeface(Config.typeFace);
		mBtnExit.setTypeface(Config.typeFace);
		
		mBtnRestart.setOnClickListener(this);	
		mBtnRevert.setOnClickListener(this);	
		mBtnExit.setOnClickListener(this);	
		
		Bundle bundle=getIntent().getExtras();
		mode=bundle.getInt("mode");
		level_id=bundle.getInt("id");
		
		level=new Level(level_id);
		
		db = new GameOpenHelper(this);
		cursor=db.selectRecord();
		cursor.moveToPosition(level_id-1);
		
		level_name=cursor.getString(1);
		Record=cursor.getInt(2);
		
		cursor.close();
		db.close();
		Score=0;
		level_title.setText("第"+level_id+"关");
		tvGoal.setText(""+level_name);
		//tvScore.setText("0");
		tvRecord.setText(""+Record);
		
		if(mode==1){
			tvScore.setText(Config.preferences.getInt("score", 0)+"");
		}
		else{
			tvScore.setText("0");
		}
		
		//GameView 初始化	
		mGameImageView=new GameImageView(this,level_id,Record,level,mode);
		RelativeLayout relativeLayout=(RelativeLayout)this.findViewById(R.id.game_panel_r1);
		relativeLayout.addView(mGameImageView);
	}

	
	public void initView(int id){
		this.level_id=id;		
		db = new GameOpenHelper(this);
		cursor=db.selectRecord();
		cursor.moveToPosition(level_id-1);
		
		level_name=cursor.getString(1);
		Record=cursor.getInt(2);
		
		cursor.close();
		db.close();
		Score=0;
		Goal=level.getGoal(level.getWords(level_id));
		tvGoal.setText(""+Goal);
		tvScore.setText("0");
		tvRecord.setText(""+Record);
		setScore(0);
		
	}
	
	public void setGoal(int num){
		tvGoal.setText(String.valueOf(num));
	}
	
	public void setScore(int score){
		tvScore.setText(""+score);
	}
	
	public void setRecord(int score,int state){
		db = new GameOpenHelper(this);
		if(state==0){
			db.UpdateScore(this.level_id, score, 0);
		}
		else if(state==1){
			cursor=db.selectRecord(level_id);
			cursor.moveToFirst();
			if(cursor.getInt(3)==0)
			{
				db.UpdateScore(level_id, score, 1);
				if(level_id<20){
					db.UpdateScore(level_id+1, 0, 0);
					Editor editor=Config.preferences.edit();
					editor.putInt("level_id", this.level_id+1);
					editor.commit();
				}				
			}
			else if(cursor.getInt(3)==1 && cursor.getInt(2)<score)
			{
				db.UpdateScore(level_id, score, state);
			}
			cursor.close();
		}
		db.close();
		tvRecord.setText(""+score);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_restart:
			mGameImageView.startGame(this.level_id,Record);
			setScore(0);
			break;
		case R.id.btn_revert:
			mGameImageView.revertGame();
			break;
		case R.id.btn_exit:
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.icon)
			.setTitle("存档")
			.setMessage("是否保存当前进度？")
			.setPositiveButton("保存", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Editor editor=Config.preferences.edit();
					editor.putInt("save_id", GameImageActivity.this.level_id);
					editor.putInt("score", Integer.parseInt(GameImageActivity.this.tvScore.getText().toString()));
					editor.putString("cardnums", mGameImageView.getCardnums());
					editor.commit();
					GameImageActivity.this.finish();
				}
			})
			.setNegativeButton("不保存", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					GameImageActivity.this.finish();
				}
			}).create().show();
			//this.finish();
		default:
			break;
		}
	}

}
