/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.TblChamcong;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cong Dan
 */
public class ChamCongXLDL {
    KNCSDL kn=null;
    public ChamCongXLDL(){
        kn=new KNCSDL();
    }
    public ArrayList<TblChamcong> getListNV() throws SQLException{
        ArrayList<TblChamcong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT tbl_chamcong.*,tbl_nhanvien.HoTen FROM tbl_chamcong,tbl_nhanvien where tbl_chamcong.MaNV=tbl_nhanvien.MaNV";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblChamcong cc=new TblChamcong();
            cc.setMaCC(result.getLong(1));
            cc.setMaNV(result.getLong(2));
            cc.setThang(result.getInt(3));
            cc.setNam(result.getInt(4));
            cc.setSoGioLam(result.getInt(5));
            cc.setTenNV(result.getString(6));
            list.add(cc);
        }
        return list;
    }
    public ArrayList<TblChamcong> getListCC(String tk) throws SQLException{
        ArrayList<TblChamcong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT tbl_chamcong.*,tbl_nhanvien.HoTen FROM tbl_chamcong,tbl_nhanvien where tbl_chamcong.MaNV=tbl_nhanvien.MaNV and (MaCC like '%"+tk+"%' or HoTen like N'%"+tk+"%' or Thang like '%"+tk+"%' or Nam like '%"+tk+"%')";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblChamcong cc=new TblChamcong();
            cc.setMaCC(result.getLong(1));
            cc.setMaNV(result.getLong(2));
            cc.setThang(result.getInt(3));
            cc.setNam(result.getInt(4));
            cc.setSoGioLam(result.getInt(5));
            cc.setTenNV(result.getString(6));
            list.add(cc);
        }
        return list;
    }
}
