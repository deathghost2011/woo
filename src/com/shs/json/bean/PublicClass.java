package com.shs.json.bean;

import java.io.Serializable;

public class PublicClass implements Serializable{
	private static String Token,uid,utoken,PATH,resultClass,status,order_no,realname;
	public static String getRealname() {
		return realname;
	}

	public static void setRealname(String realname) {
		PublicClass.realname = realname;
	}

	public static String getOrder_no() {
		return order_no;
	}

	public static void setOrder_no(String order_no) {
		PublicClass.order_no = order_no;
	}

	public static String getStatus() {
		return status;
	}

	public static void setStatus(String status) {
		PublicClass.status = status;
	}

	private static Boolean hasdingdan=false;

	public static Boolean getHasdingdan() {
		return hasdingdan;
	}

	public static void setHasdingdan(Boolean hasdingdan) {
		PublicClass.hasdingdan = hasdingdan;
	}

	public static String getToken() {
		return Token;
	}

	public static void setToken(String token) {
		Token = token;
	}

	public static String getUid() {
		return uid;
	}

	public static void setUid(String uid) {
		PublicClass.uid = uid;
	}

	public static String getUtoken() {
		return utoken;
	}

	public static void setUtoken(String utoken) {
		PublicClass.utoken = utoken;
	}

	public static String getPATH() {
		return PATH;
	}

	public static void setPATH(String pATH) {
		PATH = pATH;
	}

	public static String getResultClass() {
		return resultClass;
	}

	public static void setResultClass(String resultClass) {
		PublicClass.resultClass = resultClass;
	}

	
}
