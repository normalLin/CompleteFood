package com.species.model;

import java.util.*;
import com.food_items.model.*;

public interface SpeciesDAO_interface {
    public void insert(SpeciesVO speciesVO);
    public void update(SpeciesVO speciesVO);
    public SpeciesVO findByPrimaryKey(Integer species_id);
    public List<SpeciesVO> getAll();
    public Set<Food_itemsVO> getItemsBySpecies_id(Integer species_id);
}
