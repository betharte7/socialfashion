
package proyecto.socialfashion.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import proyecto.socialfashion.Entidades.Publicacion;

import proyecto.socialfashion.Repositorios.PublicacionRepositorio;


@Service
public class PublicacionServicio {
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;

    public Publicacion buscarPorId(String idPublicacion) {
        return publicacionRepositorio.getById(idPublicacion);
    }

    public Optional<Publicacion> buscarPublicacionPorId(String idPublicacion) {

        return publicacionRepositorio.findById(idPublicacion);
    }

}
