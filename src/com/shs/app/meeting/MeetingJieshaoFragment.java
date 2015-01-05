package com.shs.app.meeting;

import com.shs.app.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class MeetingJieshaoFragment extends Fragment{

public static MeetingJieshaoFragment meetingJieshaoFragment;
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
	public static MeetingJieshaoFragment getInstance(Bundle args) {
		meetingJieshaoFragment = new MeetingJieshaoFragment();
		if (args != null) {
			meetingJieshaoFragment.setArguments(args);
		}
		return meetingJieshaoFragment;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_meeting_jieshao, null);
		return rootView;
	}
}
