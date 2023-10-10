package proyecto.socialfashion.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

import proyecto.socialfashion.Entidades.Comentario;
import proyecto.socialfashion.Entidades.Publicacion;
import proyecto.socialfashion.Entidades.Reporte;
import proyecto.socialfashion.Entidades.Usuario;
import proyecto.socialfashion.Enumeraciones.Estado;
import proyecto.socialfashion.Enumeraciones.Tipo;
import proyecto.socialfashion.Enumeraciones.TipoObjeto;
import proyecto.socialfashion.Servicios.ComentarioServicio;
import proyecto.socialfashion.Servicios.PublicacionServicio;
import proyecto.socialfashion.Servicios.ReporteServicio;

@Controller
@RequestMapping("/reportar")
public class ReporteControlador {

    @Autowired
    private ReporteServicio reporteServicios;

    @Autowired
    private ComentarioServicio comentarioServicio;
    @Autowired
    private PublicacionServicio publicacionServicio;

    @PostMapping("/publicacion/{idPublicacion}")
    @PreAuthorize("isAuthenticated()")
    public String denunciarPublicacion(@PathVariable String idPublicacion,
            @RequestBody String texto,
            @RequestParam Tipo tipo,
            @RequestParam TipoObjeto tipoObjeto,
            @RequestParam String idObjeto,
            @AuthenticationPrincipal Usuario usuario, ModelMap modelo) {

        if (tipo != Tipo.SPAM && tipo != Tipo.CONTENIDO_OFENSIVO && tipo != Tipo.INCUMPLIMIENTO_DE_REGLAS) {
            modelo.addAttribute("mensaje", "Tipo de denuncia inválido");
        }
        if (tipoObjeto != TipoObjeto.COMENTARIO && tipoObjeto != TipoObjeto.PUBLICACION) {
            modelo.addAttribute("mensaje", "Tipo de objeto de denuncia inválido");
        }

        if (tipoObjeto == TipoObjeto.COMENTARIO) {
            Optional<Comentario> respuestaC = comentarioServicio.buscarComentarioPorId(idObjeto);

            if (respuestaC.isPresent()) {
                modelo.addAttribute("mensaje", "No se encontro comentario");
            }
        }
        if (tipoObjeto == TipoObjeto.PUBLICACION) {
            Optional<Publicacion> respuestaP = publicacionServicio.buscarPublicacionPorId(idObjeto);

            if (respuestaP.isPresent()) {
                modelo.addAttribute("mensaje", "No se encontro Publicación");
            }
        }
        if (tipoObjeto == TipoObjeto.USUARIO) {
            Optional<Publicacion> respuestaU = publicacionServicio.buscarPublicacionPorId(idObjeto);

            if (respuestaU.isPresent()) {
                modelo.addAttribute("mensaje", "No se encontro Usuario");
            }
        }
        try {
            Reporte reporte = new Reporte(texto, Estado.PENDIENTE, tipo, tipoObjeto, idObjeto, usuario);
            reporteServicios.guardarReporte(reporte);
            modelo.addAttribute("mensaje", "Reporte guardado exitosamente");

            return ResponseEntity.status(HttpStatus.CREATED).body("Reporte guardado exitosamente");
        } catch (Exception e) {
            modelo.addAttribute("mensaje", "Error al guardar el reporte");
        }

    }

}
