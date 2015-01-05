package com.shs.app.meals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.json.bean.ZaoCanClass;
import com.shs.json.util.ImageLoad;

import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus.NmeaListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MealsListAdapter extends BaseAdapter {
	private Context mContext;
	private List<JSONObject> list;
	List<JSONObject> data_list = new ArrayList<JSONObject>();


	public void setList(List<JSONObject> list) {
		this.list = list;
	}

	public MealsListAdapter(Context mContext) {
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
					R.layout.meals_list_item, null);
			viewHolder.tianjia = (Button) convertView
					.findViewById(R.id.tianjia);
			viewHolder.type = (TextView) convertView
					.findViewById(R.id.zaocan_type);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.item_icon);
			viewHolder.etTotal = (EditText) convertView
					.findViewById(R.id.et_total);
			viewHolder.btnDesc = (Button) convertView
					.findViewById(R.id.btn_desc);
			viewHolder.btnAdd = (Button) convertView.findViewById(R.id.btn_add);
			
			viewHolder.item_title = (TextView) convertView
					.findViewById(R.id.item_title);
			
			
			viewHolder.item_price = (TextView) convertView
					.findViewById(R.id.item_price);
			
			viewHolder.zaocan_id = (TextView) convertView
					.findViewById(R.id.zaocan_id);
			
			
			viewHolder.imgurl = (TextView) convertView
					.findViewById(R.id.zaocan_imageurl);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		JSONObject mark = list.get(position);
		
		try {
			 viewHolder.zaocan_id.setVisibility(View.GONE);
			 viewHolder.imgurl.setVisibility(View.GONE);
			String imageurl=mark.getString("pic");
			viewHolder.imgurl.setText(imageurl);
			String id=mark.getString("id");
			String name=mark.getString("name");
			String enname=mark.getString("en_name");
			String price=mark.getString("price");
			viewHolder.zaocan_id.setText(id);
			viewHolder.type.setText(name);
			viewHolder.imageView.setImageBitmap(ImageLoad.getimage(imageurl));
			viewHolder.item_title.setText(enname);
			viewHolder.item_price.setText("￥"+price);
			viewHolder.etTotal.setText("1");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		switch (mark) {
//		case 0:
//			break;
//		case 1:
//			viewHolder.imageView.setBackgroundResource(R.drawable.usa);
//			viewHolder.type.setText("美式早餐");
//			break;
//		case 2:
//			viewHolder.imageView.setBackgroundResource(R.drawable.china);
//			viewHolder.type.setText("中式早餐");
//			break;
//		default:
//			break;
//		}
//		
		
		initListener(viewHolder,position);
		return convertView;
	}

	private class ViewHolder {
		TextView type,item_title,item_price,zaocan_id,imgurl;
		EditText etTotal;
		ImageView imageView;
		Button tianjia, btnDesc, btnAdd;
	}

	private void initListener(final ViewHolder viewHolder,final int postion) {
		viewHolder.tianjia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				ZaoCanClass.setGroup_id(viewHolder.zaocan_id.getText().toString());
				ZaoCanClass.setFenshu(viewHolder.etTotal.getText().toString());
				ZaoCanClass.setPrice(viewHolder.item_price.getText().toString().replace("￥", ""));
				ZaoCanClass.setTitle(viewHolder.type.getText().toString());
				ZaoCanClass.setImageurl(viewHolder.imgurl.getText().toString());
				//save
				Intent intent = new Intent(mContext,
						DetailMealsActivity.class);
				mContext.startActivity(intent);
	
			}
		});
		viewHolder.btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int total =Integer.valueOf(viewHolder.etTotal.getText().toString());
				total += 1;
				viewHolder.etTotal.setText(String.valueOf(total));
				ZaoCanClass.setFenshu(viewHolder.etTotal.getText().toString());
				ZaoCanClass.setPrice(viewHolder.item_price.getText().toString().replace("￥", ""));
			}
		});
		viewHolder.btnDesc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int total =Integer.valueOf(viewHolder.etTotal.getText().toString());
				if (total > 1) {
					total -= 1;
					viewHolder.etTotal.setText(String.valueOf(total));
					ZaoCanClass.setFenshu(viewHolder.etTotal.getText().toString());
					ZaoCanClass.setPrice(viewHolder.item_price.getText().toString().replace("￥", ""));
				}

			}
		});
	}
}
