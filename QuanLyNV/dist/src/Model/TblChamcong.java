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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_chamcong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblChamcong.findAll", query = "SELECT t FROM TblChamcong t")
    , @NamedQuery(name = "TblChamcong.findByMaCC", query = "SELECT t FROM TblChamcong t WHERE t.maCC = :maCC")
    , @NamedQuery(name = "TblChamcong.findByThang", query = "SELECT t FROM TblChamcong t WHERE t.thang = :thang")
    , @NamedQuery(name = "TblChamcong.findByNam", query = "SELECT t FROM TblChamcong t WHERE t.nam = :nam")})
public class TblChamcong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaCC")
    private Long maCC;
    @Basic(optional = false)
    @Column(name = "Thang")
    private int thang;
    @Basic(optional = false)
    @Column(name = "Nam")
    private int nam;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maCC")
    private Collection<TblLuong> tblLuongCollection;
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV")
    @ManyToOne(optional = false)
    private Long maNV;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblChamcong")
    private Collection<TblChitietchamcong> tblChitietchamcongCollection;
    private String tenNV;
    private int soGioLam;

    public int getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(int soGioLam) {
        this.soGioLam = soGioLam;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public TblChamcong() {
    }

    public TblChamcong(Long maCC) {
        this.maCC = maCC;
    }

    public TblChamcong(Long maCC, int thang, int nam) {
        this.maCC = maCC;
        this.thang = thang;
        this.nam = nam;
    }

    public Long getMaCC() {
        return maCC;
    }

    public void setMaCC(Long maCC) {
        this.maCC = maCC;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    @XmlTransient
    public Collection<TblLuong> getTblLuongCollection() {
        return tblLuongCollection;
    }

    public void setTblLuongCollection(Collection<TblLuong> tblLuongCollection) {
        this.tblLuongCollection = tblLuongCollection;
    }

    public long getMaNV() {
        return maNV;
    }

    public void setMaNV(long maNV) {
        this.maNV = maNV;
    }

    @XmlTransient
    public Collection<TblChitietchamcong> getTblChitietchamcongCollection() {
        return tblChitietchamcongCollection;
    }

    public void setTblChitietchamcongCollection(Collection<TblChitietchamcong> tblChitietchamcongCollection) {
        this.tblChitietchamcongCollection = tblChitietchamcongCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maCC != null ? maCC.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblChamcong)) {
            return false;
        }
        TblChamcong other = (TblChamcong) object;
        if ((this.maCC == null && other.maCC != null) || (this.maCC != null && !this.maCC.equals(other.maCC))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TblChamcong[ maCC=" + maCC + " ]";
    }
    
}
