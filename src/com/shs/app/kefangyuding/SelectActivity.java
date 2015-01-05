package com.shs.app.kefangyuding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shs.app.R;
import com.shs.app.R.layout;
import com.shs.app.meals.ShopingCardActivity;
import com.shs.json.bean.KeFangInfoClass;
import com.shs.json.bean.LoginClass;
import com.shs.json.bean.PublicClass;
import com.shs.json.util.HotelListJson;
import com.shs.json.util.HotelzleiJson;
import com.shs.json.util.KeFangInfoJson;
import com.tailinkj.manager.Manager;

public class SelectActivity extends Activity implements OnClickListener {
	private Button left;
	private Button right;
	private TextView title;
	private ListView listView;
	private Button search;
	private LinearLayout chakan;
	private String resultClass = "";// 列表对象
	FangjianInfoListAdapter adapter = null;

	// 获取酒店名称列表
	// private PublicClass pubClass = new PublicClass();

	private HotelListJson Hjson = new HotelListJson();

	private HotelzleiJson HTypeljson = new HotelzleiJson();// 酒店类型

	private KeFangInfoJson kefanginfo = new KeFangInfoJson();// 查询列表
	// private KeFangInfoClass kefangList = new KeFangInfoClass();
	// ListInfoClass lisinfo=new ListInfoClass();
	List<FangjianInfoListAdapter> Adapterlist = new ArrayList<FangjianInfoListAdapter>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("选择房间");
		/*
		 * search = (Button) this.findViewById(R.id.btn_search); chakan =
		 * (LinearLayout) this.findViewById(R.id.chakan_fangjian);
		 * search.setOnClickListener(this);
		 */
		listView = (ListView) this.findViewById(R.id.list);

		KeFangTyple(KeFangInfoClass.getHotel_no(),
				KeFangInfoClass.getIn_date(), KeFangInfoClass.getOut_date());

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				TextView idtxt = (TextView) view.findViewById(R.id.hotel_id);
				String kefangid = idtxt.getText().toString().trim();

				TextView mtxt = (TextView) view.findViewById(R.id.item_price);
				String mony = mtxt.getText().toString().trim();
				mony = mony.replace("￥", "");
				if (!kefangid.equals("")) {
					
					
					TextView room_id = (TextView) view.findViewById(R.id.room_id);
					String roomid = room_id.getText().toString().trim();
					KeFangInfoClass.setRoom_id(roomid);
					TextView room_type = (TextView) view
							.findViewById(R.id.room_type);
					String roomtype = room_type.getText().toString().trim();

					KeFangInfoClass.setRoom_typeid(roomtype);
					String startdata=KeFangInfoClass.getIn_date();
					String enddata=KeFangInfoClass.getOut_date();
					
				  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				  long days = 0;
				  try
				  {
				     Date d1 = df.parse(startdata);
				     Date d2 = df.parse(enddata);
				     long diff = d2.getTime() - d1.getTime();
				     days = diff / (1000 * 60 * 60 * 24);
				  }
				  catch (Exception e)
				  {
				  }

			
					int fangjian=Integer.parseInt(KeFangInfoClass.getRoom_num());
					Double valuemony=Double.valueOf(mony.toString().trim());
					Double totalmony=valuemony*(days+1)*fangjian; 
					KeFangInfoClass.setMoney(String.valueOf(totalmony));
					Intent intent = new Intent(SelectActivity.this,
							OrderCommitActivity.class);
					startActivity(intent);
				}
			}
		});
	}

	// 客房类型列表
	// hotel_no 酒店编号
	private String KeFangTyple(String hotel_id, String startDate, String endDate) {
		String eeroinfo = "";
		List<JSONObject> room_list = new ArrayList<JSONObject>();
		List<String> name = new ArrayList<String>();
		List<Integer> count = new ArrayList<Integer>();
		// 客房类型列表1
		PublicClass
				.setPATH(Manager.kefangleixing1);
		PublicClass.setToken(Manager.token);

		resultClass = HTypeljson.ReturnJsonStr();
		if (resultClass.equals("")) {
			eeroinfo = "无法连接服务器，请确认网络是否连接！";
		} else {
			try {
				JSONObject result = new JSONObject(resultClass);
				String error = result.getString("error");

				if (!error.equals("0")) {
					String errorinfo = result.getString("error_info");
					eeroinfo = errorinfo;
				} else {
					JSONObject data = result.getJSONObject("data");
					JSONArray type_list = data.getJSONArray("type_list");
					for (int i = 0; i < type_list.length(); i++) {
						JSONObject typle = (JSONObject) type_list.opt(i);

						String Id = typle.getString("id");

						KeFangInfoClass.setRoom_typeid(Id);
						// 客房类型列表2
						PublicClass
								.setPATH(Manager.kefangleixing2);
						PublicClass
								.setToken(Manager.token);

						resultClass = kefanginfo.ReturnJsonStr();

						try {
							JSONObject resultdata = new JSONObject(resultClass);
							error = result.getString("error");

							if (error.equals("0")) {
								JSONObject dataresult = resultdata
										.getJSONObject("data");

								JSONArray room = dataresult
										.getJSONArray("room_list");
								if (room.length() > 0) {
									JSONObject nametp = typle;

									nametp.put("IsType", true);
									room_list.add(nametp);
								}
								for (int m = 0; m < room.length(); m++) {
									JSONObject nametypetemp = room
											.getJSONObject(m);
									nametypetemp.put("IsType", false);
									nametypetemp.put("room_type", Id);
									name.add(typle.getString("name"));
									room_list.add(nametypetemp);

								}

								count.add(room.length());
							}

						} catch (JSONException e) {
							eeroinfo = "无法连接服务器，请确认网络是否连接！";
						}

					}

					adapter = new FangjianInfoListAdapter(this);

					adapter.setList(room_list, name, count);

					listView.setAdapter(adapter);

				}

			} catch (JSONException e) {
				eeroinfo = "无法连接服务器，请确认网络是否连接！";
			}
		}
		return eeroinfo;
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
