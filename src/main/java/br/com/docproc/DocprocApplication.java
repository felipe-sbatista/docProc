package br.com.docproc;

import br.com.docproc.entity.Funcao;
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

    @Autowired
    FuncaoRepository funcaoRepository;



    @Override
    public void run(String... args) throws Exception {

        //gerar entidades para teste
        gerarTiposCaptura();
        gerarTiposArquivo();
        gerarFuncao();
        gerarUsuarios();


    }


    private void gerarTiposCaptura(){
        TipoCaptura tp = new TipoCaptura();
        tp.setAtivo(true);
        tp.setFormaCaptura("web");

        TipoCaptura tp2 = new TipoCaptura();
        tp2.setAtivo(true);
        tp2.setFormaCaptura("desktop");

        TipoCaptura tp3 = new TipoCaptura();
        tp3.setAtivo(true);
        tp3.setFormaCaptura("mobile");

        tipoCapturaRepository.save(tp);
        tipoCapturaRepository.save(tp2);
        tipoCapturaRepository.save(tp3);

    }

    private void gerarTiposArquivo(){
        TipoArquivo ta = new TipoArquivo();
        ta.setAtivo(true);
        ta.setFormato("txt");

        TipoArquivo ta1 = new TipoArquivo();
        ta1.setAtivo(true);
        ta1.setFormato("pdf");

        TipoArquivo ta2= new TipoArquivo();
        ta2.setAtivo(true);
        ta2.setFormato("doc");

        TipoArquivo ta3= new TipoArquivo();
        ta3.setAtivo(true);
        ta3.setFormato("docx");

        tipoArquivoRepository.save(ta);
        tipoArquivoRepository.save(ta1);
        tipoArquivoRepository.save(ta2);
        tipoArquivoRepository.save(ta3);
    }

    private void gerarFuncao(){
        Funcao funcao1 = new Funcao();
        funcao1.setDescricao("ADMIN");

        Funcao funcao2 = new Funcao();
        funcao2.setDescricao("USER");

        funcaoRepository.save(funcao1);
        funcaoRepository.save(funcao2);
    }

    private void gerarUsuarios(){
        Usuario u1 = new Usuario();
        u1.setMatricula("12");

        List<TipoArquivo> tipoArquivos = new ArrayList<>();
        List<TipoCaptura> tipoCapturas = new ArrayList<>();
        tipoArquivos.add(tipoArquivoRepository.findByFormato("txt"));
        tipoArquivos.add(tipoArquivoRepository.findByFormato("pdf"));
        tipoCapturas.add(tipoCapturaRepository.findByFormaCaptura("web"));

        u1.setPermissoesArquivo(tipoArquivos);
        u1.setPermissoesCaptura(tipoCapturas);
        u1.setFuncao(funcaoRepository.findByDescricao("ADMIN"));

        Usuario u2 = new Usuario();
        u2.setMatricula("13");

        List<TipoArquivo> tipoArquivos2 = new ArrayList<>();
        List<TipoCaptura> tipoCapturas2 =new ArrayList<>();
        tipoArquivos2.add(tipoArquivoRepository.findByFormato("doc"));
        tipoArquivos2.add(tipoArquivoRepository.findByFormato("docx"));
        tipoCapturas2.add(tipoCapturaRepository.findByFormaCaptura("mobile"));

        u2.setPermissoesArquivo(tipoArquivos2);
        u2.setPermissoesCaptura(tipoCapturas2);
        u2.setFuncao(funcaoRepository.findByDescricao("USER"));


        usuarioRepository.save(u1);
        usuarioRepository.save(u2);
    }

}
