package com.shs.app.meals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.app.springs.OrderSpringActivity;
import com.shs.json.bean.ListZaoCanClass;
import com.shs.json.bean.PublicClass;
import com.shs.json.bean.ZaoCanClass;
import com.shs.json.util.DetailMealsJson;
import com.shs.json.util.ZaoCanJson;
import com.tailinkj.manager.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ZaoCanFragment extends Fragment {
	public static ZaoCanFragment springHotFragment;
	private View rootView;
	private	String resultClass="";//列表对象
	private ZaoCanJson zaocan = new ZaoCanJson();// 查询列表
	MealsListAdapter adapter;
	ListView listView ;
	/*
	 * 执行顺序先后 onAttach onCreate :保存恢复数据 onCreateView onActivityCreated 具体操作
	 */
	public static ZaoCanFragment getInstance(Bundle args) {
		springHotFragment = new ZaoCanFragment();
		if (args != null) {
			springHotFragment.setArguments(args);
		}
		return springHotFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_zaocan, null);
	
		GetZaoCanList();
	  
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Button gouwuche = (Button) view.findViewById(R.id.tianjia);
				final TextView idtxt = (TextView) view.findViewById(R.id.zaocan_id);
				gouwuche.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						String idtext=idtxt.getText().toString();
						ZaoCanClass.setGroup_id(idtext);
//					
//						ListZaoCanClass.setZaocanlist(ZaoCanClass);
						Intent intent = new Intent(getActivity(),
								DetailMealsActivity.class);
						getActivity().startActivity(intent);

					}
				});
			
			}
		});
		return rootView;
	}
	//获取早餐列表1
	private void GetZaoCanList() {
		
	}
	

	//获取早餐列表1
//	private void GetZaoCanList()
//	{
//        String eeroinfo="";
//    	List<JSONObject> data_list = new ArrayList<JSONObject>();
//		PublicClass.setPATH(Manager.zaocan1);
//		PublicClass.setToken(Manager.token);
//
//	    resultClass= zaocan.ReturnJsonStr();
//		if(resultClass.equals(""))
//		{
//			 eeroinfo="无法连接服务器，请确认网络是否连接！";
//		}
//		else
//		{
//			try {
//				JSONObject result = new JSONObject( resultClass);  
//				String error = result.getString("error");  
//			
//				if(!error.equals("0"))
//				{
//					 String errorinfo = result.getString("error_info");  
//					 eeroinfo=errorinfo;
//				}
//				else
//				{
//					
//					
//					JSONObject data = result.getJSONObject("data");
//					JSONArray datalist = data.getJSONArray("datalist");
//					
//					for(int i=0;i<datalist.length();i++)
//					{
//						JSONObject datevalue = (JSONObject) datalist.opt(i);
//						data_list.add(datevalue);
//					}
//					
//					    listView = (ListView) rootView.findViewById(R.id.list);
//					    adapter = new MealsListAdapter(getActivity());
//						adapter.setList(data_list);
//						listView.setAdapter(adapter);
//					
//				}
//				
//			} catch (JSONException e) {
//				 eeroinfo="无法连接服务器，请确认网络是否连接！";
//			}
//		}
//	}
}
