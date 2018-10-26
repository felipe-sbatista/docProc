package br.com.docproc.entity;

import br.com.docproc.base.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@EqualsAndHashCode
public class Usuario extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String matricula;

    @Getter @Setter
    private String password;

    @ManyToMany
    @JoinTable(name="usuario_tipoCaptura",
            joinColumns = @JoinColumn(name="usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_captura_id", referencedColumnName = "id"))
    @Getter @Setter
    private List<TipoCaptura> permissoesCaptura;


    @ManyToMany
    @JoinTable(name="usuario_tipoArquivo",
            joinColumns = @JoinColumn(name="usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_arquivo_id", referencedColumnName = "id"))
    @Getter @Setter
    private List<TipoArquivo> permissoesArquivo;

    @ManyToOne
    @Getter @Setter
    private Funcao funcao;


}
