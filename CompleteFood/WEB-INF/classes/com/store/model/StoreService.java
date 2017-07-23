package com.store.model;

import java.util.List;

public class StoreService {

	private StoreDAO_interface dao;
	
	public StoreService(){
		dao = new StoreDAO();
	}
	public StoreVO addStore(String  store_name , Integer phone_number , String shop_address ,
			String web_url , Double subTotal , Double mini_Price , String deliveryOperationTime , Integer style_id ){
		
		StoreVO storeVO = new StoreVO();
		

		storeVO.setStore_name(store_name);
//		storeVO.setStore_images(store_images);
		storeVO.setPhone_number(phone_number);
		storeVO.setShop_address(shop_address);
		storeVO.setWeb_url(web_url);
		storeVO.setSubTotal(subTotal);
		storeVO.setMini_Price(mini_Price);
		storeVO.setDeliveryOperationTime(deliveryOperationTime);
		storeVO.setStyle_id(style_id);
		dao.insert(storeVO);
		
		return storeVO;
	}
	
	//預留給 Struts 2 用的
	public void addStore(StoreVO storeVO){
		dao.insert(storeVO);
	}
	public StoreVO updateStore(Integer  store_id , String  store_name , Integer phone_number , String shop_address ,
			String web_url , Double subTotal , Double mini_Price , String deliveryOperationTime , Integer style_id ){
		
		StoreVO storeVO = new StoreVO();
		
		storeVO.setStore_id(store_id);
		storeVO.setStore_name(store_name);
//		storeVO.setStore_images(store_images);
		storeVO.setPhone_number(phone_number);
		storeVO.setShop_address(shop_address);
		storeVO.setWeb_url(web_url);
		storeVO.setSubTotal(subTotal);
		storeVO.setMini_Price(mini_Price);
		storeVO.setDeliveryOperationTime(deliveryOperationTime);
		storeVO.setStyle_id(style_id);
		dao.update(storeVO);
		
		return dao.findByPrimaryKey(store_id);
	}
	
	//預留給 Struts 2 用的
	public void updateStore(StoreVO storeVO) {
		dao.update(storeVO);
	}

	public void deleteStore(Integer store_id) {
		dao.delete(store_id);
	}

	public StoreVO getOneStore(Integer store_id) {
		return dao.findByPrimaryKey(store_id);
	}

	public List<StoreVO> getAll() {
		return dao.getAll();
	}
}
