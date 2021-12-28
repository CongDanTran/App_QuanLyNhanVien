/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ChamCongXLDL;
import Controller.DAO;
import Controller.KNCSDL;
import Controller.KyLuongXLDL;
import Controller.LuongXLDL;
import Controller.NhanVienXLDL;
import Model.InPhieuLuong;
import Model.TblChamcong;
import Model.TblChucvu;
import Model.TblKyluong;
import Model.TblLuong;
import Model.TblNhanvien;
import Model.TblPhongban;
import java.awt.Color;
import java.awt.HeadlessException;

import java.awt.Image;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Cong Dan
 */
public class frmLuong extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmLuong
     */
    LuongXLDL lDL;
    NhanVienXLDL nvDL;
    KyLuongXLDL klDL;
    ChamCongXLDL ccDL;
    DefaultTableModel table;
  
    DAO dao;
    Color mauxanh = new Color(0, 112, 192);

    public frmLuong() {
        initComponents();
        lDL = new LuongXLDL();
        nvDL = new NhanVienXLDL();
        klDL = new KyLuongXLDL();
        ccDL = new ChamCongXLDL();
        
        loadAnh(lblSearch, "src\\img\\tk.png");
        dao = new DAO("jdbc:mysql://localhost:3306/qlnhanvien");
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadNV();
        loadmaNV("");
        loadmaKyLuong();
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);
        Date dt= new Date();
        int year= dt.getYear();
    }

    void loadmaNV(String ma) {
        try {
            ArrayList<TblNhanvien> KH = nvDL.getListNV(ma);
            for (TblNhanvien kh : KH) {
                cboMaNhanVien.addItem(kh);
            }
        } catch (Exception ex) {

        }
    }

    void loadmaKyLuong() {
        try {
            ArrayList<TblKyluong> ch = klDL.getListNV();
            for (TblKyluong c : ch) {
                cboMaKyLuong.addItem(c);
            }
        } catch (Exception ex) {

        }
    }

    void loadAnh(JLabel lbl, String fileanh) {
        //TODO add your handling code here:
        ImageIcon icon = new ImageIcon(fileanh);
        Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        lbl.setIcon(new ImageIcon(image));
    }

    void loadNV() {
        ArrayList<TblLuong> list;
        try {
            list = lDL.getListNV();
            table = (DefaultTableModel) tblLuong.getModel();
            for (TblLuong nv : list) {
                table.addRow(new Object[]{nv.getMaSo(), nv.getTenNV(), nv.getThang(), nv.getMaCC(), nv.getHeSoLuong(), nv.getThuong(), nv.getThue(), nv.getTru(), nv.getSoNgayLam(), nv.getTongLuong(), nv.getNgayPhat()});
            }
        } catch (SQLException ex) {

        }
    }

    private boolean KTDL() throws HeadlessException {
        StringBuilder sb = new StringBuilder();

        if (txtHeSoLuong.getText().equals("")) {
            sb.append("Vui lòng nhập hệ số lương\n");
            txtHeSoLuong.requestFocus();
        }
        if (txtThue.getText().equals("")) {
            sb.append("Vui lòng nhập thuế\n");
            txtThue.requestFocus();
        }
        if (txtSoNgayLam.getText().equals("")) {
            sb.append("Vui lòng nhập số ngày làm\n");
            txtSoNgayLam.requestFocus();
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    public void getTinhLuong() throws SQLException{
        if(!txtMaCC.getText().equals("")){
             KNCSDL kn=new KNCSDL();
        ArrayList<TblChamcong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT tbl_chamcong.* FROM tbl_chamcong where MaCC="+txtMaCC.getText();
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblChamcong cc=new TblChamcong();
            cc.setMaCC(result.getLong(1));
            cc.setMaNV(result.getLong(2));
            cc.setThang(result.getInt(3));
            cc.setNam(result.getInt(4));
            cc.setSoGioLam(result.getInt(5));
            txtSoNgayLam.setText(result.getInt(5)+"");
            list.add(cc);
        }
       
        }
       
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
        btnTinh = new rojerusan.RSButtonMetro();
        jLabel13 = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        txtMaCC = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtHeSoLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtThuong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboMaNhanVien = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cboMaKyLuong = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtMaSo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtThue = new javax.swing.JTextField();
        txtTru = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSoNgayLam = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTongLuong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jdcNgayPhat = new com.toedter.calendar.JDateChooser();
        txtTimKiem = new app.bolivia.swing.JCTextField();
        btnThem = new rojerusan.RSButtonMetro();
        btnSua = new rojerusan.RSButtonMetro();
        btnXoa = new rojerusan.RSButtonMetro();
        btnLuu = new rojerusan.RSButtonMetro();
        btnHuy = new rojerusan.RSButtonMetro();
        lblSearch = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLuong = new rojeru_san.complementos.RSTableMetro();
        btnIn = new rojerusan.RSButtonMetro();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTinh.setText("Tính");
        btnTinh.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhActionPerformed(evt);
            }
        });
        jPanel1.add(btnTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1510, 350, 110, 50));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("Lương:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, -1));

        txtLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 453, 46));

        txtMaCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtMaCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 453, 46));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("Thông Tin Lương");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(637, 21, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Mã Số:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Hệ Số Lương:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, -1));

        txtHeSoLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtHeSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 453, 46));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Thưởng:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 120, -1, -1));

        txtThuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtThuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 110, 450, 47));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Thuế:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Tên Nhân Viên:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));

        cboMaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboMaNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaNhanVienItemStateChanged(evt);
            }
        });
        jPanel1.add(cboMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 453, 46));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Mã Kỳ Lương:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        cboMaKyLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboMaKyLuong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaKyLuongItemStateChanged(evt);
            }
        });
        jPanel1.add(cboMaKyLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 450, 46));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Mã Chấm Công:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        txtMaSo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaSo.setEnabled(false);
        jPanel1.add(txtMaSo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 453, 47));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Trừ:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 240, -1, -1));

        txtThue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtThue, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 170, 453, 50));

        txtTru.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtTru, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 230, 453, 47));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Số Giờ Làm:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 300, -1, -1));

        txtSoNgayLam.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtSoNgayLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 290, 453, 47));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("Tổng Lương:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 360, -1, -1));

        txtTongLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtTongLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 350, 330, 47));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Ngày Phát:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 410, -1, 40));

        jdcNgayPhat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jdcNgayPhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 410, 453, 48));

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTimKiem.setPlaceholder("Tìm kiếm tại đây...");
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 590, 460, 40));

        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 120, 50));

        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, 120, 50));

        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 510, 120, 50));

        btnLuu.setText("Lưu");
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 510, 120, 50));

        btnHuy.setText("Hủy");
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 510, 120, 50));

        lblSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });
        jPanel1.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 590, 40, 40));

        tblLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã số", "Tên nhân viên", "Mã kỳ lương", "Mã chấm công", "Hệ số lương", "Thưởng", "Thuế", "Trừ", "Số giờ làm", "Tổng lương", "Ngày phát"
            }
        ));
        tblLuong.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblLuong.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblLuong.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblLuong.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblLuong.setRowHeight(25);
        tblLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLuongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLuong);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, 1560, 320));

        btnIn.setText("In");
        btnIn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });
        jPanel1.add(btnIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 510, 120, 50));

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
    boolean them, sua, xoa;
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        them = true;
        sua = false;
        xoa = false;
        btnThem.setEnabled(false);
        btnThem.setBackground(Color.GRAY);
        btnSua.setEnabled(false);
        btnSua.setBackground(Color.GRAY);
        btnXoa.setEnabled(false);
        btnXoa.setBackground(Color.GRAY);
        btnLuu.setEnabled(true);
        btnLuu.setBackground(mauxanh);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        them = false;
        sua = true;
        xoa = false;
        btnThem.setEnabled(false);
        btnThem.setBackground(Color.GRAY);
        btnSua.setEnabled(false);
        btnSua.setBackground(Color.GRAY);
        btnXoa.setEnabled(false);
        btnXoa.setBackground(Color.GRAY);
        btnLuu.setEnabled(true);
        btnLuu.setBackground(mauxanh);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        them = false;
        sua = false;
        xoa = true;
        btnThem.setEnabled(false);
        btnThem.setBackground(Color.GRAY);
        btnSua.setEnabled(false);
        btnSua.setBackground(Color.GRAY);
        btnXoa.setEnabled(false);
        btnXoa.setBackground(Color.GRAY);
        btnLuu.setEnabled(true);
        btnLuu.setBackground(mauxanh);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        if (them) {
            if (KTDL()) {
                TblNhanvien kl = (TblNhanvien) cboMaNhanVien.getSelectedItem();
                String manv = String.valueOf(kl.getMaNV());
                Integer songaylam = Integer.parseInt(txtSoNgayLam.getText());
                TblKyluong ky= (TblKyluong) cboMaKyLuong.getSelectedItem();
                String makl= String.valueOf(ky.getMaKL());
                Date dt = new Date();       
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngay = dateFormat.format(dt);
                try {
                    Connection conn = dao.getConnect();
                    String caulenh = "INSERT INTO tbl_luong VALUES(null,'" + manv + "','" + makl + "','" + txtMaCC.getText() + "','" + txtHeSoLuong.getText() + "','" + txtThuong.getText() + "','" + txtThue.getText() + "','" + txtTru.getText() + "','" + songaylam + "','" + txtTongLuong.getText() + "','" + ngay + "')";
                    boolean a = dao.Lenh(caulenh, conn);
                    if (a) {
                        tblLuong.removeAll();
                        table.setRowCount(0);
                        loadNV();
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm KHÔNG thành công");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(frmLuong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (sua) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Integer maso = Integer.parseInt(txtMaSo.getText());
            TblNhanvien kl = (TblNhanvien) cboMaNhanVien.getSelectedItem();
            String manv = String.valueOf(kl.getMaNV());
            Integer songaylam = Integer.parseInt(txtSoNgayLam.getText());
            TblKyluong ky= (TblKyluong) cboMaKyLuong.getSelectedItem();
                String makl= String.valueOf(ky.getMaKL());
            String ngayphat = dateFormat.format(jdcNgayPhat.getDate());

            try {
                Connection conn = dao.getConnect();
                String caulenh = "UPDATE tbl_luong SET MaNV='" + manv + "',MaKL='" + makl + "',MaCC='" + txtMaCC.getText() + "',HeSoLuong='" + txtHeSoLuong.getText() + "',Thuong='" + txtThuong.getText() + "',Thue='" + txtThue.getText() + "',Tru='" + txtTru.getText() + "',SoNgayLam='" + txtSoNgayLam.getText() + "',TongLuong='" + txtTongLuong.getText() + "',NgayPhat='" + ngayphat + "'where MaSo=" + maso;
                boolean a = dao.Lenh(caulenh, conn);
                if (a) {
                    tblLuong.removeAll();
                    table.setRowCount(0);
                    loadNV();
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa KHÔNG thành công");
                }
            } catch (SQLException ex) {
                Logger.getLogger(frmLuong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (xoa) {
            Integer maso = Integer.parseInt(txtMaSo.getText());
            try {
                Connection conn = dao.getConnect();
                String caulenh = "DELETE FROM tbl_luong WHERE MaSo=" + maso;
                boolean a = dao.Lenh(caulenh, conn);
                if (a) {
                    tblLuong.removeAll();
                    table.setRowCount(0);
                    loadNV();
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa KHÔNG thành công");
                }
            } catch (SQLException ex) {
                Logger.getLogger(frmLuong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed
    void hienthitxt(int i) {
        TableModel model = tblLuong.getModel();
        txtMaSo.setText(model.getValueAt(i, 0).toString());
        String cbosub = model.getValueAt(i, 1).toString();
        for (int j = 0; j < cboMaNhanVien.getItemCount(); j++) {
            if (cboMaNhanVien.getItemAt(j).toString().equalsIgnoreCase(cbosub)) {
                cboMaNhanVien.setSelectedIndex(j);
            }
        }
        String cbosubmakl = model.getValueAt(i, 2).toString();
        for (int j = 0; j < cboMaKyLuong.getItemCount(); j++) {
            if (cboMaKyLuong.getItemAt(j).toString().equalsIgnoreCase(cbosubmakl)) {
                cboMaKyLuong.setSelectedIndex(j);
            }
        }
        txtMaCC.setText(model.getValueAt(i, 3).toString());
        txtHeSoLuong.setText(model.getValueAt(i, 4).toString());
        txtThuong.setText(model.getValueAt(i, 5).toString());
        txtThue.setText(model.getValueAt(i, 6).toString());
        txtTru.setText(model.getValueAt(i, 7).toString());
        txtSoNgayLam.setText(model.getValueAt(i, 8).toString());
        txtTongLuong.setText(model.getValueAt(i, 9).toString());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(i, 10).toString());
        } catch (ParseException ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        jdcNgayPhat.setDate(date);
    }
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        txtMaSo.setText("");
        txtHeSoLuong.setText("");
        txtThuong.setText("");
        txtThue.setText("");
        txtTru.setText("");
        txtSoNgayLam.setText("");
        txtTongLuong.setText("");
        txtMaCC.setText("");
        tblLuong.removeAll();
        table.setRowCount(0);
        loadNV();
        btnThem.setEnabled(true);
        btnThem.setBackground(mauxanh);
        btnSua.setEnabled(true);
        btnSua.setBackground(mauxanh);
        btnXoa.setEnabled(true);
        btnXoa.setBackground(mauxanh);
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.gray);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void tblLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLuongMouseClicked
        // TODO add your handling code here:
        int i = tblLuong.getSelectedRow();
        hienthitxt(i);
    }//GEN-LAST:event_tblLuongMouseClicked

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked
        // TODO add your handling code here:
        tblLuong.removeAll();
        table.setRowCount(0);
        String tk = txtTimKiem.getText();
        ArrayList<TblLuong> list;
        try {
            list = lDL.getListLuong(tk);
            table = (DefaultTableModel) tblLuong.getModel();
            for (TblLuong nv : list) {
                table.addRow(new Object[]{nv.getMaSo(), nv.getTenNV(), nv.getMaKL(), nv.getMaCC(), nv.getHeSoLuong(), nv.getThuong(), nv.getThue(), nv.getTru(), nv.getSoNgayLam(), nv.getTongLuong(), nv.getNgayPhat()});
            }
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_lblSearchMouseClicked
   void loadchamcong(String manv,String thang) throws SQLException{
        KNCSDL kn=new KNCSDL();
        ArrayList<TblChamcong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="select tbl_chamcong.* from tbl_chamcong where MaNV="+manv+" and thang="+thang +" and Nam=Year(now())";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            TblChamcong cc=new TblChamcong();
            txtMaCC.setText(result.getLong(1)+"");
            cc.setMaCC(result.getLong(1));
            cc.setMaNV(result.getLong(2));
            cc.setThang(result.getInt(3));
            cc.setNam(result.getInt(4));
            cc.setSoGioLam(result.getInt(5));
           
        }
        
    
}
    private void cboMaNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaNhanVienItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED){
           TblNhanvien ch = (TblNhanvien) cboMaNhanVien.getSelectedItem();
            long madv = ch.getMaNV();
            try {
                float dv= nvDL.getHeso(""+madv);
                txtHeSoLuong.setText(dv+"");
            } catch (SQLException ex) {
                Logger.getLogger(frmLuong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cboMaNhanVienItemStateChanged

    private void cboMaKyLuongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaKyLuongItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED){
          
            TblNhanvien nv = (TblNhanvien) cboMaNhanVien.getSelectedItem();
            String manv = String.valueOf(nv.getMaNV());
            try {
                loadchamcong(manv, cboMaKyLuong.getSelectedItem().toString());
                getTinhLuong();
                TblKyluong ky= (TblKyluong) cboMaKyLuong.getSelectedItem();
                String ma= String.valueOf(ky.getMaKL());
                TblKyluong kl= klDL.getKL(ma);
                txtLuong.setText(kl.getTienLuong()+"");
            } catch (SQLException ex) {
                Logger.getLogger(frmLuong.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
    }//GEN-LAST:event_cboMaKyLuongItemStateChanged

    private void btnTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhActionPerformed
        // TODO add your handling code here:
        txtTongLuong.setText(String.valueOf(Float.parseFloat(txtLuong.getText())*Float.parseFloat(txtHeSoLuong.getText())*Integer.parseInt(txtSoNgayLam.getText()) +Float.parseFloat(txtThuong.getText())-Float.parseFloat(txtThue.getText())-Float.parseFloat(txtTru.getText())));
    }//GEN-LAST:event_btnTinhActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        try {
             report = JasperCompileManager.compileReport("src/View/PhieuLuong.jrxml");
              TblKyluong ky= (TblKyluong) cboMaKyLuong.getSelectedItem();
                String ma= String.valueOf(ky.getMaKL());
            print(ma);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnInActionPerformed
    private static JasperReport report;
     private static Collection getData(String ma) throws Exception {
        KNCSDL kn=new KNCSDL();
          ArrayList<InPhieuLuong> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="SELECT l.MaSo,l.MaNV,nv.HoTen,l.HeSoLuong,kl.TienLuong,kl.Thang,l.Thuong,l.Thue,l.Tru,l.SoNgayLam,l.TongLuong FROM tbl_luong l,tbl_kyluong kl,tbl_nhanvien nv WHERE l.MaNV=nv.MaNV AND l.MaKL=kl.MaKL AND kl.MaKL="+ma;
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            InPhieuLuong nv=new InPhieuLuong();
            nv.setMaSo(result.getLong(1));
            nv.setMaNV(result.getLong(2));
            nv.setTenNV(result.getString(3));
            nv.setHeSoLuong(result.getFloat(4));
            nv.setLuong(result.getFloat(5));
            nv.setThang(result.getInt(6));
            nv.setThuong(result.getFloat(7));
            nv.setThue(result.getFloat(8));
            nv.setTru(result.getFloat(9));
            nv.setSoGioLam(result.getInt(10));
            nv.setTongLuong(result.getFloat(11));
            
            list.add(nv);
        }
        return list;
        
    }

 
    public static void print(String ma) {
        try {
            JRBeanCollectionDataSource jcd = new JRBeanCollectionDataSource(getData(ma));
            JasperPrint print = JasperFillManager.fillReport(report, null, jcd);
            if (false) {
                JasperPrintManager.printReport(print, true);    //  print auto
            } else {
                JasperViewer.viewReport(print, false);  //  view
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonMetro btnHuy;
    private rojerusan.RSButtonMetro btnIn;
    private rojerusan.RSButtonMetro btnLuu;
    private rojerusan.RSButtonMetro btnSua;
    private rojerusan.RSButtonMetro btnThem;
    private rojerusan.RSButtonMetro btnTinh;
    private rojerusan.RSButtonMetro btnXoa;
    private javax.swing.JComboBox<TblKyluong> cboMaKyLuong;
    private javax.swing.JComboBox<TblNhanvien> cboMaNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcNgayPhat;
    private javax.swing.JLabel lblSearch;
    private rojeru_san.complementos.RSTableMetro tblLuong;
    private javax.swing.JTextField txtHeSoLuong;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaCC;
    private javax.swing.JTextField txtMaSo;
    private javax.swing.JTextField txtSoNgayLam;
    private javax.swing.JTextField txtThue;
    private javax.swing.JTextField txtThuong;
    private app.bolivia.swing.JCTextField txtTimKiem;
    private javax.swing.JTextField txtTongLuong;
    private javax.swing.JTextField txtTru;
    // End of variables declaration//GEN-END:variables
}
