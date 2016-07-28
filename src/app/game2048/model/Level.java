package app.game2048.model;

import app.game2048.R;

public final class Level {
	private String[] words;
	private int[] colors;
	private int[] images;
	private int cardSize;
	private int textSize;
	
	public Level(int levelId){
		setTextSize(levelId);
		setCardSize(levelId);
		words=getWords(levelId);
		colors=getColors(levelId);
		images=getImages(levelId);
	}
	
	public int getGoal(String[] words){
		return words.length-1;
	}
	
	public int getImageGoal(int[] images){
		return images.length-1;
	}
	
	public int getCardSize(){
		return this.cardSize;
	}
	
	public int getTextSize(){
		return this.textSize;
	}
	
	
	private final static String[] words1={"","2","4","8","16","32","64","128","256"};//8
	private final static String[] words2={"","��","Ǯ","��","��","��","��","֣","��"};//8
	
	private final static String[] words3={"","121","144","169","196","225","256","289","324","361"};//9
	private final static String[] words4={"","Ѫ�ֵ��о�","��ǩ��","ð��ʷ","����¼","������","��˹��ά������Ȯ","�ֲ���","�������","��̽��"};//9
	private final static String[] words5={"","��","��","��","��","��","��","��","��","��","��","���"};//11
	private final static String[] words6={"","2","4","8","16","32","64","128","256","512","1024","2048"};//11
	
	private final static String[] words7={"","1","2","3","5","8","13","21","34","55","89","144"};//11
	private final static String[] words8={"","��","��","��","Ŀ","�Ǹ�","��","����","��","��","��","��"};//11
	private final static String[] words9={"","ʿ��","�೤","�ų�","����","Ӫ��","�ų�","�ó�","ʦ��","����","˾��","����"};//11
	private final static String[] words10={"","��Ů","��Ӧ","����","����","��","��","����","�ʹ���","�ʺ�","̫��","̫��̫��"};//11
	private final static String[] words11={"","��","��","��","��","��","��","Ԫ","��","��","���","���͹�"};//11	
	private final static String[] words12={"","������","����","ֱ���ɻ�","�߿�����","���ʿռ�վ","ͬ������","���ú�","�϶�2��","�����","�������","�����ߺ�","��ҵ��"};//12
	
	private final static String[] words13={"","��","��","��","î","��","��","��","δ","��","��","��","��","��֧"};//13
	private final static String[] words14={"","H","He","Li","Be","B","C","N","O","F","Ne","Na","Mg","Al","Si","P"};//15
	private final static String[] words15={"","��ԭͻΧ","����ս��","�໯�ս��","������ս��","ǧ��Ծ�����ɽ","����ս��","����ս��","ƽ��ս��","�ɽ�ս��",
			"��Ŵ�����","��Ŵ�����","��ź��ϵ�","�������","��ۻع�","���Żع�","�ڴ����ͳһ"};//16
	private final static String[] words16={"","��","��","��","��","��","����","��","�ϱ���","��","��","���ʮ��","��","Ԫ","��","��","���","���͹�"};//17

	
	
	private final static int[] images1={R.drawable.whiteback,R.drawable.tudors1,R.drawable.tudors2,R.drawable.tudors3,R.drawable.tudors4,R.drawable.tudors5,R.drawable.tudors6,R.drawable.tudors7};//7
	private final static int[] images2={R.drawable.whiteback,R.drawable.java2,R.drawable.java,R.drawable.html,R.drawable.ajax,
			R.drawable.javaee,R.drawable.javaee2,R.drawable.android,R.drawable.swift
			};//8
	private final static int[] images3={R.drawable.whiteback,R.drawable.wonder1,R.drawable.wonder2,R.drawable.wonder3,R.drawable.wonder4,
			R.drawable.wonder5,R.drawable.wonder6,R.drawable.wonder7,R.drawable.wonder8,R.drawable.wonder9};//9
	
	private final static int[] images4={R.drawable.whiteback,R.drawable.musician1,R.drawable.musician2,R.drawable.musician3,R.drawable.musician4,R.drawable.musician5,
			R.drawable.musician6,R.drawable.musician7,R.drawable.musician8,R.drawable.musician9,R.drawable.musician10,R.drawable.musician11};//11
	
	private final static int[] itemScores={1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768,65536,131072,262144,524288,1048576};
	
	private final static int[] colors1={0x00000000,0xffffffff,0xffffffcd,0xffc0c0c0,0xffb0171f,0xffe3170d,0xffff6100,
			0xfff0e68c,0xffffff00,0xff708069,0xff0000ff,0xffff0000,0xff3c4a34
			};
	private final static int[] colors2={0x00000000,0xffeee5db,0xffffffcd,0xfffffaf0,0xffcfcfcf,0xffb0171f,0xff7fffd4,
			0xffbc8f8f,0xffdda0dd,0xffffd700,0xffe3170d,0xffbdfcc9,0xffff6100,0xfff0e68c,0xffffff00,0xff708069,
			0xff0000ff,0xffff0000,0xff3c4a34
			};
	
	public int getItemScore(int itemNum){
		if(itemNum<=2)
			return 0;
		else if(itemNum<=itemScores.length-3)
			return itemScores[itemNum-3];
		else
			return itemScores[itemScores.length-1];
	}
	
	public int[] getImages(int levelId){
		switch(levelId)
		{
		case 13:
			return images1;
		case 14:
			return images2;
		case 15:
			return images3;
		case 16:
			return images4;
		default:
			return images1;
		}
	}
	
	public String[] getWords(int levelId){
		switch(levelId){
		case 1:
			return words1;
		case 2:
			return words2;
		case 3:
			return words3;
		case 4:
			return words4;
		case 5:
			return words5;
		case 6:
			return words6;
		case 7:
			return words7;
		case 8:
			return words8;
		case 9:
			return words9;
		case 10:
			return words10;
		case 11:
			return words11;
		case 12:
			return words12;
		case 17:
			return words13;
		case 18:
			return words14;
		case 19:
			return words15;
		case 20:
			return words16;
		default:
			return words1;
		}
	}
	
	public int[] getColors(int levelId){
		if(levelId<13)
			return colors1;
		else if(levelId>16)
			return colors2;
		else
			return colors1;
		/*
		switch(levelId){
		case 1:
			return colors1;
		case 2:
			return colors1;
		case 3:
			return colors1;
		case 4:
			return colors2;
		case 5:
			return colors1;
		case 6:
			return colors1;
		case 7:
			return colors1;
		case 8:
			return colors2;
		case 9:
			return colors1;
		case 10:
			return colors1;
		case 11:
			return colors1;
		case 12:
			return colors2;
		default:
			return colors1;			
		}*/

	}
	
	private void setTextSize(int levelId){
		if(levelId<13)
			this.textSize=35;
		else if(levelId>16)
			this.textSize=30;
		else
			this.textSize=35;
	}
	
	private void setCardSize(int levelId){
		if(levelId<17)
			this.cardSize=4;
		else
			this.cardSize=5;
	}
	
	
	
}
