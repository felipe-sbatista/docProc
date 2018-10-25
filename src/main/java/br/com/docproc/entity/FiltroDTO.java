package br.com.docproc.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class FiltroDTO {

    @Getter @Setter
    private TipoCaptura tipoCaptura;

    @Getter @Setter
    private TipoArquivo tipoArquivo;

    @Getter @Setter
    private Date dataEnvio;


}
