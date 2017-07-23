package com.store.model;

import java.sql.Date;

public class StoreVO implements java.io.Serializable {
	private Integer  store_id ;		//主鍵
	private String store_name   ;		//店名	
	private byte[] store_images ;		//店logo
	private Integer phone_number ;  	//電話	
	private String shop_address ; 		//地址
	private String web_url	;				//網址
	private Integer star_avg ;     		//評分~幾顆星
	private Double subTotal ;        		//外送費
	private Double mini_Price ;      	//最低外送金額
	private String deliveryOperationTime ;	//營業時間
	private Integer style_id; 				//餐廳類型-(用代號代表)
	
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public byte[] getStore_images() {
		return store_images;
	}
	public void setStore_images(byte[] store_images) {
		this.store_images = store_images;
	}
	public Integer getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}
	public String getShop_address() {
		return shop_address;
	}
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}
	public String getWeb_url() {
		return web_url;
	}
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	public Integer getStar_avg() {
		return star_avg;
	}
	public void setStar_avg(Integer star_avg) {
		this.star_avg = star_avg;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public Double getMini_Price() {
		return mini_Price;
	}
	public void setMini_Price(Double mini_Price) {
		this.mini_Price = mini_Price;
	}
	
	public String getDeliveryOperationTime() {
		return deliveryOperationTime;
	}
	public void setDeliveryOperationTime(String deliveryOperationTime) {
		this.deliveryOperationTime = deliveryOperationTime;
	}
	public Integer getStyle_id() {
		return style_id;
	}
	public void setStyle_id(Integer Style_id) {
		this.style_id = Style_id;
	}
}
