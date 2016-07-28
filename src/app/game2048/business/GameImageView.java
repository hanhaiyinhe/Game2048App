package app.game2048.business;

import java.util.ArrayList;
import java.util.List;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.GridLayout;
import app.game2048.config.Config;
import app.game2048.model.CardImageItem;
import app.game2048.model.CardItem;
import app.game2048.model.Level;
import app.game2048.view.GameImageActivity;

public class GameImageView extends GridLayout implements OnTouchListener{
	private CardImageItem[][] mGameMatrix;
	private List<Point> mBlanks;
	private int mGameLines;
	private int mStartX,mStartY,mEndX,mEndY;
	private List<Integer> mCalList;
	private int mKeyItemNum=-1;
	private int[][] mGameMatrixHistory;
	private int mScoreHistory=0;
	private int mHighScore=0;
	private int mGoal;
	private int mScore=0;
	private int mItemSize=0;
	
	private int[] images;
	
	private int levelId;
	private Level level;

	public GameImageView(Context context,int levelId,int record,Level level,int mode) {
		super(context);
		// TODO Auto-generated constructor stub
		this.levelId=levelId;
		this.mHighScore=record;
		this.mScore=0;
		this.level=level;
		
		images=level.getImages(levelId);
		this.mGoal=level.getImageGoal(images);
		this.mGameLines=level.getCardSize();
		//initGameImageMatrix(record);
		
		if(mode==1){
			this.mScore=Config.preferences.getInt("score", 0);
			loadGameImageMatrix(record);
		}
		else{
			this.mScore=0;
			initGameImageMatrix(record);
		}
	}
	
	public void startGame(int levelId,int record){
		this.mScore=0;
		initGameImageMatrix(record);
		initGameImageView(mItemSize);
	}
	
	public void initGameImageView(int cardSize){
		removeAllViews();
		CardImageItem card;
		for(int i=0;i<mGameLines;i++){
			for(int j=0;j<mGameLines;j++){
				card=new CardImageItem(getContext(),0,images[0]);
				addView(card,cardSize,cardSize);	
				mGameMatrix[i][j]=card;
				mBlanks.add(new Point(i,j));
			}
		}	
		addRandomNum();
		addRandomNum();
	}
	
	public void revertGame(){
		int sum=0;
		int num=0;
		for(int[] element:mGameMatrixHistory){
			for(int i:element)
				sum+=i;
		}
		if(sum!=0){
			GameImageActivity.getGameImageActivity().setScore(mScoreHistory);
			mScore=mScoreHistory;
			for(int i=0;i<mGameLines;i++){
				for(int j=0;j<mGameLines;j++){
					//mGameMatrix[i][j].setNum(mGameMatrixHistory[i][j]);
					num=mGameMatrixHistory[i][j];
					mGameMatrix[i][j].updateNum(images[num], num);
				}
			}
		}
	}
	
	public void addRandomNum(){
		getBlanks();
		if(mBlanks.size()>0){
			int randomNum=(int)(Math.random()*mBlanks.size());
			Point randomPoint=mBlanks.get(randomNum);
			//mGameMatrix[randomPoint.x][randomPoint.y].setNum(Math.random()>0.2d?1:2);
			int newNum=Math.random()>0.2d?1:2;
			mGameMatrix[randomPoint.x][randomPoint.y].updateNum(images[newNum], newNum);
			animCreate(mGameMatrix[randomPoint.x][randomPoint.y]);
		}
	}

	public void animCreate(CardImageItem target){
		ScaleAnimation sa=new ScaleAnimation(0.1f,1,0.1f,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		sa.setDuration(100);
		target.setAnimation(null);
		target.getImageView().startAnimation(sa);
	}
	
	public String getCardnums(){
		char[] ch=new char[mGameLines*mGameLines];
		int count=0;
		int tmpNum=0;
		for(int i=0;i<mGameLines;i++){
			for(int j=0;j<mGameLines;j++){
				tmpNum=mGameMatrix[i][j].getCardNum();
				if(tmpNum<10){
					ch[count]=(char)('0'+tmpNum);
				}
				else{
					ch[count]=(char)('A'+tmpNum-10);
				}
				count++;
			}
		}
		return String.valueOf(ch);
	}
	
	
	private void getBlanks(){
		mBlanks.clear();
		for(int i=0;i<mGameLines;i++){
			for(int j=0;j<mGameLines;j++){
				if(mGameMatrix[i][j].getCardNum()==0)
					mBlanks.add(new Point(i,j));
			}
		}
	}
	
	private void initGameImageMatrix(int record){
		removeAllViews();
		mScoreHistory=0;
		mScore=0;
		mGameMatrix=new CardImageItem[mGameLines][mGameLines];
		mGameMatrixHistory=new int[mGameLines][mGameLines];
		mCalList=new ArrayList<Integer>();
		mBlanks=new ArrayList<Point>();

		mHighScore=record;
		setColumnCount(mGameLines);
		setRowCount(mGameLines);
		setOnTouchListener(this);
		
		DisplayMetrics metrics=new DisplayMetrics();
		WindowManager wm=(WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display=wm.getDefaultDisplay();
		display.getMetrics(metrics);
		mItemSize=metrics.widthPixels/mGameLines;
		initGameImageView(mItemSize);
		
	}
	
	private void loadGameImageMatrix(int record){
		removeAllViews();
		mScoreHistory=0;
		mGameMatrix=new CardImageItem[mGameLines][mGameLines];
		mGameMatrixHistory=new int[mGameLines][mGameLines];
		mCalList=new ArrayList<Integer>();
		mBlanks=new ArrayList<Point>();

		mHighScore=record;
		setColumnCount(mGameLines);
		setRowCount(mGameLines);
		setOnTouchListener(this);
		
		DisplayMetrics metrics=new DisplayMetrics();
		WindowManager wm=(WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display=wm.getDefaultDisplay();
		display.getMetrics(metrics);
		mItemSize=metrics.widthPixels/mGameLines;
		String cardnums=Config.preferences.getString("cardnums", "null");
		CardImageItem card;
		int count=0;
		int num=0;	
		for(int i=0;i<mGameLines;i++){
			for(int j=0;j<mGameLines;j++){
				card=new CardImageItem(getContext(),0,images[0]);
				addView(card,mItemSize,mItemSize);	
				mGameMatrix[i][j]=card;
				if(cardnums.charAt(count)<='9')
					num=cardnums.charAt(count)-'0';
				else
					num=cardnums.charAt(count)-'A'+10;
				if(num==0)
					mBlanks.add(new Point(i,j));
				mGameMatrix[i][j].updateNum(images[num],num);
				count++;
			}
		}	
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			saveHistoryMatrix();
			mStartX=(int)event.getX();
			mStartY=(int)event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			mEndX=(int)event.getX();
			mEndY=(int)event.getY();
			judgeDirection(mEndX-mStartX,mEndY-mStartY);
			if(isMoved()){
				addRandomNum();

				GameImageActivity.getGameImageActivity().setScore(mScore);
			}
			checkCompleted();
			break;
		default:
			break;
		}
		return true;
	}
	
	private void saveHistoryMatrix(){
		mScoreHistory=mScore;
		for(int i=0;i<mGameLines;i++){
			for(int j=0;j<mGameLines;j++){
				mGameMatrixHistory[i][j]=mGameMatrix[i][j].getCardNum();
			}
		}
	}
	
	private int getDeviceDensity(){
		DisplayMetrics metrics=new DisplayMetrics();
		WindowManager wm=(WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);
		return (int)metrics.density;
	}
	
	private void judgeDirection(int offsetX,int offsetY){
		int density=getDeviceDensity();
		int slideDis=5*density;
		boolean flagNormal=(Math.abs(offsetX)>slideDis||Math.abs(offsetY)>slideDis);
		if(flagNormal){
			if(Math.abs(offsetX)>Math.abs(offsetY)){
				if(offsetX>slideDis)
					swipeRight();
				else
					swipeLeft();
			}else{
				if(offsetY>slideDis)
					swipeDown();
				else
					swipeUp();
			}
		}
	}
	
	private int checkNums() {
        getBlanks();
        if (mBlanks.size() == 0) {
            for (int i = 0; i < mGameLines; i++) {
                for (int j = 0; j < mGameLines; j++) {
                    if (j < mGameLines - 1) {
                        if (mGameMatrix[i][j].getCardNum() == mGameMatrix[i][j + 1].getCardNum()) {
                            return 1;
                        }
                    }
                    if (i < mGameLines - 1) {
                        if (mGameMatrix[i][j].getCardNum() == mGameMatrix[i + 1][j].getCardNum()) {
                            return 1;
                        }
                    }
                }
            }
            return 0;
        }
        for (int i = 0; i < mGameLines; i++) {
            for (int j = 0; j < mGameLines; j++) {
                if (mGameMatrix[i][j].getCardNum() == mGoal) {
                    return 2;
                }
            }
        }
        return 1;
    }
	
	private void checkCompleted(){
	    int result=checkNums();
		if(result==0){
			//i
			if(mScore>mHighScore){
				mHighScore=mScore;
				GameImageActivity.getGameImageActivity().setRecord(mScore, 0);
			}
			AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
			builder.setTitle("游戏失败!")
			.setPositiveButton("重玩", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					//startGame();
					startGame(levelId,mHighScore);
				}
			}).setNegativeButton("退出", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					GameImageActivity.getGameImageActivity().finish();
				}
			}).create().show();
		}
		else if(result==2){
			if(mScore>mHighScore){
				mHighScore=mScore;
			}
			GameImageActivity.getGameImageActivity().setRecord(mScore, 1);
				
			
			AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
			builder.setTitle("游戏胜利!")
			.setPositiveButton("重玩", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startGame(levelId,mHighScore);
				}
			})
			.setNegativeButton("退出", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					GameImageActivity.getGameImageActivity().finish();
				}
			}).create().show();
		}
		else{
			result=1;
		}
	}
	
	private boolean isMoved(){
		for(int i=0;i<mGameLines;i++){
			for(int j=0;j<mGameLines;j++)
			{
				if(mGameMatrixHistory[i][j]!=mGameMatrix[i][j].getCardNum())
					return true;
			}
		}
		return false;
	}
	
	private void swipeUp(){
		int num=0;
		for(int i=0;i<mGameLines;i++){
			for(int j=0;j<mGameLines;j++){
				int currentNum=mGameMatrix[j][i].getCardNum();
				if(currentNum!=0){
					if(mKeyItemNum==-1){
						mKeyItemNum=currentNum;
					}
					else{
						if(mKeyItemNum==currentNum){
							mCalList.add(mKeyItemNum+1);
							mScore+=level.getItemScore(mKeyItemNum+1);
							mKeyItemNum=-1;
						}else{
							mCalList.add(mKeyItemNum);
							mKeyItemNum=currentNum;
						}
					}
				}
				else
				{
					continue;
				}
			}
			if(mKeyItemNum!=-1)
			{
				mCalList.add(mKeyItemNum);
			}
			for(int j=0;j<mCalList.size();j++){
				num=mCalList.get(j);
				mGameMatrix[j][i].updateNum(images[num], num);
			}
			for(int m=mCalList.size();m<mGameLines;m++){
				mGameMatrix[m][i].updateNum(images[0], 0);
			}
			mKeyItemNum=-1;
			mCalList.clear();
		}
	}
	
	private void swipeDown(){
		int num=0;
		for(int i=mGameLines-1;i>=0;i--){
			for(int j=mGameLines-1;j>=0;j--){
				int currentNum=mGameMatrix[j][i].getCardNum();
				if(currentNum!=0){
					if(mKeyItemNum==-1)
						mKeyItemNum=currentNum;
					else{
						if(mKeyItemNum==currentNum){
							mCalList.add(mKeyItemNum+1);
							mScore+=level.getItemScore(mKeyItemNum+1);
							mKeyItemNum=-1;
						}else{
							mCalList.add(mKeyItemNum);
							mKeyItemNum=currentNum;
						}
					}
				}else{
					continue;
				}
			}
			if(mKeyItemNum!=-1)
				mCalList.add(mKeyItemNum);
			for(int j=0;j<mGameLines-mCalList.size();j++){

				mGameMatrix[j][i].updateNum(images[0], 0);
			}
			int index=mCalList.size()-1;
			for(int m=mGameLines-mCalList.size();m<mGameLines;m++){
				num=mCalList.get(index);
				mGameMatrix[m][i].updateNum(images[num], num);
				index--;
			}
			mKeyItemNum=-1;
			mCalList.clear();
			index=0;
		}
	}
	
	private void swipeLeft(){
		int num=0;
		for(int i=0;i<mGameLines;i++){
			for(int j=0;j<mGameLines;j++){
				int currentNum=mGameMatrix[i][j].getCardNum();
				if(currentNum!=0){
					if(mKeyItemNum==-1)
						mKeyItemNum=currentNum;
					else{
						if(mKeyItemNum==currentNum){
							mCalList.add(mKeyItemNum+1);
							mScore+=level.getItemScore(mKeyItemNum+1);
							mKeyItemNum=-1;
						}else{
							mCalList.add(mKeyItemNum);
							mKeyItemNum=currentNum;
						}
					}
				}else{
					continue;
				}
			}
			if(mKeyItemNum!=-1){
				mCalList.add(mKeyItemNum);
			}
			for(int j=0;j<mCalList.size();j++){
				num=mCalList.get(j);
				mGameMatrix[i][j].updateNum(images[num], num);
			}
			for(int m=mCalList.size();m<mGameLines;m++){
				mGameMatrix[i][m].updateNum(images[0], 0);
			}
			mKeyItemNum=-1;
			mCalList.clear();
		}
	}
	
	private void swipeRight(){
		int num=0;
		for(int i=mGameLines-1;i>=0;i--){
			for(int j=mGameLines-1;j>=0;j--){
				int currentNum=mGameMatrix[i][j].getCardNum();
				if(currentNum!=0){
					if(mKeyItemNum==-1){
						mKeyItemNum=currentNum;
					}
					else{
						if(mKeyItemNum==currentNum){
							mCalList.add(mKeyItemNum+1);
							mScore+=level.getItemScore(mKeyItemNum+1);
							mKeyItemNum=-1;
						}else{
							mCalList.add(mKeyItemNum);
							mKeyItemNum=currentNum;
						}
					}
				}else{
					continue;
				}
			}
			if(mKeyItemNum!=-1){
				mCalList.add(mKeyItemNum);
			}
			for(int j=0;j<mGameLines-mCalList.size();j++){
				mGameMatrix[i][j].updateNum(images[0], 0);
			}
			int index=mCalList.size()-1;
			for(int m=mGameLines-mCalList.size();m<mGameLines;m++){
				num=mCalList.get(index);
				mGameMatrix[i][m].updateNum(images[num],num);
				index--;
			}
			mKeyItemNum=-1;
			mCalList.clear();
			index=0;
		}
	}
}
