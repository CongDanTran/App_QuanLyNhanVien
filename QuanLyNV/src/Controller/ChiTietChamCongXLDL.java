/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TblChitietchamcong;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cong Dan
 */
public class ChiTietChamCongXLDL {
    KNCSDL kn=null;
    public ChiTietChamCongXLDL(){
        kn=new KNCSDL();
    }
    public ArrayList<TblChitietchamcong> getListNV(String ngay) throws SQLException{
        ArrayList<TblChitietchamcong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT * FROM tbl_chitietchamcong WHERE Ngay= '"+ ngay+"'";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblChitietchamcong nv=new TblChitietchamcong();
            nv.setMaCC(result.getLong(1));
            nv.setNgay(result.getDate(2));
            nv.setGioVao(result.getTime(3));
            nv.setGioRa(result.getTime(4));
            nv.setGhiChu(result.getString(5));
            list.add(nv);
        }
        return list;
    }
    public ArrayList<TblChitietchamcong> getListCTCC(String tk) throws SQLException{
        ArrayList<TblChitietchamcong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT * FROM tbl_chitietchamcong where MaCC like '%"+tk+"%'";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblChitietchamcong nv=new TblChitietchamcong();
            nv.setMaCC(result.getLong(1));
            nv.setNgay(result.getDate(2));
            nv.setGioVao(result.getTime(3));
            nv.setGioRa(result.getTime(4));
            nv.setGhiChu(result.getString(5));
            list.add(nv);
        }
        return list;
    }
}
