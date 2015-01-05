package com.shs.app.restaurant;

import java.util.HashMap;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantZhaopaicaiListAdapter extends BaseAdapter {
	private Context mContext;
	private List<Integer> list;
	private HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public RestaurantZhaopaicaiListAdapter(Context mContext) {
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
					R.layout.restaurant_zhaopaicai_list_item, null);
			viewHolder.tianjia = (Button) convertView
					.findViewById(R.id.tianjia);
			viewHolder.etTotal = (EditText) convertView
					.findViewById(R.id.et_total);
			viewHolder.btnDesc = (Button) convertView
					.findViewById(R.id.btn_desc);
			viewHolder.btnAdd = (Button) convertView.findViewById(R.id.btn_add);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		hashMap.put(position, 0);
		initListener(viewHolder,position);
		return convertView;
	}

	private class ViewHolder {
		TextView type;
		EditText etTotal;
		Button tianjia, btnDesc, btnAdd;
	}
	private void initListener(final ViewHolder viewHolder,final int postion) {
		viewHolder.tianjia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new
				// Intent(mContext,OrderSpringActivity.class);
				// mContext.startActivity(intent);
			}
		});
		viewHolder.btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int total = hashMap.get(postion);
				total += 1;
				viewHolder.etTotal.setText(String.valueOf(total));
				hashMap.remove(postion);
				hashMap.put(postion, total);

			}
		});
		viewHolder.btnDesc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int total = hashMap.get(postion);
				if (total > 0) {
					total -= 1;
					viewHolder.etTotal.setText(String.valueOf(total));
					hashMap.remove(postion);
					hashMap.put(postion, total);
				}

			}
		});
	}
}
