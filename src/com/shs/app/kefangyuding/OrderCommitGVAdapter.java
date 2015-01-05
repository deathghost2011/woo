package com.shs.app.kefangyuding;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.shs.app.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.GpsStatus.NmeaListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderCommitGVAdapter extends BaseAdapter {
	private Context mContext;
	private List<String> list =new ArrayList<String>();

	public void setList(List<String> list) {
		this.list = list;
	}

	public OrderCommitGVAdapter(Context mContext) {
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
					R.layout.order_commit_gv_item, null);
			viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
	
		String	itms= list.get(position);
		URL url = null;
		try {
			url = new URL(itms);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			viewHolder.iv.setImageBitmap(BitmapFactory.decodeStream(url.openStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		return convertView;
	}

	private class ViewHolder {
		ImageView iv;
	}
}
