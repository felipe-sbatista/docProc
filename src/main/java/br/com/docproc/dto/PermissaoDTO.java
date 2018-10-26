package br.com.docproc.dto;

import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PermissaoDTO {

    private Usuario admin;

    private Usuario usuario;

    private List<TipoArquivo> tipoArquivos;

    private List<TipoCaptura> tipoCapturas;
}
