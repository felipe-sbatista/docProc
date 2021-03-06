package br.com.docproc.controller;

import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.serviceImpl.TipoCapturaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-captura")
public class TipoCapturaController {
    @Autowired
    private TipoCapturaServiceImpl service;


    @GetMapping("/ativos")
    public ResponseEntity<List<TipoCaptura>> getAll(){
        return ResponseEntity.ok(service.getTodosAtivos());
    }

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody TipoCaptura tipoCaptura){
        return ResponseEntity.ok(service.salvar(tipoCaptura));
    }
}
