package br.com.docproc.mock;

import java.util.ArrayList;
import java.util.List;

public class MockImagem {
    public List<String> palavras = new ArrayList<>();

    public List<String> getPalavras(byte[] conteudo) {
        this.palavras.add("teste1");
        this.palavras.add("teste3");
        this.palavras.add("teste4");
        this.palavras.add("teste11");
        this.palavras.add("teste4");
        this.palavras.add("teste2");
        this.palavras.add("teste2");
        return palavras;
    }
}
