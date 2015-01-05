package com.shs.app.wedgit.calender;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.shs.app.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CalendarView extends View {

	// 画布宽高
	private float mCanvasWidth;
	private float mCanvasHeight;

	private Paint mPaint, mPaint2, mPaint3, mPaint4;

	// 单元格的宽高
	private float mCellWidth;
	private float mCellHeight;

	// 字体大小
	private float mTextSize;

	// 当前年份
	private int mYear = 2014;
	// 当前月份
	private int mMonth = 5;

	// cell index与日期字符串的映射map
	@SuppressLint("UseSparseArrays")
	private Map<Integer, String> mDateMap = new HashMap<Integer, String>();

	// 已选择的日期（并非指在当前View中高亮选中的，而是parent中已选择的日期）
	private String mSelectedDateStr;

	// 日期选中事件
	private OnDateSelectedListener mOnDateSelectedListener;

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Style.FILL);
		mPaint.setColor(getResources().getColor(R.color.blue));
		Calendar cal = Calendar.getInstance();
		this.mYear = cal.get(Calendar.YEAR);
		this.mMonth = cal.get(Calendar.MONTH) + 1;
		mPaint2 = new Paint();
		mPaint2.setFlags(Paint.ANTI_ALIAS_FLAG);
		mPaint2.setStyle(Style.FILL);
		mPaint2.setColor(getResources().getColor(
				R.color.calendar_date_text_black));
		mPaint2.setTextSize(12);
		// 阳历节假日画笔----------------
		mPaint3 = new Paint();
		mPaint3.setFlags(Paint.ANTI_ALIAS_FLAG);
		mPaint3.setStyle(Style.FILL);
		mPaint3.setColor(getResources().getColor(
				R.color.calendar_date_text_black));
		mPaint3.setTextSize(12);
		// 农历节假日画笔----------------
		mPaint4 = new Paint();
		mPaint4.setFlags(Paint.ANTI_ALIAS_FLAG);
		mPaint4.setStyle(Style.FILL);
		mPaint4.setColor(getResources().getColor(
				R.color.calendar_date_text_black));
		mPaint4.setTextSize(12);
	}

	public CalendarView(Context context, int year, int month,
			String selectedDateStr) {
		super(context);
		this.mYear = year;
		this.mMonth = month;
		setCurrentSelectedDate(selectedDateStr);
		mPaint = new Paint();
		mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Style.FILL);
		mPaint.setColor(getResources().getColor(R.color.blue));
		mPaint2 = new Paint();
		mPaint2.setFlags(Paint.ANTI_ALIAS_FLAG);
		mPaint2.setStyle(Style.FILL);
		mPaint2.setColor(getResources().getColor(
				R.color.calendar_date_text_black));
		mPaint2.setTextSize(12);
		// 阳历节假日画笔----------------
		mPaint3 = new Paint();
		mPaint3.setFlags(Paint.ANTI_ALIAS_FLAG);
		mPaint3.setStyle(Style.FILL);
		mPaint3.setColor(getResources().getColor(
				R.color.calendar_date_text_black));
		mPaint3.setTextSize(12);
		// 农历节假日画笔----------------
		mPaint4 = new Paint();
		mPaint4.setFlags(Paint.ANTI_ALIAS_FLAG);
		mPaint4.setStyle(Style.FILL);
		mPaint4.setColor(getResources().getColor(
				R.color.calendar_date_text_black));
		mPaint4.setTextSize(12);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 绘制表头（星期）
		drawHeader(canvas);
		drawDateCells(canvas);
	}

	/**
	 * 设置当前日期字符串
	 * 
	 * @param dateStr
	 */
	public void setCurrentSelectedDate(String dateStr) {
		if (dateStr.indexOf(":") != -1)
			dateStr = dateStr.substring(0, 10);
		this.mSelectedDateStr = dateStr;
	}

	public void refreshView() {
		invalidate();
	}

	public void setOnDateSelectedListener(OnDateSelectedListener listener) {
		this.mOnDateSelectedListener = listener;
	}

	/**
	 * 绘制表头（星期）
	 * 
	 * @param canvas
	 */
	private void drawHeader(Canvas canvas) {
		// 绘制一条线
		mPaint2.setColor(getResources().getColor(R.color.blue));
		canvas.drawLine(8, mCellHeight, mCellWidth * 7 - 8, mCellHeight,
				mPaint2);
		mPaint2.setStrokeWidth(2);
		mPaint2.setStyle(Style.STROKE);
		canvas.drawCircle(mCellWidth * 1 / 2, mCellHeight * 1 / 2,
				24, mPaint2);
		canvas.drawCircle(mCellWidth * 13 / 2, mCellHeight * 1 / 2,
				24, mPaint2);
		String[] days = { "日", "一", "二", "三", "四", "五", "六" };
		mPaint.setTextSize(mTextSize);
		float leftMargin = (mCellWidth - mPaint.measureText(days[0])) / 2;
		for (int i = 0; i < 7; i++) {
			RectF cellRect = new RectF(mCellWidth * i, 0, mCellWidth * (i + 1),
					mCellHeight);
			mPaint.setColor(getResources().getColor(R.color.transparent));
			// mPaint.setColor(getResources().getColor(R.color.calendar_date_cell_gray));
			canvas.drawRect(cellRect, mPaint);
			mPaint.setColor(getResources().getColor(R.color.pink));
			canvas.drawText(days[i], cellRect.left + leftMargin,
					cellRect.bottom - mTextSize, mPaint);
		}
	}

	private void drawDateCells(Canvas canvas) {
		// 当月第1号的cell坐标
		int cellIndexOfFirstDay = 0;
		// 当月最后一天的cell坐标
		int cellIndexOfLastDay = 0;

		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, this.mYear);
		cal.set(Calendar.MONTH, this.mMonth - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// 获取当月天数
		int dayCountOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 获取当月1号是星期几
		int dayOfFirstDate = cal.get(Calendar.DAY_OF_WEEK);
		cellIndexOfFirstDay = dayOfFirstDate - 1;
		cellIndexOfLastDay = cellIndexOfFirstDay + dayCountOfMonth;

		// 循环填充当月日期字符
		int date = 1;
		for (int i = cellIndexOfFirstDay; i < cellIndexOfLastDay; i++) {
			String dateStr = generateDateStr(mYear, mMonth, date);
			// 往画布上标记今天----------------------------------------------------------------------mayu
			drawToday(i, canvas, mYear, mMonth, date);
			drawCellBg(canvas, i, dateStr);
			mDateMap.put(i, dateStr);
			float[] textPosition = getTextPositionByCellIndex(i,
					String.valueOf(date), mPaint);
			setTextColorByCellIndex(dateStr, true);
			canvas.drawText(String.valueOf(date), textPosition[0],
					textPosition[1], mPaint);
			if (!drawHoliday(i, canvas, date, true)) {
				System.out.println("往画布上绘制农历-----------------"+i);
				// 往画布上绘制当前月的农历----------------------------------------------------------------------mayu
				drawChineseDate(i, canvas, textPosition, mYear, mMonth, date,
						mPaint4, true);
			}
			;
			date++;
		}
		// 填充上月末尾日期和下月开始日期
		fillPrevAndNextMonthCellsData(canvas, cellIndexOfFirstDay,
				cellIndexOfLastDay, cal);
	}

	private boolean drawHoliday(int i, Canvas canvas, int date,
			boolean isCurrentMounth) {
		if (isCurrentMounth) {
			mPaint3.setColor(getResources().getColor(R.color.pink));
		} else {
			mPaint3.setColor(getResources().getColor(
					R.color.calendar_date_text_gray));
		}
		String gregorianHolidayText = CalendarUtil.getGregorianHoliday(mMonth,
				date);
		String chineseHolidayText2 = CalendarUtil.getChineseHoliday(mYear,
				mMonth, date);
		// 农历颜色设置：正常日期为灰色，节假日为红色
		if (!gregorianHolidayText.equals("") || !chineseHolidayText2.equals("")) {
			if (chineseHolidayText2.equals("")) {
				float[] textPosition2 = getTextPositionByCellIndex(i,
						gregorianHolidayText, mPaint3);
				canvas.drawText(gregorianHolidayText, textPosition2[0],
						textPosition2[1], mPaint3);
			} else if (gregorianHolidayText.equals("")) {
				float[] textPosition2 = getTextPositionByCellIndex(i,
						chineseHolidayText2, mPaint3);
				canvas.drawText(chineseHolidayText2, textPosition2[0],
						textPosition2[1], mPaint3);
			} else {
				float[] textPosition2 = getTextPositionByCellIndex(i,
						chineseHolidayText2 + "(" + gregorianHolidayText + ")",
						mPaint3);
				canvas.drawText(chineseHolidayText2 + "("
						+ gregorianHolidayText + ")", textPosition2[0],
						textPosition2[1], mPaint3);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 绘制节假日
	 * 
	 * @param index
	 * @param canvas
	 * @param text
	 */
	/*
	 * private void drawHoliday(int index,Canvas canvas,String text){
	 * //农历颜色设置：正常日期为灰色，节假日为红色 float[] textPosition2 =
	 * getTextPositionByCellIndex(index, text, mPaint2); canvas.drawText(text,
	 * textPosition2[0], textPosition2[1], mPaint2);
	 * //--------------------------
	 * --------------------------------------------end }
	 */

	/**
	 * 往画布上绘制今天
	 * 
	 * @param i
	 * @param canvas
	 * @param Year
	 * @param Month
	 * @param date
	 */
	public void drawToday(int i, Canvas canvas, int Year, int Month, int date) {
		if (getCurrentDateTimeStr().equals(generateDateStr(Year, Month, date))) {
			mPaint2.setColor(getResources().getColor(
					R.color.calendar_date_text_red));
			mPaint2.setTextSize(14);
			mPaint2.setColor(getResources().getColor(
					R.color.pink));
			mPaint2.setStyle(Style.FILL_AND_STROKE);
			// 280dp/7 - 4dp = 36dp
//			canvas.drawCircle(
//					(getRectByCellIndex(i).right + getRectByCellIndex(i).left) * 1 / 2,
//					(getRectByCellIndex(i).bottom + getRectByCellIndex(i).top) * 1 / 2,
//					36, mPaint2);
			// ----------------------------------------------------------------------------------------------------------
//			 canvas.drawText("今天", getRectByCellIndex(i).left +
//			 mPaint2.getTextSize()/2, getRectByCellIndex(i).top +
//			 mPaint2.getTextSize() * 3/2, mPaint2);
			// 绘制这个三角形,你可以绘制任意多边形
			 Path path = new Path();
			 path.moveTo(getRectByCellIndex(i).left * 1/3 +
			 getRectByCellIndex(i).right * 2/3,
			 getRectByCellIndex(i).bottom);// 此点为多边形的起点
			 path.lineTo(getRectByCellIndex(i).right,
			 getRectByCellIndex(i).top * 1/4 + getRectByCellIndex(i).bottom *
			 3/4);
			 path.lineTo(getRectByCellIndex(i).right,
			 getRectByCellIndex(i).bottom);
			 path.close(); // 使这些点构成封闭的多边形
			 canvas.drawPath(path, mPaint2);
		}
	}

	/**
	 * 往画布上绘制农历
	 * 
	 * @param index
	 *            单元格位置
	 * @param canvas
	 *            画布
	 * @param textPosition
	 *            画笔位置
	 * @param mYear
	 *            年
	 * @param mMonth
	 *            月
	 * @param date
	 *            日
	 * @param mPaint2
	 *            画笔
	 * @param color
	 *            颜色值
	 */
	private void drawChineseDate(int index, Canvas canvas,
			float[] textPosition, int mYear, int mMonth, int date,
			Paint mPaint4, boolean isCurrentMounth) {
		mPaint4.setColor(getResources().getColor(
				R.color.pink));
		// 往画布上绘制农历----------------------------------------------------------------------mayu
		CalendarUtil c = new CalendarUtil();
		c.setGregorian(mYear, mMonth, date);
		c.computeChineseFields();
		c.computeSolarTerms();
		String text = c.getDateString();
		// 农历颜色设置：正常日期为灰色，节假日为红色
		float[] textPosition2 = getTextPositionByCellIndex(index, text, mPaint4);
		if (isCurrentMounth) {
			for (int i = 0; i < c.getChineseDayNames().length; i++) {
				if (c.getChineseDayNames()[i].equals(c.getDateString())) {
					mPaint4.setColor(getResources()
							.getColor(R.color.calendar_date_text_gray));
					break;
				}
			}
		} else {
			mPaint4.setColor(getResources().getColor(
					R.color.calendar_date_text_gray));
		}
		canvas.drawText(text, textPosition2[0], textPosition2[1], mPaint4);
		// ----------------------------------------------------------------------end
	}

	/**
	 * 绘制单元格背景----颜色为蓝色
	 * 
	 * @param canvas
	 * @param index
	 * @param date
	 */
	private void drawCellBg(Canvas canvas, int index, String date) {
		RectF cellRect = getRectByCellIndex(index);
		setCellColorByCellIndex(date, index);
		// 矩形，蓝色背景
		// canvas.drawRect(cellRect, mPaint);
		// canvas.drawCircle((getRectByCellIndex(i).right +
		// getRectByCellIndex(i).left) * 1/2, (getRectByCellIndex(i).bottom +
		// getRectByCellIndex(i).top) * 1/2, 36, mPaint2);
		// 圆形，红色背景
		float cx = (cellRect.right + cellRect.left) * 1 / 2;
		float cy = (cellRect.top + cellRect.bottom) * 1 / 2;
		canvas.drawCircle(cx, cy, 36, mPaint);
	}

	private String generateDateStr(int year, int month, int date) {
		StringBuffer sb = new StringBuffer(year + "").append("-");
		sb.append(month < 10 ? ("0" + month) : month).append("-");
		sb.append(date < 10 ? ("0" + date) : date);
		return sb.toString();
	}

	/**
	 * 填充上月末尾几天和下月开头几天的日期，保证42个日期格占满
	 * 
	 * @param prevEndIndex
	 *            -上个月最后一天所在cell的index
	 * @param currEndIndex
	 *            -当前月最后一天所在单元格的index
	 * @param currentMonthCalendar
	 */
	private void fillPrevAndNextMonthCellsData(Canvas canvas, int prevEndIndex,
			int currEndIndex, GregorianCalendar currentMonthCalendar) {
		GregorianCalendar calPrev = (GregorianCalendar) currentMonthCalendar
				.clone();
		calPrev.add(Calendar.MONTH, -1);
		GregorianCalendar calNext = (GregorianCalendar) currentMonthCalendar
				.clone();
		calNext.add(Calendar.MONTH, 1);
		// 上个月最后一天的日期
		int lastDateOfPrevMonth = calPrev
				.getActualMaximum(Calendar.DAY_OF_MONTH);
		mPaint.setColor(getResources()
				.getColor(R.color.calendar_date_text_gray));
		for (int i = prevEndIndex - 1; i >= 0; i--) {
			String dateStr = generateDateStr(calPrev.get(Calendar.YEAR),
					calPrev.get(Calendar.MONTH) + 1, lastDateOfPrevMonth);
			drawCellBg(canvas, i, dateStr);
			mDateMap.put(i, dateStr);
			float[] textPosition = getTextPositionByCellIndex(i,
					String.valueOf(lastDateOfPrevMonth), mPaint);
			setTextColorByCellIndex(
					generateDateStr(calPrev.get(Calendar.YEAR),
							calPrev.get(Calendar.MONTH) + 1,
							lastDateOfPrevMonth), false);
			canvas.drawText(String.valueOf(lastDateOfPrevMonth),
					textPosition[0], textPosition[1], mPaint);
			if (!drawHoliday(i, canvas, lastDateOfPrevMonth, false)) {
				// 往画布上绘制农历----------------------------------------------------------------------mayu
				drawChineseDate(i, canvas, textPosition,
						calPrev.get(Calendar.YEAR),
						calPrev.get(Calendar.MONTH) + 1, lastDateOfPrevMonth,
						mPaint4, false);
			}
			;
			lastDateOfPrevMonth--;
		}
		int t_day = 1;
		for (int i = currEndIndex; i < 42; i++) {
			String dateStr = generateDateStr(calNext.get(Calendar.YEAR),
					calNext.get(Calendar.MONTH) + 1, t_day);
			drawCellBg(canvas, i, dateStr);
			mDateMap.put(i, dateStr);
			float[] textPosition = getTextPositionByCellIndex(i,
					String.valueOf(t_day), mPaint);
			setTextColorByCellIndex(
					generateDateStr(calNext.get(Calendar.YEAR),
							calNext.get(Calendar.MONTH) + 1, t_day), false);
			canvas.drawText(String.valueOf(t_day), textPosition[0],
					textPosition[1], mPaint);
			if (!drawHoliday(i, canvas, t_day, false)) {
				// 往画布上绘制农历----------------------------------------------------------------------mayu
				drawChineseDate(i, canvas, textPosition,
						calNext.get(Calendar.YEAR),
						calNext.get(Calendar.MONTH) + 1, t_day, mPaint4, false);
			}
			;
			t_day++;
		}
	}

	/**
	 * 根据cell的index设置画笔颜色（针对文字）
	 * 
	 * @param i
	 * @param isCurrentMonth
	 *            -是否是当前月份的单元格
	 */
	private void setTextColorByCellIndex(String date, boolean isCurrentMonth) {
		int textColor = 0;
		if (date.equals(mSelectedDateStr)) {
			textColor = getResources().getColor(R.color.white);
		} else {
			// textColor = getResources().getColor(isCurrentMonth ?
			// R.color.calendar_date_text_black :
			// R.color.calendar_date_text_gray);
			textColor = getResources().getColor(
					isCurrentMonth ? R.color.calendar_date_text_black
							: R.color.calendar_date_text_gray);
		}
		mPaint.setColor(textColor);
	}

	/**
	 * 根据cell的index设置画笔颜色（针对单元格背景）
	 * 
	 * @param i
	 */
	private void setCellColorByCellIndex(String date, int index) {
		System.out.println("date:--------------\n" + date);
		int cellColor = 0;
		if (date != null && date.equals(mSelectedDateStr)) {
			if (mSelectedDateStr.equals(getCurrentDateTimeStr())) {
				cellColor = getResources().getColor(R.color.pink);
			} else {
				cellColor = getResources().getColor(R.color.blue);
			}
		} else {
			// cellColor = getResources().getColor((index / 7) % 2 == 0 ?
			// R.color.white : R.color.calendar_date_cell_gray);
			cellColor = getResources().getColor(R.color.transparent);// 设置背景透明
		}
		mPaint.setColor(cellColor);
	}

	/**
	 * 获得当前日期
	 * 
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	private String getCurrentDateTimeStr() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * 根据单元格的index获取Rect
	 * 
	 * @param index
	 * @return
	 */
	private RectF getRectByCellIndex(int index) {
		int rowIndex = index / 7;
		int colIndex = index % 7;
		return new RectF(mCellWidth * colIndex, mCellHeight * (rowIndex + 1),
				mCellWidth * (colIndex + 1), mCellHeight * (rowIndex + 2));
	}

	/**
	 * 根据cell的index确定cell内文字的位置
	 * 
	 * @param index
	 * @return
	 */
	private float[] getTextPositionByCellIndex(int index, String text,
			Paint paint) {
		float[] position = new float[2];
		RectF rect = getRectByCellIndex(index);
		float leftMargin = (mCellWidth - paint.measureText(text)) / 2;
		position[0] = rect.left + leftMargin;
		position[1] = rect.bottom - paint.getTextSize();
		return position;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mCanvasWidth = MeasureSpec.getSize(widthMeasureSpec);
		mCanvasHeight = MeasureSpec.getSize(heightMeasureSpec);
		// System.out.println("mCanvasWidth=" + mCanvasWidth + ",mCanvasHeight="
		// + mCanvasHeight);
		mCellWidth = mCanvasWidth / 7f;
		mCellHeight = mCanvasHeight / 7f;
		mTextSize = (mCellHeight / 3);
		setMeasuredDimension((int) mCanvasWidth, (int) mCanvasHeight);
	}

	/**
	 * 处理点击事件 ------------------ start
	 */

	// 上次按下事件的y坐标
	float lastEventDownY;

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastEventDownY = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			// 纵向移动不超过单元格高度，则为有效点击
			if (Math.abs(event.getY() - lastEventDownY) < mCellHeight) {
				fireClickEvent(event.getX(), event.getY());
			}
			break;
		case MotionEvent.ACTION_MOVE:

			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * 触发点击事件
	 * 
	 * @param x
	 * @param y
	 */
	private void fireClickEvent(float x, float y) {
		// 根据坐标计算所属单元格index
		int lineNum = (int) (y / mCellHeight) - 1;
		int colNum = (int) (x / mCellWidth);
		if (lineNum > -1) {
			int index = lineNum * 7 + colNum;
			String dateStr = mDateMap.get(index);
			setCurrentSelectedDate(dateStr);
			if (mOnDateSelectedListener != null)
				mOnDateSelectedListener.onDateSelected(dateStr);
			invalidate();
		}
	}

	public static interface OnDateSelectedListener {
		public void onDateSelected(String dateStr);
	}

	/**
	 * 处理点击事件 ------------------ end
	 */

}
