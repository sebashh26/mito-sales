package com.mitocode.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mitocode.model.Category;
import com.mitocode.repo.ICategoryRepo;

import lombok.RequiredArgsConstructor;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor//atributo deb ser maracdo como final ahi lo tomara como inyeccio de dependencias
public class CategoryServiceImpl implements ICategoryService{

	//si instancio CategoryServiceImpl se instancia ICategoryRepo con autowired es decir hay alto acomplamiento
	// se sugiere hacerlo por constructor
	//@Autowired//busca una instancia y proporcionar donde sse la coloco
	private final ICategoryRepo categoryRepo;// spring busca la impl de la interface

	@Override
	public Category save(Category category) throws Exception {
		
		return categoryRepo.save(category);
	}

	@Override
	public Category update(Category category, Integer id) throws Exception {
		category.setIdCategory(id);
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> readAll() throws Exception {
		// TODO Auto-generated method stub
		return categoryRepo.findAll();
	}

	@Override
	public Category readById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return categoryRepo.findById(id).orElse(new Category());
	}

	@Override
	public void delete(Integer id) throws Exception {
		
		categoryRepo.deleteById(id);
	}
		
	//inyeccion por contructor, spring sabe q necesitas una inctacia la busca en el container y te la pone
	//por loobok se simplifica
//	public CategoryServiceImpl(ICategoryRepo categoryRepo) {
//		super();
//		this.categoryRepo = categoryRepo;
//	}

	/*@Override
	public Category validAndSave(Category category) {
		
		if(category.getIdCategory()>0) {
			//categoryRepo= new CategoryRepo();// es no es buebo manejarlo asi se debe hacer inyeccion de dependencia
			return categoryRepo.save(category);
		}else {
			return new Category();
		}
	}*/
}
