package com.shs.app.meeting;

import java.util.List;

import com.shs.app.R;


import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BanMeetingAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private List<Integer> itemList;
	public BanMeetingAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public List<Integer> getItemList() {
		return itemList;
	}

	public void setItemList(List<Integer> itemList) {
		this.itemList = itemList;
	}

	@Override
	public int getCount() {
		return itemList == null ? 0 : itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.ban_meeting_item,
					null);
			vh = new ViewHolder();
			vh.apply = (ImageView) convertView.findViewById(R.id.lijishenqing);
			vh.icon = (ImageView) convertView.findViewById(R.id.item_icon);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		int current = itemList.get(position);
		if(current%3 == 0){
			vh.icon.setBackgroundResource(R.drawable.meet_pic1);
		}else if(current%3 == 1){
			vh.icon.setBackgroundResource(R.drawable.meet_pic2);
		}else {
			vh.icon.setBackgroundResource(R.drawable.meet_pic3);
		}
		vh.apply.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,BanMeetingActivity.class);
				context.startActivity(intent);
			}
		});
		return convertView;
	}
	private class ViewHolder {
		private ImageView apply,icon;
	}

}
