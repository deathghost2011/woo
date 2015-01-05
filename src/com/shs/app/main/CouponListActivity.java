package com.shs.app.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shs.app.R;

public class CouponListActivity extends Activity {
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupon_list);
		((Button)findViewById(R.id.btn_left)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		((Button)findViewById(R.id.btn_right)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.pub_title)).setText("优惠券");
		
		List<String> list = new ArrayList<String>();
		for (int i = 0; i <1; i++) {
			list.add("测试数据");
		}
		
		lv = ((ListView)findViewById(R.id.lv_coupon));
		CouponAdapter adapter = new CouponAdapter(this);
		adapter.setList(list);
		lv.setAdapter(adapter);
	}
	
	
	class CouponAdapter extends BaseAdapter {
		private Context mContext;
		private List<String> list;

		public void setList(List<String> list) {
			this.list = list;
		}

		public CouponAdapter(Context mContext) {
			this.mContext = mContext;
		}

		@Override
		public int getCount() {
			return list == null ? 0 : list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.coupon_item, null);
				viewHolder.coupon = (ImageView) convertView
						.findViewById(R.id.coupon);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
//			ImageLoadUtil.displayImage("imageUrl", viewHolder.coupon);
			viewHolder.coupon.setImageResource(R.drawable.img_coupon);
			return convertView;
		}

		private class ViewHolder {
			ImageView coupon;
		}
	}

}
