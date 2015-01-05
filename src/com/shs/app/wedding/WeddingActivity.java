package com.shs.app.wedding;

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

public class WeddingActivity extends FragmentActivity implements OnClickListener {
	/**
	 * 当前点击第几项
	 */
	private int currentIndex = 0;

	private TextView huwai, huwaiGuide, jiaotang, yuding, yudingGuide,
			jiaotangGuide;
	private LinearLayout lhuwai, ljiaotang, lyuding;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private Button left;
	private Button right;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wedding);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("婚礼婚宴");
		initTitleView();
		initViewPager();
	}

	/**
	 * 初始化选项卡菜单
	 */
	private void initTitleView() {
		huwai = (TextView) this.findViewById(R.id.huwai);
		lhuwai = (LinearLayout) this.findViewById(R.id.ll_huanwai);
		lhuwai.setOnClickListener(this);
		huwaiGuide = (TextView) this.findViewById(R.id.huwai_guide);
		jiaotang = (TextView) this.findViewById(R.id.jiaotang);
		ljiaotang = (LinearLayout) this.findViewById(R.id.ll_jiaotang);
		ljiaotang.setOnClickListener(this);
		jiaotangGuide = (TextView) this.findViewById(R.id.jiaotang_guide);
		yuding = (TextView) this.findViewById(R.id.yuding);
		lyuding = (LinearLayout) this.findViewById(R.id.ll_yuding);
		lyuding.setOnClickListener(this);
		yudingGuide = (TextView) this.findViewById(R.id.yuding_guide);
		changTextColor(0);
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		fragmentsList = new ArrayList<Fragment>();
		fragmentsList.add(HuWaiFragment.getInstance(null));
		fragmentsList.add(JiaoTangFragment.getInstance(null));
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
	 *            当前选中
	 */
	private void changTextColor(int curent) {
		intTabView();
		switch (curent) {
		case 0:
			huwaiGuide.setVisibility(View.VISIBLE);
			huwai.setTextColor(getResources().getColor(
					R.color.meeting_title_guide));
			break;

		case 1:
			jiaotangGuide.setVisibility(View.VISIBLE);
			jiaotang.setTextColor(getResources().getColor(
					R.color.meeting_title_guide));
			break;
		case 2:
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
		huwaiGuide.setVisibility(View.INVISIBLE);
		huwai.setTextColor(getResources().getColor(R.color.black));
		jiaotangGuide.setVisibility(View.INVISIBLE);
		jiaotang.setTextColor(getResources().getColor(R.color.black));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_huanwai:
			currentIndex = 0;
			mPager.setCurrentItem(0);
			break;
		case R.id.ll_jiaotang:
			currentIndex = 1;
			mPager.setCurrentItem(1);
			break;
		case R.id.ll_yuding:
			currentIndex = 2;
			mPager.setCurrentItem(2);
			break;

		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:
		}
		changTextColor(currentIndex);
	}
}
