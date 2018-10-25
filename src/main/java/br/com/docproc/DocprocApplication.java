package br.com.docproc;

import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.entity.Usuario;
import br.com.docproc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DocprocApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DocprocApplication.class, args);
    }

    @Autowired
    TipoArquivoRepository tipoArquivoRepository;

    @Autowired
    TipoCapturaRepository tipoCapturaRepository;

    @Autowired
    ArquivoRepository arquivoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;



    @Override
    public void run(String... args) throws Exception {

    }

}
