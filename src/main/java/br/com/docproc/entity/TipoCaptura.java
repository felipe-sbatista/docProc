package br.com.docproc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoCaptura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String formaCaptura;

    private boolean ativo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFormaCaptura() {
        return formaCaptura;
    }

    public void setFormaCaptura(String formaCaptura) {
        this.formaCaptura = formaCaptura;
    }


    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
