package br.com.docproc.serviceImpl;

import br.com.docproc.base.AbstractService;
import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.repository.TipoArquivoRepository;
import br.com.docproc.service.TipoArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoArquivoServiceImpl extends AbstractService<TipoArquivo, Long>  implements TipoArquivoService {

    @Autowired
    private TipoArquivoRepository repository;

    List<TipoArquivo> getTodosAtivos(){
        return repository.findByAtivoTrue();
    }

    TipoArquivo getByTipo(String tipo){
        return repository.findByFormato(tipo);
    }
}
