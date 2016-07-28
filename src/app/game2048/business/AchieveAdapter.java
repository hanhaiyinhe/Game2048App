package app.game2048.business;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.game2048.R;
import app.game2048.config.Config;

public class AchieveAdapter extends BaseAdapter {
	private List<String> LevelID;
	private List<String> LevelScore;
 	private LayoutInflater inflater;
 	
 	public AchieveAdapter(Context context,List<String> LevelID ,List<String> LevelScore){
 		inflater = LayoutInflater.from(context);
		this.LevelID=LevelID;
		this.LevelScore=LevelScore;
 	}
 	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return LevelID.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return LevelID.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Achieve_ContantsHolder holder = new Achieve_ContantsHolder();
		if(convertView==null){
			convertView = inflater.inflate(R.layout.list_achieve, null);
			holder.id = (TextView)convertView.findViewById(R.id.achievelist_id_textview);
			holder.score = (TextView)convertView.findViewById(R.id.achievelist_score_textview);
			convertView.setTag(holder);
		}else{
			holder = (Achieve_ContantsHolder)convertView.getTag();
		}
		//typeFace
		holder.id.setTypeface(Config.typeFace);
		holder.score.setTypeface(Config.typeFace);
		//
		holder.id.setText(this.LevelID.get(position));
		holder.score.setText(this.LevelScore.get(position));

		return convertView;
	}
	
	public class Achieve_ContantsHolder
	{
		private TextView id;
		private TextView score;
	}

}
