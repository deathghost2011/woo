package com.shs.app.meals;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shs.app.R;
import com.tailinkj.manager.Mananger;

public class ShopingCardActivity extends Activity implements OnClickListener{
	private Button left;
	private Button right,jiesuan;
	private TextView title;
	private CheckBox button;
	private XlistView xlistView;
	List<ZaoCan> li;
	ShopingCardAdapter adapter;
	public static Handler handler;
	static TextView shuliang,zongjia,zongjia3;
	Mananger mananger;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoing_card);
		left = (Button) this.findViewById(R.id.btn_left);
		left.setOnClickListener(this);
		right = (Button) this.findViewById(R.id.btn_right);
		right.setVisibility(View.GONE);
		title = (TextView) this.findViewById(R.id.pub_title);
		title.setText("购物车");
		shuliang=(TextView) findViewById(R.id.shopingcard_total);
		zongjia=(TextView) findViewById(R.id.zongjia2);
		zongjia3=(TextView) findViewById(R.id.zongjia3);
		xlistView=(XlistView) findViewById(R.id.shopingcard_xlistview);
		jiesuan=(Button) findViewById(R.id.jiesuan);
		li=new ArrayList<ZaoCan>();
//		Mananger.SqlCreate(ShopingCardActivity.this);
		mananger=new Mananger();
//		mananger.SqlSave(this, new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","1","5",false));
//		mananger.SqlSave(this, new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","2","5","1","5",false));
//		mananger.SqlSave(this, new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","2","1","5",false));
//		mananger.SqlSave(this, new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","2","1","5",true));
		adapter=new ShopingCardAdapter(this);
		li=(List<ZaoCan>) mananger.SqlSelectAll(this, "");
		adapter.setList(li);
		xlistView.setAdapter(adapter);
		jiesuan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mananger.SqlDeleteAll(ShopingCardActivity.this, "");
				for (int i = 0; i < xlistView.getChildCount(); i++) {
					View layout=xlistView.getChildAt(i);
					CheckBox button=(CheckBox) layout.findViewById(R.id.shopingcard_readiobutton1);
					if(!button.isChecked()){
						mananger.SqlSave(ShopingCardActivity.this, li.get(i));
					}else{
						//选中的
					}
				}
				finish();
//				li=(List<ZaoCan>) mananger.SqlSelectAll(ShopingCardActivity.this, "");
//				adapter.setList(li);
//				xlistView.setAdapter(adapter);
			}
		});
		button=(CheckBox) findViewById(R.id.shopingcard_quanxuan);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getTag()!=null&&v.getTag().equals("2")){
					for (int i = 0; i < li.size(); i++) {
						li.get(i).setXuanzhong(false);
					}
					adapter.notifyDataSetChanged();	
					handler.sendEmptyMessage(1);
					v.setTag("1");
				}else{
					for (int i = 0; i < li.size(); i++) {
						li.get(i).setXuanzhong(true);
					}
					adapter.notifyDataSetChanged();	
					handler.sendEmptyMessage(1);
					v.setTag("2");
				}
			}
		});
		 handler=new Handler(new Handler.Callback() {
				
				@Override
				public boolean handleMessage(Message msg) {
					// TODO Auto-generated method stub
					int shuliang2 = 0;
					double zongjia2 = 0;
					for (int i= 0; i < li.size(); i++) {
						if(li.get(i).isXuanzhong()){
							shuliang2+=Integer.parseInt(li.get(i).getShuliang());
							zongjia2+=Double.parseDouble(li.get(i).getPrice())*Integer.parseInt(li.get(i).getShuliang());
						}
						shuliang.setText("共"+shuliang2+"件");
						shuliang.setTextSize(18);
						zongjia.setText("￥"+zongjia2);
						zongjia.setTextSize(18);
						zongjia3.setText("￥"+zongjia2);
						zongjia3.setTextSize(18);
					}
					return false;
				}
			});
			handler.sendEmptyMessage(1);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.btn_left:
			
			mananger.SqlDeleteAll(ShopingCardActivity.this, "");
			for (int i = 0; i < xlistView.getChildCount(); i++) {
				View layout=xlistView.getChildAt(i);
				CheckBox button=(CheckBox) layout.findViewById(R.id.shopingcard_readiobutton1);
					mananger.SqlSave(ShopingCardActivity.this, li.get(i));
			}
			this.finish();
			break;
		}
	}
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:{
			
			mananger.SqlDeleteAll(ShopingCardActivity.this, "");
			for (int i = 0; i < xlistView.getChildCount(); i++) {
				View layout=xlistView.getChildAt(i);
				CheckBox button=(CheckBox) layout.findViewById(R.id.shopingcard_readiobutton1);
					mananger.SqlSave(ShopingCardActivity.this, li.get(i));
			}
			finish();
		}

		break;

		default:
			break;
		}
		return super.onKeyUp(keyCode, event);
	}
}
