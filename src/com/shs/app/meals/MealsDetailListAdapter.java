package com.shs.app.meals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

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
		radiobuttonmap=new HashMap<Integer, Boolean>();
		checkboxmap=new HashMap<Integer, Boolean>();
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
List<Integer > li=new ArrayList<Integer>();
TreeMap< Integer , Integer> hashMap=new TreeMap<Integer, Integer>();
public static HashMap< Integer , Boolean> radiobuttonmap;
public static HashMap< Integer , Boolean> checkboxmap;
boolean ii=true;
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		RadioButton radioButton = new RadioButton(mContext);
		radioButton.setId(0x1c1b3);
		RadioGroup radiogroup= new RadioGroup(mContext);
//		if (convertView == null) {
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
//		} else {
//			viewHolder = (ViewHolder) convertView.getTag();
//		}
		final JSONObject itms = list.get(position);
		//拿到当前的jsonObject
//		System.out.println(list.get(position));
		 viewHolder.typeid.setVisibility(View.GONE);
		//隐藏两个textview
		 viewHolder.idshicai.setVisibility(View.GONE);
	
		 try {
		  viewHolder.type.setText(itms.getString("Headname"));
		  //设置type
			if(itms.getBoolean("IsType") == true)
			 {
//			判断istype为true
//				li.add(position);
				hashMap.put(position, position);
    			viewHolder.type.setVisibility(View.VISIBLE);
    	
    		  	typelayout.setVisibility(View.GONE);
    			
				viewHolder.breakfast.setVisibility(View.GONE);
				radioButton.setVisibility(View.GONE);
				viewHolder.checkBox.setVisibility(View.GONE);
			 }
			else
			{
//				判断istype为fasle
				 viewHolder.type.setVisibility(View.GONE);
				 typelayout.setVisibility(View.VISIBLE);	
				 
			 
				 if(itms.getBoolean("Iscorr") == true)
				 {
					 if(checkboxmap.get(position)==null){
						 checkboxmap.put(position, false);
					 }
					//true多选 
					 String name=itms.getString("name");  
					 radioButton.setVisibility(View.GONE);
				     viewHolder.breakfast.setText(name);
				     viewHolder.checkBox.setVisibility(View.VISIBLE);
				     if(checkboxmap.get(position)){
				    	 viewHolder.checkBox.setChecked(true);
					  }else{
						  viewHolder.checkBox.setChecked(false);
					  }
				     viewHolder.checkBox.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
//								System.out.println("viewHolder*-----"+position);
								li.clear();
								Iterator it = hashMap.keySet().iterator();  
						        while (it.hasNext()) {  
						            //it.next()得到的是key，tm.get(key)得到obj  
//						            System.out.println(hashMap.get(it.next())+"/////");  
						            li.add(hashMap.get(it.next()));
						        } 
						        li.add(list.size());
						        
								for (int i = 0; i < li.size()-1; i++) {
									if(position>li.get(i)&&position<li.get(i+1)){
										//找到当前的区间
										int ge=0;
										for (int j = li.get(i); j < li.get(i+1); j++) {
//											System.out.println(position+"====="+li.get(i)+"====="+li.get(i+1)+"===="+radiobuttonmap.get(j));
											if(checkboxmap.get(j)!=null&&checkboxmap.get(j)==true){
												ge++;
											}
										}
										if(ge<2){
											if(checkboxmap.get(position)){
												checkboxmap.put(position, false);
											}else{
												checkboxmap.put(position, true);
											}
											
											notifyDataSetChanged();
										}else{
											checkboxmap.put(position, false);
											notifyDataSetChanged();
										}
//										checkboxmap.put(position, true);
//										try {
////											System.out.println(itms.getString("Headname")+"---"+itms.getString("name"));
//										} catch (JSONException e) {
//											// TODO Auto-generated catch block
//											e.printStackTrace();
//										}
										notifyDataSetChanged();
										break;
									}
								}
							
							}
						  });
				 }
				 else
				 {//false单选
					 if(radiobuttonmap.get(position)==null){
						 radiobuttonmap.put(position, false);
					 }
					  String name=itms.getString("name");  
				      viewHolder.checkBox.setVisibility(View.GONE);
					  viewHolder.breakfast.setText(name);
					  if(radiobuttonmap.get(position)){
						  radioButton.setChecked(true);
					  }else{
						  radioButton.setChecked(false);
					  }
					  radioButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
//							System.out.println("read*-----"+position);
							li.clear();
							Iterator it = hashMap.keySet().iterator();  
					        while (it.hasNext()) {  
					            //it.next()得到的是key，tm.get(key)得到obj  
//					            System.out.println(hashMap.get(it.next())+"/////");  
					            li.add(hashMap.get(it.next()));
					        } 
					        li.add(list.size());
							for (int i = 0; i < li.size()-1; i++) {
								if(position>li.get(i)&&position<li.get(i+1)){
									//找到当前的区间
									if(radiobuttonmap.get(position)){
										radiobuttonmap.put(position, false);
									}else{
										radiobuttonmap.put(position, true);
									}
									for (int j = li.get(i); j < li.get(i+1); j++) {
//										System.out.println(position+"====="+li.get(i)+"====="+li.get(i+1)+"===="+radiobuttonmap.get(j));
										if(j!=position){
											radiobuttonmap.put(j, false);
										}
										
									}
									
//									radiobuttonmap.put(position, true);
									try {
										System.out.println(itms.getString("Headname")+"---"+itms.getString("name"));
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									notifyDataSetChanged();
									break;
									
									
								}
							}
						}
					  });
//					  LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//						 params.setMargins(0, 0, 100, 0);
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
