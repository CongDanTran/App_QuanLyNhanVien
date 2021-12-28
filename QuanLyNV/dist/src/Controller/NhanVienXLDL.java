/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TblNhanvien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cong Dan
 */
public class NhanVienXLDL {
    KNCSDL kn=null;
    public NhanVienXLDL(){
        kn=new KNCSDL();
    }
    public ArrayList<TblNhanvien> getListNV(String ma) throws SQLException{
        ArrayList<TblNhanvien> list=new ArrayList<>();
        Connection conn=kn.getConnect();
         String sql="";
        if(ma.equals("")){
                 sql  ="SELECT tbl_nhanvien.*,tbl_chucvu.TenCV,tbl_phongban.TenPB FROM tbl_nhanvien,tbl_chucvu,tbl_phongban where tbl_nhanvien.MaCV=tbl_chucvu.MaCV and tbl_nhanvien.MaPB=tbl_phongban.MaPB";

        }else{
                 sql  ="SELECT tbl_nhanvien.*,tbl_chucvu.TenCV,tbl_phongban.TenPB FROM tbl_nhanvien,tbl_chucvu,tbl_phongban where tbl_nhanvien.MaCV=tbl_chucvu.MaCV and tbl_nhanvien.MaPB=tbl_phongban.MaPB AND tbl_phongban.MaPB="+ ma;

        }
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblNhanvien nv=new TblNhanvien();
            nv.setMaNV(result.getLong(1));
            nv.setHoTen(result.getString(2));
            nv.setNgaySinh(result.getDate(3));
            nv.setDiaChi(result.getString(4));
            nv.setGioiTinh(result.getString(5));
            nv.setNgayVaoLam(result.getDate(6));
            nv.setSdt(result.getInt(7));
            nv.setMaCV(result.getLong(8));
            nv.setMaPB(result.getLong(9));
            nv.setHinhAnh(result.getString(10));
            nv.setTrangThai(result.getString(11));
            nv.setTenCV(result.getString(12));
            nv.setTenPB(result.getString(13));
            list.add(nv);
        }
        return list;
    }
    
     public float getHeso(String ma) throws SQLException{
     float heso=0;
        Connection conn=kn.getConnect();
         String sql="";
      
                 sql  ="SELECT tbl_nhanvien.*,tbl_chucvu.TenCV,tbl_phongban.TenPB,tbl_chucvu.HeSo FROM tbl_nhanvien,tbl_chucvu,tbl_phongban where tbl_nhanvien.MaCV=tbl_chucvu.MaCV and tbl_nhanvien.MaPB=tbl_phongban.MaPB AND tbl_nhanvien.MaNV="+ ma;

        
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
          TblNhanvien nv=new TblNhanvien();
        while(result.next()){
          
            nv.setMaNV(result.getLong(1));
            nv.setHoTen(result.getString(2));
            nv.setNgaySinh(result.getDate(3));
            nv.setDiaChi(result.getString(4));
            nv.setGioiTinh(result.getString(5));
            nv.setNgayVaoLam(result.getDate(6));
            nv.setSdt(result.getInt(7));
            nv.setMaCV(result.getLong(8));
            nv.setMaPB(result.getLong(9));
            nv.setHinhAnh(result.getString(10));
            nv.setTrangThai(result.getString(11));
            nv.setTenCV(result.getString(12));
            nv.setTenPB(result.getString(13));
            heso=result.getFloat(14);
        }
        return heso;
    }
    
    public ArrayList<TblNhanvien> getListNhanVien(String tk) throws SQLException{
        ArrayList<TblNhanvien> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT tbl_nhanvien.*,tbl_chucvu.TenCV,tbl_phongban.TenPB FROM tbl_nhanvien,tbl_chucvu,tbl_phongban where tbl_nhanvien.MaCV=tbl_chucvu.MaCV and tbl_nhanvien.MaPB=tbl_phongban.MaPB and (MaNV = '"+tk+"' or HoTen like N'%"+tk+"%' or GioiTinh like N'%"+tk+"%' or SDT = '"+tk+"' or TenCV like '%"+tk+"%' or TenPB like '%"+tk+"%')";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblNhanvien nv=new TblNhanvien();
            nv.setMaNV(result.getLong(1));
            nv.setHoTen(result.getString(2));
            nv.setNgaySinh(result.getDate(3));
            nv.setDiaChi(result.getString(4));
            nv.setGioiTinh(result.getString(5));
            nv.setNgayVaoLam(result.getDate(6));
            nv.setSdt(result.getInt(7));
            nv.setMaCV(result.getLong(8));
            nv.setMaPB(result.getLong(9));
            nv.setHinhAnh(result.getString(10));
            nv.setTrangThai(result.getString(11));
            nv.setTenCV(result.getString(12));
            nv.setTenPB(result.getString(13));
            list.add(nv);
        }
        return list;
    }
}
