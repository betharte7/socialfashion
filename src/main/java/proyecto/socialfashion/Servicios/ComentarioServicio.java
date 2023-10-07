package proyecto.socialfashion.Servicios;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import proyecto.socialfashion.Entidades.Comentario;
import proyecto.socialfashion.Enumeraciones.Estado;
import proyecto.socialfashion.Repositorios.ComentarioRepositorio;

@Service
public class ComentarioServicio {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    @Transactional
    public void guardarComentario(Comentario comentario) {

        comentarioRepositorio.save(comentario);
    }
    
    @Transactional
    public ResponseEntity<String> borrarComentario(String idComentario) {
        Optional<Comentario> comentarioOptional = comentarioRepositorio.findById(idComentario);
        if (comentarioOptional.isPresent()) {
            Comentario comentario = comentarioOptional.get();
            comentario.setEstado(Estado.DESESTIMADO);
            comentarioRepositorio.save(comentario);
            return ResponseEntity.status(HttpStatus.OK).body("Comentario Borrado");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comentario no encontrado");
        }
    }
    @Transactional
    public Optional<Comentario> buscarComentarioPorId(String id) {

        return comentarioRepositorio.findById(id);
    }

}

