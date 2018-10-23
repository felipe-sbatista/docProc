package br.com.docproc.repository;

import br.com.docproc.entity.Arquivo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    List<Arquivo> findAll(Specification<Arquivo> arquivoSpecification);

    Arquivo findByNomeArquivo();
}
