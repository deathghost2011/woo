package com.shs.app.personal;

import java.util.ArrayList;

import android.app.Activity;
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
import android.widget.TextView;

import com.shs.app.R;


public class AllOrderActivity extends FragmentActivity implements OnClickListener{
	/**
	 * 当前点击第几项
	 */
	private int currentIndex = 0;

	private TextView meuyouzhifu,yizhifu,meiyouGuide,yiGuide;

	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private Button left;
	private Button right;
	private TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_order);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("全部订单");
		initTitleView();
		initViewPager();
	}
	
	/**
	 * 初始化选项卡菜单
	 */
	private void initTitleView() {
		yizhifu = (TextView) this.findViewById(R.id.yi_zhifu);
		yizhifu.setOnClickListener(this);
		yiGuide = (TextView) this.findViewById(R.id.yi_zhifu_guide);
		meuyouzhifu = (TextView) this.findViewById(R.id.meiyou_zhifu);
		meuyouzhifu.setOnClickListener(this);
		meiyouGuide = (TextView) this.findViewById(R.id.meiyou_zhifu_guide);
		changTextColor(0);
	}
	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		fragmentsList = new ArrayList<Fragment>();
		fragmentsList.add(MeiyouZhifuFragment.getInstance(null));
		fragmentsList.add(YizhifuFragment.getInstance(null));
		
		mPager = (ViewPager) this.findViewById(R.id.vPager);
		mPager.setAdapter(new MyFragmentPagerAdapter(this.getSupportFragmentManager(), fragmentsList));
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
			meiyouGuide.setVisibility(View.VISIBLE);
			meuyouzhifu.setTextColor(getResources().getColor(R.color.meeting_title_guide));
			break;

		case 1:
			yiGuide.setVisibility(View.VISIBLE);
			yizhifu.setTextColor(getResources().getColor(R.color.meeting_title_guide));
			break;
		default:
			break;
		}
	}
	private void intTabView(){
		meiyouGuide.setVisibility(View.INVISIBLE);
		meuyouzhifu.setTextColor(getResources().getColor(R.color.black));
		yiGuide.setVisibility(View.INVISIBLE);
		yizhifu.setTextColor(getResources().getColor(R.color.black));
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.meiyou_zhifu:
			currentIndex = 0;
			mPager.setCurrentItem(0);
			break;
		case R.id.yi_zhifu:
			currentIndex = 1;
			mPager.setCurrentItem(1);
			break;
		
		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:
		}
		changTextColor(currentIndex);
	}
}
