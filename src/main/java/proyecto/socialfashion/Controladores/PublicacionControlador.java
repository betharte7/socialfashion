
package proyecto.socialfashion.Controladores;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import proyecto.socialfashion.Entidades.Publicacion;
import proyecto.socialfashion.Enumeraciones.Categoria;
import proyecto.socialfashion.Excepciones.MiException;
import proyecto.socialfashion.Servicios.PublicacionServicio;

@Controller
public class PublicacionControlador {
    
    @Autowired
    PublicacionServicio publicacionServicio;
    
    @Autowired
    UsuarioServicio usuarioServicio;
    
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/publicaciones")
    public String publicaciones(ModelMap modelo){
        try {
            ArrayList<Publicacion> publicacionesAlta = (ArrayList<Publicacion>) publicacionServicio.listaPublicacionOrdenadasPorFechaAlta();
            modelo.put("listaPorTendencias", publicacionesAlta);
            //HTML con la pagina en donde se encuentran las publicaciones
            return""
        } catch (MiException ex) {
            modelo.put("Error", ex.getMessage());
            //HTML en donde se se trabaje el error
            return""
        }
    }
    
    
    
    
    
    
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/registrarPubli")
    public String registrarPublicacion(){
        //Agg html para crear publicacion
    return "";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/registro/{idUsuario}")
    public String registro(@RequestParam String contenido, @RequestParam String categoria,@PathVariable String idUsuario, ModelMap modelo, MultipartFile archivo){
        
        try {
            
            //Servicio para traer un usuario
            Usuario usuario = usuarioServicio.getOne(idUsuario);
            
            if (categoria.equalsIgnoreCase("INDUMENTARIA")) {
                 publicacionServicio.CrearPublicacion(archivo, contenido,new Date(), Categoria.INDUMENTARIA, usuario);
                
            } else if(categoria.equalsIgnoreCase("MAQUILLAJE")) {
             publicacionServicio.CrearPublicacion(archivo, contenido,new Date() , Categoria.MAQUILLAJE, usuario);
                
            }else if(categoria.equalsIgnoreCase("CALZADO")){
             publicacionServicio.CrearPublicacion(archivo, contenido,new Date() , Categoria.CALZADO, usuario);
                
            }else{
             publicacionServicio.CrearPublicacion(archivo, contenido,new Date() , Categoria.MARROQUINERIA, usuario);
            }
           
            
            modelo.put("exito", "Publicacion registrada correctamente!");
            
            //Agg html en el que este el formulario. IDEM ANTERIOR
            return "";
        } catch (MiException ex) {
            
            modelo.put("Error", ex.getMessage());
            modelo.put("imagen", archivo);
            modelo.put("nombre", contenido);
            modelo.put("email", categoria);
            
            //Agg html en el que este el formulario. IDEM ANTERIOR o Que redireccione a una pagina error
            return "";
        }
        
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/tendencias")
    public String Tendencias(ModelMap modelo){
    
        try {
            
           ArrayList<Publicacion>listaPorTendencias = (ArrayList<Publicacion>) publicacionServicio.listaPublicacionOrdenadasPorLikes();
            modelo.put("listaPorTendencias", listaPorTendencias);
            
            //HTML en el que se encuentran las tendencias
            return"";
            
        } catch (MiException ex) {
        
            
            //HTML EN EL QUE SE INDIQUE ERROR DE TENDENCIAS
        }
    
    
    }
    
    
    
    
    
    
    
    
    
}
