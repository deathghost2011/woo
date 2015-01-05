package com.shss.wedgit.dialog;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;

/**
 * 创建CustomAlertDialog,在android4.0及以上的版本dialog采用Holo Light theme
 * @param context
 * @return light alert dialog
 * @author mayu
 *
 */
public class CustomAlertDialog extends AlertDialog {

	/**
	 * 
	 * 
	 * @param context
	 * @return light alert dialog
	 */
	public static CustomAlertDialog create(Context context) {
		CustomAlertDialog dialog;
		if (isICSOrHigher())
			dialog = new CustomAlertDialog(context, 3);
		else {
			dialog = new CustomAlertDialog(context);
			dialog.setInverseBackgroundForced(true);
		}
		return dialog;
	}

	public static CustomAlertDialog create(Context context, String title,
			String message) {
		CustomAlertDialog dialog = create(context);
		dialog.setTitle(title);
		dialog.setMessage(message);
		return dialog;
	}

	public static CustomAlertDialog create(Context context, int title,
			int message) {
		CustomAlertDialog dialog = create(context);
		dialog.setTitle(title);
		dialog.setMessage(context.getString(message));
		return dialog;
	}

	public static CustomAlertDialog create(Context context, int title,
			String message) {
		CustomAlertDialog dialog = create(context);
		dialog.setTitle(title);
		dialog.setMessage(message);
		return dialog;
	}

	public static CustomAlertDialog create(Context context, String title,
			int message) {
		return create(context, title, context.getString(message));
	}

	protected CustomAlertDialog(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param theme
	 */
	protected CustomAlertDialog(Context context, int theme) {
		super(context, theme);
	}

	public CustomAlertDialog setPositiveButton(int text,
			OnClickListener listener) {
		return setPositiveButton(getContext().getString(text), listener);
	}

	public CustomAlertDialog setPositiveButton(CharSequence text,
			OnClickListener listener) {
		setButton(BUTTON_POSITIVE, text, listener);
		return this;
	}

	public CustomAlertDialog setPositiveButton(OnClickListener listener) {
		return setPositiveButton(android.R.string.ok, listener);
	}

	public CustomAlertDialog setNegativeButton(int text,
			OnClickListener listener) {
		return setNegativeButton(getContext().getString(text), listener);
	}

	public CustomAlertDialog setNegativeButton(CharSequence text,
			OnClickListener listener) {
		setButton(BUTTON_NEGATIVE, text, listener);
		return this;
	}

	public CustomAlertDialog setNegativeButton(OnClickListener listener) {
		return setNegativeButton(android.R.string.cancel, listener);
	}
	public static  boolean isICSOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }
}
