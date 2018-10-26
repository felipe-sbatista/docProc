package br.com.docproc.teste;

import br.com.docproc.dto.FiltroDTO;
import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.repository.TipoArquivoRepository;
import br.com.docproc.repository.TipoCapturaRepository;
import br.com.docproc.serviceImpl.ArquivoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArquivoServiceTest {

    @Autowired
    private ArquivoServiceImpl service;

    @Autowired
    private TipoCapturaRepository tipoCapturaRepository;

    @Autowired
    private TipoArquivoRepository tipoArquivoRepository;

    @Test
    public void findByFiltros() {
        FiltroDTO filtro = new FiltroDTO();
        TipoCaptura tipoCaptura = tipoCapturaRepository.findByFormaCaptura("web");
        TipoArquivo tipoArquivo = tipoArquivoRepository.findByFormato("doc");
        filtro.setTipoArquivo(tipoArquivo);
        filtro.setTipoCaptura(tipoCaptura);
        //List<Arquivo> result = service.findByFiltros(filtro);
        //assertEquals(result.size(), 1);
    }
}