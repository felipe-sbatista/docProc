package br.com.docproc.service;

import br.com.docproc.entity.TipoCaptura;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoCapturaService {
    TipoCaptura getByForma(String captura);
    List<TipoCaptura> getTodosAtivos();
    ResponseEntity salvarNovo(String descricao);
}
