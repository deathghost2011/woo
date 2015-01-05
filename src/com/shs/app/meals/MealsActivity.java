package com.shs.app.meals;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shs.app.R;

public class MealsActivity extends FragmentActivity implements OnClickListener {
	/**
	 * 当前点击第几项
	 */
	private int currentIndex = 0;

	private TextView zoacan, zaocanGuide, quantian, jiushui, jiushuiGuid,
			quantianGuid;
	private LinearLayout lzaocan, lquantian, ljiushui;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private Button left;
	private Button right;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meals);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("客房送餐");
		initTitleView();
		initViewPager();
		
	}
@Override
protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();

}
	/**
	 * 初始化选项卡菜单
	 */
	private void initTitleView() {
		zoacan = (TextView) this.findViewById(R.id.zaocan);
		lzaocan = (LinearLayout) this.findViewById(R.id.ll_zaocan);
		lzaocan.setOnClickListener(this);
		zaocanGuide = (TextView) this.findViewById(R.id.zaocan_guide);
		quantian = (TextView) this.findViewById(R.id.quantian);
		lquantian = (LinearLayout) this.findViewById(R.id.ll_quantian);
		lquantian.setOnClickListener(this);
		quantianGuid = (TextView) this.findViewById(R.id.quantian_guide);
		jiushui = (TextView) this.findViewById(R.id.jiushui);
		ljiushui = (LinearLayout) this.findViewById(R.id.ll_jiushui);
		ljiushui.setOnClickListener(this);
		jiushuiGuid = (TextView) this.findViewById(R.id.jiushui_guide);
		changTextColor(0);
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		fragmentsList = new ArrayList<Fragment>();
		fragmentsList.add(ZaoCanFragment.getInstance(null));
//		fragmentsList.add(QuantianFragment.getInstance(null));
//		fragmentsList.add(JiuShuiFragment.getInstance(null));
		mPager = (ViewPager) this.findViewById(R.id.vPager);
		mPager.setAdapter(new MyFragmentPagerAdapter(this
				.getSupportFragmentManager(), fragmentsList));
		mPager.setCurrentItem(0);
//		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

	}

	class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		private ArrayList<Fragment> list;

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public MyFragmentPagerAdapter(FragmentManager fragmentManager,
				ArrayList<Fragment> list) {
			super(fragmentManager);
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			currentIndex = arg0;
			changTextColor(currentIndex);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	}

	/**
	 * @param curent
	 *            当前选中
	 */
	private void changTextColor(int curent) {
		intTabView();
		switch (curent) {
		case 0:
			zaocanGuide.setVisibility(View.VISIBLE);
			zoacan.setTextColor(getResources().getColor(
					R.color.meeting_title_guide));
			break;

		case 1:
			quantianGuid.setVisibility(View.VISIBLE);
			quantian.setTextColor(getResources().getColor(
					R.color.meeting_title_guide));
			break;
		case 2:
			jiushuiGuid.setVisibility(View.VISIBLE);
			jiushui.setTextColor(getResources().getColor(
					R.color.meeting_title_guide));
			break;
		default:
			break;
		}
	}

	private void intTabView() {
		jiushuiGuid.setVisibility(View.INVISIBLE);
		jiushui.setTextColor(getResources().getColor(R.color.black));
		zaocanGuide.setVisibility(View.INVISIBLE);
		zoacan.setTextColor(getResources().getColor(R.color.black));
		quantianGuid.setVisibility(View.INVISIBLE);
		quantian.setTextColor(getResources().getColor(R.color.black));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_zaocan:
			currentIndex = 0;
			mPager.setCurrentItem(0);
			break;
		case R.id.ll_quantian:
//			currentIndex = 1;
//			mPager.setCurrentItem(1);
			break;
		case R.id.ll_jiushui:
//			currentIndex = 2;
//			mPager.setCurrentItem(2);
			break;

		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:
		
			break;
		}
		changTextColor(currentIndex);
	}
}
