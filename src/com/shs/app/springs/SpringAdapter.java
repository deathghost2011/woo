package com.shs.app.springs;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.app.meals.DetailMealsActivity;
import com.shs.json.bean.WenQuanClass;
import com.shs.json.bean.ZaoCanClass;
import com.shs.json.util.ImageLoad;

import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus.NmeaListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SpringAdapter extends BaseAdapter {
	private Context mContext;
	private List<JSONObject> list;
	List<JSONObject> data_list = new ArrayList<JSONObject>();
	public void setList(List<JSONObject> list) {
		this.list = list;
	}

	public SpringAdapter(Context mContext) {
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
					R.layout.spring_item, null);
			viewHolder.imageView = (Button) convertView.findViewById(R.id.woyaocanjia);
			convertView.setTag(viewHolder);
			
			viewHolder.id_text = (TextView) convertView
					.findViewById(R.id.id_text);
			viewHolder.price = (TextView) convertView
					.findViewById(R.id.price);
			viewHolder.item_conent = (TextView) convertView
					.findViewById(R.id.item_conent);
			viewHolder.tv_num = (TextView) convertView
					.findViewById(R.id.tv_num);
			
			viewHolder.item_icon = (ImageView) convertView
					.findViewById(R.id.item_icon);
			
			viewHolder.item_title = (TextView) convertView
					.findViewById(R.id.item_title);
			
			
			
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			
			
		}
		
		JSONObject mark = list.get(position);
		
		try {
			 viewHolder.id_text.setVisibility(View.GONE);
			 viewHolder.price.setVisibility(View.GONE);
			 
			String imageurl=mark.getString("pic");
			viewHolder.item_icon.setImageBitmap(ImageLoad.getimage(imageurl));
			
			String id=mark.getString("id");
			viewHolder.id_text.setText(id);
			
			String price=mark.getString("price");
			viewHolder.price.setText(price);
			
			String   info=mark.getString("info");
			viewHolder.item_conent.setText(info);
			

			String  itemtitle=mark.getString("title");
			viewHolder.item_title.setText(itemtitle);
			
			String  ordercount=mark.getString("order_count");
			viewHolder.tv_num.setText(ordercount);
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		initListener(viewHolder,position);
	
		return convertView;
	}

	private class ViewHolder {
		ImageView item_icon;
		Button imageView;
		TextView item_conent ,tv_num,id_text,price,item_title;
	}
	
	private void initListener(final ViewHolder viewHolder,final int postion) {
	
		
		viewHolder.imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				WenQuanClass.setId(viewHolder.id_text.getText().toString());
				WenQuanClass.setPrice(viewHolder.price.getText().toString());
			
				Intent intent = new Intent(mContext,OrderSpringActivity.class);
				mContext.startActivity(intent);
			}
		});
	}
}
