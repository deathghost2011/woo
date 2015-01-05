package com.shs.app.main;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shs.app.R;
import com.shs.app.kefangyuding.KeFangYuDingActivity;
import com.shs.app.libin.LibinActivity;
import com.shs.app.login_register.LoginActivity;
import com.shs.app.login_register.RegisterActivity;
import com.shs.app.meals.MealsActivity;
import com.shs.app.meeting.MeetingFragmentActivity;
import com.shs.app.meeting.MeetingFragmentPre;
import com.shs.app.personal.AccountActivity;
import com.shs.app.restaurant.RestaurantActivity;
import com.shs.app.shehuishan.IntroduceActivity;
import com.shs.app.springs.SpringActivity;
import com.shs.app.wedding.WeddingActivity;

public class SlidFra extends Fragment {
	private static final String TAG = SlidFra.class.getSimpleName();
	private TextView jiudianqun,dichan,shangyi,login, register;
	private GridView gv;
	private List<String> textList;
	private List<Integer> imgList;
	private View v1, v2, v3,v4,v5;
	private ImageView menu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.slidfra, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		menu = (ImageView) getActivity().findViewById(R.id.slid_menu);
		menu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainAct.sm.toggle();
			}
		});
		v1 = getActivity().findViewById(R.id.v1);
		v2 = getActivity().findViewById(R.id.v2);
		v3 = getActivity().findViewById(R.id.v3);
		v4 = getActivity().findViewById(R.id.v4);
		v5 = getActivity().findViewById(R.id.v5);
		jiudianqun = (TextView) getActivity().findViewById(R.id.jiudianqun);
		jiudianqun.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v1.setBackgroundColor(getResources().getColor(
						R.color.slid_text_check));
				v2.setBackgroundColor(getResources().getColor(R.color.white));
				v3.setBackgroundColor(getResources().getColor(R.color.white));
				v4.setBackgroundColor(getResources().getColor(R.color.white));
				v5.setBackgroundColor(getResources().getColor(R.color.white));
			}
		});
		dichan = (TextView) getActivity().findViewById(R.id.dichan);
		dichan.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v1.setBackgroundColor(getResources().getColor(
						R.color.white));
				v2.setBackgroundColor(getResources().getColor(R.color.slid_text_check));
				v3.setBackgroundColor(getResources().getColor(R.color.white));
				v4.setBackgroundColor(getResources().getColor(R.color.white));
				v5.setBackgroundColor(getResources().getColor(R.color.white));
			}
		});
		shangyi = (TextView) getActivity().findViewById(R.id.shangye);
		shangyi.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v1.setBackgroundColor(getResources().getColor(
						R.color.white));
				v2.setBackgroundColor(getResources().getColor(R.color.white));
				v3.setBackgroundColor(getResources().getColor(R.color.slid_text_check));
				v4.setBackgroundColor(getResources().getColor(R.color.white));
				v5.setBackgroundColor(getResources().getColor(R.color.white));
			}
		});
		register = (TextView) getActivity().findViewById(R.id.register);
		register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v1.setBackgroundColor(getResources().getColor(
						R.color.white));
				v2.setBackgroundColor(getResources().getColor(R.color.white));
				v3.setBackgroundColor(getResources().getColor(R.color.white));
				v4.setBackgroundColor(getResources().getColor(R.color.slid_text_check));
				v5.setBackgroundColor(getResources().getColor(R.color.white));
				Intent intent = new Intent(getActivity(),RegisterActivity.class);
				getActivity().startActivity(intent);
			}
		});
		login = (TextView) getActivity().findViewById(R.id.login);
		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v1.setBackgroundColor(getResources().getColor(
						R.color.white));
				v2.setBackgroundColor(getResources().getColor(R.color.white));
				v3.setBackgroundColor(getResources().getColor(R.color.white));
				v4.setBackgroundColor(getResources().getColor(R.color.white));
				v5.setBackgroundColor(getResources().getColor(R.color.slid_text_check));
				Intent intent = new Intent(getActivity(),LoginActivity.class);
				getActivity().startActivity(intent);
			}
		});
		initList();
		gv = (GridView) getActivity().findViewById(R.id.slid_gv);
		gv.setAdapter(new SlidGVAdapter());
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					getActivity().startActivity(new Intent(getActivity(), IntroduceActivity.class));
					break;
				case 1:
					getActivity().startActivity(new Intent(getActivity(), KeFangYuDingActivity.class));
					break;
				case 2:
					getActivity().startActivity(new Intent(getActivity(), SpringActivity.class));
					break;
				case 3:
					getActivity().startActivity(new Intent(getActivity(), MeetingFragmentActivity.class));
					break;
				case 4:
					getActivity().startActivity(new Intent(getActivity(), WeddingActivity.class));
					break;
				case 5:
					getActivity().startActivity(new Intent(getActivity(), RestaurantActivity.class));
					break;
				case 6:
					getActivity().startActivity(new Intent(getActivity(), MealsActivity.class));
					break;
				case 7:
					getActivity().startActivity(new Intent(getActivity(), LibinActivity.class));
					break;
				case 8:
					getActivity().startActivity(new Intent(getActivity(), LibinActivity.class));
					break;
				case 9:
					getActivity().startActivity(new Intent(getActivity(), FeedBackActivity.class));
					break;
				default:
					break;
				}

			}
		});
	}

	private void initList() {
		textList = new ArrayList<String>();
		textList.add("社会山介绍");
		textList.add("客房预定");
		textList.add("嘉佑温泉");
		textList.add("会议邀请");
		textList.add("婚礼婚宴");
		textList.add("餐厅酒吧");
		textList.add("客房送餐");
		textList.add("礼宾服务");
		textList.add("客房服务");
		textList.add("投诉建议");
		imgList = new ArrayList<Integer>();
		imgList.add(R.drawable.l1);
		imgList.add(R.drawable.l2);
		imgList.add(R.drawable.l3);
		imgList.add(R.drawable.l4);
		imgList.add(R.drawable.l5);
		imgList.add(R.drawable.l6);
		imgList.add(R.drawable.l7);
		imgList.add(R.drawable.l8);
		imgList.add(R.drawable.l9);
		imgList.add(R.drawable.l10);
	}

	class SlidGVAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imgList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = LayoutInflater.from(getActivity()).inflate(
					R.layout.slid_grid_item, null);
			ImageView iv = (ImageView) v.findViewById(R.id.slid_grid_item_iv);
			TextView tv = (TextView) v.findViewById(R.id.slid_grid_item_tv);
			iv.setImageResource(imgList.get(position));
			tv.setText(textList.get(position));
			return v;
		}

	}
}
