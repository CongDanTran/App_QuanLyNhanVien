/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CongDan
 */
@Entity
@Table(name = "tbl_nhanvien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNhanvien.findAll", query = "SELECT t FROM TblNhanvien t")
    , @NamedQuery(name = "TblNhanvien.findByMaNV", query = "SELECT t FROM TblNhanvien t WHERE t.maNV = :maNV")
    , @NamedQuery(name = "TblNhanvien.findByHoTen", query = "SELECT t FROM TblNhanvien t WHERE t.hoTen = :hoTen")
    , @NamedQuery(name = "TblNhanvien.findByNgaySinh", query = "SELECT t FROM TblNhanvien t WHERE t.ngaySinh = :ngaySinh")
    , @NamedQuery(name = "TblNhanvien.findByDiaChi", query = "SELECT t FROM TblNhanvien t WHERE t.diaChi = :diaChi")
    , @NamedQuery(name = "TblNhanvien.findByGioiTinh", query = "SELECT t FROM TblNhanvien t WHERE t.gioiTinh = :gioiTinh")
    , @NamedQuery(name = "TblNhanvien.findByNgayVaoLam", query = "SELECT t FROM TblNhanvien t WHERE t.ngayVaoLam = :ngayVaoLam")
    , @NamedQuery(name = "TblNhanvien.findBySdt", query = "SELECT t FROM TblNhanvien t WHERE t.sdt = :sdt")
    , @NamedQuery(name = "TblNhanvien.findByTrangThai", query = "SELECT t FROM TblNhanvien t WHERE t.trangThai = :trangThai")})
public class TblNhanvien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaNV")
    private Long maNV;
    @Basic(optional = false)
    @Column(name = "HoTen")
    private String hoTen;
    @Basic(optional = false)
    @Column(name = "NgaySinh")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;
    @Basic(optional = false)
    @Column(name = "DiaChi")
    private String diaChi;
    @Basic(optional = false)
    @Column(name = "GioiTinh")
    private String gioiTinh;
    @Basic(optional = false)
    @Column(name = "NgayVaoLam")
    @Temporal(TemporalType.DATE)
    private Date ngayVaoLam;
    @Basic(optional = false)
    @Column(name = "SDT")
    private int sdt;
    @Basic(optional = false)
    @Lob
    @Column(name = "HinhAnh")
    private String hinhAnh;
    @Basic(optional = false)
    @Column(name = "TrangThai")
    private String trangThai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maNV")
    private Collection<TblLuong> tblLuongCollection;
    @JoinColumn(name = "MaCV", referencedColumnName = "MaCV")
    @ManyToOne(optional = false)
    private Long maCV;
    @JoinColumn(name = "MaPB", referencedColumnName = "MaPB")
    @ManyToOne(optional = false)
    private Long maPB;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblNhanvien")
    private Collection<TblTaikhoan> tblTaikhoanCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maNV")
    private Collection<TblChamcong> tblChamcongCollection;
    private String tenCV;
    private String tenPB;

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public TblNhanvien() {
    }

    public TblNhanvien(Long maNV) {
        this.maNV = maNV;
    }

    public TblNhanvien(Long maNV, String hoTen, Date ngaySinh, String diaChi, String gioiTinh, Date ngayVaoLam, int sdt, String hinhAnh, String trangThai) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.ngayVaoLam = ngayVaoLam;
        this.sdt = sdt;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }

    public Long getMaNV() {
        return maNV;
    }

    public void setMaNV(Long maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @XmlTransient
    public Collection<TblLuong> getTblLuongCollection() {
        return tblLuongCollection;
    }

    public void setTblLuongCollection(Collection<TblLuong> tblLuongCollection) {
        this.tblLuongCollection = tblLuongCollection;
    }

    public long getMaCV() {
        return maCV;
    }

    public void setMaCV(long maCV) {
        this.maCV = maCV;
    }

    public long getMaPB() {
        return maPB;
    }

    public void setMaPB(long maPB) {
        this.maPB = maPB;
    }

    @XmlTransient
    public Collection<TblTaikhoan> getTblTaikhoanCollection() {
        return tblTaikhoanCollection;
    }

    public void setTblTaikhoanCollection(Collection<TblTaikhoan> tblTaikhoanCollection) {
        this.tblTaikhoanCollection = tblTaikhoanCollection;
    }

    @XmlTransient
    public Collection<TblChamcong> getTblChamcongCollection() {
        return tblChamcongCollection;
    }

    public void setTblChamcongCollection(Collection<TblChamcong> tblChamcongCollection) {
        this.tblChamcongCollection = tblChamcongCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maNV != null ? maNV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNhanvien)) {
            return false;
        }
        TblNhanvien other = (TblNhanvien) object;
        if ((this.maNV == null && other.maNV != null) || (this.maNV != null && !this.maNV.equals(other.maNV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return hoTen;
    }
    
}
