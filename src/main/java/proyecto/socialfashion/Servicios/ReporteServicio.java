package proyecto.socialfashion.Servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.socialfashion.Entidades.Reporte;
import proyecto.socialfashion.Enumeraciones.Estado;
import proyecto.socialfashion.Enumeraciones.TipoObjeto;
import proyecto.socialfashion.Repositorios.ReporteRepositorio;

@Service
public class ReporteServicio {

    @Autowired
    private ReporteRepositorio reporteRepositorio;

    public void guardarReporte(Reporte reporte) {
        reporteRepositorio.save(reporte);
    }

    @Transactional
    public void desestimarReporte(Reporte reporte) {
        Reporte reporteAux = reporte;
        reporteAux.setEstado(Estado.DESESTIMADO);
        reporteRepositorio.save(reporteAux);
    }
    @Transactional
    public void aceptarReporte(Reporte reporte) {
        Reporte reporteAux = reporte;
        reporteAux.setEstado(Estado.ACEPTADO);
        reporteRepositorio.save(reporteAux);
    }

    @Transactional
    public Optional<Reporte> buscarReportePorId(String id) {

        return reporteRepositorio.findById(id);
    }

    @Transactional
    public List<Reporte> obtenerUsuariosReportadosPendientes() {
        List<Reporte> reportes = reporteRepositorio.findAll();
        return reportes.stream()
                .filter(reporte -> reporte.getEstado() == Estado.PENDIENTE && reporte.getTipoObjeto() == TipoObjeto.PUBLICACION)
                .collect(Collectors.toList());


    }

    @Transactional
    public List<Reporte> obtenerComentariosReportadosPendientes(){
        List<Reporte> reportes = reporteRepositorio.findAll();
        return reportes.stream()
        .filter(reporte -> reporte.getEstado() == Estado.PENDIENTE && reporte.getTipoObjeto() == TipoObjeto.COMENTARIO)
        .collect(Collectors.toList());
    }
    
    
    @Transactional
    public List<Reporte> obtenerPublicacionesReportadasPendientes() {
        List<Reporte> reportes = reporteRepositorio.findAll();
    
        return reportes.stream()
            .filter(reporte -> reporte.getEstado() == Estado.PENDIENTE && reporte.getTipoObjeto() == TipoObjeto.PUBLICACION)
            .collect(Collectors.toList());
    }



}