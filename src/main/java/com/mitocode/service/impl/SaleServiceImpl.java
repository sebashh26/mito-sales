package com.mitocode.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	@Override
	public void callProcedure4() {
		saleRepo.callProcedure4();
	}

	@Override
	public Sale getSaleMostExpensive() {
		return saleRepo.findAll().stream().max(Comparator.comparing(Sale::getTotal)).orElseGet(Sale:: new);
	}

	@Override
	public String getBestSellerPerson() {
		//Map<String, Double> mapSales = saleRepo.findAll().stream().collect(Collectors.groupingBy(e -> e.getUser().getUserName(), Collectors.summingDouble(e -> e.getTotal())));
		//*-------------------1------------------
//		Stream<Sale> stream=saleRepo.findAll().stream();
//		Map<Integer, List<Sale>> mapSales = stream.collect(Collectors.groupingBy(Sale::getIdSale));
		//---------------------2-----------------
		//en base sería así: select u.user_name, sum(s.total)from sale s, user_data u where s.id_user=u.id_user group by u.user_name
		Map<String, Double> mapByUser = saleRepo.findAll().stream().collect(Collectors.groupingBy(e -> e.getUser().getUserName(), 
				Collectors.summingDouble(Sale::getTotal)));
		
		return Collections.max(mapByUser.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
		
	}

	@Override
	public Map<String, Long> getSalesCountBySeller() {
		//data base: select u.user_name, COUNT(s.*) from sale s, user_data u where s.id_user=u.id_user group by u.user_name
		Map<String, Long> mapByUser = saleRepo.findAll().stream().collect(Collectors.groupingBy(s -> s.getUser().getUserName(), Collectors.counting()));		
		return mapByUser;
	}
	

}
