package proyecto.socialfashion.Controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<String> guardarComentario(
            @PathVariable String idPublicacion,
            @RequestBody String texto,
            @AuthenticationPrincipal Usuario usuario) {

        try {
            
            Optional<Publicacion> respuesta = publicacionRepositorio.findById(idPublicacion);
            
            if (respuesta.isPresent()) {
                Publicacion publicacion = respuesta.get();
                Comentario comentario = new Comentario(texto, usuario, publicacion);
                comentarioServicio.guardarComentario(comentario);
                return ResponseEntity.status(HttpStatus.CREATED).body("Comentario guardado exitosamente");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Publicacion inexistente");
            }    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el comentario");
        }
    }
    
}
