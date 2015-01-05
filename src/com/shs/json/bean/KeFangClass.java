package com.shs.json.bean;

import java.io.Serializable;

public class KeFangClass implements Serializable{
   private String id,no,name,hotel_id,room_type,num,price,remark;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getNo() {
	return no;
}

public void setNo(String no) {
	this.no = no;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getHotel_id() {
	return hotel_id;
}

public void setHotel_id(String hotel_id) {
	this.hotel_id = hotel_id;
}

public String getRoom_type() {
	return room_type;
}

public void setRoom_type(String room_type) {
	this.room_type = room_type;
}

public String getNum() {
	return num;
}

public void setNum(String num) {
	this.num = num;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public String getRemark() {
	return remark;
}

public void setRemark(String remark) {
	this.remark = remark;
}

}
