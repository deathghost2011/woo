package com.shs.app.meeting;


import java.util.ArrayList;

import com.shs.app.R;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class MeetingFragmentPre extends Fragment implements OnClickListener{
	
	/**
	 * 当前点击第几项
	 */
	private int currentIndex = 0;

	private TextView jingqiMeeting,jingqiMeetingGuide, jieshaoMeeting, jieshaoMeetingGuide,canMeeting,canMeetingGuide,banMeeting,banMeetingGuide;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private Button left;
	private Button right;
	private TextView title;
	/*
	 * View
	 */
	private View rootView;
	
	/*
	 * 执行顺序先后
	 * onAttach 
	 * onCreate :保存恢复数据
	 * onCreateView
	 * onActivityCreated 具体操作
	 */
	public static MeetingFragmentPre getInstance(Bundle args) {
		MeetingFragmentPre creditFragment = new MeetingFragmentPre();
		if (args != null) {
			creditFragment.setArguments(args);
		}
		return creditFragment;
	}
	
	@Override
	public void onAttach(Activity activity) {
//		callBack = (CallBack) activity;
		super.onAttach(activity);
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.fragment_meeting_pre, container, false);
		left = (Button) rootView.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) rootView.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) rootView.findViewById(R.id.pub_title);
		title.setText("会议邀请");
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initTitleView(rootView);
		initViewPager(rootView);
	}
	
	/**
	 * 初始化选项卡菜单
	 */
	private void initTitleView(View view) {
		jieshaoMeeting = (TextView) view.findViewById(R.id.meeting_jieshao);
		jieshaoMeeting.setOnClickListener(this);
		jieshaoMeetingGuide = (TextView) view.findViewById(R.id.meeting_jieshao_guide);
		jingqiMeeting = (TextView) view.findViewById(R.id.jinqi_meeting);
		jingqiMeeting.setOnClickListener(this);
		jingqiMeetingGuide = (TextView) view.findViewById(R.id.jinqi_meeting_guide);
//		canMeeting = (TextView) view.findViewById(R.id.canjia_meeting);
//		canMeeting.setOnClickListener(this);
//		canMeetingGuide = (TextView) view.findViewById(R.id.canjia_meeting_guide);
		banMeeting = (TextView) view.findViewById(R.id.ban_meeting);
		banMeeting.setOnClickListener(this);
		banMeetingGuide = (TextView) view.findViewById(R.id.ban_meeting_guide);
		changTextColor(0);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.jinqi_meeting:
			currentIndex = 0;
			mPager.setCurrentItem(0);
			break;
		case R.id.meeting_jieshao:
			currentIndex = 1;
			mPager.setCurrentItem(1);
			break;
//		case R.id.canjia_meeting:
//			currentIndex = 2;
//			mPager.setCurrentItem(2);
//			break;
		case R.id.ban_meeting:
			currentIndex = 2;
			mPager.setCurrentItem(2);
			break;
		case R.id.btn_left:
			getActivity().finish();
			break;
		case R.id.btn_right:
		}
		changTextColor(currentIndex);
	}

	/**
	 * @param curent
	 *            当前选中
	 */
	private void changTextColor(int curent) {
		intTabView();
		switch (curent) {
		case 0:
			jingqiMeetingGuide.setVisibility(View.VISIBLE);
			jingqiMeeting.setTextColor(getResources().getColor(R.color.meeting_title_guide));
			break;

		case 1:
			jieshaoMeetingGuide.setVisibility(View.VISIBLE);
			jieshaoMeeting.setTextColor(getResources().getColor(R.color.meeting_title_guide));
			break;
//		case 2:
//			canMeetingGuide.setVisibility(View.VISIBLE);
//			canMeeting.setTextColor(getResources().getColor(R.color.meeting_title_guide));
//			break;
		case 2:
			banMeetingGuide.setVisibility(View.VISIBLE);
			banMeeting.setTextColor(getResources().getColor(R.color.meeting_title_guide));
			break;
		default:
			break;
		}
	}
	
	private void intTabView(){
		jingqiMeetingGuide.setVisibility(View.INVISIBLE);
		jingqiMeeting.setTextColor(getResources().getColor(R.color.black));
		jieshaoMeetingGuide.setVisibility(View.INVISIBLE);
		jieshaoMeeting.setTextColor(getResources().getColor(R.color.black));
//		canMeetingGuide.setVisibility(View.INVISIBLE);
//		canMeeting.setTextColor(getResources().getColor(R.color.black));
		banMeetingGuide.setVisibility(View.INVISIBLE);
		banMeeting.setTextColor(getResources().getColor(R.color.black));
	}
	/**
	 * 初始化ViewPager
	 */
	private void initViewPager(View view) {
		fragmentsList = new ArrayList<Fragment>();
		fragmentsList.add(JingqiMeetingFragment.getInstance(null));
		fragmentsList.add(MeetingJieshaoFragment.getInstance(null));
//		fragmentsList.add(CanMeetingFragment.getInstance(null));
		fragmentsList.add(BanMeetingFragment.getInstance(null));
		
		mPager = (ViewPager) view.findViewById(R.id.vPager);
		mPager.setAdapter(new MyFragmentPagerAdapter(getFragmentManager(),fragmentsList));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

	}

	class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		private ArrayList<Fragment> list;

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public MyFragmentPagerAdapter(FragmentManager fm,
				ArrayList<Fragment> list) {
			super(fm);
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
}
