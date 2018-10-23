package br.com.docproc.repository;


import br.com.docproc.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByMatricula(String matricula);

    Usuario findByMatriculaAndPassword(String login, String senha);
}
