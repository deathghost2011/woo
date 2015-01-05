package com.tailinkj.manager;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

public class ManagerAll {
	//早餐列表1
	public void keFangSongCan( AjaxCallBack<String> callBack){
		
		FinalHttp finalHttp=new FinalHttp();
		finalHttp.post(Manager.zaocan1+Manager.APIURL, callBack);
	}
}
