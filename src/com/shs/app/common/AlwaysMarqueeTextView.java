package com.shs.app.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
/**
 * 
 * @author 
 *textview一直获取焦点，编译文字显示不完的时候，带跑马灯效果
 */
public class AlwaysMarqueeTextView extends TextView {
	public AlwaysMarqueeTextView(Context context) {
		super(context);
	}

	public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AlwaysMarqueeTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean isFocused() {
		return true;
	}
}
