package com.shs.app.meals;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.shs.app.R;

public class ShopingCardActivity extends Activity implements OnClickListener{
	private Button left;
	private Button right;
	private TextView title;
	private RadioButton button;
	private XlistView xlistView;
	List<ZaoCan> li;
	ShopingCardAdapter adapter;
	public static Handler handler;
	static TextView shuliang,zongjia,zongjia3;
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
		shuliang=(TextView) findViewById(R.id.total);
		zongjia=(TextView) findViewById(R.id.zongjia2);
		zongjia3=(TextView) findViewById(R.id.zongjia3);
		xlistView=(XlistView) findViewById(R.id.shopingcard_xlistview);
		li=new ArrayList<ZaoCan>();
		adapter=new ShopingCardAdapter(this);
		li.add(new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","5","5",false));
		li.add(new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","5","5",true));
		li.add(new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","5","5",true));
		li.add(new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","5","5",true));
		li.add(new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","5","5",true));
		li.add(new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","5","5",true));
		li.add(new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","5","5",true));
		li.add(new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","5","5",true));
		li.add(new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","5","5",true));
		li.add(new ZaoCan("http://pic.nipic.com/2007-11-09/2007119122325154_2.jpg","4","3","5","5",true));
		adapter.setList(li);
		xlistView.setAdapter(adapter);
	
		button=(RadioButton) findViewById(R.id.shopingcard_quanxuan);
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
					int shuliang2 = 0,zongjia2 = 0;
					for (int i= 0; i < li.size(); i++) {
						if(li.get(i).isXuanzhong()){
							shuliang2++;
							zongjia2+=Integer.parseInt(li.get(i).getPrice());
						}
						shuliang.setText("共"+shuliang2+"件");
						shuliang.setTextSize(18);
						zongjia.setText("￥"+zongjia2);
						zongjia.setTextSize(18);
						zongjia3.setText("￥"+zongjia2);
						zongjia3.setTextSize(18);
					}
					System.out.println(li.size()+"------");
					return false;
				}
			});
			handler.sendEmptyMessage(1);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.btn_left:
			this.finish();
			break;
		}
	}


}
