package com.shs.app.springs;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.app.meals.MealsListAdapter;
import com.shs.json.bean.PublicClass;
import com.shs.json.util.WenQuanJson;
import com.shs.json.util.ZaoCanJson;
import com.tailinkj.manager.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SpringHotFragment extends Fragment {
	public static SpringHotFragment springHotFragment;
	private View rootView;
	private	String resultClass="";//列表对象
	private WenQuanJson wenquan = new WenQuanJson();// 查询列表
	ListView listView;
	SpringAdapter adapter ;
	/*
	 * 执行顺序先后 onAttach onCreate :保存恢复数据 onCreateView onActivityCreated 具体操作
	 */
	public static SpringHotFragment getInstance(Bundle args) {
		springHotFragment = new SpringHotFragment();
		if (args != null) {
			springHotFragment.setArguments(args);
		}
		return springHotFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_hot_spring, null);
		GetZaoCanList();
	    listView = (ListView) rootView.findViewById(R.id.list);

		return rootView;
	}
	
	//获取早餐列表
	private void GetZaoCanList()
	{
        String eeroinfo="";
    	List<JSONObject> data_list = new ArrayList<JSONObject>();
		PublicClass.setPATH(Manager.zaocanliebiao2);
		PublicClass.setToken(Manager.token);

	    resultClass= wenquan.ReturnJsonStr();
		if(resultClass.equals(""))
		{
			 eeroinfo="无法连接服务器，请确认网络是否连接！";
		}
		else
		{
			try {
				JSONObject result = new JSONObject( resultClass);  
				String error = result.getString("error");  
			
				if(!error.equals("0"))
				{
					 String errorinfo = result.getString("error_info");  
					 eeroinfo=errorinfo;
				}
				else
				{
					
					
					JSONObject data = result.getJSONObject("data");
					JSONArray datalist = data.getJSONArray("datalist");
					
					for(int i=0;i<datalist.length();i++)
					{
						JSONObject datevalue = (JSONObject) datalist.opt(i);
						data_list.add(datevalue);
					}
					
					    listView = (ListView) rootView.findViewById(R.id.list);
					    adapter = new SpringAdapter(getActivity());
						adapter.setList(data_list);
						listView.setAdapter(adapter);
					
				}
				
			} catch (JSONException e) {
				 eeroinfo="无法连接服务器，请确认网络是否连接！";
			}
		}
	}
}
