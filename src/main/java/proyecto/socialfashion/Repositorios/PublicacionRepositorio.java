
package proyecto.socialfashion.Repositorios;


import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.socialfashion.Entidades.Publicacion;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String> {
    
   
    @Query("SELECT p FROM Publicacion p WHERE p.Contenido = :contenido")
    public Publicacion buscarPorContenido(@Param("contenido")String contenido);
    
    @Query("SELECT p FROM Publicacion p WHERE p.alta = :fechaAlta")
    public Publicacion buscarPorFechaDeAlta(@Param("fechaAlta")Date fechaAlta);
    


    
}


