package com.store.model;

import java.util.*;

public interface StoreDAO_interface {

	   public void insert(StoreVO storeVO);		//新增店家
       public void update(StoreVO storeVO);		//編輯店家
       public void delete(Integer store_id);			//店家下架
       public StoreVO findByPrimaryKey(Integer storeno);
       public List<StoreVO> getAll();					
}
