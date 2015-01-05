package com.shs.app.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.shs.app.R;

public class RestaurantFra extends Fragment{
	private static final String TAG = RestaurantFra.class.getSimpleName();
	private ImageView iv,iv2;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.restaurantfra, null);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		iv=(ImageView) getActivity().findViewById(R.id.restaurant);
		String imgUrl="drawable://" + R.drawable.restaurant;
		iv2=(ImageView) getActivity().findViewById(R.id.restaurant2);
		String imgUrl2="drawable://" + R.drawable.restaurant2;
		MainAct.imageLoader.displayImage(imgUrl, iv);
		MainAct.imageLoader.displayImage(imgUrl2, iv2);
	}
}
