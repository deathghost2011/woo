package com.shs.app.meals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.app.kefangyuding.FangjianInfoListAdapter;
import com.shs.json.bean.KeFangInfoClass;
import com.shs.json.bean.PublicClass;
import com.shs.json.bean.WenQuanClass;
import com.shs.json.bean.ZaoCanClass;
import com.shs.json.util.DetailMealsJson;
import com.shs.json.util.GetJSJieGuo;
import com.shs.json.util.ImageLoad;
import com.tailinkj.manager.Manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DetailMealsActivity extends Activity implements OnClickListener{
	private Button left;
	private Button right;
	private TextView title,zaocan_type,zongjia;
	private ListView listView;
	private Button btnDesc,btnAdd,tianjia;
	private int total;
	ImageView item_icon;
	private EditText etTotal;
	private MealsDetailListAdapter adapter;
	private	String resultClass="";//列表对象
	private DetailMealsJson detailzaocan = new DetailMealsJson();// 查询列表
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meals_detail);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("菜单详情");
		btnAdd = (Button) this.findViewById(R.id.btn_add);
		btnAdd.setOnClickListener(this);
		btnDesc = (Button) this.findViewById(R.id.btn_desc);
		btnDesc.setOnClickListener(this);
		tianjia = (Button) this.findViewById(R.id.tianjia);
		tianjia.setOnClickListener(this);
		etTotal = (EditText) this.findViewById(R.id.et_total);
		etTotal.setText(String.valueOf(total));
		listView = (ListView) this.findViewById(R.id.list);
		zaocan_type= (TextView) this.findViewById(R.id.zaocan_type);
		zongjia= (TextView) this.findViewById(R.id.zongjia);
		item_icon= (ImageView) this.findViewById(R.id.item_icon);
		inition();
		GetZaoCanList();
	}
	
	//加载页面
	private void  inition()
	{
		zaocan_type.setText(ZaoCanClass.getTitle());
		etTotal.setText(ZaoCanClass.getFenshu());
		zongjia.setText("￥"+String.valueOf(GetJSJieGuo.GetResultzaocan(Integer.valueOf(ZaoCanClass.getFenshu()))));

		item_icon.setImageBitmap(ImageLoad.getimage(ZaoCanClass.getImageurl()));
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
			zongjia.setText("￥"+String.valueOf(GetJSJieGuo.GetResultzaocan(Integer.valueOf(total))));
			break;
		case R.id.btn_desc:
			if(total > 1){
				total -= 1;
				etTotal.setText(String.valueOf(total));
				zongjia.setText("￥"+String.valueOf(GetJSJieGuo.GetResultzaocan(Integer.valueOf(total))));

			}
		case R.id.tianjia:
			Intent intent = new Intent(this, ShopingCardActivity.class);
			
			this.startActivity(intent);
			break;
			
		}
	}
	//获取早餐列表
		private void GetZaoCanList()
		{
			
			
	        String eeroinfo="";
	    	JSONObject nametp = null ;
	        List<JSONObject> listzaocan = new ArrayList<JSONObject>();
			PublicClass.setPATH(Manager.zaocan);
			PublicClass.setToken(Manager.token);
            String headname="";
		    resultClass= detailzaocan.ReturnJsonStr();
			if(resultClass.equals(""))
			{
				 eeroinfo="无法连接服务器，请确认网络是否连接！";
			}
			else
			{
				try {
					JSONObject result = new JSONObject(resultClass);
					String error = result.getString("error");
					if (!error.equals("0")) {
						String errorinfo = result.getString("error_info");
						eeroinfo = errorinfo;
					} else {
						JSONObject data = result.getJSONObject("data");
						JSONArray type_list = data.getJSONArray("datalist");
						for (int i = 0; i < type_list.length(); i++) {
							JSONObject typle = (JSONObject) type_list.opt(i);
							try {
						
								JSONArray zaocan = typle
										.getJSONArray("goods_list");
								
								if (zaocan.length() > 0) {
								    nametp = typle;
								    headname=typle.getString("name");
								    if(headname.equals(""))
								    {
								    	headname="暂无分类";
								        nametp.put("Headname", "暂无分类");
								
								    }
								    else
								    {
								    	   nametp.put("Headname", headname);
								    }
									nametp.put("IsType", true);
									listzaocan.add(nametp);
								}
								for (int m = 0; m < zaocan.length(); m++) {
									
									
									JSONObject nametypetemp = zaocan
											.getJSONObject(m);
									
						
								   nametypetemp.put("Headname", headname);

									if(typle.getString("type").equals("2"))
									{
										nametypetemp.put("Iscorr", true);
									
									}
									else
									{
										nametypetemp.put("Iscorr", false);
									
									}
									
									nametypetemp.put("IsType", false);
									listzaocan.add(nametypetemp);

								}
							} catch (JSONException e) {
								eeroinfo = "无法连接服务器，请确认网络是否连接！";
							}

						}

						    adapter = new MealsDetailListAdapter(this);
							adapter.setList(listzaocan);
							listView.setAdapter(adapter);

					}

				} catch (JSONException e) {
					eeroinfo = "无法连接服务器，请确认网络是否连接！";
				}
					
			}
		}
	

}
