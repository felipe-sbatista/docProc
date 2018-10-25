package br.com.docproc.controller;

import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.serviceImpl.PermissaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigsController{

    @Autowired
    private PermissaoServiceImpl service;

    @PostMapping("/setPermissao/tipoArquivo")
    public ResponseEntity<String> setTipoArquivo(@RequestBody List<TipoArquivo> tiposArquivo){
        service.salvarTiposArquivos(tiposArquivo);
        return ResponseEntity.ok("Permissoes alteradas!");
    }

    @PostMapping("/setPermissao/tipoCaptura")
    public ResponseEntity<String> setTipoCaptura(@RequestBody List<TipoCaptura> tiposCaptura){
        service.salvarCapturas(tiposCaptura);
        return ResponseEntity.ok("Capturas alteradas!");
    }
}
