package com.shs.app.libin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shs.app.R;

public class MeijingActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_meijing);
		((Button)findViewById(R.id.btn_left)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
