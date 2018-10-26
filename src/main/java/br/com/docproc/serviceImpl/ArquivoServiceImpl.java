package br.com.docproc.serviceImpl;

import br.com.docproc.base.AbstractService;
import br.com.docproc.dto.FiltroDTO;
import br.com.docproc.entity.*;
import br.com.docproc.exception.EnvioException;
import br.com.docproc.exception.PermissaoException;
import br.com.docproc.processador.ProcessadorFactory;
import br.com.docproc.repository.ArquivoRepository;
import br.com.docproc.service.ArquivoService;
import br.com.docproc.processador.ProcessadorTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
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
public class ArquivoServiceImpl extends AbstractService<Arquivo, Long> implements ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private TipoArquivoServiceImpl tipoArquivoService;

    @Autowired
    private TipoCapturaServiceImpl tipoCapturaService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    public HttpEntity salvarArquivo(MultipartFile file, String tipo, String captura, String matricula) throws IOException {
        try {
            Usuario usuario = usuarioService.getByMatricula(matricula);
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

            httpHeaders.setContentType(new MediaType("application","xls"));
            httpHeaders.set("Content-Disposition","attachment;filename=" + arquivo.getNomeArquivoSemExtensao()+".xls");
            return new HttpEntity<>(planilha,httpHeaders);
        } catch (EnvioException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }catch(PermissaoException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    private void popularArquivo(Arquivo arquivo, MultipartFile file, String tipo, String captura) throws IOException {
        arquivo.setDataIncersao(new Date());
        arquivo.setNomeArquivo(file.getOriginalFilename());
        arquivo.setArquivo(file.getBytes());
        arquivo.setTipoArquivo(tipoArquivoService.getByTipo(tipo));
        arquivo.setTipoCaptura(tipoCapturaService.getByForma(captura));
    }

    private void checkCapturaArquivo(String captura) throws EnvioException {
        List<TipoCaptura> result = tipoCapturaService.getTodosAtivos();
        for(TipoCaptura tipoCaptura: result){
            if(tipoCaptura.getFormaCaptura().equals(captura)){
                return;
            }
        }
        throw new EnvioException("Forma de captura do arquivo invalido!");
    }

    private void checkTipoArquivo(String tipo) throws EnvioException {
        List<TipoArquivo> result = tipoArquivoService.getTodosAtivos();
        for(TipoArquivo tipoArquivo: result){
            if(tipoArquivo.getFormato().equals(tipo)){
                return;
            }
        }
        throw new EnvioException("Tipo de arquivo invalido!");
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

        //instancia tipo processador
        ProcessadorTexto processador = new ProcessadorFactory().factory(arquivo.getTipoArquivo().getFormato());

        //pegar todas as palavras
        List<String>result = processador.lerArquivo(arquivo.getArquivo());

        //organizar pela quantidade
        Map<String, Integer> map = processador.processarTexto(result);

        String nomeFile = arquivo.getNomeArquivoSemExtensao()+".xls";
        processador.gerarExcel(nomeFile, map);
        byte[] planilha = Files.readAllBytes(Paths.get(nomeFile));
        Files.delete(Paths.get(nomeFile));
        return planilha;
    }

    private void checkPermissao(Usuario usuario, Arquivo arquivo) throws PermissaoException {
        if(!usuario.getPermissoesArquivo().contains(arquivo.getTipoArquivo())){
            throw new PermissaoException("Tipo de arquivo nao permitido ao usuario!");
        }
        if(!usuario.getPermissoesCaptura().contains(arquivo.getTipoCaptura())){
            throw new PermissaoException("Tipo de captura nao permitido ao usuario!");
        }
    }
}
