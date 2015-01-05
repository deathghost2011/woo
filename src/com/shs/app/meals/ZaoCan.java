package com.shs.app.meals;

public class ZaoCan {
	private String img;
	private String title;
	private String price;
	private String shuliang;
	private String type;
	private boolean xuanzhong;
	public ZaoCan(String img, String title, String price, String shuliang,
			String type, boolean xuanzhong) {
		super();
		this.img = img;
		this.title = title;
		this.price = price;
		this.shuliang = shuliang;
		this.type = type;
		this.xuanzhong = xuanzhong;
	}
	public ZaoCan() {
		super();
	}
	@Override
	public String toString() {
		return "ZaoCan [img=" + img + ", title=" + title + ", price=" + price
				+ ", shuliang=" + shuliang + ", type=" + type + ", xuanzhong="
				+ xuanzhong + "]";
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getShuliang() {
		return shuliang;
	}
	public void setShuliang(String shuliang) {
		this.shuliang = shuliang;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isXuanzhong() {
		return xuanzhong;
	}
	public void setXuanzhong(boolean xuanzhong) {
		this.xuanzhong = xuanzhong;
	}
	
}
