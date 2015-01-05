package com.shs.app.kefangyuding;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FangjianInfoListAdapter extends BaseAdapter {
	private Context mContext;
	private List<JSONObject> list;
	LinearLayout typelayout;
	LayoutInflater latoutinflater;
    String typenametemp="";
    private List<String> hosttylename;
    int value=1;
    int valuetemp=0;
    public Boolean falg=false;
    List< Integer> countlist =new ArrayList< Integer>() ;
	
	public void setList(List<JSONObject> l,List<String> HT,  List< Integer> c) {
		this.list = l;
		hosttylename=HT;
		countlist=c;
	
	}

	public FangjianInfoListAdapter(Context mContext) {
		this.mContext = mContext;
		latoutinflater= LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		JSONObject itms=null;
		itms= list.get(position);
		return itms;
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
			convertView = latoutinflater.inflate(
					R.layout.fangjian_info_item, null);
			    typelayout=(LinearLayout) convertView
					.findViewById(R.id.type_layout);
			viewHolder.divider = (ImageView) convertView
					.findViewById(R.id.item_divider);
			viewHolder.type = (TextView) convertView
					.findViewById(R.id.fangjian_type);
			viewHolder.fangjian = (TextView) convertView
					.findViewById(R.id.item_title);
			
			viewHolder.hotel_id = (TextView) convertView
					.findViewById(R.id.hotel_id);
			
			viewHolder.room_type = (TextView) convertView
					.findViewById(R.id.room_type);
			
			viewHolder.item_hint=(TextView) convertView
					.findViewById(R.id.item_hint);
			
			viewHolder.item_price=(TextView) convertView
					.findViewById(R.id.item_price);
			viewHolder.room_id=(TextView) convertView
					.findViewById(R.id.room_id);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			
		}
		JSONObject itms=null;
	
		try {
			
			
			 itms= list.get(position);
		     
					 viewHolder.room_id.setVisibility(View.GONE);
		     
			 viewHolder.hotel_id.setVisibility(View.GONE);
			 viewHolder.room_type.setVisibility(View.GONE);
//		    if(!typenametemp.equals(hosttylename.get(position)))
			 if(itms.getBoolean("IsType") == true)
			 {
		    	viewHolder.divider.setVisibility(View.GONE);
		    	typelayout.setVisibility(View.GONE);
    			viewHolder.type.setVisibility(View.VISIBLE);
    			viewHolder.type.setText(itms.getString("name"));
    			
    			viewHolder.fangjian.setVisibility(View.GONE);
				viewHolder.item_hint.setVisibility(View.GONE);
				viewHolder.item_price.setVisibility(View.GONE);
		
		    }
		    else	
		    {
		    
		    	 String room_name = itms.getString("name");  
		    	
				 String count=itms.getString("count");  
				 String price=itms.getString("price");  
				 String hid=itms.getString("hotel_id");  
				 String room_id=itms.getString("id");  
				 String room_type=itms.getString("room_type");  
				
			     typelayout.setVisibility(View.VISIBLE);
				 viewHolder.hotel_id.setText(hid);
				 viewHolder.room_type.setText(room_type);
				 viewHolder.room_id.setText(room_id);
				 
			     viewHolder.fangjian.setVisibility(View.VISIBLE);
				 viewHolder.item_hint.setVisibility(View.VISIBLE);
				 viewHolder.item_price.setVisibility(View.VISIBLE);
				 
				viewHolder.divider.setVisibility(View.VISIBLE);
				viewHolder.type.setVisibility(View.GONE);
				
				
				viewHolder.fangjian.setText(room_name);
				viewHolder.item_hint.setText("仅剩"+count+"间");
				viewHolder.item_price.setText("￥"+price);
		    }
			
			
			
//			typenametemp=type_name;
//			value=position;
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return convertView;
	}

	private class ViewHolder {
		TextView type,fangjian,item_hint,item_price,hotel_id,room_type,room_id;
		ImageView divider;
	}
}
