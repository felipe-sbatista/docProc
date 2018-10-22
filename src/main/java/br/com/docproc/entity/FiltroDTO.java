package br.com.docproc.entity;

import java.util.Date;

public class FiltroDTO {

    private TipoCaptura tipoCaptura;

    private TipoArquivo tipoArquivo;

    private Date dataEnvio;

    public TipoCaptura getTipoCaptura() {
        return tipoCaptura;
    }

    public void setTipoCaptura(TipoCaptura tipoCaptura) {
        this.tipoCaptura = tipoCaptura;
    }

    public TipoArquivo getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(TipoArquivo tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
