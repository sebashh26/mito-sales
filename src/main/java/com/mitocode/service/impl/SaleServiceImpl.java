package com.mitocode.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import com.mitocode.model.SaleDetail;
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

	//select p.name, sum(quantity) from sale_detail sd, product p	where sd.id_product= p.id_product GROUP by p.name;
	@Override
	public Map<String, Double> getMostSellerProduct() {
		 
		Stream<Sale> streamSale =  saleRepo.findAll().stream();
		Stream<List<SaleDetail>> streamListSaleDetail = streamSale.map(Sale::getDetails);//s -> s.getDetails() y releja lo de abajo
		//[detall1 ->[det1, det2, det7], dellate 2 ->[det3, det4], detalle3 ->[det5, det6]] osea tengo un array com los prod de un amismi detalle
		
		
		/*List<List<SaleDetail>>listSaleDetail = streamSale.map(Sale::getDetails).toList();//s -> s.getDetails()
		[
			[SaleDetail(idSaleDetail=3, product=Product(idProduct=1, category=Category(idCategory=1, name=computer, description=Some computers, enabled=true),
			 name=asus vivobook, description=asus corei9, price=950.0, enabled=true), quantity=5, salePrice=950.0, discount=0.0),	 
			 SaleDetail(idSaleDetail=4, product=Product(idProduct=2, category=Category(idCategory=1, name=computer, description=Some computers, enabled=true),
			 name=hp 1000, description=hp core i3 1000, price=5999.99, enabled=true), quantity=1, salePrice=5999.99, discount=0.0),	 
			 SaleDetail(idSaleDetail=5,product=Product(idProduct=3, category=Category(idCategory=5, name=mouse, description=some mouse, enabled=true),
			 name=logitec m50, description=mouse logitech, price=79.99, enabled=true), quantity=2, salePrice=79.99, discount=0.0)], 
			 
			[SaleDetail(idSaleDetail=6, product=Product(idProduct=1, category=Category(idCategory=1, name=computer, description=Some computers, enabled=true), 
			name=asus vivobook, description=asus corei9, price=950.0, enabled=true), quantity=1, salePrice=950.0, discount=0.0)], 
			
			[SaleDetail(idSaleDetail=7, product=Product(idProduct=1, category=Category(idCategory=1, name=computer, description=Some computers, enabled=true),
			name=asus vivobook, description=asus corei9, price=950.0, enabled=true), quantity=2, salePrice=950.0, discount=0.0)],

			[SaleDetail(idSaleDetail=8, product=Product(idProduct=3, category=Category(idCategory=5, name=mouse, description=some mouse, enabled=true), 
			name=logitec m50, description=mouse logitech, price=79.99, enabled=true), quantity=1, salePrice=79.99, discount=0.0)], 
			
			[SaleDetail(idSaleDetail=9, product=Product(idProduct=3, category=Category(idCategory=5, name=mouse, description=some mouse, enabled=true),
			name=logitec m50, description=mouse logitech, price=79.99, enabled=true), quantity=5, salePrice=79.99, discount=0.0)], 
			
			[SaleDetail(idSaleDetail=10, product=Product(idProduct=3, category=Category(idCategory=5, name=mouse, description=some mouse, enabled=true), 
			name=logitec m50, description=mouse logitech, price=79.99, enabled=true), quantity=5, salePrice=79.99, discount=0.0)]]*/
		
		
		//[det1, det2, det7, det3, det4, det5, det6] se desacoplen con flat map

		Stream<SaleDetail> streamSaleDetail = streamListSaleDetail.flatMap(Collection::stream);//list -> list.stream();
		Map<String, Double> byProduct = streamSaleDetail.collect(Collectors.groupingBy(d -> d.getProduct().getName(), Collectors.summingDouble(SaleDetail::getQuantity)));//d -> d.getQuantity()
		
		//Map<String,Double> sortedNewMap1 = byProduct.entrySet().stream().sorted((e1,e2)-> Double.compare(e1.getValue().doubleValue(), e2.getValue().doubleValue()))
        //.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new)).reversed();
		
		Map<String,Double> sortedNewMap2 = byProduct.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap:: new//linked respect the orde
						
		));
		return sortedNewMap2;
	}
	

}
