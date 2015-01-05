package com.shs.app.wedgit.calender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shs.app.R;
import com.shs.app.wedgit.calender.CalendarView.OnDateSelectedListener;

/**
 * 公共组件 -- 日历选择器
 * 
 * @author alaowan
 * 
 *         使用说明： 调用startActivityForResult方法启动该Activity
 *         intent中extra传入key为“time”的字符串（格式为yyyy-MM-dd
 *         HH:mm）作为初始选中日期，不传则默认选中当前日期时间
 *         onActivityResult接收返回结果，resultCode为RESULT_OK
 *         ，intent中key为“result”的string为日期选择结果（格式为yyyy-MM-dd HH:mm）
 * 
 */
@SuppressLint("UseSparseArrays")
public class CalendarActivity extends FragmentActivity implements OnClickListener {

	public static enum DateColumn {
		HOUR, MINUTE
	};

	// 分钟简化模式，分钟只能选择00和30
	public static final String INTENT_KEY_SIMPLE_MINUTE = "simple_minute";

	// 总共支持的页数（每一页是一个月）
	private static final int TOTAL_MONTH_COUNT = 1200;
	// 支持的最早年份
	private static final int FIRST_YEAR = 2012;

	private ViewPager mPager;
	private CalendarPagerAdapter mPagerAdapter;
	// 年月标题栏
	private TextView mTxtMonthTitle;

	// 返回上一页面
//	private View mBack;
	// 确定选择
//	private Button mBtn_right;
	private Button mBtn_ok;
	private String mSelectedDate;

	List<String> mHourList;
	List<String> mMinuteList;
	private LayoutInflater mInflater;
	private View mListLine;
	private ListView mListviewHour;
	private ListView mListviewMinute;
	private String mHour;
	private String mMinute;

	// 是否是分钟简化模式
	private boolean mIsSimpleMinute;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);

		mIsSimpleMinute = getIntent().getBooleanExtra(INTENT_KEY_SIMPLE_MINUTE, false);

		mTxtMonthTitle = (TextView) findViewById(R.id.txt_title_month);
//		mBack = findViewById(R.id.btn_back);
//		mBtn_right = (Button)findViewById(R.id.btn_right);
//		mBtn_right.setBackgroundResource(R.drawable.title_button_right);
//		((TextView) findViewById(R.id.tv_title)).setText("我的日历");
		mBtn_ok = (Button)findViewById(R.id.btn_ok);
		mBtn_ok.setOnClickListener(this);
		mPager = (ViewPager) findViewById(R.id.viewpager);

		/**
		 * 接收启动此Activity时传递的参数，用于初始化默认显示日期
		 */
		Intent intent = getIntent();
		String initDateStr = intent.getStringExtra("time");
		// 如果传递了默认日期，则切换到指定日期，否则默认显示当前日期
		int initIndex = 0;
		if (initDateStr == null || "".equals(initDateStr))
			initDateStr = getCurrentDateTimeStr();
		initIndex = getIndexByDateStr(initDateStr);
		setSelectedDate(initDateStr);

		/**
		 * 初始化ViewPager
		 */
		mPagerAdapter = new CalendarPagerAdapter();
		mPager.setAdapter(mPagerAdapter);
		mPager.setOnPageChangeListener(new PageChangeListener());
		// 切换到指定月份的page
		mPager.setCurrentItem(initIndex);

//		mBtn_right.setOnClickListener(this);
//		mBack.setOnClickListener(this);

		initLists();
		mInflater = LayoutInflater.from(this);
		mListLine = findViewById(R.id.list_line);
		mListviewHour = (ListView) findViewById(R.id.list_hour);
		mListviewHour.setSelector(R.color.transparent);
		mListviewHour.setAdapter(new DateAdapter(DateColumn.HOUR));
		mListviewHour.setOnScrollListener(new DateScrollListener(DateColumn.HOUR));
		mListviewMinute = (ListView) findViewById(R.id.list_minute);
		mListviewMinute.setSelector(R.color.transparent);
		mListviewMinute.setAdapter(new DateAdapter(DateColumn.MINUTE));
		mListviewMinute.setOnScrollListener(new DateScrollListener(DateColumn.MINUTE));
		if (initDateStr.indexOf(":") != -1) {
			setSelectedTime(initDateStr.substring(11));
		}
	}

	public String getSelectedDate() {
		return mSelectedDate;
	}

	public void setSelectedDate(String dateStr) {
		this.mSelectedDate = dateStr;
	}

	class CalendarPagerAdapter extends PagerAdapter {

		Map<Integer, CalendarView> views = new HashMap<Integer, CalendarView>();

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			int[] date = getYearMonthByIndex(position);
			CalendarView calendarView = new CalendarView(CalendarActivity.this, date[0], date[1], getSelectedDate());
			//进入动画
//			calendarView.setAnimation(AnimationUtils.loadAnimation(CalendarActivity.this, R.anim.calender_show));
			calendarView.setOnDateSelectedListener(new OnDateSelectedListener() {
				@Override
				public void onDateSelected(String dateStr) {
					setSelectedDate(dateStr);
				}
			});
			LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			calendarView.setLayoutParams(layoutParams);
			container.addView(calendarView, layoutParams);
			views.put(position, calendarView);
			return calendarView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
			views.remove(position);
		}

		@Override
		public int getCount() {
			return TOTAL_MONTH_COUNT;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

	class PageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {
			// System.out.println("onPageScrollStateChanged state:" + state);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int index) {
			int[] date = getYearMonthByIndex(index);
			mTxtMonthTitle.setText(date[0] + "年" + date[1] + "月");
			CalendarView calendarView = mPagerAdapter.views.get(index);
			if (calendarView != null)
				calendarView.setCurrentSelectedDate(getSelectedDate());
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		case R.id.btn_right:
			/*Intent intentResult = new Intent();
			String dateStr = getSelectedDate();
			if (dateStr.indexOf(":") != -1)
				dateStr = dateStr.substring(0, 10);
			intentResult.putExtra("result", dateStr + " " + getSelectedTime());
			setResult(RESULT_OK, intentResult);
			finish();*/
//			break;
//		case R.id.btn_back:
//			finish();
//			break;
		case R.id.btn_ok:
			Intent intentResult = new Intent();
			String dateStr = getSelectedDate();
			if (dateStr.indexOf(":") != -1)
				dateStr = dateStr.substring(0, 10);
			intentResult.putExtra("result", dateStr + " " + getSelectedTime());
			setResult(RESULT_OK, intentResult);
			finish();
			break;
		default:
			break;
		}
	}

	/**
	 * 根据页号计算出应该显示的年月
	 * 
	 * @param index
	 * @return
	 */
	private int[] getYearMonthByIndex(int index) {
		int[] result = new int[2];
		result[0] = FIRST_YEAR + index / 12;
		result[1] = index % 12 + 1;
		return result;
	}

	/**
	 * 根据指定日期计算出在pager中的页号
	 * 
	 * @param dateStr
	 * @return
	 */
	private int getIndexByDateStr(String dateStr) {
		Date date = Tools.parseDate(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int index = (year - FIRST_YEAR) * 12 + month;
		return index;
	}

	@SuppressLint("SimpleDateFormat")
	private String getCurrentDateTimeStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
	}

	class DateAdapter extends BaseAdapter {

		private DateColumn col;

		public DateAdapter(DateColumn col) {
			this.col = col;
		}

		@Override
		public int getCount() {
			switch (col) {
			case HOUR:
				return mHourList.size();

			case MINUTE:
				return mMinuteList.size();

			}
			return 0;
		}

		@Override
		public Object getItem(int index) {
			switch (col) {
			case HOUR:
				return mHourList.get(index);

			case MINUTE:
				return mMinuteList.get(index);

			}
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup listView) {

			List<String> dataList = null;
			switch (col) {
			case HOUR:
				dataList = mHourList;
				break;
			case MINUTE:
				dataList = mMinuteList;
				break;
			}

			ViewHolder holder = null;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.pub_time_picker_item, null);
				holder = new ViewHolder();
				holder.text = (TextView) convertView.findViewById(R.id.text);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			String selection = (col == DateColumn.HOUR ? mHour : mMinute);
			if (dataList.get(index).equals(selection))
				holder.text.setTextColor(getResources().getColor(R.color.blue));
			else
				holder.text.setTextColor(getResources().getColor(R.color.calendar_time_picker_item_text_gray));
			holder.text.setText(dataList.get(index));
			return convertView;
		}

	}

	class ViewHolder {
		TextView text;
	}

	class DateScrollListener implements OnScrollListener {

		private DateColumn col;

		public DateScrollListener(DateColumn col) {
			this.col = col;
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if (scrollState == SCROLL_STATE_IDLE) {
				fixPosition(view, col);
			}
		}

	}

	private void fixPosition(AbsListView listView, DateColumn col) {
		// 取出listview中可见的那几个View
		ArrayList<View> touchables = listView.getTouchables();
		// 计算出可见的几个View最中间的那个View

		// 获取selection框的top位置
		int lineTop = mListLine.getTop();
		int minTopDiff = 1000;
		View minDiffView = null;
		int direction = 1;
		for (View touchable : touchables) {
			int top = touchable.getTop();
			TextView textView = (TextView) ((LinearLayout) touchable).getChildAt(0);
			textView.setTextColor(getResources().getColor(R.color.calendar_time_picker_item_text_gray));
			if (top > 0 && Math.abs(top - lineTop) < minTopDiff) {
				minTopDiff = Math.abs(top - lineTop);
				minDiffView = touchable;
				if (top - lineTop < 0)
					direction = -1;
				else
					direction = 1;
			}
		}
		// System.out.println("move:" + direction * minTopDiff);
		new SmoothScrollTask().execute(new Object[] { direction * minTopDiff, listView });
		TextView textView = (TextView) ((LinearLayout) minDiffView).getChildAt(0);
		textView.setTextColor(getResources().getColor(R.color.blue));

		String text = textView.getText().toString();
		switch (col) {
		case HOUR:
			this.mHour = text;
			break;
		case MINUTE:
			this.mMinute = text;
			break;
		default:
			break;
		}
	}

	class SmoothScrollTask extends AsyncTask<Object, Integer, Void> {
		int sleep = 5;
		ListView listView;
		int direction;

		@Override
		protected Void doInBackground(Object... params) {
			int totalY = (Integer) params[0];
			listView = (ListView) params[1];
			direction = totalY < 0 ? -1 : 1;
			for (int i = 1; i <= Math.abs(totalY); i++) {
				publishProgress(0);
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			listView.scrollBy(0, direction);
		}

	}

	private void initLists() {
		mHourList = new ArrayList<String>();
		mHourList.add("");
		for (int i = 0; i < 24; i++) {
			mHourList.add(i < 10 ? ("0" + String.valueOf(i)) : String.valueOf(i));
		}
		mHourList.add("");

		mMinuteList = new ArrayList<String>();
		mMinuteList.add("");

		// 分钟简化模式，只显示00和30
		if (mIsSimpleMinute) {
			mMinuteList.add("00");
			mMinuteList.add("30");
		} else {
			for (int i = 0; i < 60; i++) {
				mMinuteList.add(i < 10 ? ("0" + String.valueOf(i)) : String.valueOf(i));
			}
		}
		mMinuteList.add("");
	}

	private void setHour(String hour) {
		this.mHour = hour;
		if (hour.startsWith("0"))
			hour = hour.substring(1);
		int index = Integer.parseInt(hour);
		setTimeSelection(mListviewHour, index);
	}

	private void setMinute(String minute) {
		this.mMinute = minute;
		if (minute.startsWith("0"))
			minute = minute.substring(1);
		int index = Integer.parseInt(minute);
		setTimeSelection(mListviewMinute, index);
	}

	private void setTimeSelection(ListView listView, int index) {
		float density = getResources().getDisplayMetrics().density;
		// 向上偏移5dp（刨除边框的marginTop 5）
		listView.setSelectionFromTop(index, (int) (density * -5));
	}

	public String getSelectedTime() {
		return this.mHour + ":" + this.mMinute;
	}

	private void setSelectedTime(String dateStr) {
		String[] split = dateStr.split(":");
		setHour(split[0]);
		// 分钟简化模式下，如果传过来的分钟数不是00和30，则改为00
		if (mIsSimpleMinute && !split[1].equals("00") && !split[1].equals("30"))
			split[1] = "00";
		setMinute(split[1]);
	}
}
