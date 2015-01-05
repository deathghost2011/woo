package com.shs.app.meeting;

import com.shs.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class CanMeetingFragment  extends Fragment{

public static CanMeetingFragment canMeetingFragment;
	
	private View rootView;
	
	/*
	 * 执行顺序先后
	 * onAttach 
	 * onCreate :保存恢复数据
	 * onCreateView
	 * onActivityCreated 具体操作
	 */
	public static CanMeetingFragment getInstance(Bundle args) {
		canMeetingFragment = new CanMeetingFragment();
		if (args != null) {
			canMeetingFragment.setArguments(args);
		}
		return canMeetingFragment;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_yuding_wedding, null);
		return rootView;
	}

}
