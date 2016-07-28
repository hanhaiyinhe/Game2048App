package app.game2048.model;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;

import android.widget.ImageView;

public class CardImageItem extends FrameLayout {
	private int cardNum;
	private int backImage;
	private ImageView imageView;
	private LayoutParams mParams;
	
	public CardImageItem(Context context,int cardNum,int backImage) {
		super(context);
		// TODO Auto-generated constructor stub
		this.cardNum=cardNum;
		this.backImage=backImage;
		initCardItem();
	}

	
	public void initCardItem(){
		setBackgroundColor(Color.GRAY);
		imageView=new ImageView(getContext());
		setCardNum(cardNum);
		setBackImage(this.backImage);		
		
		mParams=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mParams.setMargins(5, 5, 5, 5);
		addView(imageView,mParams);
	}

	public void updateNum(int backImage,int cardNum){
		setCardNum(cardNum);
		setBackImage(backImage);
	}
	
	public int getCardNum() {
		return cardNum;
	}

	private void setCardNum(int cardNum) {
		this.cardNum = cardNum;		
	}
	
	private void setBackImage(int backImage){
		this.imageView.setBackgroundResource(backImage);
		//this.imageView.setImageResource(backImage);
	}

	public View getImageView() {
		return imageView;
	}
}
