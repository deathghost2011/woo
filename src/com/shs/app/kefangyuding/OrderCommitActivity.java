package com.shs.app.kefangyuding;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.app.login_register.LoginActivity;
import com.shs.app.personal.AccountActivity;
import com.shs.json.bean.KeFangInfoClass;
import com.shs.json.bean.LoginClass;
import com.shs.json.bean.PublicClass;
import com.shs.json.util.HotelInfoJson;
import com.shs.json.util.HotelListJson;
import com.shs.json.util.HotelzleiJson;
import com.shs.json.util.KFYDingJson;
import com.shs.json.util.KeFangInfoJson;
import com.shs.json.util.KeFangJson;
import com.tailinkj.manager.Manager;



import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class OrderCommitActivity extends Activity implements OnClickListener {
	private Button left;
	private Button right;
	
	private Button  topare;
	private TextView title;
	private TextView hostinfo;
	private GridView gridView;
	ListView goods;
	ListView surround;
	ListView zhence;
	// 订单详情
//	private PublicClass pubClass = new PublicClass();

	private HotelInfoJson Ijson = new HotelInfoJson();
    //客房
	private KeFangJson Kjson = new KeFangJson();
	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合
	private List<View> dots; // 图片标题正文的那些点
	private String resultClass = "";
	List<String> list =new ArrayList<String>();
	// An ExecutorService that can schedule commands to run after a given delay,
	// or to execute periodically.
	private ScheduledExecutorService scheduledExecutorService;
	private int currentItem = 0; // 当前图片的索引号
	
	KFYDingJson  kfydjson=new KFYDingJson();
	
	// 切换当前显示的图片
		@SuppressLint("HandlerLeak") private Handler handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
			};
		};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_commit);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		
		topare=(Button) this.findViewById(R.id.topare);
		topare.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		hostinfo=(TextView) this.findViewById(R.id.jiudian_miaoshu);
		title.setText("预定客房");
		gridView = (GridView) this.findViewById(R.id.gv);
		goods = (ListView) this.findViewById(R.id.lv_goods);
		surround=(ListView) this.findViewById(R.id.lv_surround);
		zhence=(ListView) this.findViewById(R.id.lv_zhence);
		GetHotelInfo();
		GetKeFangInfo();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:
			break;
		case R.id.topare:
			String userid=LoginClass.getUid();
		
			if(userid==null)
			{
				Toast.makeText(this, "请先登录系统", Toast.LENGTH_LONG).show();
				PublicClass.setHasdingdan(true);
				 startActivity(new Intent(this,
						 LoginActivity.class));
			}
			else
			{
				String eeroinfo = ToPare();
				if(!eeroinfo.equals(""))
				{
					Toast.makeText(this, "您的订单提交失败", Toast.LENGTH_LONG).show();
				}
				else
				{
					Toast.makeText(this, "您的订单提交成功", Toast.LENGTH_LONG).show();
					PublicClass.setHasdingdan(false);
					 startActivity(new Intent(this,
							 AccountActivity.class));
				}
				
			}
			
			
			break;
		default:
			break;
		}
	}
	
	//去付款
	private String ToPare()
	{
		String eeroinfo = "";
	
		PublicClass.setPATH(Manager.qufukuan);
		PublicClass.setToken(Manager.token);

		resultClass = kfydjson.ReturnJsonStr();
		if (resultClass.equals("")) {
			eeroinfo = "无法连接服务器，请确认网络是否连接！";
		} else {
			try {
				JSONObject result = new JSONObject(resultClass);
				String error = result.getString("error");

				if (!error.equals("0")) {
					String errorinfo = result.getString("error_info");
					eeroinfo = errorinfo;
				} else {
				
				}
			} catch (JSONException e) {
		
			}
		}
		
		return eeroinfo;
		
	}

	// 酒店订单详情
	private String GetHotelInfo() {

		String eeroinfo = "";

		PublicClass.setPATH(Manager.jiudianxiangqing);
		PublicClass.setToken(Manager.token);
		List<JSONObject> listgood = new ArrayList<JSONObject>();
		List<JSONObject> listsund = new ArrayList<JSONObject>();
		List<String> listzhengc = new ArrayList<String>();
		 ListTextGoodsAdapter listadaptergood = new ListTextGoodsAdapter(this);
		 ListSurrendAdater listadaptersun = new ListSurrendAdater(this);
		 ListZhengCeAdater listadapterzhengce = new ListZhengCeAdater(this);
		resultClass = Ijson.ReturnJsonStr();
		if (resultClass.equals("")) {
			eeroinfo = "无法连接服务器，请确认网络是否连接！";
		} else {
			try {
				JSONObject result = new JSONObject(resultClass);
				String error = result.getString("error");

				if (!error.equals("0")) {
					String errorinfo = result.getString("error_info");
					eeroinfo = errorinfo;
				} else {
					JSONObject data = result.getJSONObject("data");
//					String no = data.getString("id");
//					String name = data.getString("name");
//				
					String info = data.getString("info");
				
					hostinfo.setText(info);
					JSONArray environment = data.getJSONArray("environment");
					for (int i = 0; i < environment.length(); i++) {
						JSONObject typle = (JSONObject) environment.opt(i);
					
						list.add(typle.getString("pic"));
					
					}
					OrderCommitGVAdapter adapter = new OrderCommitGVAdapter(this);
					adapter.setList(list);
					gridView.setAdapter(adapter);
					
					 JSONArray goodjson = data.getJSONArray("goods");
				
					 if(goodjson.length()>0)
					 {
					
					for (int i = 0; i < goodjson.length(); i++) {
						JSONObject typle = (JSONObject) goodjson.opt(i);
					
						listgood.add(typle);
					
					}
					 
					 listadaptergood.setList(listgood);
					  goods.setAdapter(listadaptergood);
					 }
					 
					 
					 JSONArray  surroundjson = data.getJSONArray("surround");
				
					 if(surroundjson.length()>0)
					 {
						
					for (int i = 0; i < surroundjson.length(); i++) {
						JSONObject typle = (JSONObject) surroundjson.opt(i);
					
						listsund.add(typle);
					
					}
					listadaptersun.setList(listsund);
					  surround.setAdapter(listadaptersun);

				
					 }
					 
					  listzhengc.add(data.getString("policy")) ;
					
					  listadapterzhengce.setList(listzhengc);
					  zhence.setAdapter(listadapterzhengce);




				}

			} catch (JSONException e) {
				eeroinfo = "无法连接服务器，请确认网络是否连接！";
			}
		}
		return eeroinfo;

	}
	
	
	// 获取客房信息
		private String GetKeFangInfo() {

			String eeroinfo = "";

			PublicClass.setPATH(Manager.kefangxinxi);
			PublicClass.setToken(Manager.token);

			resultClass = Kjson.ReturnJsonStr();
			if (resultClass.equals("")) {
				eeroinfo = "无法连接服务器，请确认网络是否连接！";
			} else {
				try {
					JSONObject result = new JSONObject(resultClass);
					String error = result.getString("error");

					if (!error.equals("0")) {
						String errorinfo = result.getString("error_info");
						eeroinfo = errorinfo;
					} else {
						JSONObject data = result.getJSONObject("data");
					
						JSONObject room_info = data.getJSONObject("room_info");
						imageViews = new ArrayList<ImageView>();
						JSONArray piclist = room_info.getJSONArray("piclist");
						for (int i = 0; i < piclist.length(); i++) {
							JSONObject pic = (JSONObject) piclist.opt(i);
							String picurl=pic.getString("pic");
						
							URL url = null;
							ImageView imageView = new ImageView(this);
							try {
								url = new URL(picurl);
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							try {
								imageView.setImageBitmap(BitmapFactory.decodeStream(url.openStream()));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							imageView.setScaleType(ScaleType.CENTER_CROP);
							imageViews.add(imageView);

						}
					

						
						
						dots = new ArrayList<View>();
						for(int i=0;i<imageViews.size();i++)
						{
							dots.add(findViewById(R.id.v_dot0));
						}
						
				


						viewPager = (ViewPager) findViewById(R.id.vp);
						viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
						// 设置一个监听器，当ViewPager中的页面改变时调用
						viewPager.setOnPageChangeListener(new MyPageChangeListener());

					}

				} catch (JSONException e) {
					eeroinfo = "无法连接服务器，请确认网络是否连接！";
				}
			}
			return eeroinfo;

		}
		
		/**
		 * 填充ViewPager页面的适配器
		 * 
		 * @author Administrator
		 * 
		 */
		private class MyAdapter extends PagerAdapter {

			@Override
			public int getCount() {
				return imageViews.size();
			}

			@Override
			public Object instantiateItem(View arg0, int arg1) {
				((ViewPager) arg0).addView(imageViews.get(arg1));
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

			@Override
			public void restoreState(Parcelable arg0, ClassLoader arg1) {

			}

			@Override
			public Parcelable saveState() {
				return null;
			}

			@Override
			public void startUpdate(View arg0) {

			}

			@Override
			public void finishUpdate(View arg0) {

			}
		}
		/**
		 * 当ViewPager中页面的状态发生改变时调用
		 * 
		 * @author Administrator
		 * 
		 */
		private class MyPageChangeListener implements OnPageChangeListener {
			private int oldPosition = 0;

			/**
			 * This method will be invoked when a new page becomes selected.
			 * position: Position index of the new selected page.
			 */
			public void onPageSelected(int position) {
		
				dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
				dots.get(position).setBackgroundResource(R.drawable.dot_focused);
				oldPosition = position;
			}

			public void onPageScrollStateChanged(int arg0) {

			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}
		}
		
		@Override
		protected void onStart() {
			scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
			// 当Activity显示出来后，每两秒钟切换一次图片显示
			scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
			super.onStart();
		}

		@Override
		protected void onStop() {
			// 当Activity不可见的时候停止切换
			scheduledExecutorService.shutdown();
			super.onStop();
		}
		
		/**
		 * 换行切换任务
		 * 
		 * @author Administrator
		 * 
		 */
		private class ScrollTask implements Runnable {

			public void run() {
				synchronized (viewPager) {
					System.out.println("currentItem: " + currentItem);
					currentItem = (currentItem + 1) % imageViews.size();
					handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
				}
			}

		}

}
