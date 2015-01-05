package com.shs.app.springs;

import java.util.ArrayList;
import java.util.List;

import com.shs.app.R;

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

public class QuanBuTuanGouFragment extends Fragment {

	public static QuanBuTuanGouFragment quanBuTuanGouFragment;
	/*
	 * View
	 */
	private View rootView;

	/*
	 * 执行顺序先后 onAttach onCreate :保存恢复数据 onCreateView onActivityCreated 具体操作
	 */
	public static QuanBuTuanGouFragment getInstance(Bundle args) {
		quanBuTuanGouFragment = new QuanBuTuanGouFragment();
		if (args != null) {
			quanBuTuanGouFragment.setArguments(args);
		}
		return quanBuTuanGouFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_hot_spring, null);
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < 20; i++) {
			integers.add(i);
		}
		
		ListView listView = (ListView) rootView.findViewById(R.id.list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.e("asd", "asdkfskjf");
				Intent intent = new Intent(getActivity(),
						DetailSpringActivity.class);
				getActivity().startActivity(intent);
			}
		});
		SpringAdapter adapter = new SpringAdapter(getActivity());
//		adapter.setList(integers);
		listView.setAdapter(adapter);
		return rootView;
	}
}
