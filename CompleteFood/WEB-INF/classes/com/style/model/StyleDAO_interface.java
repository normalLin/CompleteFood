package com.style.model;

import java.util.*;
import com.store.model.*;

public interface StyleDAO_interface {
    public void insert(StyleVO styleVO);
    public void update(StyleVO styleVO);
    public StyleVO findByPrimaryKey(Integer style_id);
    public List<StyleVO> getAll();
    //查詢某部門的員工(一對多)(回傳 Set)
    public Set<StoreVO> getStoresByStyle_id(Integer style_id);
}
