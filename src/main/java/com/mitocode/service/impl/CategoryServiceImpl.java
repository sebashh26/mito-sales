package com.mitocode.service.impl;

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

	
	
}
