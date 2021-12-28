///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controller;
//
//import Model.InTheNhanVien;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
///**
// *
// * @author CongDan
// */
//public class InTheNhanVienXLDL {
//    KNCSDL kn=null;
//
//    public InTheNhanVienXLDL() {
//        kn=new KNCSDL();
//    }
//    public ArrayList<InTheNhanVien> getListNV(String id) throws SQLException{
//        ArrayList<InTheNhanVien> list=new ArrayList<>();
//        Connection conn=kn.getConnect();
//        String sql="select tbl_nhanvien.*,tbl_ChiTietPhieuGui.MaSoThuCung,tbl_ChiTietPhieuGui.MaChuong from tbl_PhieuGui,tbl_ChiTietPhieuGui where (tbl_PhieuGui.MaPhieuGui=tbl_ChiTietPhieuGui.MaPhieuGui and tbl_PhieuGui.MaPhieuGui="+id+")";
//        Statement statement=(Statement) conn.createStatement();
//        ResultSet result=statement.executeQuery(sql);
//        while(result.next()){
//            InTheNhanVien nv=new InTheNhanVien();
//            nv.setMaNV(result.getLong(1));
//            nv.setHoTen(result.getString(2));
//            nv.setNgaySinh(result.getDate(3));
//            nv.setDiaChi(result.getString(4));
//            nv.setGioiTinh(result.getString(5));
//            nv.setNgayVaoLam(result.getDate(6));
//            nv.setSdt(result.getInt(7));
//            nv.setMaCV(result.getLong(8));
//            nv.setMaPB(result.getLong(9));
//            nv.setHinhAnh(result.getString(10));
//            nv.setTrangThai(result.getString(11));
//            nv.setTenCV(result.getString(12));
//            nv.setTenPB(result.getString(13));
//            list.add(nv);
//        }
//        return list;
//    }
//}
