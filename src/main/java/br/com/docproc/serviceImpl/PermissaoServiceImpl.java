package br.com.docproc.serviceImpl;

import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.repository.TipoArquivoRepository;
import br.com.docproc.repository.TipoCapturaRepository;
import br.com.docproc.service.PermissaoService;
import br.com.docproc.service.TipoArquivoService;
import br.com.docproc.service.TipoCapturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoServiceImpl implements PermissaoService {

    @Autowired
    private TipoArquivoServiceImpl tipoArquivoService;

    @Autowired
    private TipoCapturaServiceImpl tipoCapturaService;


    //Se nao houver captura ou arquivo serão criados novos, se houver, serão atualizados

    public void salvarCapturas(List<TipoCaptura> tiposCaptura) {
        for(TipoCaptura tc:tiposCaptura){
            tipoCapturaService.salvar(tc);
        }
    }


    public void salvarTiposArquivos(List<TipoArquivo> tiposArquivos) {
        for(TipoArquivo tp:tiposArquivos){
            tipoArquivoService.salvar(tp);
        }
    }


}
