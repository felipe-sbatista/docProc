package br.com.docproc.service;

import br.com.docproc.entity.Arquivo;
import br.com.docproc.entity.FiltroDTO;
import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.exception.EnvioException;
import br.com.docproc.repository.ArquivoRepository;
import br.com.docproc.repository.TipoArquivoRepository;
import br.com.docproc.repository.TipoCapturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private TipoArquivoRepository tipoArquivoRepository;

    @Autowired
    private TipoCapturaRepository tipoCapturaRepository;

    public void salvarArquivo(MultipartFile file, String tipo, String captura) throws IOException {

        try {
            checkTipoArquivo(tipo);
            checkCapturaArquivo(captura);

            Arquivo arquivo = new Arquivo();
            arquivo.setDataIncersao(new Date());
            arquivo.setNomeArquivo(file.getOriginalFilename());
            arquivo.setArquivo(file.getBytes());

            TipoArquivo tipoArq = new TipoArquivo();
            tipoArq.setFormato(file.getContentType());
            arquivo.setTipoArquivo(tipoArq);

            arquivoRepository.save(arquivo);
        } catch (EnvioException e) {
            e.printStackTrace();
        }
    }

    private void checkCapturaArquivo(String captura) throws EnvioException {
        List<TipoCaptura> result = tipoCapturaRepository.findByAtivoTrue();
        for(TipoCaptura tipoCaptura: result){
            if(tipoCaptura.getFormaCaptura().equals(captura)){
                return;
            }
        }
        throw new EnvioException("Tipo de captura do arquivo invalido!");
    }

    private void checkTipoArquivo(String tipo) throws EnvioException {
        List<TipoArquivo> result = tipoArquivoRepository.findByAtivoTrue();
        for(TipoArquivo tipoArquivo: result){
            if(tipoArquivo.getFormato().equals(tipo)){
                return;
            }
        }
        throw new EnvioException("Tipo de captura do arquivo invalido!");
    }

    public List<Arquivo> findByFiltros(FiltroDTO filtro) {
        List<Arquivo> arquivos = arquivoRepository.findAll(new Specification<Arquivo>() {
            @Override
            public Predicate toPredicate(Root<Arquivo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicados = new ArrayList<>();

                if (filtro.getDataEnvio() != null) {
                    predicados.add(criteriaBuilder.equal(root.get("dataEnvio"), filtro.getDataEnvio()));
                }

                if(filtro.getTipoArquivo() != null){
                    predicados.add(criteriaBuilder.equal(root.get("tipoArquivo"), filtro.getTipoArquivo()));
                }

                if(filtro.getTipoCaptura() != null){
                    predicados.add(criteriaBuilder.equal(root.get("tipoCaptura"), filtro.getTipoCaptura()));
                }

                return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
            }
        });
        return arquivos;
    }

    public Arquivo findArquivo(String nome){
        return arquivoRepository.findByNomeArquivo();
    }
}
