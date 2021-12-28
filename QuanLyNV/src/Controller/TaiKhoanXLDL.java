/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TblTaikhoan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cong Dan
 */
public class TaiKhoanXLDL {
    KNCSDL kn=null;
    public TaiKhoanXLDL(){
        kn=new KNCSDL();
    }
    public ArrayList<TblTaikhoan> getListNV() throws SQLException{
        ArrayList<TblTaikhoan> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT tbl_taikhoan.*,tbl_nhanvien.HoTen FROM tbl_taikhoan,tbl_nhanvien where tbl_taikhoan.MaNV=tbl_nhanvien.MaNV";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblTaikhoan nv=new TblTaikhoan();
            nv.setMaNV(result.getLong(1));
            nv.setTenTK(result.getString(2));
            nv.setMatKhau(result.getString(3));
            nv.setQuyen(result.getString(4));
            nv.setTenNV(result.getString(5));
            list.add(nv);
        }
        return list;
    }
    public ArrayList<TblTaikhoan> getListTaiKhoan(String tk) throws SQLException{
        ArrayList<TblTaikhoan> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT tbl_taikhoan.*,tbl_nhanvien.HoTen FROM tbl_taikhoan,tbl_nhanvien where tbl_taikhoan.MaNV=tbl_nhanvien.MaNV and (HoTen like N'%"+tk+"%' or TenTK like N'%"+tk+"%')";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblTaikhoan nv=new TblTaikhoan();
            nv.setMaNV(result.getLong(1));
            nv.setTenTK(result.getString(2));
            nv.setMatKhau(result.getString(3));
            nv.setQuyen(result.getString(4));
            nv.setTenNV(result.getString(5));
            list.add(nv);
        }
        return list;
    }
}
