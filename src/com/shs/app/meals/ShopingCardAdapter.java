package com.shs.app.meals;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.shs.app.R;
import com.shs.app.main.MainAct;

public class ShopingCardAdapter extends BaseAdapter {
	private Context mContext;
	private List<ZaoCan> list;
	List<ZaoCan> data_list = new ArrayList<ZaoCan>();


	public void setList(List<ZaoCan> list) {
		this.list = list;
	}
	protected DisplayImageOptions options;
	ImageLoaderConfiguration config ;
	public ShopingCardAdapter(Context mContext) {
		this.mContext = mContext;
		options = new DisplayImageOptions.Builder()
		.cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
		.imageScaleType(ImageScaleType.EXACTLY)
		.bitmapConfig(Bitmap.Config.ARGB_8888).resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
		.displayer(new RoundedBitmapDisplayer(5))// 是否设置为圆角，弧度为多少
		.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
		.build();

		 config = new ImageLoaderConfiguration.Builder(
				mContext)
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
						new BaseImageDownloader(mContext, 3000, 8000))
				.defaultDisplayImageOptions(options).build();// 如果需要打开缓存机制，需要自己builde一个option,可以是DisplayImageOptions.createSimple()
		// .enableLogging();
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
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView=LayoutInflater.from(mContext).inflate(R.layout.shopingcardactivity_item, null);
			viewHolder.button=(RadioButton) convertView.findViewById(R.id.shopingcard_readiobutton1);
			viewHolder.item_title=(TextView) convertView.findViewById(R.id.shopingcard_item_title);
			viewHolder.type=(TextView) convertView.findViewById(R.id.shopingcard_item_hint);
			viewHolder.item_price=(TextView) convertView.findViewById(R.id.shopingcard_jiage);
			viewHolder.zaocan_shuliang=(TextView) convertView.findViewById(R.id.shopingcard_shuliang);
			viewHolder.delete=(TextView) convertView.findViewById(R.id.shopingcard_delete);
			viewHolder.imageView=(ImageView) convertView.findViewById(R.id.shopingcard_item_icon);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.item_price.setText("￥"+list.get(position).getPrice());
		viewHolder.item_title.setText(list.get(position).getTitle());
		viewHolder.zaocan_shuliang.setText(list.get(position).getShuliang());
		viewHolder.type.setText(list.get(position).getType());
		MainAct.imageLoader.init(config);
		MainAct.imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
		MainAct.imageLoader.displayImage(list.get(position).getImg(), viewHolder.imageView);
		if(list.get(position).isXuanzhong()){
			viewHolder.button.setChecked(true);
			viewHolder.button.setTag("2");
		}else{
			viewHolder.button.setChecked(false);
			viewHolder.button.setTag("1");
		}
		viewHolder.button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getTag()=="1"){
					((CompoundButton) v).setChecked(true);
					v.setTag("2");
					list.get(position).setXuanzhong(true);
					ShopingCardActivity.handler.sendEmptyMessage(1);
				}else{
					((CompoundButton) v).setChecked(false);
					v.setTag("1");
					list.get(position).setXuanzhong(false);
					ShopingCardActivity.handler.sendEmptyMessage(1);
				}	
			}
		});
		viewHolder.delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				list.remove(position);
				System.out.println("delete");
				notifyDataSetChanged();
				ShopingCardActivity.handler.sendEmptyMessage(1);
				//从数据库删除某条
			}
		});
		return convertView;
	}

	private class ViewHolder {
		TextView type,item_title,item_price,zaocan_shuliang,delete;
		ImageView imageView;
		RadioButton button;
	}

}
