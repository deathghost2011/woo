package com.shs.app.personal;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.json.bean.PublicClass;
import com.shs.json.util.MyOrderJson;
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

public class YizhifuFragment extends Fragment {

	public static YizhifuFragment yizhifuFragment;
	MyOrderJson orderjson=new MyOrderJson();
	private List<JSONObject> list= new ArrayList<JSONObject>();
	String resultClass;
	/*
	 * View
	 */
	private View rootView;

	/*
	 * 执行顺序先后 onAttach onCreate :保存恢复数据 onCreateView onActivityCreated 具体操作
	 */
	public static YizhifuFragment getInstance(Bundle args) {
		yizhifuFragment = new YizhifuFragment();
		if (args != null) {
			yizhifuFragment.setArguments(args);
		}
		return yizhifuFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_meiyou_zhifu, null);
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			integers.add(i);
		}
		
		ListView listView = (ListView) rootView.findViewById(R.id.list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Log.e("asd", "asdkfskjf");
////				Intent intent = new Intent(getActivity(),
////						DetailSpringActivity.class);
////				getActivity().startActivity(intent);
			}
		});
		getnopareorderlist();
		OrderYiZhifuAdapter adapter = new OrderYiZhifuAdapter(getActivity());
		adapter.setList(list);
		listView.setAdapter(adapter);
		return rootView;
	}
	
	//显示个人信息
	private void getnopareorderlist()
	{
		
		PublicClass.setPATH(Manager.gerenxinxi1);
		PublicClass.setStatus("已支付");
		resultClass= orderjson.ReturnJsonStr();
	
		if(!resultClass.equals(""))
		{
			try {
				JSONObject result = new JSONObject( resultClass);  
				String error = result.getString("error");  
			
				if(error.equals("0"))
				{
					JSONObject data = new JSONObject("data");  
					JSONArray type_list = data.getJSONArray("order_list");
					for(int i=0;i<type_list.length();i++)
					{
					list.add(type_list.getJSONObject(i));
					}
				}
				
			} catch (JSONException e) {
				
			}
		}

	}
}
