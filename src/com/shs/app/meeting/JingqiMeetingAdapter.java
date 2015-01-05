package com.shs.app.meeting;

import java.util.List;

import com.shs.app.R;

import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus.NmeaListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class JingqiMeetingAdapter extends BaseAdapter {
	private Context mContext;
	private List<Integer> list;

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public JingqiMeetingAdapter(Context mContext) {
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder(); 
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.jingqi_meeting_item, null);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.woyaocanjia);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext,JingqiMeetingActivity.class);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}

	private class ViewHolder {
		ImageView imageView;
	}
}
