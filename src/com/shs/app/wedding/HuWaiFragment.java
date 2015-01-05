package com.shs.app.wedding;

import java.util.ArrayList;
import java.util.List;

import com.shs.app.R;
import com.shs.app.springs.DetailSpringActivity;
import com.shs.app.springs.OrderSpringActivity;
import com.shs.app.springs.SpringAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class HuWaiFragment extends Fragment {
	public static HuWaiFragment springHotFragment;
	private View rootView;

	/*
	 * 执行顺序先后 onAttach onCreate :保存恢复数据 onCreateView onActivityCreated 具体操作
	 */
	public static HuWaiFragment getInstance(Bundle args) {
		springHotFragment = new HuWaiFragment();
		if (args != null) {
			springHotFragment.setArguments(args);
		}
		return springHotFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_huwai_wedding, null);
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < 20; i++) {
			integers.add(i);
		}
		
		ListView listView = (ListView) rootView.findViewById(R.id.list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(),
						WeddingDetailActivity.class);
				getActivity().startActivity(intent);
			}
		});
		HuwaiWeddingAdapter adapter = new HuwaiWeddingAdapter(getActivity());
		adapter.setList(integers);
		listView.setAdapter(adapter);
		return rootView;
	}
}
