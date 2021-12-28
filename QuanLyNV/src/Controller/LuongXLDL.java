/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TblLuong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cong Dan
 */
public class LuongXLDL {
    KNCSDL kn=null;
    public LuongXLDL(){
        kn=new KNCSDL();
    }
    public ArrayList<TblLuong> getListNV() throws SQLException{
        ArrayList<TblLuong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT tbl_Luong.*,tbl_nhanvien.HoTen,tbl_kyluong.Thang FROM tbl_kyluong, tbl_Luong,tbl_nhanvien where tbl_Luong.MaNV=tbl_nhanvien.MaNV AND tbl_kyluong.MaKL=tbl_luong.MaKL";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblLuong nv=new TblLuong();
            nv.setMaSo(result.getLong(1));
            nv.setMaNV(result.getLong(2));
            nv.setMaKL(result.getLong(3));
            nv.setMaCC(result.getLong(4));
            nv.setHeSoLuong(result.getFloat(5));
            nv.setThuong(result.getFloat(6));
            nv.setThue(result.getFloat(7));
            nv.setTru(result.getFloat(8));
            nv.setSoNgayLam(result.getInt(9));
            nv.setTongLuong(result.getFloat(10));
            nv.setNgayPhat(result.getDate(11));
            nv.setTenNV(result.getString(12));
            nv.setThang(result.getInt(13));
            list.add(nv);
        }
        return list;
    }
    public ArrayList<TblLuong> getListLuong(String tk) throws SQLException{
        ArrayList<TblLuong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT tbl_Luong.*,tbl_nhanvien.HoTen FROM tbl_Luong,tbl_nhanvien where tbl_Luong.MaNV=tbl_nhanvien.MaNV and (MaSo like '%"+tk+"%' or HoTen like N'%"+tk+"%' or MaKL like '%"+tk+"%' or MaCC like '%"+tk+"%' )";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblLuong nv=new TblLuong();
            nv.setMaSo(result.getLong(1));
            nv.setMaNV(result.getLong(2));
            nv.setMaKL(result.getLong(3));
            nv.setMaCC(result.getLong(4));
            nv.setHeSoLuong(result.getFloat(5));
            nv.setThuong(result.getFloat(6));
            nv.setThue(result.getFloat(7));
            nv.setTru(result.getFloat(8));
            nv.setSoNgayLam(result.getInt(9));
            nv.setTongLuong(result.getFloat(10));
            nv.setNgayPhat(result.getDate(11));
            nv.setTenNV(result.getString(12));
            list.add(nv);
        }
        return list;
    }
        public ArrayList<TblLuong> getChamCong(String tk) throws SQLException{
        ArrayList<TblLuong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT tbl_Luong.*,tbl_nhanvien.HoTen FROM tbl_Luong,tbl_nhanvien where tbl_Luong.MaNV=tbl_nhanvien.MaNV and (MaSo like '%"+tk+"%' or HoTen like N'%"+tk+"%' or MaKL like '%"+tk+"%' or MaCC like '%"+tk+"%' )";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblLuong nv=new TblLuong();
            nv.setMaSo(result.getLong(1));
            nv.setMaNV(result.getLong(2));
            nv.setMaKL(result.getLong(3));
            nv.setMaCC(result.getLong(4));
            nv.setHeSoLuong(result.getFloat(5));
            nv.setThuong(result.getFloat(6));
            nv.setThue(result.getFloat(7));
            nv.setTru(result.getFloat(8));
            nv.setSoNgayLam(result.getInt(9));
            nv.setTongLuong(result.getFloat(10));
            nv.setNgayPhat(result.getDate(11));
            nv.setTenNV(result.getString(12));
            list.add(nv);
        }
        return list;
    }
    
}
