package com.shs.app.meeting;

import java.util.ArrayList;
import java.util.List;

import com.shs.app.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
public class BanMeetingFragment extends Fragment{

public static BanMeetingFragment banMeetingFragment;
	/*
	 * View
	 */
	private View rootView;
	
	/*
	 * 执行顺序先后
	 * onAttach 
	 * onCreate :保存恢复数据
	 * onCreateView
	 * onActivityCreated 具体操作
	 */
	public static BanMeetingFragment getInstance(Bundle args) {
		banMeetingFragment = new BanMeetingFragment();
		if (args != null) {
			banMeetingFragment.setArguments(args);
		}
		return banMeetingFragment;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_ban_meeting, null);
		GridView gridView = (GridView) rootView.findViewById(R.id.gv);
		List<Integer> integers = new ArrayList<Integer>();
		for(int i= 0;i<30;i++){
			integers.add(i);
		}
		BanMeetingAdapter adapter = new BanMeetingAdapter(getActivity());
		adapter.setItemList(integers);
		gridView.setAdapter(adapter);
		return rootView;
	}
}
