package com.mitocode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ISaleRepo;
import com.mitocode.service.IProcedureDTO;
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

	@Override
	public List<ProcedureDTO> callProcedure1() {
		List<ProcedureDTO> list = new ArrayList<>();
		
		saleRepo.callProcedure1().forEach(e ->{
			ProcedureDTO dto = new ProcedureDTO();
			dto.setQuantityfn((Integer) e[0]);
			dto.setDatetimefn((String) e[1]);
			list.add(dto);
		});
		
		return list;
	}

	@Override
	public List<IProcedureDTO> callProcedure2() {
		return saleRepo.callProcedure2();
	}

	@Override
	public List<ProcedureDTO> callProcedure3() {
		return saleRepo.callProcedure3();
	}

	

}
