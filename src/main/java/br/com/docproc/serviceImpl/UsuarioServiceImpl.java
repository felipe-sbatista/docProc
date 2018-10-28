package br.com.docproc.serviceImpl;

import br.com.docproc.base.AbstractService;
import br.com.docproc.entity.Usuario;
import br.com.docproc.repository.UsuarioRepository;
import br.com.docproc.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends AbstractService<Usuario, Long> {

    @Autowired
    private UsuarioRepository repository;

    public Usuario getByMatricula(String matricula) {
        return repository.findByMatricula(matricula);
    }

    public ResponseEntity salvarNovo(Usuario usuario) {
        if(usuario.getMatricula() == null || usuario.getMatricula().isEmpty()){
            return ResponseEntity.ok("Matricula nao informada");
        }
        return ResponseEntity.ok(repository.save(usuario));
    }
}
