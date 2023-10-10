package proyecto.socialfashion.Controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import proyecto.socialfashion.Entidades.Comentario;
import proyecto.socialfashion.Entidades.Publicacion;
import proyecto.socialfashion.Entidades.Usuario;
import proyecto.socialfashion.Repositorios.PublicacionRepositorio;
import proyecto.socialfashion.Servicios.ComentarioServicio;

@Controller
@RequestMapping("/")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;


    @PostMapping("/publicacion/{idPublicacion}")
    @PreAuthorize("isAuthenticated()")
    public String guardarComentario(
            @PathVariable String idPublicacion,
            @RequestBody String texto,
            @AuthenticationPrincipal Usuario usuario,Model modelo) {

        try {
            Optional<Publicacion> respuesta = publicacionRepositorio.findById(idPublicacion);
            if (respuesta.isPresent()) {
                Publicacion publicacion = respuesta.get();
                Comentario comentario = new Comentario(texto, usuario, publicacion);
                comentarioServicio.guardarComentario(comentario);
                modelo.addAttribute("mensaje", "Comentario guardado exitosamente");
                return "index.html";
            }else{
                modelo.addAttribute("mensaje", "Publicacion inexistente");
                return "index.html";
            }    
        } catch (Exception e) {
            modelo.addAttribute(e.getMessage());
            return "index.html";
        }
    }

    @PostMapping("/comentario/{idComentario}")
    @PreAuthorize("isAuthenticated()")
    public String borrarComentario(
            @PathVariable String idComentario,
            @AuthenticationPrincipal Usuario usuario, Model modelo) {

        try {
            Optional<Comentario> respuesta = comentarioServicio.buscarComentarioPorId(idComentario);
            if (respuesta.isPresent()) {
                Comentario comentario = respuesta.get();
                if(comentario.getIdUsuario().toString()==usuario.getIdUsuario().toString()){
                    comentarioServicio.borrarComentario(comentario.getIdComentario());
                    modelo.addAttribute("mensaje", "Comentario guardado exitosamente");
                    return "index.html";
                }else{
                    modelo.addAttribute("mensaje", "Usuario Incorrecto");
                    return "index.html";
                }  
            }else{
                modelo.addAttribute("mensaje", "Publicacion inexistente"); 
                return "index.html"; 
            }    
        } catch (Exception e) {
            modelo.addAttribute(e.getMessage());
            return "index.html";
        }
    }
    
}
