package br.com.docproc.service;

import br.com.docproc.entity.TipoArquivo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoArquivoService {
    List<TipoArquivo> getTodosAtivos();
    ResponseEntity salvarNovo(String descricao);

}
