package br.com.docproc.dto;

import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class FiltroDTO {

    private TipoCaptura tipoCaptura;

    private TipoArquivo tipoArquivo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dataEnvio;
}
