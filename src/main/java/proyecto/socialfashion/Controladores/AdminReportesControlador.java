package proyecto.socialfashion.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import proyecto.socialfashion.Entidades.Comentario;
import proyecto.socialfashion.Entidades.Publicacion;
import proyecto.socialfashion.Entidades.Reporte;
import proyecto.socialfashion.Entidades.Usuario;
import proyecto.socialfashion.Enumeraciones.Roles;
import proyecto.socialfashion.Servicios.ComentarioServicio;
import proyecto.socialfashion.Servicios.PublicacionServicio;
import proyecto.socialfashion.Servicios.ReporteServicio;
import proyecto.socialfashion.Servicios.UsuarioServicio;

@Controller
@RequestMapping("/admin")
public class AdminReportesControlador {

    private ReporteServicio reporteServicio;
    private UsuarioServicio usuarioServicio;
    private ComentarioServicio comentarioServicio;
    private PublicacionServicio publicacionServicio;

    @Autowired
    public AdminReportesControlador(ReporteServicio reporteServicio, UsuarioServicio usuarioServicio,
            ComentarioServicio comentarioServicio, PublicacionServicio publicacionServicio) {
        this.reporteServicio = reporteServicio;
        this.usuarioServicio = usuarioServicio;
        this.comentarioServicio = comentarioServicio;
        this.publicacionServicio = publicacionServicio;

    }

    @GetMapping("/usuarios")
    @PreAuthorize("isAuthenticated()")
    public String reporteUsuarios(@AuthenticationPrincipal Usuario usuario, ModelMap modelo) {

        if (usuario.getRoles() == Roles.ADMIN) {
            List<Reporte> reporteUsuario = reporteServicio.obtenerUsuariosReportadosPendientes();
            modelo.addAttribute("Reporte Usuarios", reporteUsuario);
            return "adminUsuarios";
        } else {
            modelo.addAttribute("mensaje", "Usuario no permitido");
            return "index.html";
        }
    }

    @GetMapping("/comentarios")
    @PreAuthorize("isAuthenticated()")
    public String reporteComentarios(@AuthenticationPrincipal Usuario usuario, ModelMap modelo) {
        if (usuario.getRoles() == Roles.ADMIN) {
            List<Reporte> reporteComentarios = reporteServicio.obtenerComentariosReportadosPendientes();
            modelo.addAttribute("Reporte Comentarios", reporteComentarios);
            return "adminComentarios";
        } else {
            modelo.addAttribute("mensaje", "Usuario no permitido");
            return "index.html";
        }
    }

    @GetMapping("/publicaciones")
    @PreAuthorize("isAuthenticated()")
    public String reportePublicaciones(@AuthenticationPrincipal Usuario usuario, ModelMap modelo, Model model) {
        if (usuario.getRoles() == Roles.ADMIN) {
            List<Reporte> reportePublicaciones = reporteServicio.obtenerPublicacionesReportadasPendientes();
            modelo.addAttribute("reporte Publicaciones", reportePublicaciones);
            return "adminPublicaciones";
        } else {
            modelo.addAttribute("mensaje", "Usuario no permitido");
            return "index.html";
        }
    }

    @PostMapping("/usuarios")
    @PreAuthorize("isAuthenticated()")
    public String resultadoReporteUsuario(@PathVariable String estado,
            @PathVariable String idObjeto,
            @PathVariable String idReporte,
            @AuthenticationPrincipal Usuario usuario, Model modelo) {
        Optional<Usuario> respuestaU = usuarioServicio.buscarUsuarioOptionalId(idObjeto);
        if (respuestaU.isPresent()) {
            Usuario usuario2 = respuestaU.get();
            String mensajeValidacion = reporteServicio.procesarReporte(estado, idObjeto, idReporte, usuario, modelo, usuario2);
            modelo.addAttribute("mensaje", mensajeValidacion);
        } else {
            modelo.addAttribute("mensaje", "Comentario no encontrado");
            return "admin/usuarios";
        }
                    return "admin/usuarios";

    }

    @PostMapping("/comentario")
    @PreAuthorize("isAuthenticated()")
    public String resultadoReporteComentario(@PathVariable String estado,
            @PathVariable String idObjeto,
            @PathVariable String idReporte,
            @AuthenticationPrincipal Usuario usuario, Model modelo) {
        Optional<Comentario> respuestaC = comentarioServicio.buscarComentarioPorId(idObjeto);
        if (respuestaC.isPresent()) {
            Comentario comentario = respuestaC.get();
            String mensajeValidacion = reporteServicio.procesarReporte(estado, idObjeto, idReporte, usuario, modelo, comentario);
            modelo.addAttribute("mensaje", mensajeValidacion);
        } else {
            modelo.addAttribute("mensaje", "Comentario no encontrado");
            return "admin/comentario";
        }
        return "admin/comentario";
    }

    @PostMapping("/publicacion")
    @PreAuthorize("isAuthenticated()")
    public String resultadoReportePublicacion(@PathVariable String estado,
            @PathVariable String idObjeto,
            @PathVariable String idReporte,
            @AuthenticationPrincipal Usuario usuario, Model modelo) {
        Optional<Publicacion> respuestaP = publicacionServicio.buscarPublicacionPorId(idObjeto);
        if (respuestaP.isPresent()) {
            Publicacion publicacion = respuestaP.get();
            String mensajeValidacion = reporteServicio.procesarReporte(estado, idObjeto, idReporte, usuario, modelo, publicacion );
            modelo.addAttribute("mensaje", mensajeValidacion);
        }else{
            modelo.addAttribute("mensaje", "Publicacion no encontrada");
            return "admin/publicacion";
        }
    return "admin/publicacion";
    }
}
/*
 * 
 * 
 * @PostMapping("/usuarios")
 * 
 * @PreAuthorize("isAuthenticated()")
 * public String resultadoReporteUsuario(@PathVariable String estado,
 * 
 * @PathVariable String idObjeto,
 * 
 * @PathVariable String idReporte,
 * 
 * @AuthenticationPrincipal Usuario usuario, Model modelo) {
 * if (usuario.getRoles() == Roles.ADMIN) {
 * Optional<Usuario> respuestaU =
 * usuarioServicio.buscarUsuarioOptionalId(idObjeto);
 * if (respuestaU.isPresent()) {
 * Usuario usuario2= respuestaU.get();
 * Optional<Reporte> respuestaR = reporteServicio.buscarReportePorId(idReporte);
 * Reporte reporte = new Reporte();
 * if (respuestaR.isPresent()) {
 * reporte = respuestaR.get();
 * estado = estado.toUpperCase();
 * if (estado.equals(Estado.ACEPTADO.toString())) {
 * if (usuario.getEstado() == true) {
 * usuarioServicio.cambiarEstado(usuario2.getIdUsuario());
 * reporteServicio.aceptarReporte(reporte);
 * modelo.addAttribute("mensaje", "Usuario dado de baja");
 * return "admin/usuarios";
 * } else {
 * modelo.addAttribute("mensaje", "Usuario ya estaba dado de baja");
 * return "admin/usuarios";
 * }
 * } else if (estado.equals(Estado.DESESTIMADO.toString())) {
 * reporteServicio.desestimarReporte(reporte);
 * } else {
 * modelo.addAttribute("mensaje", "Opción no permitida");
 * return "admin/usuarios";
 * }
 * 
 * } else {
 * modelo.addAttribute("mensaje", "Reporte inexistente");
 * return "admin/usuarios";
 * }
 * } else {
 * modelo.addAttribute("mensaje", "Usuario no encontrado");
 * return "admin/usuarios";
 * }
 * } else {
 * modelo.addAttribute("mensaje", "Usuario no permitido");
 * return "index.html";
 * }
 * return "admin/usuarios";
 * }
 * 
 * @PostMapping("/comentario")
 * 
 * @PreAuthorize("isAuthenticated()")
 * public String resultadoReporteComentario(@PathVariable String estado,
 * 
 * @PathVariable String idObjeto,
 * 
 * @PathVariable String idReporte,
 * 
 * @AuthenticationPrincipal Usuario usuario, Model modelo) {
 * if (usuario.getRoles() == Roles.ADMIN) {
 * Optional<Comentario> respuestaC =
 * comentarioServicio.buscarComentarioPorId(idObjeto);
 * if (respuestaC.isPresent()) {
 * Comentario comentario= respuestaC.get();
 * Optional<Reporte> respuestaR = reporteServicio.buscarReportePorId(idReporte);
 * Reporte reporte = new Reporte();
 * if (respuestaR.isPresent()) {
 * reporte = respuestaR.get();
 * estado = estado.toUpperCase();
 * if (estado.equals(Estado.ACEPTADO.toString())) {
 * if (comentario.getEstado() == true) {
 * comentarioServicio.cambiarEstado(comentario.getIdComentario());
 * reporteServicio.aceptarReporte(reporte);
 * modelo.addAttribute("mensaje", "Comentario dado de baja");
 * return "admin/comentario";
 * } else {
 * modelo.addAttribute("mensaje", "COmentario ya estaba dado de baja");
 * return "admin/comentario";
 * }
 * } else if (estado.equals(Estado.DESESTIMADO.toString())) {
 * reporteServicio.desestimarReporte(reporte);
 * } else {
 * modelo.addAttribute("mensaje", "Opción no permitida");
 * return "admin/comentario";
 * }
 * 
 * } else {
 * modelo.addAttribute("mensaje", "Reporte inexistente");
 * return "admin/comentario";
 * }
 * } else {
 * modelo.addAttribute("mensaje", "COmentario no encontrado");
 * return "admin/comentario";
 * }
 * } else {
 * modelo.addAttribute("mensaje", "Usuario no permitido");
 * return "index.html";
 * }
 * return "admin/comentario";
 * }
 * 
 * @PostMapping("/publicacion")
 * 
 * @PreAuthorize("isAuthenticated()")
 * public String resultadoReportePublicacion(@PathVariable String estado,
 * 
 * @PathVariable String idObjeto,
 * 
 * @PathVariable String idReporte,
 * 
 * @AuthenticationPrincipal Usuario usuario, Model modelo) {
 * if (usuario.getRoles() == Roles.ADMIN) {
 * Optional<Publicacion> respuestaP =
 * publicacionServicio.buscarPublicacionPorId(idObjeto);
 * if (respuestaP.isPresent()) {
 * Publicacion publicacion= respuestaP.get();
 * Optional<Reporte> respuestaR = reporteServicio.buscarReportePorId(idReporte);
 * Reporte reporte = new Reporte();
 * if (respuestaR.isPresent()) {
 * reporte = respuestaR.get();
 * estado = estado.toUpperCase();
 * if (estado.equals(Estado.ACEPTADO.toString())) {
 * if (publicacion.isEstado() == true) {
 * publicacionServicio.BajaPublicacion(publicacion.getIdPublicacion());
 * reporteServicio.aceptarReporte(reporte);
 * modelo.addAttribute("mensaje", "Publicacion dado de baja");
 * return "admin/publicacion";
 * } else {
 * modelo.addAttribute("mensaje", "Pblicacion ya estaba dado de baja");
 * return "admin/publicacion";
 * }
 * } else if (estado.equals(Estado.DESESTIMADO.toString())) {
 * reporteServicio.desestimarReporte(reporte);
 * } else {
 * modelo.addAttribute("mensaje", "Opción no permitida");
 * return "admin/publicacion";
 * }
 * 
 * } else {
 * modelo.addAttribute("mensaje", "Reporte inexistente");
 * return "admin/publicacion";
 * }
 * } else {
 * modelo.addAttribute("mensaje", "Publicacion no encontrado");
 * return "admin/publicacion";
 * }
 * } else {
 * modelo.addAttribute("mensaje", "Usuario no permitido");
 * return "index.html";
 * }
 * return "admin/publicacion";
 * }
 */
