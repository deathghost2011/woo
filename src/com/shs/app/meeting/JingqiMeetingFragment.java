package com.shs.app.meeting;


import java.util.ArrayList;
import java.util.List;

import com.shs.app.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
public class JingqiMeetingFragment extends Fragment {
	public static JingqiMeetingFragment jinqiMeetingFragment;
	private View rootView;
	/*
	 * 执行顺序先后
	 * onAttach 
	 * onCreate :保存恢复数据
	 * onCreateView
	 * onActivityCreated 具体操作
	 */
	public static JingqiMeetingFragment getInstance(Bundle args) {
		jinqiMeetingFragment = new JingqiMeetingFragment();
		if (args != null) {
			jinqiMeetingFragment.setArguments(args);
		}
		return jinqiMeetingFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.fragment_jingqi_meeting ,null);
		List<Integer> integers = new ArrayList<Integer>();
		for(int i= 0;i<20;i++){
			integers.add(i);
		}
		ListView listView = (ListView) rootView.findViewById(R.id.list);
		JingqiMeetingAdapter adapter = new JingqiMeetingAdapter(getActivity());
		adapter.setList(integers);
		listView.setAdapter(adapter);
		return rootView;
	}
}
