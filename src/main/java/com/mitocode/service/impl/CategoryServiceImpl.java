package com.mitocode.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mitocode.model.Category;
import com.mitocode.repo.ICategoryRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

	private final ICategoryRepo categoryRepo;

	@Override
	public IGenericRepo<Category, Integer> getRepo() {
		return categoryRepo;
	}

	@Override
	public List<Category> findByName(String name) {
		return categoryRepo.findByName(name);
	}

	@Override
	public List<Category> findByNameLike(String name) {
		return categoryRepo.findByNameLike("%" +name+ "%");
	}

	@Override
	public List<Category> findByNameOrEnabled(String name, boolean enabled) {
		return categoryRepo.findByNameOrEnabled(name, enabled);
	}

	@Override
	public List<Category> getNameAndDescription(String name, String description) {
		return categoryRepo.getNameAndDescription2(name, description);
	}

	@Override
	public List<Category> getNameSQL(String name) {
		return categoryRepo.getNameSQL(name);
	}

	

	
	
}
