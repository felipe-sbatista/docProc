package br.com.docproc.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter @Setter
    private Date dataIncersao;

    @Getter @Setter
    private String nomeArquivo;

    @Getter @Setter
    @ManyToOne
    private TipoArquivo tipoArquivo;

    @Getter @Setter
    @ManyToOne
    private TipoCaptura tipoCaptura;

    @Getter @Setter
    @Lob
    private byte[] arquivo;



    public String getNomeArquivoSemExtensao(){
        String nome[] = this.nomeArquivo.split("\\.");
        // se o arquivo nao esta com a extensao no final do nome
        if(!nome[nome.length - 1].equals(this.tipoArquivo.getFormato())){
            return this.nomeArquivo;
        }
        String result = "";
        for(int i = 0;i < nome.length-1; i++){
            result = result + nome[i];
        }
        return result;
    }
}
