package proyecto.socialfashion.Servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Reporte> buscarReportePorId(String id) {

        return reporteRepositorio.findById(id);
    }

    @Transactional
    public List<Reporte> obtenerUsuariosReportados() {
        List<Reporte> reporteAux = reporteRepositorio.findAll();
        List<Reporte> reporteUsuario = new ArrayList();
        for (Reporte reporte : reporteAux) {
            if(reporte.getEstado()==Estado.PENDIENTE && reporte.getTipoObjeto()==TipoObjeto.USUARIO){
                reporteUsuario.add(reporte);
            }
        }
        return reporteUsuario;
    }


    @Transactional
    public ArrayList<Reporte> obtenerComentariosReportados(){
        List<Reporte> reporteAux= reporteRepositorio.findAll();
        ArrayList<Reporte> reporteComentario = new ArrayList();
        for (Reporte reporte : reporteAux) {
            if(reporte.getEstado()==Estado.PENDIENTE && reporte.getTipoObjeto()==TipoObjeto.COMENTARIO){
                reporteComentario.add(reporte);
            }
        }
        return reporteComentario;
    }
    
    @Transactional
    public ArrayList<Reporte> obtenerPublicacionesReportadas() {
        List<Reporte> reporteAux = reporteRepositorio.findAll();
        ArrayList<Reporte> reportePublicacion = new ArrayList();
        for (Reporte reporte : reporteAux) {
            if(reporte.getEstado()==Estado.PENDIENTE && reporte.getTipoObjeto()==TipoObjeto.PUBLICACION){
                reportePublicacion.add(reporte);
            }
        }
        return reportePublicacion;
    }
}