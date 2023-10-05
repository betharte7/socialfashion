
package proyecto.socialfashion.Entidades;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import proyecto.socialfashion.Enumeraciones.Categoria;

@Entity
public class Publicacion {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idPublicacion;
    
    @Basic
    private String Contenido;
    
    @Temporal(TemporalType.DATE)
    private Date alta;
     
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
   
    private boolean estado;
    
    @OneToMany
    private ArrayList<Like> likes;
    
    @OneToMany
    private ArrayList<Comentario> comentarios;
    
    
    @OneToOne
    private String idUsuario;
    
    @OneToOne
    private String idImagen;

    public Publicacion() {
    }

    public Publicacion(String idPublicacion, String Contenido, Date alta, Categoria categoria, boolean estado, ArrayList<Like> likes, ArrayList<Comentario> comentarios, String idUsuario, String idImagen) {
        this.idPublicacion = idPublicacion;
        this.Contenido = Contenido;
        this.alta = alta;
        this.categoria = categoria;
        this.estado = estado;
        this.likes = likes;
        this.comentarios = comentarios;
        this.idUsuario = idUsuario;
        this.idImagen = idImagen;
    }

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(String idImagen) {
        this.idImagen = idImagen;
    }

    @Override
    public String toString() {
        return "Publicacion: " + "idPublicacion=" + idPublicacion + ", Contenido=" + Contenido + ", alta=" + alta + ", categoria=" + categoria + ", estado=" + estado + ", likes=" + likes + ", comentarios=" + comentarios + ", idUsuario=" + idUsuario + ", idImagen=" + idImagen;
    }
    
    
    
    
    
    
}
