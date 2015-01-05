package com.shs.json.bean;

import java.util.List;

public class ListZaoCanClass {
	private static List<ZaoCanClass> zaocanlist;

	public static List<ZaoCanClass> getZaocanlist() {
		return zaocanlist;
	}

	public static void setZaocanlist(ZaoCanClass zaocanlist) {
		ListZaoCanClass.zaocanlist.add(zaocanlist);
	}

}
