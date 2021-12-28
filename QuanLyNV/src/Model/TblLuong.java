/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CongDan
 */
@Entity
@Table(name = "tbl_luong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblLuong.findAll", query = "SELECT t FROM TblLuong t")
    , @NamedQuery(name = "TblLuong.findByMaSo", query = "SELECT t FROM TblLuong t WHERE t.maSo = :maSo")
    , @NamedQuery(name = "TblLuong.findByHeSoLuong", query = "SELECT t FROM TblLuong t WHERE t.heSoLuong = :heSoLuong")
    , @NamedQuery(name = "TblLuong.findByThuong", query = "SELECT t FROM TblLuong t WHERE t.thuong = :thuong")
    , @NamedQuery(name = "TblLuong.findByThue", query = "SELECT t FROM TblLuong t WHERE t.thue = :thue")
    , @NamedQuery(name = "TblLuong.findByTru", query = "SELECT t FROM TblLuong t WHERE t.tru = :tru")
    , @NamedQuery(name = "TblLuong.findBySoNgayLam", query = "SELECT t FROM TblLuong t WHERE t.soNgayLam = :soNgayLam")
    , @NamedQuery(name = "TblLuong.findByTongLuong", query = "SELECT t FROM TblLuong t WHERE t.tongLuong = :tongLuong")
    , @NamedQuery(name = "TblLuong.findByNgayPhat", query = "SELECT t FROM TblLuong t WHERE t.ngayPhat = :ngayPhat")})
public class TblLuong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaSo")
    private Long maSo;
    @Basic(optional = false)
    @Column(name = "HeSoLuong")
    private float heSoLuong;
    @Basic(optional = false)
    @Column(name = "Thuong")
    private float thuong;
    @Basic(optional = false)
    @Column(name = "Thue")
    private float thue;
    @Basic(optional = false)
    @Column(name = "Tru")
    private float tru;
    @Basic(optional = false)
    @Column(name = "SoNgayLam")
    private int soNgayLam;
    @Basic(optional = false)
    @Column(name = "TongLuong")
    private float tongLuong;
    @Basic(optional = false)
    @Column(name = "NgayPhat")
    @Temporal(TemporalType.DATE)
    private Date ngayPhat;
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV")
    @ManyToOne(optional = false)
    private Long maNV;
    @JoinColumn(name = "MaKL", referencedColumnName = "MaKL")
    @ManyToOne(optional = false)
    private Long maKL;
    @JoinColumn(name = "MaCC", referencedColumnName = "MaCC")
    @ManyToOne(optional = false)
    private Long maCC;
    private String tenNV;
    private int thang;

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }
    

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public TblLuong() {
    }

    public TblLuong(Long maSo) {
        this.maSo = maSo;
    }

    public TblLuong(Long maSo, float heSoLuong, float thuong, float thue, float tru, int soNgayLam, float tongLuong, Date ngayPhat) {
        this.maSo = maSo;
        this.heSoLuong = heSoLuong;
        this.thuong = thuong;
        this.thue = thue;
        this.tru = tru;
        this.soNgayLam = soNgayLam;
        this.tongLuong = tongLuong;
        this.ngayPhat = ngayPhat;
    }

    public Long getMaSo() {
        return maSo;
    }

    public void setMaSo(Long maSo) {
        this.maSo = maSo;
    }

    public float getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(float heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public float getThuong() {
        return thuong;
    }

    public void setThuong(float thuong) {
        this.thuong = thuong;
    }

    public float getThue() {
        return thue;
    }

    public void setThue(float thue) {
        this.thue = thue;
    }

    public float getTru() {
        return tru;
    }

    public void setTru(float tru) {
        this.tru = tru;
    }

    public int getSoNgayLam() {
        return soNgayLam;
    }

    public void setSoNgayLam(int soNgayLam) {
        this.soNgayLam = soNgayLam;
    }

    public float getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(float tongLuong) {
        this.tongLuong = tongLuong;
    }

    public Date getNgayPhat() {
        return ngayPhat;
    }

    public void setNgayPhat(Date ngayPhat) {
        this.ngayPhat = ngayPhat;
    }

    public long getMaNV() {
        return maNV;
    }

    public void setMaNV(long maNV) {
        this.maNV = maNV;
    }

    public long getMaKL() {
        return maKL;
    }

    public void setMaKL(long maKL) {
        this.maKL = maKL;
    }

    public long getMaCC() {
        return maCC;
    }

    public void setMaCC(long maCC) {
        this.maCC = maCC;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maSo != null ? maSo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblLuong)) {
            return false;
        }
        TblLuong other = (TblLuong) object;
        if ((this.maSo == null && other.maSo != null) || (this.maSo != null && !this.maSo.equals(other.maSo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TblLuong[ maSo=" + maSo + " ]";
    }
    
}
