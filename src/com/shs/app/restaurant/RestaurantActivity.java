package com.shs.app.restaurant;

import com.shs.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantActivity extends Activity implements OnClickListener{
	private Button left;
	private Button right;
	private TextView title;
	private Button restaurant1,restaurant2,restaurant3,restaurant4,restaurant5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("餐厅酒吧");
		restaurant1 = (Button) this.findViewById(R.id.btn_restaurant1);
		restaurant1.setOnClickListener(this);
		restaurant2 = (Button) this.findViewById(R.id.btn_restaurant2);
		restaurant2.setOnClickListener(this);
		restaurant3 = (Button) this.findViewById(R.id.btn_restaurant3);
		restaurant3.setOnClickListener(this);
		restaurant4 = (Button) this.findViewById(R.id.btn_restaurant4);
		restaurant4.setOnClickListener(this);
		restaurant5 = (Button) this.findViewById(R.id.btn_restaurant5);
		restaurant5.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.btn_left:
			this.finish();
			break;
		case R.id.relativeLayout1:
			Intent intent1 = new Intent(this,RestaurantDetailActivity.class);
			startActivity(intent1);
			break;
		case R.id.btn_restaurant1:
			Intent intent2 = new Intent(this,RestaurantDetailActivity.class);
			startActivity(intent2);
			break;
		case R.id.btn_restaurant2:
			Intent intent3 = new Intent(this,RestaurantDetailActivity.class);
			startActivity(intent3);			break;
		case R.id.btn_restaurant3:
			Intent intent4 = new Intent(this,RestaurantDetailActivity.class);
			startActivity(intent4);			break;
		case R.id.btn_restaurant4:
			Intent intent = new Intent(this,RestaurantDetailActivity.class);
			startActivity(intent);			break;
		case R.id.btn_restaurant5:
			Intent intent5 = new Intent(this,RestaurantDetailActivity.class);
			startActivity(intent5);			break;
		case R.id.btn_right:
		}
	}

}
