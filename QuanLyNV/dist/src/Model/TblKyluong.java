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
@Table(name = "tbl_kyluong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblKyluong.findAll", query = "SELECT t FROM TblKyluong t")
    , @NamedQuery(name = "TblKyluong.findByMaKL", query = "SELECT t FROM TblKyluong t WHERE t.maKL = :maKL")
    , @NamedQuery(name = "TblKyluong.findByThang", query = "SELECT t FROM TblKyluong t WHERE t.thang = :thang")
    , @NamedQuery(name = "TblKyluong.findByTienLuong", query = "SELECT t FROM TblKyluong t WHERE t.tienLuong = :tienLuong")})
public class TblKyluong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaKL")
    private Long maKL;
    @Basic(optional = false)
    @Column(name = "Thang")
    private int thang;
    @Basic(optional = false)
    @Column(name = "TienLuong")
    private float tienLuong;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maKL")
    private Collection<TblLuong> tblLuongCollection;

    public TblKyluong() {
    }

    public TblKyluong(Long maKL) {
        this.maKL = maKL;
    }

    public TblKyluong(Long maKL, int thang, float tienLuong) {
        this.maKL = maKL;
        this.thang = thang;
        this.tienLuong = tienLuong;
    }

    public Long getMaKL() {
        return maKL;
    }

    public void setMaKL(Long maKL) {
        this.maKL = maKL;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public float getTienLuong() {
        return tienLuong;
    }

    public void setTienLuong(float tienLuong) {
        this.tienLuong = tienLuong;
    }

    @XmlTransient
    public Collection<TblLuong> getTblLuongCollection() {
        return tblLuongCollection;
    }

    public void setTblLuongCollection(Collection<TblLuong> tblLuongCollection) {
        this.tblLuongCollection = tblLuongCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maKL != null ? maKL.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblKyluong)) {
            return false;
        }
        TblKyluong other = (TblKyluong) object;
        if ((this.maKL == null && other.maKL != null) || (this.maKL != null && !this.maKL.equals(other.maKL))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return thang+"";
    }
    
}
