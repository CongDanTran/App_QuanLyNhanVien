/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CongDan
 */
@Entity
@Table(name = "tbl_chucvu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblChucvu.findAll", query = "SELECT t FROM TblChucvu t")
    , @NamedQuery(name = "TblChucvu.findByMaCV", query = "SELECT t FROM TblChucvu t WHERE t.maCV = :maCV")
    , @NamedQuery(name = "TblChucvu.findByTenCV", query = "SELECT t FROM TblChucvu t WHERE t.tenCV = :tenCV")
    , @NamedQuery(name = "TblChucvu.findByHeSo", query = "SELECT t FROM TblChucvu t WHERE t.heSo = :heSo")})
public class TblChucvu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaCV")
    private Long maCV;
    @Basic(optional = false)
    @Column(name = "TenCV")
    private String tenCV;
    @Basic(optional = false)
    @Column(name = "HeSo")
    private float heSo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maCV")
    private Collection<TblNhanvien> tblNhanvienCollection;

    public TblChucvu() {
    }

    public TblChucvu(Long maCV) {
        this.maCV = maCV;
    }

    public TblChucvu(Long maCV, String tenCV, float heSo) {
        this.maCV = maCV;
        this.tenCV = tenCV;
        this.heSo = heSo;
    }

    public Long getMaCV() {
        return maCV;
    }

    public void setMaCV(Long maCV) {
        this.maCV = maCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public float getHeSo() {
        return heSo;
    }

    public void setHeSo(float heSo) {
        this.heSo = heSo;
    }

    @XmlTransient
    public Collection<TblNhanvien> getTblNhanvienCollection() {
        return tblNhanvienCollection;
    }

    public void setTblNhanvienCollection(Collection<TblNhanvien> tblNhanvienCollection) {
        this.tblNhanvienCollection = tblNhanvienCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maCV != null ? maCV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblChucvu)) {
            return false;
        }
        TblChucvu other = (TblChucvu) object;
        if ((this.maCV == null && other.maCV != null) || (this.maCV != null && !this.maCV.equals(other.maCV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tenCV;
    }
    
}
