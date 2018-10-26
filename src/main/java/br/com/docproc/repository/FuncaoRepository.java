package br.com.docproc.repository;

import br.com.docproc.entity.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
    Funcao findByDescricao(String descricao);
}
