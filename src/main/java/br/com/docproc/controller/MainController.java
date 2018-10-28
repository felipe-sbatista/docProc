package br.com.docproc.controller;

import br.com.docproc.entity.Arquivo;
import br.com.docproc.dto.FiltroDTO;
import br.com.docproc.serviceImpl.ArquivoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private ArquivoServiceImpl service;

    @PostMapping(value = "/listarArquivos")
    public ResponseEntity<List<Arquivo>> listarArquivos(@RequestBody FiltroDTO filtro){
        return service.findByFiltros(filtro);
    }

    @PostMapping(value = "/enviarArquivo")
    public HttpEntity envio(
            @RequestParam("file") MultipartFile file,
            @RequestParam("tipo") String tipo,
            @RequestParam("captura") String captura,
            @RequestParam ("usuario") String matricula
    ) throws IOException {

        return service.salvarArquivo(file, tipo, captura, matricula);
    }

}
