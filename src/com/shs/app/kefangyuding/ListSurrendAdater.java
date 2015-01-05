package com.shs.app.kefangyuding;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shs.app.R;


public class ListSurrendAdater  extends BaseAdapter {
	private Context mContext;
	private List<JSONObject> list;
	LinearLayout typelayout;
	LayoutInflater latoutinflater;


	public void setList(List<JSONObject> l) {
		this.list = l;
		
	}

	public ListSurrendAdater(Context mContext) {
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
					R.layout.list_sund, null);
	
			viewHolder.text = (TextView) convertView
					.findViewById(R.id.text);
		
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			
		}
		JSONObject itms=null;
	
		try {
			
			
		 itms= list.get(position);
		 viewHolder.text.setText(itms.getString("name"));
				
				
			
			
			
//			typenametemp=type_name;
//			value=position;
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return convertView;
	}

	private class ViewHolder {
		TextView text;
	}
}
