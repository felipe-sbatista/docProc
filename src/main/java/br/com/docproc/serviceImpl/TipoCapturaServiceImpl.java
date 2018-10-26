package br.com.docproc.serviceImpl;

import br.com.docproc.base.AbstractService;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.repository.TipoCapturaRepository;
import br.com.docproc.service.TipoArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoCapturaServiceImpl extends AbstractService<TipoCaptura, Long> implements TipoArquivoService {

    @Autowired
    private TipoCapturaRepository repository;

    public TipoCaptura getByForma(String captura) {
        return repository.findByFormaCaptura(captura);
    }

    public List<TipoCaptura> getTodosAtivos() {
        return repository.findByAtivoTrue();
    }
}
