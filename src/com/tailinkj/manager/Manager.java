package com.tailinkj.manager;

public class Manager {
	//	Token
	public static String token="8CE4F98878B0C302CB3DE0DCD27D8BC8";
	//index
	public static String APIURL="http://tailinkj.com/hotel/app_api/index.php/";
	//早餐列表
	public static String zaocan=APIURL+"catering/getlist2"; 
	//早餐列表
	public static String zaocan1=APIURL+"catering/getlist1"; 
	//获取酒店列表
	public static String jiudian=APIURL+"hotel/getlist"; 
	// 获取客房信息
	public static String kefangxinxi=APIURL+"room/getinfo"; 
	// 酒店订单详情
	public static String jiudianxiangqing=APIURL+"hotel/getinfo"; 
	//去付款
	public static String qufukuan=APIURL+"room/creorderform"; 
	// 客房类型列表1
	public static String kefangleixing1=APIURL+"room/getcategory"; 
	// 客房类型列表2
	public static String kefangleixing2=APIURL+"room/getlistinfo"; 
	//登录
	public static String denglu=APIURL+"user/userlogin"; 
	//注册
	public static String zhuce=APIURL+"user/userreg"; 
	//显示个人信息
	public static String gerenxinxi=APIURL+"user/getuserinfo"; 
	//显示个人信息("待支付")
	public static String gerenxinxi1=APIURL+"room/getorderform"; 
	//更新支付状态
	public static String gengxin=APIURL+"room/getorderinfo"; 
	//OrderMeiyouZhifuAdapter,该订单删除
	public static String dingdanshanchu=APIURL+"room/modifyorderform/yiquxiao"; 
	//OrderMeiyouZhifuAdapter,该订单支付
	public static String dingdanzhifu=APIURL+"room/modifyorderform/yiwancheng"; 
	//SpringHotFragment获取早餐列表
	public static String zaocanliebiao2=APIURL+"hotspring/getactivlist";
	
	
	
	
}
