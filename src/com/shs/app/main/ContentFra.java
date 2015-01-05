package com.shs.app.main;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.shs.app.R;

public class ContentFra extends Fragment{
	private static final String TAG = ContentFra.class.getSimpleName();
	public static ImageView menu;
	private ImageView more;
//	private LinearLayout vp_point_column;
//	private JazzyViewPager vp;
	private ViewPager vp;
	private List<Fragment> fList;
//	private ImageView[] mImageViews;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.contentfra, null);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		menu=(ImageView) getActivity().findViewById(R.id.content_menu);
		more=(ImageView) getActivity().findViewById(R.id.content_more);
		menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainAct.sm.toggle();
			}
		});
		initFraList();
		initVP();
//		initPoint();
	}
//	private void initPoint(){
//		vp_point_column=(LinearLayout) getActivity().findViewById(R.id.vp_point_column);
//		 mImageViews = new ImageView[fList.size()];
//			for (int i = 0; i < mImageViews.length; i++) {
//				ImageView imageView = new ImageView(getActivity());
//				imageView.setLayoutParams(new LayoutParams(10, 10));
//				mImageViews[i] = imageView;
//				if (i == 0) {
//					mImageViews[i].setBackgroundResource(R.drawable.point_blue);
//				} else {
//					mImageViews[i].setBackgroundResource(R.drawable.point_gray);
//				}
//				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//						new LayoutParams(LayoutParams.WRAP_CONTENT,
//								LayoutParams.WRAP_CONTENT));
//				params.gravity = Gravity.CENTER;
//				vp_point_column.addView(imageView, params);
//
//				imageView.setScaleType(ScaleType.CENTER);
//			}
//	}
	private void initFraList(){
		fList=new ArrayList<Fragment>();
		fList.clear();
		fList.add(new GuestRoomFra());
		fList.add(new RestaurantFra());
		fList.add(new MeetingFra());
		fList.add(new HotSpringFra());
		fList.add(new AirCorridorFra());
	}
//	private void initVP(TransitionEffect effect){
//		vp=(JazzyViewPager) getActivity().findViewById(R.id.host_vp);
//		vp.setAdapter(new HostPageAdapter(getFragmentManager(),fList));
////		vp.setTransitionEffect(effect);
//		vp.setFadeEnabled(!vp.getFadeEnabled());
//		vp.setPageMargin(10);
//		vp.setOffscreenPageLimit(6);
//	}
	private void initVP(){
		
		vp=(ViewPager) getActivity().findViewById(R.id.host_vp);
		vp.setAdapter(new HostPageAdapter(getFragmentManager(),fList));
		vp.setOffscreenPageLimit(6);
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
//				for (int i = 0; i <  mImageViews.length; i++) {
//					if (arg0==i) {
//						mImageViews[i].setImageResource(R.drawable.point_blue);
//					} else {
//						mImageViews[i].setImageResource(R.drawable.point_gray);
//					}
//					
//				}
				 switch (arg0) {  
	             case 0:  
	                 MainAct.sm.setTouchModeAbove(  
	                         SlidingMenu.TOUCHMODE_FULLSCREEN);  
	                 break;  
	             default:  
	                 MainAct.sm.setTouchModeAbove(  
	                         SlidingMenu.TOUCHMODE_MARGIN);  
	                 break;  
	             }  
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	class HostPageAdapter extends  FragmentPagerAdapter {
		private List<Fragment> fragment;
		public HostPageAdapter(FragmentManager fm,List<Fragment> fragments) {
			super(fm);
			this.fragment=fragments;
		}

		@Override
		public Fragment getItem(int position) {
			
			return fragment.get(position);
		}

		@Override
		public int getCount() {
			return fragment.size();
		}
	}
}
