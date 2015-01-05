package com.shs.app.kefangyuding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shs.app.R;
import com.shs.app.personal.AccountActivity;
import com.shs.app.wedgit.calender.CalendarActivity;
import com.shs.app.wedgit.calender.CalendarUtil;
import com.shs.json.bean.KeFangInfoClass;
import com.shs.json.bean.PublicClass;
import com.shs.json.util.HotelListJson;
import com.shs.json.util.HotelzleiJson;
import com.shs.json.util.KeFangInfoJson;
import com.shss.wedgit.dialog.OptionsDialog;
import com.shss.wedgit.dialog.OptionsDialog.NameValuePair;
import com.shss.wedgit.dialog.OptionsDialog.OnSelectWithValueListener;
import com.tailinkj.manager.Manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class KeFangYuDingActivity extends Activity implements OnClickListener {
	private Button left;
	private Button right;
	private TextView title;
	private Button btn_search;
	private RelativeLayout startTiem, endTime;
	private TextView startW, startM, startD, endW, endM, endD;
	private String startDate = "", endDate = "";
	// 选择酒店
	private LinearLayout selectJiudian;
	private List<NameValuePair> jiudianItems = new ArrayList<NameValuePair>();
	private TextView selectJiudianTv;
	// 选择客房种类
	private LinearLayout selectKefang;
	private List<NameValuePair> kefangItems = new ArrayList<NameValuePair>();
	private TextView selectKefangTv;
	// 所有加减的文本框
	private TextView chengren, ertong, fangjian, yupaoDa, yupaoZhong,
			yupaoXiao, yupaoErtong, tuoxieDa, tuoxieZhong, tuoxieErtong,
			tuoxieXiao;
	// 所有加减的减按钮
	private Button chengrenDesc, ertongDesc, fangjianDesc, yupaoDaDesc,
			yupaoZhongDesc, yupaoXiaoDesc, yupaoErtongDesc, tuoxieDaDesc,
			tuoxieZhongDesc, tuoxieErtongDesc, tuoxieXiaoDesc;
	// 所有加减的加按钮
	private Button chengrenAdd, ertongAdd, fangjianAdd, yupaoDaAdd,
			yupaoZhongAdd, yupaoXiaoAdd, yupaoErtongAdd, tuoxieDaAdd,
			tuoxieZhongAdd, tuoxieErtongAdd, tuoxieXiaoAdd;
	// 所有加减的文本框
	private int chengrenNumber, ertongNumber, fangjianNumber, yupaoDaNumber,
			yupaoZhongNumber, yupaoXiaoNumber, yupaoErtongNumber,
			tuoxieDaNumber, tuoxieZhongNumber, tuoxieErtongNumber,
			tuoxieXiaoNumber;
	
	
	// 获取酒店名称列表
//	private PublicClass pubClass = new PublicClass();

	private HotelListJson Hjson = new HotelListJson();

	private HotelzleiJson HTypeljson = new HotelzleiJson();// 酒店类型

	private KeFangInfoJson kefanginfo = new KeFangInfoJson();// 查询列表
	
//	private KeFangInfoClass kefangList = new KeFangInfoClass();
	
	private String resultClass = "";

//	ListInfoClass lisinfo=new ListInfoClass();
			
//	private String hostid="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kehuyuding);
		JiuDianNameList();
		initiontime();
		init();
		initDescAdd();
		initNumber();
		initStartWMD();
		initEndWMD();
	}
	
	//初始化时间
	private void initiontime()
	{
		    Date date = new Date();  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String specifiedDay = sdf.format(date);  
	        startDate=getSpecifiedDayBefore(specifiedDay,1);  
	        endDate=getSpecifiedDayBefore(specifiedDay,3);  
	}
	
	 /** 
     * 获得指定日期的前一天 
     *  
     * @param specifiedDay 
     * @return 
     * @throws Exception 
     */  
    public static String getSpecifiedDayBefore(String specifiedDay,int dayc) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day +dayc);  
  
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c  
                .getTime());  
        return dayBefore;  
    }  
  
    

	private void init() {
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setOnClickListener(this);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("客房预订");
		selectJiudian = (LinearLayout) this.findViewById(R.id.select_jiudian);
		selectJiudian.setOnClickListener(this);
		selectJiudianTv = (TextView) this.findViewById(R.id.select_jiudian_tv);
		selectKefang = (LinearLayout) this.findViewById(R.id.select_kefang);
		selectKefang.setOnClickListener(this);
		selectKefangTv = (TextView) this.findViewById(R.id.select_kefang_tv);
		btn_search = (Button) this.findViewById(R.id.btn_search);
		btn_search.setOnClickListener(this);
		startTiem = (RelativeLayout) this.findViewById(R.id.rl_start);
		startTiem.setOnClickListener(this);
		endTime = (RelativeLayout) this.findViewById(R.id.rl_end);
		endTime.setOnClickListener(this);
		startW = (TextView) this.findViewById(R.id.startW);
		startM = (TextView) this.findViewById(R.id.startM);
		startD = (TextView) this.findViewById(R.id.startD);
		endW = (TextView) this.findViewById(R.id.endW);
		endM = (TextView) this.findViewById(R.id.endM);
		endD = (TextView) this.findViewById(R.id.endD);
	}

	private void initDescAdd() {
		chengren = (TextView) this.findViewById(R.id.chengren_tv);
		ertong = (TextView) this.findViewById(R.id.ertong_tv);
		fangjian = (TextView) this.findViewById(R.id.fangjian_tv);
		yupaoDa = (TextView) this.findViewById(R.id.yupao_da_tv);
		yupaoZhong = (TextView) this.findViewById(R.id.yupao_zhong_tv);
		yupaoXiao = (TextView) this.findViewById(R.id.yupao_xiao_tv);
		yupaoErtong = (TextView) this.findViewById(R.id.yupao_ertong_tv);
		tuoxieDa = (TextView) this.findViewById(R.id.tuoxie_da_tv);
		tuoxieZhong = (TextView) this.findViewById(R.id.tuoxie_zhong_tv);
		tuoxieErtong = (TextView) this.findViewById(R.id.tuoxie_ertong_tv);
		tuoxieXiao = (TextView) this.findViewById(R.id.tuoxie_xiao_tv);
		chengrenDesc = (Button) this.findViewById(R.id.chengren_desc);
		chengrenDesc.setOnClickListener(this);
		ertongDesc = (Button) this.findViewById(R.id.ertong_desc);
		ertongDesc.setOnClickListener(this);
		fangjianDesc = (Button) this.findViewById(R.id.fangjian_desc);
		fangjianDesc.setOnClickListener(this);
		yupaoDaDesc = (Button) this.findViewById(R.id.yupao_da_desc);
		yupaoDaDesc.setOnClickListener(this);
		yupaoZhongDesc = (Button) this.findViewById(R.id.yupao_zhong_desc);
		yupaoZhongDesc.setOnClickListener(this);
		yupaoXiaoDesc = (Button) this.findViewById(R.id.yupao_xiao_desc);
		yupaoXiaoDesc.setOnClickListener(this);
		yupaoErtongDesc = (Button) this.findViewById(R.id.yupao_ertong_desc);
		yupaoErtongDesc.setOnClickListener(this);
		tuoxieDaDesc = (Button) this.findViewById(R.id.tuoxie_da_desc);
		tuoxieDaDesc.setOnClickListener(this);
		tuoxieZhongDesc = (Button) this.findViewById(R.id.tuoxie_zhong_desc);
		tuoxieZhongDesc.setOnClickListener(this);
		tuoxieErtongDesc = (Button) this.findViewById(R.id.tuoxie_ertong_desc);
		tuoxieErtongDesc.setOnClickListener(this);
		tuoxieXiaoDesc = (Button) this.findViewById(R.id.tuoxie_xiao_desc);
		tuoxieXiaoDesc.setOnClickListener(this);
		chengrenAdd = (Button) this.findViewById(R.id.chengren_add);
		chengrenAdd.setOnClickListener(this);
		ertongAdd = (Button) this.findViewById(R.id.ertong_add);
		ertongAdd.setOnClickListener(this);
		fangjianAdd = (Button) this.findViewById(R.id.fangjian_add);
		fangjianAdd.setOnClickListener(this);
		yupaoDaAdd = (Button) this.findViewById(R.id.yupao_da_add);
		yupaoDaAdd.setOnClickListener(this);
		yupaoZhongAdd = (Button) this.findViewById(R.id.yupao_zhong_add);
		yupaoZhongAdd.setOnClickListener(this);
		yupaoXiaoAdd = (Button) this.findViewById(R.id.yupao_xiao_add);
		yupaoXiaoAdd.setOnClickListener(this);
		yupaoErtongAdd = (Button) this.findViewById(R.id.yupao_ertong_add);
		yupaoErtongAdd.setOnClickListener(this);
		tuoxieDaAdd = (Button) this.findViewById(R.id.tuoxie_da_add);
		tuoxieDaAdd.setOnClickListener(this);
		tuoxieZhongAdd = (Button) this.findViewById(R.id.tuoxie_zhong_add);
		tuoxieZhongAdd.setOnClickListener(this);
		tuoxieErtongAdd = (Button) this.findViewById(R.id.tuoxie_ertong_add);
		tuoxieErtongAdd.setOnClickListener(this);
		tuoxieXiaoAdd = (Button) this.findViewById(R.id.tuoxie_xiao_add);
		tuoxieXiaoAdd.setOnClickListener(this);
	}

	private void initNumber() {
		chengrenNumber = 0;
		ertongNumber = 0;
		fangjianNumber = 0;
		yupaoDaNumber = 0;
		yupaoZhongNumber = 0;
		yupaoXiaoNumber = 0;
		yupaoErtongNumber = 0;
		tuoxieDaNumber = 0;
		tuoxieZhongNumber = 0;
		tuoxieErtongNumber = 0;
		tuoxieXiaoNumber = 0;
	}

	// 获取酒店名称列表
	private String JiuDianNameList() {
		String eeroinfo = "";

		PublicClass.setPATH(Manager.jiudian);
		PublicClass.setToken(Manager.token);

		resultClass = Hjson.ReturnJsonStr();
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
					JSONArray hotel_list = data.getJSONArray("hotel_list");
					for (int i = 0; i < hotel_list.length(); i++) {
						JSONObject hotel = (JSONObject) hotel_list.opt(i);

						String no = hotel.getString("id");
						String name = hotel.getString("name");
						jiudianItems.add(new NameValuePair(name, no));
					}

				}

			} catch (JSONException e) {
				eeroinfo = "无法连接服务器，请确认网络是否连接！";
			}
		}
		return eeroinfo;

	}
	
	 private void setKeFanginfoClass()
	 {

			
		   KeFangInfoClass.setIn_date(startDate);
		   KeFangInfoClass.setOut_date(endDate);
		   String adult=chengren.getText().toString().trim();
		   String children=ertong.getText().toString().trim();
		   
		   String bathrobe="浴袍:大-"+yupaoDa.getText().toString().trim()+","+"中-"+yupaoZhong.getText().toString().trim()+
				 ","+"小-" +yupaoXiao.getText().toString().trim()+","+"儿童-"+yupaoErtong.getText().toString().trim() ;
		   
		   
		   String slippers="拖鞋:大-"+tuoxieDa.getText().toString().trim()+","+"中-"+tuoxieZhong.getText().toString().trim()+
					 ","+"小-" +tuoxieXiao.getText().toString().trim()+","+"儿童-"+tuoxieErtong.getText().toString().trim() ;
		   String roomnumber=fangjian.getText().toString().trim();
		   
		   KeFangInfoClass.setAdult(adult);
		   KeFangInfoClass.setChildren(children);
		   KeFangInfoClass.setBathrobe(bathrobe);
		   KeFangInfoClass.setSlippers(slippers);
		   KeFangInfoClass.setRoom_num(roomnumber);
		   KeFangInfoClass.setReq("暂无");
	 }
		
	
		
private Long getuinxdate(String datestr)
{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = null;
	try {
		date = sdf.parse(datestr);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	long dvalue=date.getTime();
	return dvalue;
}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_left:
			this.finish();
			break;
		case R.id.btn_right:
			break;
		case R.id.select_jiudian:
			// 初始化酒店item
			jiudianItems.clear();
			JiuDianNameList();
			OptionsDialog roomOptsDialog1 = new OptionsDialog(this, v, "选择酒店",
					jiudianItems, new OnSelectWithValueListener() {
						@Override
						public void onSeleted(String text, String value) {
							selectJiudianTv.setText(text);
						
							KeFangInfoClass.setHotel_no(value);

						}
					});
			roomOptsDialog1.show();
			break;
		case R.id.select_kefang:
			// 初始化客房级别
			kefangItems.clear();
			kefangItems.add(new NameValuePair("双标间", ""));
			kefangItems.add(new NameValuePair("豪华单间", ""));
			kefangItems.add(new NameValuePair("高级单间", ""));
			OptionsDialog roomOptsDialog2 = new OptionsDialog(this, v,
					"选择客房类型", kefangItems, new OnSelectWithValueListener() {
						@Override
						public void onSeleted(String text, String value) {
							selectKefangTv.setText(text);

						}
					});
			roomOptsDialog2.show();
			break;
		case R.id.rl_start:
			startActivityForResult(new Intent(this, CalendarActivity.class),
					1001);
			break;
		case R.id.rl_end:
			startActivityForResult(new Intent(this, CalendarActivity.class),
					1002);
			break;
		case R.id.btn_search:
			setKeFanginfoClass();
				startActivityForResult(new Intent(this, SelectActivity.class),
						1002);
			break;
		case R.id.chengren_add:
			chengrenNumber += 1;
			Log.e("asdsd", chengrenNumber+";;;;");
			chengren.setText(String.valueOf(chengrenNumber));
			break;
		case R.id.ertong_add:
			ertongNumber += 1;
			ertong.setText(String.valueOf(ertongNumber));
			break;
		case R.id.fangjian_add:
			fangjianNumber += 1;
			fangjian.setText(String.valueOf(fangjianNumber));
			break;
		case R.id.yupao_da_add:
			yupaoDaNumber += 1;
			yupaoDa.setText(String.valueOf(yupaoDaNumber));
			break;
		case R.id.yupao_ertong_add:
			yupaoErtongNumber += 1;
			yupaoErtong.setText(String.valueOf(yupaoErtongNumber));
			break;
		case R.id.yupao_xiao_add:
			yupaoXiaoNumber += 1;
			yupaoXiao.setText(String.valueOf(yupaoXiaoNumber));
			break;
		case R.id.yupao_zhong_add:
			yupaoZhongNumber += 1;
			yupaoZhong.setText(String.valueOf(yupaoZhongNumber));
			break;
		case R.id.tuoxie_da_add:
			tuoxieDaNumber += 1;
			tuoxieDa.setText(String.valueOf(tuoxieDaNumber));
			break;
		case R.id.tuoxie_zhong_add:
			tuoxieZhongNumber += 1;
			tuoxieZhong.setText(String.valueOf(tuoxieZhongNumber));
			break;
		case R.id.tuoxie_xiao_add:
			tuoxieXiaoNumber += 1;
			tuoxieXiao.setText(String.valueOf(tuoxieXiaoNumber));
			break;
		case R.id.tuoxie_ertong_add:
			tuoxieErtongNumber += 1;
			tuoxieErtong.setText(String.valueOf(tuoxieErtongNumber));
			break;
		case R.id.chengren_desc:
			if (chengrenNumber > 1) {
				chengrenNumber -= 1;
				chengren.setText(String.valueOf(chengrenNumber));
			}
			break;
		case R.id.fangjian_desc:
			if (fangjianNumber >1) {
				fangjianNumber -= 1;
				fangjian.setText(String.valueOf(fangjianNumber));
			}
			break;
		case R.id.ertong_desc:
			if (ertongNumber > 0) {
				ertongNumber -= ertongNumber;
				ertong.setText(String.valueOf(ertongNumber));
			}
			break;
		case R.id.yupao_da_desc:
			if (yupaoDaNumber > 0) {
				yupaoDaNumber -= 1;
				yupaoDa.setText(String.valueOf(yupaoDaNumber));
			}
			break;
		case R.id.yupao_zhong_desc:
			if (yupaoZhongNumber > 0) {
				yupaoZhongNumber -= 1;
				yupaoZhong.setText(String.valueOf(yupaoZhongNumber));
			}
			break;
		case R.id.yupao_ertong_desc:
			if (yupaoErtongNumber > 0) {
				yupaoErtongNumber -= 1;
				yupaoErtong.setText(String.valueOf(yupaoErtongNumber));
			}
			break;
		case R.id.yupao_xiao_desc:
			if (yupaoXiaoNumber > 0) {
				yupaoXiaoNumber -= 1;
				yupaoXiao.setText(String.valueOf(yupaoXiaoNumber));
			}
			break;
		case R.id.tuoxie_da_desc:
			if (tuoxieDaNumber > 0) {
				tuoxieDaNumber -= 1;
				tuoxieDa.setText(String.valueOf(tuoxieDaNumber));
			}
			break;
		case R.id.tuoxie_zhong_desc:
			if (tuoxieZhongNumber > 0) {
				tuoxieZhongNumber -= 1;
				tuoxieZhong.setText(String.valueOf(tuoxieZhongNumber));
			}
			break;
		case R.id.tuoxie_xiao_desc:
			if (tuoxieXiaoNumber > 0) {
				tuoxieXiaoNumber -= 1;
				tuoxieXiao.setText(String.valueOf(tuoxieXiaoNumber));
			}
			break;
		case R.id.tuoxie_ertong_desc:
			if (tuoxieErtongNumber > 0) {
				tuoxieErtongNumber -= 1;
				tuoxieErtong.setText(String.valueOf(tuoxieErtongNumber));
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (resultCode == RESULT_OK) {
			if (requestCode == 1001) {
				startDate = data.getStringExtra("result");
				initStartWMD();
			} else if (requestCode == 1002) {
				endDate = data.getStringExtra("result");
				try {
					if (dateFormat.parse(endDate).getTime() < dateFormat.parse(
							startDate).getTime()) {
						Toast.makeText(this, "退房日期要大于入住日期", Toast.LENGTH_LONG)
								.show();
					} else {
						initEndWMD();
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void initStartWMD() {
		CalendarUtil calendarUtil = new CalendarUtil();
		int[] dateYMD = calendarUtil.getYMD(startDate);
		String dateM = String.valueOf(dateYMD[1]);
		String dateD = String.valueOf(dateYMD[2]);
		calendarUtil.setGregorian(dateYMD[0], dateYMD[1], dateYMD[2]);
		switch (calendarUtil.getDayOfWeek()) {
		case 1:
			startW.setText("周日");
			break;
		case 2:
			startW.setText("周一");
			break;
		case 3:
			startW.setText("周二");
			break;
		case 4:
			startW.setText("周三");
			break;
		case 5:
			startW.setText("周四");
			break;
		case 6:
			startW.setText("周五");
			break;
		case 7:
			startW.setText("周六");
			break;
		default:
			break;
		}
		startM.setText(dateM + "月");
		startD.setText(dateD);
	}

	private void initEndWMD() {
		CalendarUtil calendarUtil = new CalendarUtil();
		int[] dateYMD = calendarUtil.getYMD(endDate);
		String dateM = String.valueOf(dateYMD[1]);
		String dateD = String.valueOf(dateYMD[2]);
		calendarUtil.setGregorian(dateYMD[0], dateYMD[1], dateYMD[2]);
		switch (calendarUtil.getDayOfWeek()) {
		case 1:
			endW.setText("周日");
			break;
		case 2:
			endW.setText("周一");
			break;
		case 3:
			endW.setText("周二");
			break;
		case 4:
			endW.setText("周三");
			break;
		case 5:
			endW.setText("周四");
			break;
		case 6:
			endW.setText("周五");
			break;
		case 7:
			endW.setText("周六");
			break;
		default:
			break;
		}
		endM.setText(dateM + "月");
		endD.setText(dateD);
	}
}
