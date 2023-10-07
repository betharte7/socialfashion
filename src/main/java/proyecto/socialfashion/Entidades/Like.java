package proyecto.socialfashion.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Like {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idLike;

    @OneToOne
    private Usuario idUsuario;
    /* 
    @OneToOne
    private Publicacion idPublicacion;
    */

    private Boolean estado;

    public Like() {
    }

    public String getIdLike() {
        return idLike;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }
    /* 
    public Publicacion getIdPublicacion() {
        return idPublicacion;
    }
    */

    public Boolean getEstado() {
        return estado;
    }

    public void setIdLike(String idLike) {
        this.idLike = idLike;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    /* 
    public void setIdPublicacion(Publicacion idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
    */

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    
}
