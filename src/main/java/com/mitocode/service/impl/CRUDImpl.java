package com.mitocode.service.impl;

import java.util.List;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICRUD;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        //falta trabajar la asociacion con id
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        //orElse temporal
        return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));// ()-> obj no recibe nada y retorna lo que sea es un supplier, consumer recieb lo q sea y retorna un void
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);
    }

}
