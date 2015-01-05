package com.shs.app.restaurant;

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

public class ZhaopaicaiFragment extends Fragment {
	public static ZhaopaicaiFragment springHotFragment;
	private View rootView;

	/*
	 * 执行顺序先后 onAttach onCreate :保存恢复数据 onCreateView onActivityCreated 具体操作
	 */
	public static ZhaopaicaiFragment getInstance(Bundle args) {
		springHotFragment = new ZhaopaicaiFragment();
		if (args != null) {
			springHotFragment.setArguments(args);
		}
		return springHotFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_restaurant_zhaopaicai, null);
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			integers.add(i);
		}
		ListView listView = (ListView) rootView.findViewById(R.id.list);
		RestaurantZhaopaicaiListAdapter adapter = new RestaurantZhaopaicaiListAdapter(getActivity());
		adapter.setList(integers);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Intent intent = new Intent(getActivity(),
//						DetailMealsActivity.class);
//				getActivity().startActivity(intent);
			}
		});
		return rootView;
	}
}
