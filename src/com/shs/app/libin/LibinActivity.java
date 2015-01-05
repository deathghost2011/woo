package com.shs.app.libin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shs.app.R;

public class LibinActivity extends Activity {
	private Button left;
	private Button right;
	private TextView title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_libin);
		left = (Button) this.findViewById(R.id.btn_left);
		right = (Button) this.findViewById(R.id.btn_right);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("礼宾服务");
		((Button)findViewById(R.id.btn_left)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		((Button)findViewById(R.id.btn_meishi)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LibinActivity.this, MeishiActivity.class));
			}
		});
		((Button)findViewById(R.id.btn_meijin)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LibinActivity.this, MeijingActivity.class));
			}
		});
	}
}
