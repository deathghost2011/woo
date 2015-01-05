package com.shs.app.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnCloseListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.shs.app.R;

public class MainAct extends SlidingFragmentActivity {
	public static ImageLoader imageLoader = ImageLoader.getInstance();
	protected DisplayImageOptions options = new DisplayImageOptions.Builder()
			.cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
			.imageScaleType(ImageScaleType.EXACTLY)
			.bitmapConfig(Bitmap.Config.ARGB_8888).resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
			.displayer(new RoundedBitmapDisplayer(5))// 是否设置为圆角，弧度为多少
			.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
			.build();

	public static SlidingMenu sm;
	private Fragment mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				MainAct.this)
				.memoryCacheExtraOptions(480, 800)
				// 设置缓存图片时候的宽高最大值，默认为屏幕宽高
				.threadPoolSize(5)
				// 设置线程池的最高线程数量
				.threadPriority(Thread.NORM_PRIORITY)
				// 设置线程优先级
				.denyCacheImageMultipleSizesInMemory()
				// 自动缩放
				.memoryCache(new UsingFreqLimitedMemoryCache(4 * 1024 * 1024))
				// 设置缓存大小，UsingFrgLimitMemoryCache类可以扩展
				// .discCache(new UnlimitedDiscCache(mContext.getCacheDir()))
				// //设置硬盘缓存
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator())
				// unkown
				.imageDownloader(
						new BaseImageDownloader(MainAct.this, 3000, 8000))
				.defaultDisplayImageOptions(options).build();// 如果需要打开缓存机制，需要自己builde一个option,可以是DisplayImageOptions.createSimple()
		// .enableLogging();
		imageLoader.init(config);
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		initSlidingMenu(savedInstanceState);
	}

	public static int width;
	public static Fragment mainFragment = new ContentFra();
	public static Fragment slidFragment = new SlidFra();

	private void initSlidingMenu(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		}
		if (mContent == null) {
			mContent = mainFragment;
		}
		setBehindContentView(R.layout.menu_frame);
		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, slidFragment).commit();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mContent).commit();
		sm = getSlidingMenu();

		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);

		sm.setBehindWidth(MainAct.width * 5 / 6);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		// CanvasTransformer mTransformer=new CanvasTransformer() {
		// @Override
		// public void transformCanvas(Canvas canvas, float percentOpen) {
		// float scale = (float) (percentOpen*0.25 + 0.75);
		// canvas.scale(scale, scale, canvas.getWidth()/2,
		// canvas.getHeight()/2);
		// }
		// };
		// sm.setBehindCanvasTransformer(mTransformer);
		// Log.e("swidth", sm.getBehindOffset()+"/");
		sm.setOnCloseListener(new OnCloseListener() {

			@Override
			public void onClose() {
				ContentFra.menu.setImageResource(R.drawable.ic_menu);
			}
		});
		sm.setOnOpenListener(new OnOpenListener() {

			@Override
			public void onOpen() {
				ContentFra.menu.setImageResource(R.drawable.icon_close);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
