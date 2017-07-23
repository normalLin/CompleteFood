package com.style.model;

import java.util.List;
import java.util.Set;

import com.store.model.StoreVO;

public class StyleService  {
	private StyleDAO_interface dao;

	public StyleService() {
		dao = new StyleDAO();
	}

	public List<StyleVO> getAll() {
		return dao.getAll();
	}

	public StyleVO getOneStyle(Integer style_id) {
		return dao.findByPrimaryKey(style_id);
	}

	public Set<StoreVO> getStoresByStyle_id(Integer style_id) {
		return dao.getStoresByStyle_id(style_id);
	}
}
