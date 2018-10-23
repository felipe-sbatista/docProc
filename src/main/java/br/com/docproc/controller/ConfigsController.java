package br.com.docproc.controller;

import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigsController{


    @PostMapping("/setConfiguracoes")
    public ResponseEntity<String> setConfigs(@RequestBody List<TipoArquivo> tiposArquivo, @RequestBody List<TipoCaptura> tiposCaptura){
        return ResponseEntity.ok("");
    }
}
