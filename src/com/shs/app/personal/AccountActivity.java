package com.shs.app.personal;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shs.app.R;
import com.shs.app.kefangyuding.OrderActivity;
import com.shs.app.main.CouponListActivity;
import com.shs.app.main.FeedBackActivity;
import com.shs.app.main.MainAct;
import com.shs.app.meals.ShopingCardActivity;
import com.shs.json.bean.LoginClass;
import com.shs.json.bean.PublicClass;
import com.shs.json.util.PersonInfoJson;
import com.shss.wedgit.dialog.CustomAlertDialog;
import com.tailinkj.manager.Manager;


public class AccountActivity extends Activity implements OnClickListener{
	private Button left;
	private Button right;
	private TextView title,scoretxt;
	private Button exit;
	private RelativeLayout allOrder;
	private ImageView yhq;
	private ImageView orderall;
	//获取个人信息
	PersonInfoJson pjson=new PersonInfoJson();
	String resultClass="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showme);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("用户中心");
		scoretxt=(TextView) this.findViewById(R.id.score);
		yhq = (ImageView) this.findViewById(R.id.yhqperson);
		yhq.setOnClickListener(this);
		exit = (Button) this.findViewById(R.id.btn_regist);
		exit.setOnClickListener(this);
	
		
		allOrder = (RelativeLayout) this.findViewById(R.id.all_order);
		allOrder.setOnClickListener(this);
		InitionPersonInfo();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:
			Intent intent1 = new Intent(this, ShopingCardActivity.class);
			this.startActivity(intent1);
			break;
		case R.id.btn_regist:
			CustomAlertDialog
			.create(this, "提示", "确定要退出当前账号吗？")
			.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							Intent intent = new Intent(AccountActivity.this,MainAct.class);
							startActivity(intent);
							AccountActivity.this.finish();
						}
					}).setNegativeButton("取消", null).show();
			break;
		case R.id.all_order:
			
			
			Intent intent2 = new Intent(this, AllOrderActivity.class);
			this.startActivity(intent2);
			break;
			
		case R.id.yhqperson:
			Intent intent3 = new Intent(this, CouponListActivity.class);
			this.startActivity(intent3);
			break;
		
		}
	}
	
	//显示个人信息
	private void InitionPersonInfo()
	{
		String eeroinfo="";
		PublicClass.setPATH(Manager.gerenxinxi);
	    resultClass= pjson.ReturnJsonStr();
		if(resultClass.equals(""))
		{
			 eeroinfo="无法连接服务器，请确认网络是否连接！";
		}
		else
		{
			try {
				JSONObject result = new JSONObject( resultClass);  
				String error = result.getString("error");  
			
				if(!error.equals("0"))
				{
					 String errorinfo = result.getString("error_info");  
					 eeroinfo=errorinfo;
				}
				else
				{
					JSONObject data = result.getJSONObject("data");  
					String face=data.getString("face");
					String user_score=data.getString("user_score");
					String realname=data.getString("realname");
					PublicClass.setRealname(realname);
					scoretxt.setText(user_score);
				}
				
			} catch (JSONException e) {
				 eeroinfo="无法连接服务器，请确认网络是否连接！";
			}
		}

	}
}
