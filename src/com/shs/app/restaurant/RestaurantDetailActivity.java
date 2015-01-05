package com.shs.app.restaurant;

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

public class RestaurantDetailActivity extends FragmentActivity implements OnClickListener {
	/**
	 * 当前点击第几项
	 */
	private int currentIndex = 0;

	private TextView jieshao, jieshaoGuide, yingye, yuding, yudingGuide,
			yingyeGuide,zizhucan,zizhucanGuide,zhaopaicai,zhaopaicaiGuide;
	private LinearLayout ljieshao, lyingye, lyuding,lzhaopaicai,lzizhucan;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private Button left;
	private Button right;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_detail);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("罗曼诺海鲜餐厅");
		initTitleView();
		initViewPager();
	}

	/**
	 * 初始化选项卡菜单
	 */
	private void initTitleView() {
		jieshao = (TextView) this.findViewById(R.id.jieshao);
		ljieshao = (LinearLayout) this.findViewById(R.id.ll_jieshao);
		ljieshao.setOnClickListener(this);
		jieshaoGuide = (TextView) this.findViewById(R.id.jieshao_guide);
		yingye = (TextView) this.findViewById(R.id.yingye);
		lyingye = (LinearLayout) this.findViewById(R.id.ll_yingye);
		lyingye.setOnClickListener(this);
		yingyeGuide = (TextView) this.findViewById(R.id.yingye_guide);
		yuding = (TextView) this.findViewById(R.id.yuding);
		lyuding = (LinearLayout) this.findViewById(R.id.ll_yuding);
		lyuding.setOnClickListener(this);
		yudingGuide = (TextView) this.findViewById(R.id.yuding_guide);
		zhaopaicai = (TextView) this.findViewById(R.id.zhaopaicai);
		lzhaopaicai = (LinearLayout) this.findViewById(R.id.ll_zhaopaicai);
		lzhaopaicai.setOnClickListener(this);
		zhaopaicaiGuide = (TextView) this.findViewById(R.id.zhaopaicai_guide);
		zizhucan = (TextView) this.findViewById(R.id.zizhucan);
		lzizhucan = (LinearLayout) this.findViewById(R.id.ll_zizhucan);
		lzizhucan.setOnClickListener(this);
		zizhucanGuide = (TextView) this.findViewById(R.id.zizhucan_guide);
		changTextColor(0);
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		fragmentsList = new ArrayList<Fragment>();
		fragmentsList.add(jieshaoFragment.getInstance(null));
		fragmentsList.add(YingyeFragment.getInstance(null));
		fragmentsList.add(ZizhucanFragment.getInstance(null));
		fragmentsList.add(ZhaopaicaiFragment.getInstance(null));
		fragmentsList.add(YudingFragment.getInstance(null));
		mPager = (ViewPager) this.findViewById(R.id.vPager);
		mPager.setAdapter(new MyFragmentPagerAdapter(this
				.getSupportFragmentManager(), fragmentsList));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

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
	 *  当前选中
	 */
	private void changTextColor(int curent) {
		intTabView();
		switch (curent) {
		case 0:
			jieshaoGuide.setVisibility(View.VISIBLE);
			jieshao.setTextColor(getResources().getColor(
					R.color.meeting_title_guide));
			break;

		case 1:
			yingyeGuide.setVisibility(View.VISIBLE);
			yingye.setTextColor(getResources().getColor(
					R.color.meeting_title_guide));
			break;
		case 2:
			zizhucanGuide.setVisibility(View.VISIBLE);
			zizhucan.setTextColor(getResources().getColor(
					R.color.meeting_title_guide));
			break;
		case 3:
			zhaopaicaiGuide.setVisibility(View.VISIBLE);
			zhaopaicai.setTextColor(getResources().getColor(
					R.color.meeting_title_guide));
			break;
		case 4:
			yudingGuide.setVisibility(View.VISIBLE);
			yuding.setTextColor(getResources().getColor(
					R.color.meeting_title_guide));
			break;
		default:
			break;
		}
	}

	private void intTabView() {
		yudingGuide.setVisibility(View.INVISIBLE);
		yuding.setTextColor(getResources().getColor(R.color.black));
		jieshaoGuide.setVisibility(View.INVISIBLE);
		jieshao.setTextColor(getResources().getColor(R.color.black));
		yingyeGuide.setVisibility(View.INVISIBLE);
		yingye.setTextColor(getResources().getColor(R.color.black));
		zizhucanGuide.setVisibility(View.INVISIBLE);
		zizhucan.setTextColor(getResources().getColor(R.color.black));
		zhaopaicaiGuide.setVisibility(View.INVISIBLE);
		zhaopaicai.setTextColor(getResources().getColor(R.color.black));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_jieshao:
			currentIndex = 0;
			mPager.setCurrentItem(0);
			break;
		case R.id.ll_yingye:
			currentIndex = 1;
			mPager.setCurrentItem(1);
			break;
		case R.id.ll_zizhucan:
			currentIndex = 2;
			mPager.setCurrentItem(2);
			break;
		case R.id.ll_zhaopaicai:
			currentIndex = 2;
			mPager.setCurrentItem(3);
			break;
		case R.id.ll_yuding:
			currentIndex = 2;
			mPager.setCurrentItem(4);
			break;
		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:
		}
		changTextColor(currentIndex);
	}
}
