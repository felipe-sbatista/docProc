package br.com.docproc.serviceImpl;

import br.com.docproc.base.AbstractService;
import br.com.docproc.entity.Usuario;
import br.com.docproc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends AbstractService<Usuario, Long> {

    @Autowired
    private UsuarioRepository repository;

    public Usuario getByMatricula(String matricula) {
        return repository.findByMatricula(matricula);
    }
}
