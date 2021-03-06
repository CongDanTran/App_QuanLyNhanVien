/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.KNCSDL;
import Model.TblNhanvien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CongDan
 */
public class frmThongKe extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmThongKe
     */
     DefaultTableModel table;
    public frmThongKe() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui=(BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadNV("age", "0");
    }
    public ArrayList<TblNhanvien> getListNV(String loai, String tt) throws SQLException{
        KNCSDL kn=new KNCSDL();
        ArrayList<TblNhanvien> list=new ArrayList<>();
        Connection conn=kn.getConnect();
         String sql="";
       
         if(loai.equals("age")){
                sql  ="SELECT tbl_nhanvien.*,tbl_chucvu.TenCV,tbl_phongban.TenPB FROM tbl_nhanvien,tbl_chucvu,tbl_phongban where tbl_nhanvien.MaCV=tbl_chucvu.MaCV and tbl_nhanvien.MaPB=tbl_phongban.MaPB and (year(now())- year(tbl_nhanvien.NgaySinh))>"+ tt;

         }else if(loai.equals("luong")){
                sql  ="  SELECT tbl_nhanvien.*,tbl_chucvu.TenCV,tbl_phongban.TenPB FROM tbl_nhanvien,tbl_chucvu,tbl_phongban,tbl_luong where tbl_nhanvien.MaCV=tbl_chucvu.MaCV and tbl_nhanvien.MaPB=tbl_phongban.MaPB and tbl_luong.MaNV= tbl_nhanvien.MaNV ORDER BY tbl_luong.TongLuong LIMIT 5";

         }else if(loai.equals("ngayvaolam")){
             sql="SELECT tbl_nhanvien.*,tbl_chucvu.TenCV,tbl_phongban.TenPB FROM tbl_nhanvien,tbl_chucvu,tbl_phongban,tbl_luong where tbl_nhanvien.MaCV=tbl_chucvu.MaCV and tbl_nhanvien.MaPB=tbl_phongban.MaPB and tbl_luong.MaNV= tbl_nhanvien.MaNV ORDER BY  tbl_nhanvien.NgayVaoLam LIMIT 5";
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnThem1 = new rojerusan.RSButtonMetro();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tuoinhanvien = new javax.swing.JSpinner();
        btnThem = new rojerusan.RSButtonMetro();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new rojeru_san.complementos.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        btnThem2 = new rojerusan.RSButtonMetro();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("Th???ng k??");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 22, -1, -1));

        btnThem1.setText("Th???ng k??");
        btnThem1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Theo L????ng");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("5 nh??n vi??n c?? l????ng cao nh???t");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(83, 83, 83))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addGap(39, 39, 39)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 420, 330));

        btnThem.setText("Th???ng k??");
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setText("Theo tu???i");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(tuoinhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel2)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tuoinhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 420, 330));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? nh??n vi??n", "H??? t??n", "Ng??y sinh", "?????a ch???", "Gi???i t??nh", "Ng??y v??o l??m", "S??? ??i???n tho???i", "T??n ch???c v???", "T??n ph??ng ban", "H??nh ???nh", "Tr???ng th??i"
            }
        ));
        tblNhanVien.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblNhanVien.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblNhanVien.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblNhanVien.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblNhanVien.setRowHeight(25);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 1590, 520));

        btnThem2.setText("Th???ng k??");
        btnThem2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setText("Theo Ng??y V??o L??m");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("5 nh??n vi??n c?? ng??y v??o l??m s???m nh???t");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel4)
                .addGap(43, 43, 43)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 100, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here: 
        tblNhanVien.removeAll();
        table.setRowCount(0);
        loadNV("age", tuoinhanvien.getValue()+"");
        
    }//GEN-LAST:event_btnThemActionPerformed
  void loadNV(String loai,String tt) {
        ArrayList<TblNhanvien> list;
        try {
         list=   getListNV(loai,tt);
            table = (DefaultTableModel) tblNhanVien.getModel();
            for (TblNhanvien nv : list) {
                table.addRow(new Object[]{nv.getMaNV(), nv.getHoTen(), nv.getNgaySinh(), nv.getDiaChi(), nv.getGioiTinh(), nv.getNgayVaoLam(), nv.getSdt(), nv.getTenCV(), nv.getTenPB(), nv.getHinhAnh(), nv.getTrangThai()});
            }
        } catch (SQLException ex) {

        }
    }
    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
          tblNhanVien.removeAll();
        table.setRowCount(0);
        loadNV("luong", "");
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        // TODO add your handling code here:
          tblNhanVien.removeAll();
        table.setRowCount(0);
        loadNV("ngayvaolam", "");
    }//GEN-LAST:event_btnThem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonMetro btnThem;
    private rojerusan.RSButtonMetro btnThem1;
    private rojerusan.RSButtonMetro btnThem2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private rojeru_san.complementos.RSTableMetro tblNhanVien;
    private javax.swing.JSpinner tuoinhanvien;
    // End of variables declaration//GEN-END:variables
}
