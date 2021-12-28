/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "tbl_chitietchamcong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblChitietchamcong.findAll", query = "SELECT t FROM TblChitietchamcong t")
    , @NamedQuery(name = "TblChitietchamcong.findByMaCC", query = "SELECT t FROM TblChitietchamcong t WHERE t.tblChitietchamcongPK.maCC = :maCC")
    , @NamedQuery(name = "TblChitietchamcong.findByNgay", query = "SELECT t FROM TblChitietchamcong t WHERE t.tblChitietchamcongPK.ngay = :ngay")
    , @NamedQuery(name = "TblChitietchamcong.findByGioVao", query = "SELECT t FROM TblChitietchamcong t WHERE t.gioVao = :gioVao")
    , @NamedQuery(name = "TblChitietchamcong.findByGioRa", query = "SELECT t FROM TblChitietchamcong t WHERE t.gioRa = :gioRa")
    , @NamedQuery(name = "TblChitietchamcong.findByGhiChu", query = "SELECT t FROM TblChitietchamcong t WHERE t.ghiChu = :ghiChu")})
public class TblChitietchamcong implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId

    @Basic(optional = false)
    @Column(name = "GioVao")
    @Temporal(TemporalType.TIME)
    private Time gioVao;
    @Basic(optional = false)
    @Column(name = "GioRa")
    @Temporal(TemporalType.TIME)
    private Time gioRa;
    @Basic(optional = false)
    @Column(name = "GhiChu")
    private String ghiChu;
    @JoinColumn(name = "MaCC", referencedColumnName = "MaCC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Long MaCC;
    private Date ngay;

    public TblChitietchamcong() {
    }

   
    public Date getGioVao() {
        return gioVao;
    }

    public void setGioVao(Time gioVao) {
        this.gioVao = gioVao;
    }

    public Date getGioRa() {
        return gioRa;
    }

    public void setGioRa(Time gioRa) {
        this.gioRa = gioRa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public long getMaCC() {
        return MaCC;
    }

    public void setMaCC(long MaCC) {
        this.MaCC = MaCC;
    }
    
    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

}
