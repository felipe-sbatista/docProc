package br.com.docproc.processador;


import br.com.docproc.util.Constante;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//nao eh interface pois o projeto esta em java 8 e nao no 10, para ter os metodos gerarExcel e replace implementados
public abstract class ProcessadorTexto {
    abstract public List<String> lerArquivo(byte[] conteudo) throws IOException;

    public void gerarExcel(String nomeArquivo, Map<String, Integer> palavras){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("aba1");
        FileOutputStream outputStream;
        try{
            outputStream = new FileOutputStream(new File(nomeArquivo));
            int i = 0;
            for(String key : palavras.keySet()){
                HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(key);
                row.createCell(1).setCellValue(palavras.get(key));
                i++;
            }
            workbook.write(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String replaceCaracter(String palavra){
        for(String cr : Constante.caracteresExtras){
            if(palavra.contains(cr)) {
                palavra = palavra.replace(cr, "");
            }
        }
        return palavra;
    }

    public Map<String, Integer> processarTexto(List<String> conteudo){
        Map<String, Integer> palavras = new HashMap<>();
        for (String key : conteudo) {
            if (key.matches("[A-Za-z0-9 ]*")) {
                if (palavras.containsKey(key)) {
                    palavras.put(key, palavras.get(key) + 1);
                } else {
                    palavras.put(key, 1);
                }
            }
        }
        return palavras;
    }

}
