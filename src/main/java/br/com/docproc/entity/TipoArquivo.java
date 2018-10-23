package br.com.docproc.entity;

import javax.persistence.*;

@Entity
public class TipoArquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String formato;

    private boolean ativo;


    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }



}
