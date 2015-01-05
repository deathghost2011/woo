/**
 * 
 */
package com.shs.app.shehuishan;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.json.bean.LoginClass;
import com.shs.json.bean.PublicClass;
import com.shs.json.util.HotelListJson;
import com.shs.json.util.LoginJson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class IntroduceActivity extends Activity implements OnTouchListener,OnClickListener {
	private String TAG = "IntroduceActivity";
	private Button left;
	private Button right;
	private TextView title;
	private ViewPager viewPager;
	// 滑动的图片集合
	private List<ImageView> imageViews = new ArrayList<ImageView>();
	// 图片ID
	private int[] imageId;
	// 图片标题正文的那些点
	private List<View> dots = new ArrayList<View>();
	// 当前图片的索引号
	private int currentItem = 0;
	private Timer timer;
	private Handler handler;
	private LinearLayout layout;
	private LinearLayout.LayoutParams layoutParams;
	private MyAdapter adapter = new MyAdapter();
	// 下载的图片集合
	private FrameLayout fl;
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_introducel);
		init();
	
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
				}
			}
		};
	}
	
	
	
	

	private void init() {
		imageId = new int[] { R.drawable.jd_pic1, R.drawable.jd_pic4,
				R.drawable.jd_pic5, R.drawable.shipin };
		layout = (LinearLayout) this.findViewById(R.id.ll);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("社会山介绍");
		fl = (FrameLayout) this.findViewById(R.id.fl);
		fl.setOnTouchListener(this);
		fl.setOnClickListener(this);
		imageViews = new ArrayList<ImageView>();
		// 初始化图片资源
		for (int i = 0; i<imageId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageId[i]);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageViews.add(imageView);
			layoutParams = new LinearLayout.LayoutParams(
					(int) TypedValue.applyDimension(
							TypedValue.COMPLEX_UNIT_DIP, 5, getResources()
									.getDisplayMetrics()),
					(int) TypedValue.applyDimension(
							TypedValue.COMPLEX_UNIT_DIP, 5, getResources()
									.getDisplayMetrics()));
			layoutParams.setMargins((int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 5, getResources()
							.getDisplayMetrics()), 0, (int) TypedValue
					.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
							getResources().getDisplayMetrics()), 0);
			View view = new View(this);
			view.setBackgroundResource(R.drawable.dot_normal);
			view.setLayoutParams(layoutParams);
			layout.addView(view);
			dots.add(view);
		}
		viewPager = (ViewPager) findViewById(R.id.vp);
		// 设置填充ViewPager页面的适配器
		viewPager.setAdapter(adapter);
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());

	}
@Override
protected void onResume() {
	super.onResume();
	timer = new Timer();
	timer.schedule(new TimerTask() {
		@Override
		public void run() {
			currentItem = (currentItem + 1) % imageViews.size();
			handler.sendEmptyMessage(1);
		}
	}, 0, 3000);
}
	@Override
	protected void onStart() {
		super.onStart();
	
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 当Activity不可见的时候停止切换
		if(timer != null){
				timer.cancel();}
	}
	@Override
	protected void onStop() {
		super.onStop();
		
	}

	/**
	 * 
	 * @author 杨华雄
	 * @create 2014-7-23：下午5:26:10 @ 当ViewPager中页面的状态发生改变时调用
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		public void onPageSelected(int position) {
			currentItem = position;
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	private class MyAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return imageViews == null ? 0 : imageViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			View view = imageViews.get(arg1);
			view.setOnTouchListener(IntroduceActivity.this);
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		float eventx = 0;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "ACTION_DOWN");
			eventx = event.getX();
			timer.cancel();
		case MotionEvent.ACTION_MOVE:
			if(timer == null){
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					currentItem = (currentItem + 1) % imageViews.size();
					handler.sendEmptyMessage(1);
				}
			}, 0, 3000);
			}else {
				
				Log.e(TAG, "ACTION_MOVE");	
			}
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "ACTION_UP");
			Intent intent = new Intent(this,WebViewActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return true;	
		}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:

			break;
		default:
			break;
		}
		
	}

}