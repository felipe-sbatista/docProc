package br.com.docproc.controller;

import br.com.docproc.entity.Usuario;
import br.com.docproc.serviceImpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl service;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody Usuario usuario){
        return service.salvarNovo(usuario);
    }
}
