package com.shs.app.springs;

import java.util.ArrayList;
import java.util.List;

import com.shs.app.R;
import com.shs.app.kefangyuding.OrderCommitActivity;
import com.shs.json.bean.WenQuanClass;
import com.shs.json.util.GetJSJieGuo;
import com.shss.wedgit.dialog.OptionsDialog;
import com.shss.wedgit.dialog.OptionsDialog.NameValuePair;
import com.shss.wedgit.dialog.OptionsDialog.OnSelectWithValueListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OrderSpringActivity extends Activity implements OnClickListener{
	private Button left;
	private Button btn_search;
	private Button right;
	private TextView title;
	private Button btnDesc,btnAdd;
	private int total=1;
	private EditText etTotal;
	private LinearLayout paymentWay;
	private TextView tvPaymentWay,zongjia,txt_dj;
	private List<NameValuePair> paymentWayItems = new ArrayList<NameValuePair>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spring_order);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("提交订单");
		
		zongjia= (TextView) this.findViewById(R.id.zongjia);
		txt_dj= (TextView) this.findViewById(R.id.txt_dj);
		btnAdd = (Button) this.findViewById(R.id.btn_add);
		btnAdd.setOnClickListener(this);
		
		btn_search = (Button) this.findViewById(R.id.btn_search);
		btn_search.setOnClickListener(this);
		
		btnDesc = (Button) this.findViewById(R.id.btn_desc);
		btnDesc.setOnClickListener(this);
		etTotal = (EditText) this.findViewById(R.id.et_total);
		etTotal.setText(String.valueOf(total));
		paymentWay = (LinearLayout) this.findViewById(R.id.payment_way);
		paymentWay.setOnClickListener(this);
		tvPaymentWay = (TextView) this.findViewById(R.id.tv_payment_way);
		zongjia.setText("￥"+String.valueOf(GetJSJieGuo.GetResult(1)));
		txt_dj.setText("￥"+WenQuanClass.getPrice());
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:
			break;
		case R.id.btn_add:
			total += 1;
			etTotal.setText(String.valueOf(total));
			zongjia.setText(String.valueOf(GetJSJieGuo.GetResult(total)));
			break;
		case R.id.btn_desc:
			if(total > 1){
				total -= 1;
				etTotal.setText(String.valueOf(total));
				zongjia.setText(String.valueOf(GetJSJieGuo.GetResult(total)));
			}
			break;
			
		case R.id.btn_search:
			 Toast.makeText(getApplicationContext(), "您的订单已提交", Toast.LENGTH_LONG).show();

			 startActivity(new Intent(this,
					 SpringActivity.class));
			break;
		case R.id.payment_way:
			paymentWayItems.clear();
			paymentWayItems.add(new NameValuePair("挂房帐", ""));
			paymentWayItems.add(new NameValuePair("银联支付", ""));
			OptionsDialog roomOptsDialog2 = new OptionsDialog(this, v,
					"选择客房类型", paymentWayItems, new OnSelectWithValueListener() {
						@Override
						public void onSeleted(String text, String value) {
							tvPaymentWay.setText(text);

						}
					});
			roomOptsDialog2.show();
			break;
		}
	}

	
}
