/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TblPhongban;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cong Dan
 */
public class PhongBanXLDL {
    KNCSDL kn=null;
    public PhongBanXLDL(){
        kn=new KNCSDL();
    }
    public ArrayList<TblPhongban> getListNV() throws SQLException{
        ArrayList<TblPhongban> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT * FROM tbl_phongban";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblPhongban nv=new TblPhongban();
            nv.setMaPB(result.getLong(1));
            nv.setTenPB(result.getString(2));
            nv.setTruongPhong(result.getString(3));
            list.add(nv);
        }
        return list;
    }
    public ArrayList<TblPhongban> getListPhongBan(String tk) throws SQLException{
        ArrayList<TblPhongban> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT * FROM tbl_phongban where (MaPB = '"+tk+"' or TenPB like N'%"+tk+"%' or TruongPhong like N'%"+tk+"%')";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblPhongban nv=new TblPhongban();
            nv.setMaPB(result.getLong(1));
            nv.setTenPB(result.getString(2));
            nv.setTruongPhong(result.getString(3));
            list.add(nv);
        }
        return list;
    }
}
