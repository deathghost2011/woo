package com.shs.app.wedgit.calender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tools {

	public static <T> List<T> getLastElements(ArrayList<T> list, int nbElems) {
		return list.subList(Math.max(list.size() - nbElems, 0), list.size());
	}

	public static Long getLong(Cursor c, String col) {
		return c.getLong(c.getColumnIndex(col));
	}

	public static int getInt(Cursor c, String col) {
		return c.getInt(c.getColumnIndex(col));
	}

	public static String getString(Cursor c, String col) {
		return c.getString(c.getColumnIndex(col));
	}

	public static boolean getBoolean(Cursor c, String col) {
		return getInt(c, col) == 1;
	}

	public static Date getDateSeconds(Cursor c, String col) {
		return new Date(Long.parseLong(Tools.getString(c, col)) * 1000);
	}

	public static Date getDateMilliSeconds(Cursor c, String col) {
		return new Date(Long.parseLong(Tools.getString(c, col)));
	}

	public static boolean isInt(String value) {
		try {
			// modify by sun
			Long.parseLong(value);
			return true;
		} catch (NumberFormatException nfe) {
		}
		return false;
	}

	public static Boolean parseBool(String value) {
		Boolean res = null;
		if (value.compareToIgnoreCase("true") == 0) {
			res = true;
		} else if (value.compareToIgnoreCase("false") == 0) {
			res = false;
		}
		return res;
	}

	public static Integer parseInt(String value) {
		Integer res = null;
		try {
			res = Integer.parseInt(value);
		} catch (Exception e) {
		}

		return res;
	}

	public static Integer parseInt(String value, Integer defaultValue) {
		Integer res = defaultValue;
		try {
			res = Integer.parseInt(value);
		} catch (Exception e) {
		}

		return res;
	}

	@SuppressLint("SimpleDateFormat")
	public static Date parseDate(String dateStr) {
		try {
			String pattern = "yyyy-MM-dd HH:mm";
			if (dateStr.length() == 10)
				pattern = "yyyy-MM-dd";
			else if (dateStr.length() == 16)
				pattern = "yyyy-MM-dd HH:mm";
			else if (dateStr.length() == 19)
				pattern = "yyyy-MM-dd HH:mm:ss";

			return new SimpleDateFormat(pattern).parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String formatDate(Date date) {
		return formatDateTime(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 格式化日期时间
	 * 
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String formatDateTime(Date date, String format) {
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getMinNonNeg(int... x) {
		int min = Integer.MAX_VALUE;
		for (int i : x) {
			if (i >= 0 && i < min)
				min = i;
		}
		return min;
	}

	private static String getAppBaseDir(Context ctx) {
		String filesDir = ctx.getFilesDir().toString();
		int index = filesDir.indexOf("/files");
		return filesDir.substring(0, index);
	}

	public static String getSharedPrefDir(Context ctx) {
		return getAppBaseDir(ctx) + "/shared_prefs";
	}

	public static String shortenString(String s) {
		if (s.length() > 20) {
			return s.substring(0, 20);
		} else {
			return s;
		}
	}

	public static String getTextValue(EditText text) {
		return text.getText().toString();
	}

	public static String getJsonString(JSONObject json, String key) {
		String value = "";
		if (json.has(key)) {
			try {
				value = json.getString(key);
			} catch (JSONException e) {
			}
		}
		return value;
	}

	public static int getJsonInt(JSONObject json, String key) {
		int value = -1;
		if (json.has(key)) {
			try {
				value = json.getInt(key);
			} catch (Exception e) {
			}
		}
		return value;
	}

	public static long getJsonLong(JSONObject json, String key) {
		long value = -1;
		if (json.has(key)) {
			try {
				value = json.getLong(key);
			} catch (Exception e) {
			}
		}
		return value;
	}

	public static boolean getJsonBoolean(JSONObject json, String key) {
		boolean value = false;
		if (json.has(key)) {
			try {
				value = json.getBoolean(key);
			} catch (Exception e) {
			}
		}
		return value;
	}

	public static JSONArray getJsonArray(JSONObject json, String key) {
		JSONArray value = null;
		if (json.has(key)) {
			try {
				value = json.getJSONArray(key);
			} catch (JSONException e) {
			}
		} else {
			value = new JSONArray();
		}
		return value;
	}

	public static JSONObject getJsonObject(JSONObject json, String key) {
		JSONObject result = null;
		if (json.has(key)) {
			try {
				result = json.getJSONObject(key);
			} catch (JSONException e) {
			}
		}
		return result;
	}

	/**
	 * 返回的是R.color值
	 * 
	 * @param progress
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	/*public static int getEmergencyColor(int progress, String startDate, String endDate) {
		double pastDays = System.currentTimeMillis() - parseDate(startDate).getTime();
		double totalDays = parseDate(endDate).getTime() - parseDate(startDate).getTime();
		double emergency = progress / (pastDays / totalDays) / 100;
		if (emergency > 0.8)
			return R.color.emergency_normal;
		else if (emergency > 0.6)
			return R.color.emergency_urgent;
		else
			return R.color.emergency_urgentest;
	}*/

	/**
	 * 通用标题栏的设置方法
	 * 
	 * @param activity
	 * @param title
	 *            标题
	 * @param btnRightID
	 *            右上角按钮图片id
	 * @param showBackButton
	 *            是否显示左上角按钮
	 * @param showRigthButton
	 *            是否显示右上角按钮
	 * @param backButtonClick
	 *            左上角按钮事件
	 * @param rightButtonClick
	 *            右上角按钮事件
	 */
//	public static void setCommonActivityHeader(final Activity activity, String title, OnClickListener leftButtonClick, OnClickListener rightButtonClick) {
//		((TextView) activity.findViewById(R.id.tv_title)).setText(title);
//		View leftButton = activity.findViewById(R.id.btn_left);
//		View rightButton = activity.findViewById(R.id.btn_right);
//		if (leftButtonClick != null) {
//			leftButton.setVisibility(View.VISIBLE);
//			leftButton.setOnClickListener(leftButtonClick);
//		} else {
//			leftButton.setVisibility(View.GONE);
//		}
//		if (rightButtonClick != null) {
//			rightButton.setVisibility(View.VISIBLE);
//			rightButton.setOnClickListener(rightButtonClick);
//		} else {
//			rightButton.setVisibility(View.GONE);
//		}
//	}

	/**
	 * 根据当前时间来计算任务的剩余天数
	 * 
	 * @param endDate
	 * @return add by sun
	 */
	public static int getRemainDays(String endDate) {
		long millisDiff = Tools.parseDate(endDate).getTime() - System.currentTimeMillis();
		return getDayCountByMillis(millisDiff);
	}

	/**
	 * 计算两个日期之间的天数
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static int getDayCount(String fromDate, String toDate) {
		long millisDiff = Tools.parseDate(toDate).getTime() - Tools.parseDate(fromDate).getTime();
		return getDayCountByMillis(millisDiff);
	}

	public static int getDayCount(String fromDate, long toDateMillis) {
		long millisDiff = toDateMillis - Tools.parseDate(fromDate).getTime();
		return getDayCountByMillis(millisDiff);
	}

	public static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 根据时间差（毫秒）计算相差天数
	 * 
	 * @param timeDiff
	 * @return
	 */
	private static int getDayCountByMillis(long timeDiff) {
		int result = 0;
		int c = timeDiff < 0 ? -1 : 1;
		long millisOfDay = (24 * 1000 * 3600);
		// 相差不到24小时
		if (Math.abs(timeDiff) < millisOfDay) {
			result = 1; // 不足24小时算一天
		} else {
			result = (int) (Math.abs(timeDiff) / millisOfDay) + (Math.abs(timeDiff) % millisOfDay > 0 ? 1 : 0);
		}
		return result * c;
	}

	/**
	 * 转换手机号码格式，如果返回null，则不是手机号
	 * 
	 * @param str
	 * @return
	 */
	public static String getAvailableMobile(String str) {
		// 先去掉空格
		str = str.replace(" ", "");
		if (str.startsWith("+86"))
			str = str.substring(3);
		// 如果是11为数字，直接返回
		if (str.length() == 11 && TextUtils.isDigitsOnly(str) && str.startsWith("1"))
			return str;

		return null;
	}

}
