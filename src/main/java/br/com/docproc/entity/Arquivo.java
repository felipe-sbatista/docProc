package br.com.docproc.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Arquivo {

    @Id
    @GeneratedValue
    private int id;

    private Date dataIncersao;

    private String nomeArquivo;

    @ManyToOne
    private TipoArquivo tipoArquivo;

    @ManyToOne
    private TipoCaptura tipoCaptura;

    @Lob
    private byte[] arquivo;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Date getDataIncersao() {
        return dataIncersao;
    }

    public void setDataIncersao(Date dataIncersao) {
        this.dataIncersao = dataIncersao;
    }



    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }



    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }



    public TipoArquivo getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(TipoArquivo tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }



    public TipoCaptura getTipoCaptura() {
        return tipoCaptura;
    }

    public void setTipoCaptura(TipoCaptura tipoCaptura) {
        this.tipoCaptura = tipoCaptura;
    }
}
