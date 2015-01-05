package com.shs.app.personal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.json.bean.PublicClass;
import com.shs.json.util.GetOrderDetailJson;
import com.shs.json.util.UpOrderStateJson;
import com.tailinkj.manager.Manager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class OrderDetail extends Activity implements OnClickListener{
	private Button left;
	private Button right;
	private TextView title;
	String resultClass;
	GetOrderDetailJson getorder=new GetOrderDetailJson();
	
	// 所有加减的文本框
	private TextView chengren, ertong, fangjian, yupaoDa, yupaoZhong,
			yupaoXiao, yupaoErtong, tuoxieDa, tuoxieZhong, tuoxieErtong,
			tuoxieXiao,   hotel_name,is_pay,huiyuan_name,start_date,end_date,room_typename;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("订单详情");
		initDescAdd();
		GetDetail();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:
			break;
		}
	}
	
	
	private void initDescAdd() {
		chengren = (TextView) this.findViewById(R.id.adult);
		ertong = (TextView) this.findViewById(R.id.children);
		fangjian = (TextView) this.findViewById(R.id.room_num);
		
		yupaoDa = (TextView) this.findViewById(R.id.yupaoda);
		yupaoZhong = (TextView) this.findViewById(R.id.yupaozhong);
		yupaoXiao = (TextView) this.findViewById(R.id.yupaoxiao);
		yupaoErtong = (TextView) this.findViewById(R.id.yupaoertong);
		tuoxieDa = (TextView) this.findViewById(R.id.tuoxieda);
		tuoxieZhong = (TextView) this.findViewById(R.id.tuoxiezhong);
		tuoxieXiao = (TextView) this.findViewById(R.id.tuoxiexiao);
		tuoxieErtong = (TextView) this.findViewById(R.id.tuoxieertong);
		
		hotel_name = (TextView) this.findViewById(R.id.hotel_name);
		is_pay = (TextView) this.findViewById(R.id.is_pay);
		huiyuan_name = (TextView) this.findViewById(R.id.huiyuan_name);
		start_date = (TextView) this.findViewById(R.id.start_date);
		end_date = (TextView) this.findViewById(R.id.end_date);
		room_typename = (TextView) this.findViewById(R.id.room_typename);
		
		
		
	}
	
	//更新支付状态
	private void GetDetail()
	{
		
		PublicClass.setPATH(Manager.gengxin);
		resultClass= getorder.ReturnJsonStr();
	
		if(!resultClass.equals(""))
		{
			try {
				JSONObject result = new JSONObject( resultClass);  
				String error = result.getString("error");  
			
				if(error.equals("0"))
				{
					JSONObject data =  result.getJSONObject("data");  
					JSONObject order_info = data.getJSONObject("order_info");
					
					chengren.setText(order_info.getString("adult"));
					
					ertong.setText(order_info.getString("children"));
					fangjian.setText(order_info.getString("room_num"));
					
					String yupao=order_info.getString("bathrobe");
					String[] yp=yupao.split(":");
					String[] ypao=yp[1].split(",");
					String ypD=ypao[0].split("-")[1];
					String ypZ=ypao[1].split("-")[1];
					String ypX=ypao[2].split("-")[1];
					String ypertong=ypao[3].split("-")[1];
					yupaoDa.setText(ypD);
					
					yupaoZhong.setText(ypZ);
					yupaoXiao.setText(ypX);
					yupaoErtong.setText(ypertong);
					
					
					String tuoxie=order_info.getString("slippers");
					String[] tx=tuoxie.split(":");
					String[] txie=tx[1].split(",");
					String txD=txie[0].split("-")[1];
					String txZ=txie[1].split("-")[1];
					String txX=txie[2].split("-")[1];
					String txertong=txie[3].split("-")[1];
					
					tuoxieDa.setText(txD);
					tuoxieZhong.setText(txZ);
					tuoxieXiao.setText(txX);
					tuoxieErtong.setText(txertong);
					
					hotel_name.setText(order_info.getString("hotel_name"));
					is_pay.setText(order_info.getString("is_pay"));
					huiyuan_name.setText(PublicClass.getRealname());
					start_date.setText(order_info.getString("start_date"));
					end_date.setText(order_info.getString("end_date"));
					room_typename.setText(order_info.getString("room_typename"));
					
				}
				
			} catch (JSONException e) {
				
			}
		}

	}
}
