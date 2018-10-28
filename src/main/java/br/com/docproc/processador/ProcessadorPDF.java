package br.com.docproc.processador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.pdmodel.PDDocument;
public class ProcessadorPDF extends ProcessadorTexto {
    @Override
    public List<String> lerArquivo(byte[] conteudo) throws IOException {
        List<String> retorno = new ArrayList<>();
        File file = new File("temp");
        OutputStream os = new FileOutputStream(file);
        os.write(conteudo);
        PDDocument documento = PDDocument.load(file);

        PDFTextStripperByArea faixaByArea = new PDFTextStripperByArea();
        faixaByArea.setSortByPosition(true);

        PDFTextStripper faixa = new PDFTextStripper();

        String pdfFileInText = faixa.getText(documento);

        String linhas[] = pdfFileInText.split(" ");
        for (String linha : linhas) {
            String palavraAjustada = replaceCaracter(linha);
            retorno.add(palavraAjustada);
        }
        file.delete();
        retorno.removeIf(palavra-> palavra == null || palavra.equals(""));
        return retorno;
    }

}
