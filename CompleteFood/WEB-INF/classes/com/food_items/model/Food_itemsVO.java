package com.food_items.model;

import java.sql.Date;

public class Food_itemsVO implements java.io.Serializable {
	private Integer items_id;
	private Integer species_id;
	private String item_name;
	private byte[] food_images;
	private Double price;
	private Integer store_id;
	
	public Integer getItems_id() {
		return items_id;
	}
	public void setItems_id(Integer items_id) {
		this.items_id = items_id;
	}
	public Integer getSpecies_id() {
		return species_id;
	}
	public void setSpecies_id(Integer species_id) {
		this.species_id = species_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public byte[] getFood_images() {
		return food_images;
	}
	public void setFood_images(byte[] food_images) {
		this.food_images = food_images;
	}	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}	
}
