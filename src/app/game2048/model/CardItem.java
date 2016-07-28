package app.game2048.model;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CardItem extends FrameLayout {
	private int cardNum;
	private int backColor;
	private int textSize;
	private TextView textView;
	private LayoutParams mParams;

	public CardItem(Context context,int cardNum,int backColor,int textSize) {
		super(context);
		// TODO Auto-generated constructor stub
		this.cardNum=cardNum;
		this.textSize=textSize;
		this.backColor=backColor;
		initCardItem();
	}
	
	public void initCardItem(){
		setBackgroundColor(Color.GRAY);
		textView=new TextView(getContext());
		setCardNum(cardNum);
		setBackColor(backColor);
		
		TextPaint textPaint=textView.getPaint();
		textPaint.setFakeBoldText(true);
		textView.setGravity(Gravity.CENTER);
		mParams=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mParams.setMargins(5, 5, 5, 5);
		addView(textView,mParams);
	}

	public void updateNum(String word,int color,int cardNum){
		setCardNum(cardNum);
		this.textView.setText(""+word);
		setBackColor(color);
		int len=word.length();
		if(len<=2)
			this.textView.setTextSize(this.textSize);
		else if(len<=4)
			this.textView.setTextSize(this.textSize-5);
		else if(len<=6)
			this.textView.setTextSize(this.textSize-10);
		else
			this.textView.setTextSize(this.textSize-12);
	}
	
	public int getCardNum() {
		return cardNum;
	}

	private void setCardNum(int cardNum) {
		this.cardNum = cardNum;		
	}
	
	private void setBackColor(int backColor){
		this.backColor=backColor;
		this.textView.setBackgroundColor(backColor);
	}

	public View getTextView() {
		return textView;
	}
	
}
