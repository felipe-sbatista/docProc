package br.com.docproc.serviceImpl;

import br.com.docproc.base.AbstractService;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.service.TipoArquivoService;
import org.springframework.stereotype.Service;

@Service
public class TipoCapturaServiceImpl extends AbstractService<TipoCaptura, Long> implements TipoArquivoService {
}
