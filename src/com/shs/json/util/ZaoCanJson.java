package com.shs.json.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.net.ParseException;
import android.os.StrictMode;

import com.shs.json.bean.PublicClass;

public class ZaoCanJson {

	
	  //返回json结果
			public  String ReturnJsonStr()
			{
//			   StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
//		       StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder
//		       ().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath ().build());
			
			
			     /*读返回数据*/  
		        String strResult = null;
				 /*建立HTTPost对象*/  
		       HttpPost httpRequest = new HttpPost(PublicClass.getPATH());  
		       /* 
		        * NameValuePair实现请求参数的封装 
		       */  
		       List <NameValuePair> params = new ArrayList <NameValuePair>();  
		       params.add(new BasicNameValuePair("Token", PublicClass.getToken()));  
	 
		         /*发送请求并等待响应*/  
				HttpResponse httpResponse = null;
				try {
					/* 添加请求参数到请求对象*/  
					  httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));  
					  httpResponse = new DefaultHttpClient().execute(httpRequest);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					strResult="";
					
				} catch (ClientProtocolException e1) {
					// TODO Auto-generated catch block
					strResult="";
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					strResult="";
				}  
		         /*若状态码为200 ok*/  
				try {
		         if(httpResponse.getStatusLine().getStatusCode() == 200)   
		         {  
		      
					try {
						strResult = EntityUtils.toString(httpResponse.getEntity());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						strResult="";
					} catch (IOException e) {
						// TODO Auto-generated catch block
						strResult="";
					}  
		      
		         }  
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strResult="";
				} 
				return strResult;
			
		     
			}

}
