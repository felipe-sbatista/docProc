package br.com.docproc.service;

import br.com.docproc.entity.Arquivo;
import br.com.docproc.dto.FiltroDTO;
import br.com.docproc.entity.Usuario;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArquivoService {
    HttpEntity salvarArquivo(MultipartFile file, String tipo, String captura, String matricula) throws IOException;

    ResponseEntity<List<Arquivo>> findByFiltros(FiltroDTO filtro);

    byte[] gerarPlanilha(Arquivo arquivo) throws IOException;

}
