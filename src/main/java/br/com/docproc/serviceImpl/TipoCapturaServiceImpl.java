package br.com.docproc.serviceImpl;

import br.com.docproc.base.AbstractService;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.exception.ErroNaPersistenciaException;
import br.com.docproc.exception.RegistroJaCadastradoException;
import br.com.docproc.repository.TipoCapturaRepository;
import br.com.docproc.service.TipoArquivoService;
import br.com.docproc.service.TipoCapturaService;
import br.com.docproc.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoCapturaServiceImpl extends AbstractService<TipoCaptura, Long> implements TipoCapturaService {

    @Autowired
    private TipoCapturaRepository repository;

    public TipoCaptura getByForma(String captura) {
        return repository.findByFormaCaptura(captura);
    }

    public List<TipoCaptura> getTodosAtivos() {
        return repository.findByAtivoTrue();
    }

    public ResponseEntity salvarNovo(String descricao){
        try {
            if (repository.findByFormaCaptura(descricao) != null) {
                throw new RegistroJaCadastradoException(Constante.REGISTRO_JA_EXISTENTE);
            }
            TipoCaptura tipoCaptura = new TipoCaptura();
            tipoCaptura.setFormaCaptura(descricao);
            tipoCaptura.setAtivo(true);
            if(repository.save(tipoCaptura) == null){
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
