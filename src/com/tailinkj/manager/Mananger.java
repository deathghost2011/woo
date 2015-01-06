package com.tailinkj.manager;

import java.util.ArrayList;
import java.util.List;

import com.shs.app.meals.ZaoCan;

import net.tsz.afinal.FinalDb;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

public class Mananger {
	public static List<ZaoCan> shebei=new ArrayList<ZaoCan>();
	//创建数据库
		public static void SqlCreate(Context context){
			FinalDb db=FinalDb.create(context,"sqlafinal.db");
			System.out.println("create");
		}
	//添加
	public  boolean SqlSave(Context context,Object j){
		FinalDb db=FinalDb.create(context,"sqlafinal.db");
//		User user=new User("你好",19);
		try{
			ZaoCan jj=(ZaoCan) j;
			String num=jj.getShuliang();
			String zixuan=jj.getType();
			List<ZaoCan> resultList = db.findAllByWhere(ZaoCan.class, " title=\"" + jj.getTitle()+ "\"");
			System.out.println(resultList.size()+"++++");
			jj=null;
			for (int i = 0; i < resultList.size(); i++) {
				jj=resultList.get(i);
				if(jj.getType().equals(zixuan)){
					jj.setShuliang(Integer.parseInt(jj.getShuliang())+Integer.parseInt(num)+"");
					db.update(jj);
					System.out.println("updata");
					break;
				}
				jj=null;
			}
//			jj=resultList.get(0);
//			jj.setShuliang(Integer.parseInt(jj.getShuliang())+Integer.parseInt(num)+"");
//			System.out.println(Integer.parseInt(jj.getShuliang())+1+"---");//查看个数
//			db.update(jj);
			if(jj==null){
				db.save(j);
				System.out.println("add");
			}
		}catch(SQLiteConstraintException e){
			System.out.println("名字重复");
			//重复的添加数量
			ZaoCan jj=(ZaoCan) j;
			String num=jj.getShuliang();
			List<ZaoCan> resultList = db.findAllByWhere(ZaoCan.class, " title=\"" + jj.getTitle()+ "\"");
			jj=resultList.get(0);
			jj.setShuliang(Integer.parseInt(jj.getShuliang())+Integer.parseInt(num)+"");
//			System.out.println(Integer.parseInt(jj.getShuliang())+1+"---");//查看个数
			db.update(jj);
			return false;
		}
		return true;
	}
	//修改
	public  void SqlUpDate(Context context,Object j){
		FinalDb db=FinalDb.create(context,"sqlafinal.db");
		db.update(j);
	}
	//查询
	public  List<? extends Object> SqlSelect(Context context,String biao,String where){
		FinalDb db=FinalDb.create(context,"sqlafinal.db");
			List<ZaoCan> resultList = db.findAllByWhere(ZaoCan.class, where);
			return resultList;
	}
	public  List<? extends Object> SqlSelectAll(Context context,String biao){
		FinalDb db=FinalDb.create(context,"sqlafinal.db");
			List<ZaoCan> resultList = db.findAll(ZaoCan.class);
			return resultList;
	}
	
	
	//删除
	public  void SqlDelete(Context context,Object j){
		FinalDb db=FinalDb.create(context,"sqlafinal.db");
//		db.deleteAll(User.class);//删除 
//		db.deleteById(User.class, 2);删除单个
		db.delete(j);
	}
	//删除
		public  void SqlDeleteAll(Context context,String biao){
			FinalDb db=FinalDb.create(context,"sqlafinal.db");
//			db.deleteAll(User.class);//删除 
//			db.deleteById(User.class, 2);删除单个
				db.deleteAll(ZaoCan.class);
		}
}
