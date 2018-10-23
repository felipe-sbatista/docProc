package br.com.docproc.service;

import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.repository.TipoArquivoRepository;
import br.com.docproc.repository.TipoCapturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfiguracaoService {

    @Autowired
    private TipoCapturaRepository tipoCapturaRepository;

    @Autowired
    private TipoArquivoRepository tipoArquivoRepository;


    public void ajustarConfigs(List<TipoArquivo> tiposArquivo, List<TipoCaptura> tiposCaptura){
        salvarCapturas(tiposCaptura);
        salvarTiposArquivos(tiposArquivo);
    }

    //procura no banco para certificar-se que existe a condicao e atualiza atraves do save com ID a nova opcao
    private void salvarCapturas(List<TipoCaptura> tiposCaptura) {
        for(TipoCaptura tp:tiposCaptura){
            TipoCaptura tipoCaptura = tipoCapturaRepository.findByFormaCaptura(tp.getFormaCaptura());
            tipoCaptura.setAtivo(tp.isAtivo());
            tipoCapturaRepository.save(tipoCaptura);
        }
    }


    private void salvarTiposArquivos(List<TipoArquivo> tiposArquivos) {
        for(TipoArquivo tp:tiposArquivos){
            TipoArquivo tipo = tipoArquivoRepository.findByFormato(tp.getFormato());
            tipo.setAtivo(tp.isAtivo());
            tipoArquivoRepository.save(tipo);
        }
    }


}
