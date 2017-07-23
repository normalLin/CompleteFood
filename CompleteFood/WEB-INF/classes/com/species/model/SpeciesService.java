package com.species.model;

import java.util.List;
import java.util.Set;

import com.food_items.model.*;

public class SpeciesService  {
	private SpeciesDAO_interface dao;

	public SpeciesService() {
		dao = new SpeciesDAO();
	}

	public List<SpeciesVO> getAll() {
		return dao.getAll();
	}

	public SpeciesVO getOneSpecies(Integer species_id) {
		return dao.findByPrimaryKey(species_id);
	}

	public Set<Food_itemsVO> getItemsBySpecies_id(Integer species_id) {
		return dao.getItemsBySpecies_id(species_id);
	}
}
