package com.food_items.model;

import java.util.*;

public interface Food_itemsDAO_interface {
          public void insert(Food_itemsVO food_itemsVO);
          public void update(Food_itemsVO food_itemsVO);
          public void delete(Integer items_id);
          public List<Food_itemsVO> findByStoreId(Integer store_id);
          public Food_itemsVO findByPrimaryKey(Integer items_id);
          public List<Food_itemsVO> getAll();
}
