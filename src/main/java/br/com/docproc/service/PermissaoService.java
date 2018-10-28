package br.com.docproc.service;

import br.com.docproc.dto.PermissaoDTO;
import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PermissaoService {

    ResponseEntity setPermissoes(PermissaoDTO permissao);
}
