package br.com.docproc.repository;

import br.com.docproc.entity.TipoArquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoArquivoRepository extends JpaRepository<TipoArquivo, Long> {
    List<TipoArquivo> findByAtivoTrue();

    TipoArquivo findByFormato(String formato);
}
