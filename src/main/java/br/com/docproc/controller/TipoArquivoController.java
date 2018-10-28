package br.com.docproc.controller;

import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.serviceImpl.TipoArquivoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-arquivo")
public class TipoArquivoController {

    @Autowired
    private TipoArquivoServiceImpl service;


    @GetMapping("/ativos")
    public ResponseEntity<List<TipoArquivo>> getAll(){
        return ResponseEntity.ok(service.getTodosAtivos());
    }

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody TipoArquivo tipoArquivo){
        return ResponseEntity.ok(service.salvar(tipoArquivo));
    }
}
