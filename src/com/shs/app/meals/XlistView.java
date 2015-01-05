package com.shs.app.meals;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class XlistView extends ListView{

	public XlistView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}


public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //根据模式计算每个child的高度和宽度
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
