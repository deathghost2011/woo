package com.shs.app.personal;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.json.bean.PublicClass;
import com.shs.json.util.UpOrderStateJson;
import com.tailinkj.manager.Manager;

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
import android.widget.Toast;

public class OrderYiZhifuAdapter extends BaseAdapter {
	private Context mContext;
	private List<JSONObject> list;
	
	String resultClass;
	ViewHolder viewHoldercheck = null;
	UpOrderStateJson orderstatejson=new UpOrderStateJson();
	public void setList(List<JSONObject> list) {
		this.list = list;
	}

	public OrderYiZhifuAdapter(Context mContext) {
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
					R.layout.order_yi_zhifu_item, null);
			viewHolder.delete = (TextView) convertView
					.findViewById(R.id.delete_order);
			viewHolder.detail = (TextView) convertView
					.findViewById(R.id.detail_order);
			
			viewHolder.id_order = (TextView) convertView
					.findViewById(R.id.id_order);
			viewHolder.hotel_name = (TextView) convertView
					.findViewById(R.id.hotel_name);
			viewHolder.in_date = (TextView) convertView
					.findViewById(R.id.in_date);
			
			viewHolder.out_date = (TextView) convertView
					.findViewById(R.id.out_date);
			viewHolder.money = (TextView) convertView
					.findViewById(R.id.money);
			viewHolder.pay_money = (TextView) convertView
					.findViewById(R.id.pay_money);
			viewHolder.youhuimony = (TextView) convertView
					.findViewById(R.id.youhuimony);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHoldercheck=viewHolder;
		JSONObject itms=null;
		
		 itms= list.get(position);
	     viewHolder.id_order.setVisibility(View.GONE);
			
		 try {
			 
				String idorder = itms.getString("order_no");
				viewHolder.id_order.setText(idorder);
				
				
			String hostname = itms.getString("hotel_name");
			viewHolder.hotel_name.setText(hostname);
			
			
			
			String indate = itms.getString("start_date");
			viewHolder.in_date.setText(indate);
			
			String outdate = itms.getString("end_date");
			viewHolder.out_date.setText(outdate);
			
			String moneyvalue = itms.getString("money");
			viewHolder.money.setText(moneyvalue);
			
			
			String paymoney = itms.getString("pay_money");
			viewHolder.pay_money.setText(moneyvalue);
			viewHolder.youhuimony.setText("-￥0");
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	     
		
			initListener(viewHolder,position);
			return convertView;
		}
		
		
		private void initListener(final ViewHolder viewHolder,final int postion) {
			viewHolder.delete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						 PublicClass.setPATH(Manager.dingdanshanchu);
					 	 String dingdanid=viewHolder.id_order.getText().toString();
					 	 PublicClass.setOrder_no(dingdanid);
						setorderstate("该订单删除");
						 Intent intent = new
								 Intent(mContext,AllOrderActivity.class);
								 mContext.startActivity(intent);
					}
				});
			viewHolder.detail.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						
				
					 	 String dingdanid=viewHolder.id_order.getText().toString();
						 PublicClass.setOrder_no(dingdanid);
						 Intent intent = new
						 Intent(mContext,OrderDetail.class);
						 mContext.startActivity(intent);
					}
				});
		
		}

	private class ViewHolder {
		TextView delete, detail,id_order,hotel_name,in_date,out_date,money,pay_money,youhuimony;
	}
	
	
	//更新支付状态
		private void setorderstate(String value)
		{
			
			
			resultClass= orderstatejson.ReturnJsonStr();
		
			if(!resultClass.equals(""))
			{
				try {
					JSONObject result = new JSONObject( resultClass);  
					String error = result.getString("error");  
				
					if(error.equals("0"))
					{
						 Toast.makeText(mContext, value+"成功", Toast.LENGTH_LONG).show();
					}
					else
					{
						 Toast.makeText(mContext, value+"失败", Toast.LENGTH_LONG).show();
					}
					
				} catch (JSONException e) {
					
				}
			}

		}
}
