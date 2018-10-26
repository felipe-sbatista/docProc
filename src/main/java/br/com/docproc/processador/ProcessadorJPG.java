package br.com.docproc.processador;

import br.com.docproc.mock.MockImagem;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProcessadorJPG extends ProcessadorTexto {
    @Override
    public List<String> lerArquivo(byte[] conteudo) throws IOException {
        MockImagem mock = new MockImagem();
        return mock.getPalavras(conteudo);
    }
}
