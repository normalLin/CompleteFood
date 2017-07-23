package com.food_items.model;

import java.util.List;

public class Food_itemsService {

	private Food_itemsDAO_interface dao;
	
	public Food_itemsService(){
		dao = new Food_itemsDAO();
	}
	public Food_itemsVO addItem(Integer  species_id ,  String item_name , Double price , Integer store_id ){
		
		Food_itemsVO food_itemsVO = new Food_itemsVO();
		

		food_itemsVO.setSpecies_id(species_id);
//		storeVO.setStore_images(store_images);
		food_itemsVO.setItem_name(item_name);
		food_itemsVO.setPrice(price);
		food_itemsVO.setStore_id(store_id);
		dao.insert(food_itemsVO);
		
		return food_itemsVO;
	}
	
	public void addItem(Food_itemsVO food_itemsVO){
		dao.insert(food_itemsVO);
	}
	
	public Food_itemsVO updateItem(Integer  items_id ,  Integer species_id , String item_name , Double price  ){
		
		Food_itemsVO food_itemsVO = new Food_itemsVO();
		
		food_itemsVO.setItems_id(items_id);
		food_itemsVO.setSpecies_id(species_id);
		food_itemsVO.setItem_name(item_name);
		food_itemsVO.setPrice(price);

		dao.update(food_itemsVO);
		
		return dao.findByPrimaryKey(items_id);
	}
	
	//預留給 Struts 2 用的
	public void updateItem(Food_itemsVO food_itemsVO) {
		dao.update(food_itemsVO);
	}

	public void deleteItem(Integer items_id) {
		dao.delete(items_id);
	}

	public List<Food_itemsVO> getOneStore(Integer store_id) {
		return dao.findByStoreId(store_id);
	}
	
	public Food_itemsVO getOneItem(Integer items_id) {
		return dao.findByPrimaryKey(items_id);
	}

	public List<Food_itemsVO> getAll() {
		return dao.getAll();
	}
}
