package com.shs.app.meals;

import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.json.bean.PublicClass;
import com.shs.json.bean.ZaoCanClass;
import com.shs.json.util.DetailMealsJson;

import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus.NmeaListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MealsDetailListAdapter extends BaseAdapter {
	private Context mContext;
	private List<JSONObject> list;
	LinearLayout typelayout;
	
	
	public void setList(List<JSONObject> list) {
		this.list = list;
	}

	public MealsDetailListAdapter(Context mContext) {
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
		RadioButton radioButton = new RadioButton(mContext);
		RadioGroup radiogroup= new RadioGroup(mContext);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.meals_detail_list_item, null);
			viewHolder.type = (TextView) convertView.findViewById(R.id.breakfast_type);
			viewHolder.breakfast = (TextView) convertView.findViewById(R.id.breakfast);
			viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
		
			viewHolder.typeid = (TextView) convertView.findViewById(R.id.typeid);
			viewHolder.idshicai = (TextView) convertView.findViewById(R.id.idshicai);
			typelayout=(LinearLayout) convertView
					.findViewById(R.id.typelayout);
	
		
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		JSONObject itms = list.get(position);
		
		
		 viewHolder.typeid.setVisibility(View.GONE);
		
		 viewHolder.idshicai.setVisibility(View.GONE);
	
		 try {
			 
		  viewHolder.type.setText(itms.getString("Headname"));
			if(itms.getBoolean("IsType") == true)
			 {
			
		  
    			viewHolder.type.setVisibility(View.VISIBLE);
    	
    		  	typelayout.setVisibility(View.GONE);
    			
				viewHolder.breakfast.setVisibility(View.GONE);
				radioButton.setVisibility(View.GONE);
				viewHolder.checkBox.setVisibility(View.GONE);
			 }
			else
			{
			   
				 viewHolder.type.setVisibility(View.GONE);
				 typelayout.setVisibility(View.VISIBLE);	
				 
			 
				 if(itms.getBoolean("Iscorr") == true)
				 {
					 String name=itms.getString("name");  
					
					 radioButton.setVisibility(View.GONE);
				     viewHolder.breakfast.setText(name);
				     viewHolder.checkBox.setVisibility(View.VISIBLE);
				
				 }
				 else
				 {
					  String name=itms.getString("name");  
				      viewHolder.checkBox.setVisibility(View.GONE);
					  viewHolder.breakfast.setText(name);
					  radiogroup.addView(radioButton);
//					  radioButton.setVisibility(View.VISIBLE);
//				
				 }
				 
				 
			

			}
			
			
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		
//		if(mark<10){
//			viewHolder.checkBox.setVisibility(View.VISIBLE);
//			viewHolder.radioButton.setVisibility(View.GONE);
//			if(mark == 0){
//				viewHolder.type.setVisibility(View.VISIBLE);
//				viewHolder.type.setText("鲜榨果汁");
//			}else {
//				viewHolder.type.setVisibility(View.GONE);
//			}
//		}else {
//			viewHolder.checkBox.setVisibility(View.GONE);
//			viewHolder.radioButton.setVisibility(View.VISIBLE);
//			if(mark == 10){
//				viewHolder.type.setVisibility(View.VISIBLE);
//				viewHolder.type.setText("自选面包");
//			}else {
//				viewHolder.type.setVisibility(View.GONE);
//				viewHolder.breakfast.setText("松糕");
//			}
//		}
		 typelayout.addView(radiogroup);
		return convertView;
	}

	private class ViewHolder {
		CheckBox checkBox;
		TextView type,breakfast,typeid,idshicai;
	
	
	}
	
	


	
}
