package proyecto.socialfashion.Entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Imagen {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idImagen;

    private String mime;
    private String nombre;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;

    @OneToOne
    private Publicacion publicacion;
    
    public Imagen() {
    }

    public Imagen(String idImagen, String mime, String nombre, byte[] contenido, Publicacion publicacion) {
        this.idImagen = idImagen;
        this.mime = mime;
        this.nombre = nombre;
        this.contenido = contenido;
        this.publicacion = publicacion;
    }

  

    
    public String getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(String idImagen) {
        this.idImagen = idImagen;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
    
  
    @Override
    public String toString() {
        return "Imagen: " + "idImagen=" + idImagen + ", mime=" + mime + ", nombre=" + nombre + ", contenido=" + contenido;
    }
    
    
    
    
    
    
    
}
