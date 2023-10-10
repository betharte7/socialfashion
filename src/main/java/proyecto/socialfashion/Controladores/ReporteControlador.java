package proyecto.socialfashion.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
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
import proyecto.socialfashion.Servicios.UsuarioServicio;

@Controller
@RequestMapping("/reportar")
public class ReporteControlador {

    @Autowired
    private ReporteServicio reporteServicios;

    @Autowired
    private ComentarioServicio comentarioServicio;
    @Autowired
    private PublicacionServicio publicacionServicio;

    @Autowired
    private UsuarioServicio usuarioServicio; 

    @PostMapping("/publicacion/{idPublicacion}")
    @PreAuthorize("isAuthenticated()")
    public String denunciar(@PathVariable String idPublicacion,
            @RequestBody String texto,
            @RequestParam String tipo,
            @RequestParam String tipoObjeto,
            @RequestParam String idObjeto,
            @AuthenticationPrincipal Usuario usuario, ModelMap modelo) {
            
        tipo = tipo.toUpperCase();
        tipoObjeto = tipoObjeto.toUpperCase();
        if (!tipo.equals(Tipo.SPAM.toString()) && !tipo.equals(Tipo.CONTENIDO_OFENSIVO.toString()) && !tipo.equals(Tipo.INCUMPLIMIENTO_DE_REGLAS.toString())) {
            modelo.addAttribute("mensaje", "Tipo de denuncia inválido");
        }
        
        if (!tipoObjeto.equals(TipoObjeto.COMENTARIO.toString()) && !tipoObjeto.equals(TipoObjeto.PUBLICACION.toString()) && !tipoObjeto.equals(TipoObjeto.USUARIO.toString())) {
            modelo.addAttribute("mensaje", "Tipo de objeto de denuncia inválido");
        }

        if (tipoObjeto.equals(TipoObjeto.COMENTARIO.toString())) {
            Optional<Comentario> respuestaC = comentarioServicio.buscarComentarioPorId(idObjeto);

            if (respuestaC.isPresent()) {
                modelo.addAttribute("mensaje", "No se encontro comentario");
            }
        }
        if (tipoObjeto.equals(TipoObjeto.PUBLICACION.toString())) {
            Optional<Publicacion> respuestaP = publicacionServicio.buscarPublicacionPorId(idObjeto);

            if (respuestaP.isPresent()) {
                modelo.addAttribute("mensaje", "No se encontro Publicación");
            }
        }
        if (tipoObjeto.equals(TipoObjeto.USUARIO.toString())) {
            Optional<Usuario> respuestaU = usuarioServicio.buscarUsuarioOptionalId(idObjeto);

            if (respuestaU.isPresent()) {
                modelo.addAttribute("mensaje", "No se encontro Usuario");
            }
        }
        try {
            Tipo tipoEnum = Tipo.valueOf(tipo); 
            TipoObjeto tipoObjetoEnum = TipoObjeto.valueOf(tipoObjeto); 
        
            Reporte reporte = new Reporte(texto, Estado.PENDIENTE, tipoEnum, tipoObjetoEnum, idObjeto, usuario);
            reporteServicios.guardarReporte(reporte);
            modelo.addAttribute("mensaje", "Reporte guardado exitosamente");
            return "index.html";
        } catch (IllegalArgumentException e) {
            modelo.addAttribute("mensaje", "Tipo de denuncia o tipo de objeto inválido");
            return "index.html";
        } catch (Exception e) {
            modelo.addAttribute("mensaje", "Error al guardar el reporte");
            return "index.html";
        }
        

    }

}
