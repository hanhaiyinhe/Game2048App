package app.game2048.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameOpenHelper extends SQLiteOpenHelper {
	
	private final static String DATABASE_NAME = "Game2048_db";
	private final static String TABLE_NAME="game";
	private final static int DATABASE_VERSION = 1;
	private final static String column0="level_id";
	private final static String column1="level_name";
	private final static String column2="score";
	private final static String column3="state";
	private final static String[] columns={column0,column1,column2,column3};
	
	/*
	private final static String TABLE_NAME2="save";
	private final static String column4="id";
	private final static String column5="level_id";
	private final static String column6="level_name";
	private final static String column7="name";
	private final static String column8="score";
	private final static String column9="cardnums";
	private final String[] columns2={column4,column5,column6,column7,column8,column9};
	*/

	public GameOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="create table if not exists "+TABLE_NAME+"( level_id integer primary key, "
				+ "level_name varchar, "
				+ "score integer, "
				+ "state integer )";
		
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (1, '数字', 0, 0)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (2, '百家姓', 0, -1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (3, '平方', 0, -1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (4, '福尔摩斯', 0, -1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (5, '天干', 0, -1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (6, '2048',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (7, '斐波那契',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (8, '生物',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (9, '军队',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (10, '后宫',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (11, '朝代',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (12, '飞出地球',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (13, '都铎王朝',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (14, '疯狂教程',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (15, '古代奇迹',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (16, '音乐大师',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (17, '地支',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (18, '化学',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (19, '解放战争',0,-1)";
		db.execSQL(sql);
		sql="insert into "+TABLE_NAME+" values (20, '朝代完整',0,-1)";
		db.execSQL(sql);
		/*
		sql="create table if not exists "+TABLE_NAME2+"( id integer primary key, "
				+ "level_id integer,"
				+ "level_name varchar, "
				+ "name varchar, "
				+ "score integer, "
				+ "cardnums varchar )";
		db.execSQL(sql);*/
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql = "drop table if exists "+TABLE_NAME;
		db.execSQL(sql);
	//	sql = "drop table if exists "+TABLE_NAME2;
	//	db.execSQL(sql);
		onCreate(db);
	}

	public void UpdateScore(int id,int score,int state){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("score", score);
		values.put("state", state);
		db.update(TABLE_NAME, values, "level_id=?", new String[]{""+id});
		db.close();
	}
	
	public void ClearScore(){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("score",0);
		values.put("state",0);
		db.update(TABLE_NAME, values, "level_id=?", new String[]{"1"});
		int c=count();
		for(int i=2;i<=c;i++){
			values.put("score",0);
			values.put("state",-1);
			db.update(TABLE_NAME, values, "level_id=?", new String[]{""+i});
		}
		
		db.close();
	}
	
	public Cursor selectRecord(){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_NAME, columns, null, null, null, null, null);
		return cursor;
	}
	
	public Cursor queryLevel(){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where state=?", new String[]{String.valueOf(0)});
		return cursor;
	}
	
	public Cursor selectRecord(int levelId){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_NAME, columns, "level_id=?", new String[]{String.valueOf(levelId)}, null, null, null);
		return cursor;
	}
	
	public int count(){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_NAME, columns, null, null, null, null, null);
		if(cursor==null)
			 return 0;
		int c=cursor.getCount();
		cursor.close();
		return c;
	}
	/*
	public int countSave(){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_NAME2, columns2, null, null, null, null, null);
		if(cursor==null)
			 return 0;
		int c=cursor.getCount();
		cursor.close();
		return c;
	}*/
	/*
	public void UpdateSave(int id,int level_id,int score,String name,String cardnums){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("level_id", level_id);
		values.put("score", score);
		values.put("name", name);
		values.put("cardnums", cardnums);
		db.update(TABLE_NAME2, values, "id=?", new String[]{""+id});
		db.close();
	}
	
	public void insertSave(int id,int level_id,String level_name, String name, int score,String cardnums){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("id", id);
		values.put("level_id", level_id);
		values.put("level_name", level_name);
		values.put("name", name);
		values.put("score", score);
		values.put("cardnums", cardnums);
		db.insert(TABLE_NAME2, null, values);
		db.close();
	}
	
	public void deleteSave(int id){
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete(TABLE_NAME2, "id=?", new String[]{""+id});
		db.close();
	}
	*/

}
