/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author CongDan
 */
public  class InPhieuLuong {
    long maSo,maNV;
    String tenNV;
    float heSoLuong,luong,thuong,thue,tru,tongLuong;
    int soGioLam,thang;

    public InPhieuLuong() {
    }

    public InPhieuLuong(long maSo, long maNV, String tenNV, float heSoLuong, float luong, float thuong, float thue, float tru, float tongLuong, int soGioLam, int thang) {
        this.maSo = maSo;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.heSoLuong = heSoLuong;
        this.luong = luong;
        this.thuong = thuong;
        this.thue = thue;
        this.tru = tru;
        this.tongLuong = tongLuong;
        this.soGioLam = soGioLam;
        this.thang = thang;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    
    public long getMaSo() {
        return maSo;
    }

    public void setMaSo(long maSo) {
        this.maSo = maSo;
    }

    public long getMaNV() {
        return maNV;
    }

    public void setMaNV(long maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public float getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(float heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
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

    public float getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(float tongLuong) {
        this.tongLuong = tongLuong;
    }

    public int getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(int soGioLam) {
        this.soGioLam = soGioLam;
    }
    
}
