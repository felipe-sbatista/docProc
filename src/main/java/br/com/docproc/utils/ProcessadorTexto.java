package br.com.docproc.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProcessadorTexto {

    private String [] caracteresExtras = {",", ".", "!", "?", ";"};

    public Map<String, Integer> processarTexto(List<String> conteudo){
        Map<String, Integer> palavras = new HashMap<>();
        for (String key : conteudo) {
            if (key.matches("[A-Z|a-z|0-9]*")) {
                if (palavras.containsKey(key)) {
                    palavras.put(key, palavras.get(key) + 1);
                } else {
                    palavras.put(key, 1);
                }
            }
        }
        return palavras;
    }


    public List<String> organizarArquivoDocumento(byte[] conteudo) throws IOException {
        File file = new File("temp");
        OutputStream os = new FileOutputStream(file);
        os.write(conteudo);
        BufferedReader buffer = new BufferedReader(new FileReader(file));

        List<String> result = new ArrayList<>();
        while(buffer.ready()){
            String linha = buffer.readLine();
            String palavrasTemp[] = linha.split(" ");
            for(String palavra : palavrasTemp){
                if(!palavra.isEmpty()) {
                    String palavraAjustada = replaceCaracter(palavra);
                    result.add(palavraAjustada);
                }
            }
        }
        file.delete();
        return result;
    }

    public List<String> organizarArquivoImagem(byte[]conteudo){
        return null;
    }


    private String replaceCaracter(String palavra){
        for(String cr : this.caracteresExtras){
            if(palavra.contains(cr)) {
                palavra = palavra.replace(cr, "");
            }
        }
        return palavra;
    }

    public void generateExcel(String nomeArquivo, Map<String, Integer> palavras){
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
}
