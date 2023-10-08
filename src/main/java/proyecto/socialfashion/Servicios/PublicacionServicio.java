
package proyecto.socialfashion.Servicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
    public void CrearPublicacion(MultipartFile archivo, String contenido, Date alta, Categoria categoria, Usuario usuario, Imagen imagen ) throws MiException{

        Publicacion publicacion = new Publicacion();
        publicacion.setContenido(contenido);
        publicacion.setAlta(alta);
        publicacion.setCategoria(categoria);
        publicacion.setUsuario(usuario);
        publicacion.setImagen(imagen);
        
        Imagen imagen = imagenServicio.guardar(archivo);
        publicacion.setImagen(imagen);
        publicacionRepositorio.save(publicacion);

    }
    
     @Transactional()
    public Publicacion getOne(String idPublicacion){
        return publicacionRepositorio.getOne(idPublicacion);
    }
    
    @Transactional()
    public List<Publicacion> listaPublicacionOrdenadasPorLikes(){
    
        List<Publicacion>listaPublicacion = new ArrayList<>();
        listaPublicacion = publicacionRepositorio.findAll();
        
        
        // Se crea una collection sort y se ordena por likes de noticia
        Collections.sort(listaPublicacion, new Comparator<Publicacion>() {
            @Override
            public int compare(Publicacion publicacion1, Publicacion publicacion2) {
                int likes1 = publicacion1.getLikes().size();
                int likes2 = publicacion2.getLikes().size();
                return Integer.compare(likes2, likes1);
               
            }
        });
       
        return listaPublicacion;
    } 
    
    
    
      @Transactional()
    public List<Publicacion> listaPublicacionOrdenadasPorFechaAlta(){
    
        List<Publicacion>listaPublicacion = new ArrayList<>();
        listaPublicacion = publicacionRepositorio.findAll();
        
        
        // Se crea una collection sort y se ordena por likes de noticia
        Collections.sort(listaPublicacion, new Comparator<Publicacion>() {
            @Override
            public int compare(Publicacion publicacion1, Publicacion publicacion2) {
                
                return publicacion1.getAlta().compareTo(publicacion2.getAlta());
               
            }
        });
       
        return listaPublicacion;
    } 
    
    
    
    
    
    
    
    @Transactional
    public void BajaPublicacion(String idPublicacion){
        
        Publicacion publicacion = publicacionRepositorio.getById(idPublicacion);
        publicacion.setEstado(false);
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void validar(String contenido, Date alta, Categoria categoria, Usuario usuario, Imagen imagen) throws MiException{
    
        if (contenido.isEmpty() || contenido.equalsIgnoreCase("")) {
            throw new MiException("El contenido no puede estar estar vacio");
        }
        
        if (categoria.equals(null) || categoria == null) {
            throw new MiException("La categoria no puede ser nula o estar vacia");
        }
        
        if(alta == null || alta.equals(null)){
            throw new MiException("La fecha de alta no puede ser nula");
        }
        
        if (usuario.equals(null) || usuario == null) {
             throw new MiException("El usuario no puede ser null");
        }
        
        if (imagen.equals(null) || imagen == null) {
             throw new MiException("La imagen no puede ser null");
        }
    
    
    
    }
    
}
