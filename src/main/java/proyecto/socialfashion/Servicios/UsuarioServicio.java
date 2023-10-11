
package proyecto.socialfashion.Servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import proyecto.socialfashion.Entidades.Usuario;
import proyecto.socialfashion.Enumeraciones.Roles;
import proyecto.socialfashion.Excepciones.Excepciones;
import proyecto.socialfashion.Repositorios.UsuarioRepositorio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrar(String nombre, String email, String password, String password2) throws Excepciones {

        validar(nombre, email, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setEmail(email);

        usuario.setRoles(Roles.USER);
        usuario.setEstado(true);

        usuarioRepositorio.save(usuario);

    }

    @Transactional
    public void actualizar(String idUsuario, String nombre, String email, String password, String password2, Roles rol)
            throws Excepciones {

        validar(nombre, email, password, password2);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);

        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();

            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(new BCryptPasswordEncoder().encode(password));

            if (usuario.getRoles().equals(Roles.USER)) {
                usuario.setRoles(Roles.USER);
            } else if (usuario.getRoles().equals(Roles.ADMIN)) {
                usuario.setRoles(Roles.ADMIN);
            }

            usuario.setEstado(true);

            usuarioRepositorio.save(usuario);
        }
    }

    public Usuario getOne(String idUsuario) {
        return usuarioRepositorio.getOne(idUsuario);
    }

    @Transactional
    public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();

        usuarios = usuarioRepositorio.findAll();

        return usuarios;

    }

    @Transactional
    public void buscarUsuarioPorNombre(String nombre, ModelMap modelo) throws Excepciones {

        Usuario usuario = usuarioRepositorio.buscarPorNombre(nombre);

        if (usuario.getNombre().equals(nombre)) {
            modelo.put("exito", "El Usuario ha sido encontrado.");
            
        } else if (!usuario.getNombre().equals(nombre)) {
            modelo.put("error", "El Usuario NO se existe.");
        }

    }

    @Transactional
    public void cambiarRol(String idUsuario) {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);

        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();

            if (usuario.getRoles().equals(Roles.USER)) {

                usuario.setRoles(Roles.ADMIN);

            } else if (usuario.getRoles().equals(Roles.ADMIN)) {

                usuario.setRoles(Roles.USER);
            }
        }

    }

    @Transactional
    public void cambiarEstado(String idUsuario) {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);

        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();

            if (usuario.getEstado() == true) {

                usuario.setEstado(false);

            } else if (usuario.getEstado() == false) {

                usuario.setEstado(true);
            }
        }

    }

    public void validar(String nombre, String email, String password, String password2) throws Excepciones {

        if (nombre.isEmpty() || nombre == null) {
            throw new Excepciones("El nombre no puede estar vacío");
        }

        if (email.isEmpty() || email == null) {
            throw new Excepciones("El email no puede estar vacío");
        }

        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new Excepciones("El password no puede estar vacío y debe tener más de 5 dígitos.");
        }

        if (!password.equals(password2)) {
            throw new Excepciones("Las contraseñas ingresadas deben ser iguales.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

}