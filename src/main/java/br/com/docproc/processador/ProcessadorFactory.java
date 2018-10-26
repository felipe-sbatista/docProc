package br.com.docproc.processador;

public class ProcessadorFactory {

    public ProcessadorTexto factory(String extensao){
        switch (extensao){
            case "docx":
                return new ProcessadorDOCX();
            case "pdf":
                return new ProcessadorPDF();
            case "txt":
                return new ProcessadorTXT();
            case "tif":
                return new ProcessadorTIF();
            case "JPG":
                return new ProcessadorJPG();

                default:
                    return null;

        }
    }
}
