package br.com.docproc.serviceImpl;

import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.repository.TipoArquivoRepository;
import br.com.docproc.repository.TipoCapturaRepository;
import br.com.docproc.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoServiceImpl implements PermissaoService {

    @Autowired
    private TipoCapturaRepository tipoCapturaRepository;

    @Autowired
    private TipoArquivoRepository tipoArquivoRepository;


    //Se nao houver captura ou arquivo serão criados novos, se houver, serão atualizados

    public void salvarCapturas(List<TipoCaptura> tiposCaptura) {
        for(TipoCaptura tp:tiposCaptura){
            TipoCaptura tipoCaptura = tipoCapturaRepository.findByFormaCaptura(tp.getFormaCaptura());
            tipoCaptura.setAtivo(tp.isAtivo());
            tipoCapturaRepository.save(tipoCaptura);
        }
    }


    public void salvarTiposArquivos(List<TipoArquivo> tiposArquivos) {
        for(TipoArquivo tp:tiposArquivos){
            TipoArquivo tipo = tipoArquivoRepository.findByFormato(tp.getFormato());
            tipo.setAtivo(tp.isAtivo());
            tipoArquivoRepository.save(tipo);
        }
    }


}
