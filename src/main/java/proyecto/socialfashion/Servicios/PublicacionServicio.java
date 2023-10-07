
package proyecto.socialfashion.Servicios;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.socialfashion.Entidades.Imagen;
import proyecto.socialfashion.Entidades.Publicacion;
import proyecto.socialfashion.Entidades.Usuario;
import proyecto.socialfashion.Enumeraciones.Categoria;
import proyecto.socialfashion.Excepciones.MiException;
import proyecto.socialfashion.Repositorios.PublicacionRepositorio;

@Service
public class PublicacionServicio {
    
    @Autowired
    PublicacionRepositorio publicacionRepositorio;
    
     @Transactional
    public void CrearPublicacion(String contenido, Date alta, Categoria categoria, Usuario usuario, Imagen imagen ) throws MiException{
        
//        validar(titulo, cuerpo);
        
        Publicacion publicacion = new Publicacion();
        publicacion.setContenido(contenido);
        publicacion.setAlta(new Date());
        publicacion.setCategoria(categoria);
        publicacion.setUsuario(usuario);
        publicacion.setImagen(imagen);
        publicacionRepositorio.save(publicacion);

    }
    
    
    public void BajaPublicacion(String idPublicacion){
    
        Publicacion publicacion = 
    
    }
    
    
    
}
