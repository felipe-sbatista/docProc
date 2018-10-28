package br.com.docproc.service;

import br.com.docproc.entity.Usuario;

public interface UsuarioService {
    Usuario getByMatricula(String matricula);
}
