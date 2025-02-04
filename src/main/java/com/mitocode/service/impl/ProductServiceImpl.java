package com.mitocode.service.impl;

import org.springframework.stereotype.Service;

import com.mitocode.model.Product;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IProductRepo;
import com.mitocode.service.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

	private final IProductRepo productRepo;// spring busca la impl de la interface

	@Override
	public IGenericRepo<Product, Integer> getRepo() {
		return productRepo;
	}

	

}
