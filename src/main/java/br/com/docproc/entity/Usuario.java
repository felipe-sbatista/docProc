package br.com.docproc.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Entity
@EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

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
    @JoinTable(name="usuario_tipoCaptura",
            joinColumns = @JoinColumn(name="usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_arquivo_id", referencedColumnName = "id"))
    @Getter @Setter
    private List<TipoArquivo> permissoesArquivo;


}
