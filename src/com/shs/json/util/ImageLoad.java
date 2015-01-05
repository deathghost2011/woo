package com.shs.json.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ImageLoad {
	
	
	
	public static Bitmap getimage(String picurl)
	{
		URL url = null;
		Bitmap image=null;
		try {
			url = new URL(picurl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			image=	BitmapFactory.decodeStream(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return image;
	}

}
