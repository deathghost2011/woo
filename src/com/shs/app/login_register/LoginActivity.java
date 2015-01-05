package com.shs.app.login_register;

import org.json.JSONException;
import org.json.JSONObject;

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


import com.shs.app.R;
import com.shs.app.kefangyuding.OrderCommitActivity;
import com.shs.app.meals.ShopingCardActivity;
import com.shs.app.personal.AccountActivity;
import com.shs.json.bean.LoginClass;
import com.shs.json.bean.PublicClass;
import com.shs.json.bean.RegisterClass;
import com.shs.json.util.LoginJson;
import com.shs.json.util.RegisterJson;
import com.tailinkj.manager.Manager;

public class LoginActivity extends Activity implements OnClickListener {
	private Button btn_commit;
	private Button left;
	private Button right;
	private TextView title;
	private TextView register,forget;
    //登录
	EditText uname;
	EditText upwd;
	
	LoginJson Ljson=new LoginJson();
	String resultClass="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		uname=(EditText)findViewById(R.id.uname);
		upwd=(EditText)findViewById(R.id.upwd);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		register = (TextView) this.findViewById(R.id.tv_register);
		register.setOnClickListener(this);
		forget = (TextView) this.findViewById(R.id.tv_wangji);
		forget.setOnClickListener(this);
		title.setText("登录");
		btn_commit= (Button) findViewById(R.id.btn_commit);
		btn_commit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_commit:
			String eeroinfo=LoginClick();
			if(eeroinfo.equals(""))
			{
				 eeroinfo="您已成功了登录社会山假日温泉酒店！";
				 Toast.makeText(getApplicationContext(), eeroinfo, Toast.LENGTH_LONG).show();
				 if(PublicClass.getHasdingdan()==true)
				 {
					 startActivity(new Intent(this,
							 OrderCommitActivity.class));
				 }
				 else
				 {
				 startActivity(new Intent(this,
						 AccountActivity.class));
				 }

			}
			else
			{
				Toast.makeText(this, eeroinfo, Toast.LENGTH_LONG).show();
			}
		
			break;
		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:
			break;
		case R.id.tv_register:
			Intent intent = new Intent(this,RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.tv_wangji:
			break;
		default:
			break;
		}
	}
	
	//登录
			private String LoginClick()
			{
				String eeroinfo="";
				String uname=this.uname.getText().toString().trim();
				String pwd=this.upwd.getText().toString().trim();
				

				if(uname.equals(""))
				{
					eeroinfo +="请填写【用户名】\n";
				}
				if(pwd.equals(""))
				{
					eeroinfo +="请填写【密码】\n";
				}
			
				if(eeroinfo.equals(""))
				{
					PublicClass.setPATH(Manager.denglu);
					PublicClass.setToken(Manager.token);

					LoginClass.setUname(this.uname.getText().toString().trim());
					LoginClass.setUpwd(this.upwd.getText().toString().trim());

			    resultClass=Ljson.ReturnJsonStr();
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
							 eeroinfo="您的用户名或密码错误！";
						}
						else
						{
							JSONObject data=result.getJSONObject("data");
							
							LoginClass.setUid(data.getString("uid"));
							LoginClass.setUtoken(data.getString("utoken"));
							
							
						}
						
					} catch (JSONException e) {
						 eeroinfo="无法连接服务器，请确认网络是否连接！";
					}
				}
				}
				
				return	 eeroinfo;
			
			}
}
