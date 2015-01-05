package com.shs.app.wedding;

import java.util.ArrayList;
import java.util.List;

import com.shs.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class YudingFragment extends Fragment {
	public static YudingFragment springHotFragment;
	private View rootView;

	/*
	 * 执行顺序先后 onAttach onCreate :保存恢复数据 onCreateView onActivityCreated 具体操作
	 */
	public static YudingFragment getInstance(Bundle args) {
		springHotFragment = new YudingFragment();
		if (args != null) {
			springHotFragment.setArguments(args);
		}
		return springHotFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_yuding_wedding, null);
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			integers.add(i);
		}
		return rootView;
	}
}
