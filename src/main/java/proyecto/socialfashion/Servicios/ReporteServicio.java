package proyecto.socialfashion.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.socialfashion.Entidades.Comentario;
import proyecto.socialfashion.Entidades.Reporte;
import proyecto.socialfashion.Enumeraciones.Estado;
import proyecto.socialfashion.Repositorios.ReporteRepositorio;

@Service
public class ReporteServicio {

    @Autowired
    private ReporteRepositorio reporteRepositorio;

    public void guardarReporte(Reporte reporte) {
        reporteRepositorio.save(reporte);
    }

    public void desestimarReporte(Reporte reporte) {
        Reporte reporteAux = reporte;

        reporteAux.setEstado(Estado.DESESTIMADO);
        reporteRepositorio.save(reporteAux);
    }

    public Optional<Reporte> buscarReportePorId(String id) {

        return reporteRepositorio.findById(id);
    }

}
