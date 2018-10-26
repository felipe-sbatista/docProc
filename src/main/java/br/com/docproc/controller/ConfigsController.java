package br.com.docproc.controller;

import br.com.docproc.dto.PermissaoDTO;
import br.com.docproc.serviceImpl.PermissaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/config")
public class ConfigsController{

    @Autowired
    private PermissaoServiceImpl service;

    @PostMapping("/set-permissao-usuario")
    public ResponseEntity<String> setConfiguracoesUsuario(@RequestBody PermissaoDTO permissao){
        return service.setPermissoes(permissao);
    }


}
