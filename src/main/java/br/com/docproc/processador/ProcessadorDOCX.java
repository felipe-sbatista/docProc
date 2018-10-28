package br.com.docproc.processador;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessadorDOCX extends ProcessadorTexto {
    @Override
    public List<String> lerArquivo(byte[] conteudo) throws IOException {
        List<String> retorno = new ArrayList<>();
        File file = new File("temp");
        OutputStream os = new FileOutputStream(file);
        os.write(conteudo);
        FileInputStream fileInput = new FileInputStream(file);
        XWPFDocument document = new XWPFDocument(fileInput);

        for(XWPFParagraph p : document.getParagraphs()){
            retorno.add(p.getText());
        }
        file.delete();
        retorno.removeIf(palavra-> palavra == null || palavra.equals(""));
        return retorno;
    }

}
