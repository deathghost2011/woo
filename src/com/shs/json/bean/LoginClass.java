package com.shs.json.bean;

import java.io.Serializable;

public class LoginClass  implements Serializable{
	private static String uname,upwd,uid,utoken;

	public static String getUid() {
		return uid;
	}

	public static void setUid(String uid) {
		LoginClass.uid = uid;
	}

	public static String getUtoken() {
		return utoken;
	}

	public static void setUtoken(String utoken) {
		LoginClass.utoken = utoken;
	}

	public static String getUname() {
		return uname;
	}

	public static void setUname(String uname) {
		LoginClass.uname = uname;
	}

	public static String getUpwd() {
		return upwd;
	}

	public static void setUpwd(String upwd) {
		LoginClass.upwd = upwd;
	}


}
