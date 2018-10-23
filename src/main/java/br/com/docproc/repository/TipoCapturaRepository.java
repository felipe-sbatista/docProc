package br.com.docproc.repository;

import br.com.docproc.entity.Arquivo;
import br.com.docproc.entity.TipoCaptura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoCapturaRepository extends JpaRepository<TipoCaptura, Long> {
    List<TipoCaptura> findByAtivoTrue();


    TipoCaptura findByFormaCaptura(String forma);



}
