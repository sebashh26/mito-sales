package com.mitocode.service.impl;

import org.springframework.stereotype.Service;

import com.mitocode.model.Sale;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ISaleRepo;
import com.mitocode.service.ISaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

	private final ISaleRepo saleRepo;// spring busca la impl de la interface

	@Override
	public IGenericRepo<Sale, Integer> getRepo() {
		return saleRepo;
	}

	

}
