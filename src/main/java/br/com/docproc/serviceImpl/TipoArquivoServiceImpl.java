package br.com.docproc.serviceImpl;

import br.com.docproc.base.AbstractService;
import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.exception.ErroNaPersistenciaException;
import br.com.docproc.exception.RegistroJaCadastradoException;
import br.com.docproc.repository.TipoArquivoRepository;
import br.com.docproc.service.TipoArquivoService;
import br.com.docproc.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoArquivoServiceImpl extends AbstractService<TipoArquivo, Long>  implements TipoArquivoService {

    @Autowired
    private TipoArquivoRepository repository;

   public List<TipoArquivo> getTodosAtivos(){
        return repository.findByAtivoTrue();
    }

   public TipoArquivo getByTipo(String tipo){
        return repository.findByFormato(tipo);
    }

    public ResponseEntity salvarNovo(String descricao){
       try {
           if (repository.findByFormato(descricao) != null) {
               throw new RegistroJaCadastradoException(Constante.REGISTRO_JA_EXISTENTE);
           }
           TipoArquivo tipoArquivo = new TipoArquivo();
           tipoArquivo.setFormato(descricao);
           tipoArquivo.setAtivo(true);
           if(repository.save(tipoArquivo) == null){
               throw new ErroNaPersistenciaException(Constante.ERRO_PERSISTENCIA);
           }
           return ResponseEntity.ok("Cadastro realizado");
       }catch(RegistroJaCadastradoException e){
           e.printStackTrace();
           return ResponseEntity.ok(e.getMessage());
       } catch (ErroNaPersistenciaException e) {
           e.printStackTrace();
           return ResponseEntity.ok(e.getMessage());
       }
    }
}
