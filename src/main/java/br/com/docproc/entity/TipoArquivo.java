package br.com.docproc.entity;

import br.com.docproc.base.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "tipoArquivo")
@EqualsAndHashCode
public class TipoArquivo extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String formato;

    @Getter @Setter
    private boolean ativo;

}
