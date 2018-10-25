package br.com.docproc.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T extends AbstractEntity<ID>, ID extends Serializable> {

    @Autowired
    private JpaRepository<T, ID> repository;

    public List<T> listarTodos(){
        return repository.findAll();
    }

    public T getById(T t){
        return repository.getOne(t.getId());
    }

    public T salvar(T t){
        return repository.save(t);
    }
}
