package com.shs.app.meals;

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

public class JiuShuiFragment extends Fragment {
	public static JiuShuiFragment springHotFragment;
	private View rootView;

	/*
	 * 执行顺序先后 onAttach onCreate :保存恢复数据 onCreateView onActivityCreated 具体操作
	 */
	public static JiuShuiFragment getInstance(Bundle args) {
		springHotFragment = new JiuShuiFragment();
		if (args != null) {
			springHotFragment.setArguments(args);
		}
		return springHotFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_zaocan, null);
		rootView = inflater.inflate(R.layout.fragment_zaocan, null);
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			integers.add(i);
		}
		ListView listView = (ListView) rootView.findViewById(R.id.list);
		MealsListAdapter adapter = new MealsListAdapter(getActivity());
//		adapter.setList(integers);listView.setAdapter(adapter);
		return rootView;
	}
}
