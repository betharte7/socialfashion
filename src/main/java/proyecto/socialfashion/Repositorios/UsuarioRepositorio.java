package proyecto.socialfashion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.socialfashion.Entidades.Usuario;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
    
}
