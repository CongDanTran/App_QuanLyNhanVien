/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CongDan
 */
public class DangNhapXLDL {
    KNCSDL kn=null;
    public  DangNhapXLDL(){
        kn= new KNCSDL();
    }
  public  String dangnhap(String taikhoan, String matKhau) throws SQLException{
        
           Connection conn=kn.getConnect();
        String sql="SELECT * FROM tbl_taikhoan where TenTK like'"+taikhoan+"' and MatKhau like '"+matKhau+"'";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int i= result.getRow();
        while(result.next()){
            return result.getString(4);
        }
            return "0";
        
    }
}
