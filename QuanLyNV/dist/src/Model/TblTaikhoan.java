/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CongDan
 */
@Entity
@Table(name = "tbl_taikhoan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTaikhoan.findAll", query = "SELECT t FROM TblTaikhoan t")
    , @NamedQuery(name = "TblTaikhoan.findByMaNV", query = "SELECT t FROM TblTaikhoan t WHERE t.tblTaikhoanPK.maNV = :maNV")
    , @NamedQuery(name = "TblTaikhoan.findByTenTK", query = "SELECT t FROM TblTaikhoan t WHERE t.tblTaikhoanPK.tenTK = :tenTK")
    , @NamedQuery(name = "TblTaikhoan.findByQuyen", query = "SELECT t FROM TblTaikhoan t WHERE t.quyen = :quyen")})
public class TblTaikhoan implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId

    @Basic(optional = false)
    @Lob
    @Column(name = "MatKhau")
    private String matKhau;
    @Basic(optional = false)
    @Column(name = "Quyen")
    private String quyen;
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Long MaNV;
    private String tenTK;
    private String tenNV;

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public TblTaikhoan() {
    }


    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public long getMaNV() {
        return MaNV;
    }

    public void setMaNV(long MaNV) {
        this.MaNV = MaNV;
    }
    
    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }
    
}
