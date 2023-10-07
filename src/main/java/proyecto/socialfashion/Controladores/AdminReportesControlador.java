package proyecto.socialfashion.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import proyecto.socialfashion.Entidades.Reporte;
import proyecto.socialfashion.Servicios.ReporteServicio;

@Controller
@RequestMapping("/admin/reportes")
public class AdminReportesControlador {
    @Autowired
    private ReporteServicio reporteServicio;

    @GetMapping("/usuarios")
    public String levantarReporteUsuarios(Model model) {
        List<Reporte> reporteUsuarios = reporteServicio.obtenerUsuariosReportados();
        
        model.addAttribute("reporteUsuarios", reporteUsuarios);
        
        return "admin/reportes_usuarios";
    }

    @GetMapping("/comentarios")
    public String levantarReporteComentarios(Model model) {
        List<Reporte> reporteComentarios = reporteServicio.obtenerComentariosReportados();
        
        model.addAttribute("comentariosReportados", reporteComentarios);
        
        return "admin/reportes_comentarios";
    }

    @GetMapping("/publicaciones")
    public String levantarReportePublicaciones(Model model) {
        List<Reporte> reportePublicaciones = reporteServicio.obtenerPublicacionesReportadas();
        
        model.addAttribute("reportePublicaciones", reportePublicaciones);
        
        return "admin/reportes_publicaciones";
    }
}
