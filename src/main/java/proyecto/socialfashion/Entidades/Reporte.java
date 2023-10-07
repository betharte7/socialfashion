package proyecto.socialfashion.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Resporte {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idReported;
    private String texto;
    private Estado estado;
    private Tipo tipo;
    private String idUsuario;
    private String idComentario;
    private String idUsuarioDenuncia;

    public Resporte() {
    }

    public Resporte(String texto, Tipo tipo, String idUsuario, String idComentario, String idUsuarioDenuncia) {
        this.texto = texto;
        this.tipo = tipo;
        this.idUsuario = idUsuario;
        this.idComentario = idComentario;
        this.idUsuarioDenuncia = idUsuarioDenuncia;
    }

    public String getIdReported() {
        return idReported;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }

    public String getIdUsuarioDenuncia() {
        return idUsuarioDenuncia;
    }

    public void setIdUsuarioDenuncia(String idUsuarioDenuncia) {
        this.idUsuarioDenuncia = idUsuarioDenuncia;
    }

}
