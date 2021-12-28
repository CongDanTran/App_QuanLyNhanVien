/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TblChucvu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cong Dan
 */
public class ChucVuXLDL {
    KNCSDL kn=null;
    public ChucVuXLDL(){
        kn=new KNCSDL();
    }
    public ArrayList<TblChucvu> getListNV() throws SQLException{
        ArrayList<TblChucvu> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT * FROM tbl_chucvu";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblChucvu nv=new TblChucvu();
            nv.setMaCV(result.getLong(1));
            nv.setTenCV(result.getString(2));
            nv.setHeSo(result.getFloat(3));
            list.add(nv);
        }
        return list;
    }
    public ArrayList<TblChucvu> getListChucVu(String tk) throws SQLException{
        ArrayList<TblChucvu> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT * FROM tbl_chucvu where (MaCV like '%"+tk+"%' or TenCV like N'%"+tk+"%')";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblChucvu nv=new TblChucvu();
            nv.setMaCV(result.getLong(1));
            nv.setTenCV(result.getString(2));
            nv.setHeSo(result.getFloat(3));
            list.add(nv);
        }
        return list;
    }
}
