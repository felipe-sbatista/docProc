package br.com.docproc.processador;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessadorTXT extends ProcessadorTexto {

    public List<String> lerArquivo(byte[] conteudo) throws IOException {
        File file = new File("temp");
        OutputStream os = new FileOutputStream(file);
        os.write(conteudo);
        BufferedReader buffer = new BufferedReader(new FileReader(file));


        List<String> retorno = new ArrayList<>();
        while(buffer.ready()){
            String linha = buffer.readLine();
            String palavrasTemp[] = linha.split(" ");
            for(String palavra : palavrasTemp){
                if(!palavra.isEmpty()) {
                    String palavraAjustada = replaceCaracter(palavra);
                    retorno.add(palavraAjustada);
                }
            }
        }
        retorno.removeIf(palavra-> palavra == null || palavra.equals(""));
        file.delete();
        return retorno;
    }


}
