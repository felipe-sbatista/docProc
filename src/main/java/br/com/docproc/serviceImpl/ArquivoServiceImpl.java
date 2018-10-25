package br.com.docproc.serviceImpl;

import br.com.docproc.entity.*;
import br.com.docproc.exception.EnvioException;
import br.com.docproc.exception.PermissaoException;
import br.com.docproc.repository.ArquivoRepository;
import br.com.docproc.repository.TipoArquivoRepository;
import br.com.docproc.repository.TipoCapturaRepository;
import br.com.docproc.repository.UsuarioRepository;
import br.com.docproc.service.ArquivoService;
import br.com.docproc.utils.ProcessadorTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ArquivoServiceImpl implements ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private TipoArquivoRepository tipoArquivoRepository;

    @Autowired
    private TipoCapturaRepository tipoCapturaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public HttpEntity salvarArquivo(MultipartFile file, String tipo, String captura, Usuario usuario) throws IOException {
        try {
            checkTipoArquivo(tipo);
            checkCapturaArquivo(captura);
            Arquivo arquivo = new Arquivo();
            popularArquivo(arquivo, file, tipo, captura);
            checkPermissao(usuario, arquivo);
            arquivo  = arquivoRepository.save(arquivo);
            if(arquivo == null){
                throw new EnvioException("Erro ao salvar arquivo");
            }
            byte [] planilha = gerarPlanilha(arquivo);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition","attachment;filename=" + arquivo.getNomeArquivoSemExtensao()+".xls");
          return new HttpEntity<>(planilha,httpHeaders);
        } catch (EnvioException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().build();
        }catch(PermissaoException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Usuario sem permissao para tais parametros");
        }
    }

    private void popularArquivo(Arquivo arquivo, MultipartFile file, String tipo, String captura) throws IOException {
        arquivo.setDataIncersao(new Date());
        arquivo.setNomeArquivo(file.getOriginalFilename());
        arquivo.setArquivo(file.getBytes());
        arquivo.setTipoArquivo(tipoArquivoRepository.findByFormato(tipo));
        arquivo.setTipoCaptura(tipoCapturaRepository.findByFormaCaptura(captura));
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

    public ResponseEntity<List<Arquivo>> findByFiltros(FiltroDTO filtro) {
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
        if(arquivos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(arquivos);
    }

    public byte[] gerarPlanilha(Arquivo arquivo) throws IOException {
        ProcessadorTexto processadorTexto = new ProcessadorTexto();
        List<String>result = processadorTexto.organizarArquivoDocumento(arquivo.getArquivo());
        Map<String, Integer> map = processadorTexto.processarTexto(result);
        String nomeFile = arquivo.getNomeArquivoSemExtensao()+".xls";
        processadorTexto.generateExcel(nomeFile, map);
        byte[] planilha = Files.readAllBytes(Paths.get(nomeFile));
        return planilha;
    }

    private void checkPermissao(Usuario usuario, Arquivo arquivo) throws PermissaoException {
        if(usuario.getPermissoesArquivo().contains(arquivo.getTipoArquivo())){
            throw new PermissaoException("Tipo de arquivo nao permitido ao usuario!");
        }
        if(usuario.getPermissoesCaptura().contains(arquivo.getTipoCaptura())){
            throw new PermissaoException("Tipo de captura nao permitido ao usuario!");
        }
    }
}
