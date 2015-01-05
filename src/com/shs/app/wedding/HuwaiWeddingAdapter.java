package com.shs.app.wedding;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shs.app.R;

import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus.NmeaListener;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HuwaiWeddingAdapter extends BaseAdapter {
	private Context mContext;
	private List<Integer> list;

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public HuwaiWeddingAdapter(Context mContext) {
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.wedding_huwai_item, null);
			viewHolder.describe = (TextView) convertView.findViewById(R.id.item_describe);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String string = "户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项户外婚礼注意事项";
		viewHolder.describe.setText(Html.fromHtml(filterString(string)));
		return convertView;
	}

	private class ViewHolder {
		TextView describe;
	}

	private String filterString(String string) {
		String filterString = "";
		if (string.length() > 44) {
			filterString = string.substring(0, 40);
			filterString = filterString
					+ "...<font color='#FF9966'>[详细]</font>";
		} else {
			filterString = string + "...<font color='#FF9966'>[详细]</font>";
		}
		return filterString;
	}
}
