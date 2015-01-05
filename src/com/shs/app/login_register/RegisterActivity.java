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
import com.shs.app.meals.ShopingCardActivity;
import com.shs.app.personal.AccountActivity;
import com.shs.json.bean.PublicClass;
import com.shs.json.bean.RegisterClass;
import com.shs.json.util.RegisterJson;
import com.tailinkj.manager.Manager;

public class RegisterActivity extends Activity implements OnClickListener {
	private Button btn_register;
	private Button left;
	private Button right;
	private TextView title;
	
	//注册信息
	EditText realname;
	EditText uname;
	EditText upwd;
	EditText upwdr;
	EditText email;
	EditText mobile;
	
	RegisterJson Rjson=new RegisterJson();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		realname=(EditText)findViewById(R.id.realname);
		uname=(EditText)findViewById(R.id.uname);
		upwd=(EditText)findViewById(R.id.upwd);
		upwdr=(EditText)findViewById(R.id.upwdr);
		email=(EditText)findViewById(R.id.email);
		mobile=(EditText)findViewById(R.id.mobile);
		
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("注册");
		btn_register= (Button) findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			String eeroinfo=RegisteClick();
			if(eeroinfo.equals(""))
			{
				 eeroinfo="您已成功了注册社会山假日温泉酒店！";
				 Toast.makeText(getApplicationContext(), eeroinfo, Toast.LENGTH_LONG).show();
			     startActivity(new Intent(this, LoginActivity.class));
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
		default:
			break;
		}
	}
	
	//注册
	private String   RegisteClick()
	{
		String eeroinfo="";
		String rname=this.realname.getText().toString().trim();
		String uname=this.uname.getText().toString().trim();
		
		String pwd=this.upwd.getText().toString().trim();
		
		String pwdr=this.upwdr.getText().toString().trim();
		String email=this.email.getText().toString().trim();
		String moblie=this.mobile.getText().toString().trim();
		
		if(rname.equals(""))
		{
			eeroinfo +="请填写【姓名】\n";
		}
		if(uname.equals(""))
		{
			eeroinfo +="请填写【用户名】\n";
		}
		if(pwd.equals(""))
		{
			eeroinfo +="请填写【密码】\n";
		}
		if(!pwd.equals(pwdr))
		{
			eeroinfo +="【确认密码】填写错误\n";
		}
		
		if(email.equals(""))
		{
			eeroinfo +="请填写【邮箱】\n";
		}
		if(moblie.equals(""))
		{
			eeroinfo +="请填写【手机号】\n";
		}
		if(eeroinfo.equals(""))
		{
			PublicClass.setPATH(Manager.zhuce);
			PublicClass.setToken(Manager.token);
			RegisterClass.setRealname(this.realname.getText().toString().trim());
			RegisterClass.setUname(this.uname.getText().toString().trim());
			RegisterClass.setUpwd(this.upwd.getText().toString().trim());
			RegisterClass.setEmail(this.email.getText().toString().trim());
			RegisterClass.setMobile(this.mobile.getText().toString().trim());
		String resultClass=Rjson.ReturnJsonStr();
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
				
			} catch (JSONException e) {
				 eeroinfo="无法连接服务器，请确认网络是否连接！";
			}
		}
		}
		
		return	 eeroinfo;
	
	}
}
