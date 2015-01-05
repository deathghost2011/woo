package com.shss.wedgit.dialog;

import java.util.List;

import com.shs.app.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class OptionsDialog extends Dialog {
	/**
	 * 一般的选择框，解析值的时候使用DictMapping.getTextByKey(dictName,
	 * key);或者DictMapping.getKeyByText(dictName, text);解析
	 */
	public static int OPTION_DIALOG_TYPE_NORMAL = 1;
	/**
	 * 特殊的选择框，在OnSelectWithValueListener中传递文本和对应的 值，如省市的选择
	 */
	public static int OPTION_DIALOG_TYPE_SPECIFICAL = 2;

	/**
	 * 当前的选择框类型
	 */
	private static int CURRENT_DIALOG_TYPE = 0;

	private static final int maxDispLine = 6;
	private static final int heightOfPerItem = 35;
	private float heightOfPerItemPx;
	private float maxHeightPx;

	private Context context;
	private String dialogTitle;
	private String itemArray[];
	private String itemValue[];
	private ListView listView;
	private TextView textDialogTitle;
	private OnSelectedListener onSelectListener;
	// private OnSelectNameListener onSelectNameListener;
	private OnSelectWithValueListener onSelectWithValueListener;
	private View viewObject;
	private int mDataTextSizeInSP = 16;

	public OptionsDialog(Context context, View viewObject, String dialogTitle, int arrayResId, OnSelectedListener onSelectListener) {
		super(context, R.style.CommonDialog);
		this.dialogTitle = dialogTitle == null ? "请选择": dialogTitle;
		itemArray = context.getResources().getStringArray(arrayResId);
		this.context = context;
		this.onSelectListener = onSelectListener;
		CURRENT_DIALOG_TYPE = OPTION_DIALOG_TYPE_NORMAL;
		this.viewObject = viewObject;
		if (this.viewObject != null)
			viewObject.setClickable(false);
	}
	
	public OptionsDialog(Context context, View viewObject, String dialogTitle, String[] options, OnSelectedListener onSelectListener) {
		super(context, R.style.CommonDialog);
		this.dialogTitle = dialogTitle == null ? "请选择": dialogTitle;
		itemArray = options;
		this.context = context;
		this.onSelectListener = onSelectListener;
		CURRENT_DIALOG_TYPE = OPTION_DIALOG_TYPE_NORMAL;
		this.viewObject = viewObject;
		if (this.viewObject != null)
			viewObject.setClickable(false);
	}

	/**
	 * 特殊选项
	 * 
	 * @param context
	 * @param dialogTitle
	 * @param items
	 * @param onSelectWithValueListener
	 */
	public OptionsDialog(Context context, View viewObject, String dialogTitle, List<NameValuePair> items,
			OnSelectWithValueListener onSelectWithValueListener) {
		super(context, R.style.CommonDialog);
		this.dialogTitle = dialogTitle == null ? "请选择": dialogTitle;
		this.viewObject = viewObject;
		viewObject.setClickable(false);
		if (null != items && items.size() > 0) {
			itemArray = new String[items.size()];
			itemValue = new String[items.size()];
			for (int i = 0; i < items.size(); i++) {
				NameValuePair item = items.get(i);
				itemArray[i] = item.getName();
				itemValue[i] = item.getValue();
			}
		}
		this.context = context;
		this.onSelectWithValueListener = onSelectWithValueListener;
		CURRENT_DIALOG_TYPE = OPTION_DIALOG_TYPE_SPECIFICAL;
	}
	
	public void setDataTextSize(int size){
		this.mDataTextSizeInSP = size;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pub_dialog_options);
		heightOfPerItemPx = context.getResources().getDisplayMetrics().density * heightOfPerItem;
		maxHeightPx = heightOfPerItemPx * maxDispLine;
		listView = (ListView) findViewById(R.id.list);
		textDialogTitle = (TextView) findViewById(R.id.textDialogTitle);
		textDialogTitle.setText(dialogTitle);
		initDataLines();
		if (itemArray.length > maxDispLine) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) maxHeightPx);
			listView.setLayoutParams(params);
			listView.requestLayout();
		}
	}

	private void initDataLines() {
		listView.setAdapter(new MyAdapter());
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				OptionsDialog.this.dismiss();
				if (viewObject != null)
					viewObject.setClickable(true);
				if (CURRENT_DIALOG_TYPE == OPTION_DIALOG_TYPE_NORMAL) {
					if (onSelectListener != null) {
						onSelectListener.onSeleted(itemArray[index]);
					}
				}
				if (CURRENT_DIALOG_TYPE == OPTION_DIALOG_TYPE_SPECIFICAL) {
					if (onSelectWithValueListener != null) {
						onSelectWithValueListener.onSeleted(itemArray[index], itemValue[index]);
					}
				}

			}

		});
	}

	@Override
	protected void onStop() {
		if (viewObject != null)
			viewObject.setClickable(true);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (viewObject != null)
			viewObject.setClickable(true);
	}

	/**
	 * 一般选择框，不带值
	 * 
	 * @author Administrator
	 * 
	 */
	public static interface OnSelectedListener {

		public void onSeleted(String value);

	}

	/**
	 * 选项带值
	 * 
	 * @author Administrator
	 * 
	 */
	public static interface OnSelectWithValueListener {
		public void onSeleted(String text, String value);
	}

	/**
	 * 选择出险人名
	 * 
	 * @author Administrator
	 * 
	 */
	public static interface OnSelectNameListener {
		public void onSeleted(Object object);
	}

	class MyAdapter extends BaseAdapter {

		class ViewHolder {
			TextView txt;
		}

		@Override
		public int getCount() {
			return itemArray.length;
		}

		@Override
		public Object getItem(int position) {
			return itemArray[position];
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;

			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.pub_dialog_options_item, null);
				TextView text = (TextView) convertView.findViewById(R.id.text);
				holder = new ViewHolder();
				holder.txt = text;
				holder.txt.setTextSize(mDataTextSizeInSP);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.txt.setText(itemArray[position]);

			return convertView;
		}

	}
	
	public static class NameValuePair{
		
		private String name;
		private String value;
		
		public NameValuePair(String name, String value){
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}

		public void setKey(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "name:" + name + ", value:" + value;
		}
		
	}

}
