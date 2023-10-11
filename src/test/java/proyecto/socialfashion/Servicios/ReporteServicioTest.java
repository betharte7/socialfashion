package proyecto.socialfashion.Servicios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import proyecto.socialfashion.Entidades.Reporte;
import proyecto.socialfashion.Entidades.Usuario;
import proyecto.socialfashion.Enumeraciones.Estado;
import proyecto.socialfashion.Enumeraciones.Tipo;
import proyecto.socialfashion.Enumeraciones.TipoObjeto;
import proyecto.socialfashion.Repositorios.ReporteRepositorio;

public class ReporteServicioTest {

    @Mock
    private ReporteRepositorio reporteRepositorio;
    @Mock
    private UsuarioServicio usuarioServicios;
    @InjectMocks
    private ReporteServicio reporteServicio;

    
    Reporte reporte = new Reporte();
    Usuario usuario=new Usuario();
    usuario.s

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        reporte.setEstado(Estado.PENDIENTE);
        reporte.setIdObjeto("abcd1234");
        reporte.setIdObjeto("1234");
        Usuario usuario=();
        reporte.setIdUsuario(usuario);
        reporte.setTexto("null");
        reporte.setTipo(Tipo.CONTENIDO_OFENSIVO);
        reporte.setTipoObjeto(TipoObjeto.USUARIO);
    }

   

}
