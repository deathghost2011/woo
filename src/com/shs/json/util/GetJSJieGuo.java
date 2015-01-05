package com.shs.json.util;

import com.shs.json.bean.WenQuanClass;
import com.shs.json.bean.ZaoCanClass;

public class GetJSJieGuo {
	
	public static Double GetResult(int number)
	{
		
		
	    String price=	WenQuanClass.getPrice();
		Double valuemony=Double.valueOf(price);
		
		valuemony=valuemony*number;
		return valuemony;
	}
	
	public static Double GetResultzaocan(int number)
	{
		
		
	    String price=	ZaoCanClass.getPrice();
		Double valuemony=Double.valueOf(price);
		
		valuemony=valuemony*number;
		return valuemony;
	}

}
