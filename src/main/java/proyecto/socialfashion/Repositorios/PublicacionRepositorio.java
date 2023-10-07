
package proyecto.socialfashion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.socialfashion.Entidades.Publicacion;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String> {

}
