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
@Table(name = "tbl_phongban")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPhongban.findAll", query = "SELECT t FROM TblPhongban t")
    , @NamedQuery(name = "TblPhongban.findByMaPB", query = "SELECT t FROM TblPhongban t WHERE t.maPB = :maPB")
    , @NamedQuery(name = "TblPhongban.findByTenPB", query = "SELECT t FROM TblPhongban t WHERE t.tenPB = :tenPB")
    , @NamedQuery(name = "TblPhongban.findByTruongPhong", query = "SELECT t FROM TblPhongban t WHERE t.truongPhong = :truongPhong")})
public class TblPhongban implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaPB")
    private Long maPB;
    @Basic(optional = false)
    @Column(name = "TenPB")
    private String tenPB;
    @Basic(optional = false)
    @Column(name = "TruongPhong")
    private String truongPhong;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maPB")
    private Collection<TblNhanvien> tblNhanvienCollection;

    public TblPhongban() {
    }

    public TblPhongban(Long maPB) {
        this.maPB = maPB;
    }

    public TblPhongban(Long maPB, String tenPB, String truongPhong) {
        this.maPB = maPB;
        this.tenPB = tenPB;
        this.truongPhong = truongPhong;
    }

    public Long getMaPB() {
        return maPB;
    }

    public void setMaPB(Long maPB) {
        this.maPB = maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public String getTruongPhong() {
        return truongPhong;
    }

    public void setTruongPhong(String truongPhong) {
        this.truongPhong = truongPhong;
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
        hash += (maPB != null ? maPB.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPhongban)) {
            return false;
        }
        TblPhongban other = (TblPhongban) object;
        if ((this.maPB == null && other.maPB != null) || (this.maPB != null && !this.maPB.equals(other.maPB))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tenPB;
    }
    
}
