/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RafaMar
 */
@Entity
@Table(name = "noticias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticias.findAll", query = "SELECT n FROM Noticias n")
    , @NamedQuery(name = "Noticias.findByCodigo", query = "SELECT n FROM Noticias n WHERE n.codigo = :codigo")
    , @NamedQuery(name = "Noticias.findByTitular", query = "SELECT n FROM Noticias n WHERE n.titular = :titular")
    , @NamedQuery(name = "Noticias.findBySubtitulo", query = "SELECT n FROM Noticias n WHERE n.subtitulo = :subtitulo")
    , @NamedQuery(name = "Noticias.findByImagen", query = "SELECT n FROM Noticias n WHERE n.imagen = :imagen")
    , @NamedQuery(name = "Noticias.findByFechaPublicacion", query = "SELECT n FROM Noticias n WHERE n.fechaPublicacion = :fechaPublicacion")})
public class Noticias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "titular")
    private String titular;
    @Column(name = "subtitulo")
    private String subtitulo;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "imagen")
    private String imagen;
    @Basic(optional = false)
    @Column(name = "fecha_publicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @JoinColumn(name = "codigo_dpto", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Departamentos codigoDpto;

    public Noticias() {
    }

    public Noticias(Integer codigo) {
        this.codigo = codigo;
    }

    public Noticias(Integer codigo, Date fechaPublicacion) {
        this.codigo = codigo;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Departamentos getCodigoDpto() {
        return codigoDpto;
    }

    public void setCodigoDpto(Departamentos codigoDpto) {
        this.codigoDpto = codigoDpto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticias)) {
            return false;
        }
        Noticias other = (Noticias) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Noticias[ codigo=" + codigo + " ]";
    }
    
}
