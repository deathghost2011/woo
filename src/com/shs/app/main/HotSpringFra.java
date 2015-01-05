package com.shs.app.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.shs.app.R;

public class HotSpringFra extends Fragment{
	private static final String TAG = HotSpringFra.class.getSimpleName();
	private ImageView iv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.hotspringfra, null);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		iv=(ImageView) getActivity().findViewById(R.id.hotspring);
		String imgUrl="drawable://" + R.drawable.hotspring;
		MainAct.imageLoader.displayImage(imgUrl, iv);
	}
}
