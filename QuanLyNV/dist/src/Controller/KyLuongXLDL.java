/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TblKyluong;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cong Dan
 */
public class KyLuongXLDL {
    KNCSDL kn=null;
    public KyLuongXLDL(){
        kn=new KNCSDL();
    }
    public ArrayList<TblKyluong> getListNV() throws SQLException{
        ArrayList<TblKyluong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT * FROM tbl_kyluong";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblKyluong nv=new TblKyluong();
            nv.setMaKL(result.getLong(1));
            nv.setThang(result.getInt(2));
            nv.setTienLuong(result.getFloat(3));
            list.add(nv);
        }
        return list;
    }
    public ArrayList<TblKyluong> getListKyLuong(String tk) throws SQLException{
        ArrayList<TblKyluong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT * FROM tbl_kyluong where (MaKL like '%"+tk+"%' or Thang like N'%"+tk+"%')";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblKyluong nv=new TblKyluong();
            nv.setMaKL(result.getLong(1));
            nv.setThang(result.getInt(2));
            nv.setTienLuong(result.getFloat(3));
            list.add(nv);
        }
        return list;
    }
    public TblKyluong getKL(String ma) throws SQLException{
        Connection conn=kn.getConnect();
        String sql="SELECT * FROM tbl_kyluong where MaKL="+ma;
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
          TblKyluong nv=new TblKyluong();
        while(result.next()){
          
            nv.setMaKL(result.getLong(1));
            nv.setThang(result.getInt(2));
            nv.setTienLuong(result.getFloat(3));
            
        }
        return nv;
    }
}
