import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;

import proyecto.socialfashion.Entidades.Usuario;

@Service
public class UsuarioService {
    public Usuario getUsuario(Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
        return usuario;
    }
}