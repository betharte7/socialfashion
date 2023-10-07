
package proyecto.socialfashion.Repositorios;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.socialfashion.Entidades.Publicacion;
import proyecto.socialfashion.Entidades.Usuario;
import proyecto.socialfashion.Enumeraciones.Categoria;
import proyecto.socialfashion.Excepciones.MiException;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Usuario, String> {
    
   
    
    
    
    
    
    
}


