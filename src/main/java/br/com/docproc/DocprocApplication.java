package br.com.docproc;

import br.com.docproc.entity.Role;
import br.com.docproc.entity.TipoArquivo;
import br.com.docproc.entity.TipoCaptura;
import br.com.docproc.entity.Usuario;
import br.com.docproc.repository.*;
import br.com.docproc.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DocprocApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DocprocApplication.class, args);
    }

    @Autowired
    TipoArquivoRepository tipoArquivoRepository;

    @Autowired
    TipoCapturaRepository tipoCapturaRepository;

    @Autowired
    ArquivoRepository arquivoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role role1 = new Role();
        role1.setRole(Roles.ADMIN);

        Role role2 = new Role();
        role2.setRole(Roles.USUARIO);
        roleRepository.save(role1);
        roleRepository.save(role2);


        Usuario usuario1 = new Usuario();
        usuario1.setMatricula("11");
        usuario1.setPassword("123");
        List<Role> roles1 = new ArrayList<>();
        roles1.add(role1);
        usuario1.setRoles(roles1);

        Usuario usuario2 = new Usuario();
        usuario2.setPassword("321");
        usuario2.setMatricula("22");
        List<Role> roles2 = new ArrayList<>();
        roles2.add(role2);
        usuario2.setRoles(roles2);

        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);


        TipoCaptura tipoCaptura1 = new TipoCaptura();
        tipoCaptura1.setAtivo(true);
        tipoCaptura1.setFormaCaptura("web");

        TipoCaptura tipoCaptura2 = new TipoCaptura();
        tipoCaptura2.setAtivo(true);
        tipoCaptura2.setFormaCaptura("desktop");

        tipoCapturaRepository.save(tipoCaptura1);
        tipoCapturaRepository.save(tipoCaptura2);


        TipoArquivo tipoArquivo1 = new TipoArquivo();
        tipoArquivo1.setFormato("doc");
        tipoArquivo1.setAtivo(true);

        TipoArquivo tipoArquivo2 = new TipoArquivo();
        tipoArquivo2.setFormato("txt");
        tipoArquivo2.setAtivo(true);

        tipoArquivoRepository.save(tipoArquivo1);
        tipoArquivoRepository.save(tipoArquivo2);



    }

}
