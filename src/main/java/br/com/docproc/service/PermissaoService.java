package br.com.docproc.service;

import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;

import java.util.List;

public interface PermissaoService {

    void salvarCapturas(List<TipoCaptura> tiposCaptura);

    void salvarTiposArquivos(List<TipoArquivo> tiposArquivos);
}
