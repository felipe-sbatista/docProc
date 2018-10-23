package br.com.docproc.controller;

import br.com.docproc.entity.Arquivo;
import br.com.docproc.entity.FiltroDTO;
import br.com.docproc.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
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

    @GetMapping(value = "/baixarArquivo/{nomeArquivo}")
    public ResponseEntity<Resource> baixarArquivo(@PathVariable String nomeArquivo){
        Arquivo result = service.findArquivo(nomeArquivo);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(result.getTipoArquivo().getFormato()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + result.getNomeArquivo() +"\"")
                .body(new ByteArrayResource(result.getArquivo()));

    }
}
