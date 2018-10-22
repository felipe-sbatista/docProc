package br.com.docproc.controller;

import br.com.docproc.entity.Arquivo;
import br.com.docproc.entity.FiltroDTO;
import br.com.docproc.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private ArquivoService service;


    @PostMapping(value = "/enviarArquivo")
    public String envio(
            @RequestParam("file") MultipartFile file,
            @RequestParam("tipo") String tipo,
            @RequestParam("captura") String captura
    ) throws IOException {

        //salvar o arquivo
        service.salvarArquivo(file, tipo, captura);
        return "";
    }


    @GetMapping(value = "/listarArquivos")
    public ResponseEntity<List<Arquivo>> listArquivos(FiltroDTO filtro){
        List<Arquivo> result = service.findByFiltros(filtro);
        if(result.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
       // List<>
        for(Arquivo arquivo: result){

        }
        return ResponseEntity.ok(result);

    }
}
