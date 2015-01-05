package com.shs.app.meeting;

import com.shs.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BanMeetingActivity extends Activity implements OnClickListener {
	private Button left;
	private Button right;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ban_meeting);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("我要办会");
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
