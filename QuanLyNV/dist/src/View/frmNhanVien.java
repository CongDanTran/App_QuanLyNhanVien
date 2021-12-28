/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ChucVuXLDL;
import Controller.DAO;
import Controller.NhanVienXLDL;
import Controller.PhongBanXLDL;
import Model.InTheNhanVien;
import Model.TblChucvu;
import Model.TblNhanvien;
import Model.TblPhongban;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;

import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
public class frmNhanVien extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmNhanVien
     */
    NhanVienXLDL nvDL;
    ChucVuXLDL cvDL;
    PhongBanXLDL pbDL;
    DefaultTableModel table;
    DAO dao;
    Color mauxanh = new Color(0, 112, 192);

    public frmNhanVien() {
        initComponents();
        nvDL = new NhanVienXLDL();
        cvDL = new ChucVuXLDL();
        pbDL = new PhongBanXLDL();
        loadAnh(lblSearch, "src\\img\\tk.png");
        dao = new DAO("jdbc:mysql://localhost:3306/qlnhanvien");
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadNV("");
        loadmaCV();
        loadmaPB();
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);
    }

    void loadmaCV() {
        try {
            ArrayList<TblChucvu> KH = cvDL.getListNV();
            for (TblChucvu kh : KH) {
                cboMaChucVu.addItem(kh);
            }
        } catch (Exception ex) {

        }
    }

    void loadmaPB() {
        try {
            ArrayList<TblPhongban> KH = pbDL.getListNV();
            for (TblPhongban kh : KH) {
                cboMaPhongBan.addItem(kh);
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

    void loadhinh(JLabel lbl, String fileanh) {
        //TODO add your handling code here:
        ImageIcon icon = new ImageIcon(fileanh);
        Image image = icon.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
        lbl.setIcon(new ImageIcon(image));
    }

    void loadNV(String ma) {
        ArrayList<TblNhanvien> list;
        try {
            list = nvDL.getListNV(ma);
            table = (DefaultTableModel) tblNhanVien.getModel();
            for (TblNhanvien nv : list) {
                table.addRow(new Object[]{nv.getMaNV(), nv.getHoTen(), nv.getNgaySinh(), nv.getDiaChi(), nv.getGioiTinh(), nv.getNgayVaoLam(), nv.getSdt(), nv.getTenCV(), nv.getTenPB(), nv.getHinhAnh(), nv.getTrangThai()});
            }
        } catch (SQLException ex) {

        }
    }

    private boolean KTDL() throws HeadlessException {
        StringBuilder sb = new StringBuilder();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String ngaysinh = dateFormat.format(jdcNgaySinh.getDate());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đúng định dạng ngày sinh");
            return false;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String ngayvaolam = dateFormat.format(jdcNgayVaoLam.getDate());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đúng định dạng ngày vào làm");
            return false;
        }
        if (tenFile == "") {
            sb.append("Vui lòng chọn file ảnh\n");
        }
        if (txtHoTen.getText().equals("")) {
            sb.append("Vui lòng nhập họ tên\n");
            txtHoTen.requestFocus();
        }
        if (txtDiaChi.getText().equals("")) {
            sb.append("Vui lòng nhập địa chỉ\n");
            txtDiaChi.requestFocus();
        }
        if (txtGioiTinh.getText().equals("")) {
            sb.append("Vui lòng nhập giới tính\n");
            txtGioiTinh.requestFocus();
        }
        if (txtSDT.getText().equals("")) {
            sb.append("Vui lòng nhập số điện thoại\n");
            txtSDT.requestFocus();
        }
        if (txtTrangThai.getText().equals("")) {
            sb.append("Vui lòng nhập trạng thái\n");
            txtTrangThai.requestFocus();
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jdcNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jdcNgayVaoLam = new com.toedter.calendar.JDateChooser();
        cboMaPhongBan = new javax.swing.JComboBox<>();
        cboMaChucVu = new javax.swing.JComboBox<>();
        txtTrangThai = new javax.swing.JTextField();
        lblHinhAnh = new javax.swing.JLabel();
        txtTimKiem = new app.bolivia.swing.JCTextField();
        btnThem = new rojerusan.RSButtonMetro();
        btnSua = new rojerusan.RSButtonMetro();
        btnXoa = new rojerusan.RSButtonMetro();
        btnLuu = new rojerusan.RSButtonMetro();
        btnThemfile = new rojerusan.RSButtonMetro();
        lblSearch = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new rojeru_san.complementos.RSTableMetro();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaNhanVien1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtHoTen1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jdcNgaySinh1 = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        txtDiaChi1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtGioiTinh1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtSDT1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jdcNgayVaoLam1 = new com.toedter.calendar.JDateChooser();
        cboMaPhongBan1 = new javax.swing.JComboBox<>();
        cboMaChucVu1 = new javax.swing.JComboBox<>();
        txtTrangThai1 = new javax.swing.JTextField();
        lblHinhAnh1 = new javax.swing.JLabel();
        txtTimKiem1 = new app.bolivia.swing.JCTextField();
        rSButtonMetro6 = new rojerusan.RSButtonMetro();
        rSButtonMetro7 = new rojerusan.RSButtonMetro();
        rSButtonMetro8 = new rojerusan.RSButtonMetro();
        rSButtonMetro9 = new rojerusan.RSButtonMetro();
        rSButtonMetro10 = new rojerusan.RSButtonMetro();
        lblSearch1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNhanVien1 = new rojeru_san.complementos.RSTableMetro();
        btnHuy = new rojerusan.RSButtonMetro();
        btnIn = new rojerusan.RSButtonMetro();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("Thông Tin Nhân Viên");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Mã Nhân Viên:");

        txtMaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaNhanVien.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Họ và Tên:");

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Ngày Sinh:");

        jdcNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Địa Chỉ:");

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Giới Tính:");

        txtGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Ngày Vào làm:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Số Điện Thoại:");

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("Tên Chức Vụ:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Tên Phòng Ban:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("Trạng Thái:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setText("Hình Ảnh:");

        jdcNgayVaoLam.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cboMaPhongBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboMaPhongBan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaPhongBanItemStateChanged(evt);
            }
        });

        cboMaChucVu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtTrangThai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTimKiem.setPlaceholder("Tìm kiếm tại đây...");

        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLuu.setText("Lưu");
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnThemfile.setText("Thêm File");
        btnThemfile.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThemfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemfileActionPerformed(evt);
            }
        });

        lblSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Ngày sinh", "Địa chỉ", "Giới tính", "Ngày vào làm", "Số điện thoại", "Tên chức vụ", "Tên phòng ban", "Hình ảnh", "Trạng thái"
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

        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel9.setText("Thông Tin Nhân Viên");
        jInternalFrame1.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(581, 13, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Mã Nhân Viên:");
        jInternalFrame1.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 105, -1, -1));
        jInternalFrame1.getContentPane().add(txtMaNhanVien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 96, 453, 46));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("Họ và Tên:");
        jInternalFrame1.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 165, -1, -1));
        jInternalFrame1.getContentPane().add(txtHoTen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 160, 453, 47));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setText("Ngày Sinh:");
        jInternalFrame1.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 235, -1, -1));
        jInternalFrame1.getContentPane().add(jdcNgaySinh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 228, 453, 43));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setText("Địa Chỉ:");
        jInternalFrame1.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 291, -1, -1));
        jInternalFrame1.getContentPane().add(txtDiaChi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 287, 453, 46));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setText("Giới Tính:");
        jInternalFrame1.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 356, -1, -1));
        jInternalFrame1.getContentPane().add(txtGioiTinh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 351, 453, 49));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setText("Ngày Vào làm:");
        jInternalFrame1.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 99, -1, 43));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel20.setText("Số Điện Thoại:");
        jInternalFrame1.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 159, -1, -1));
        jInternalFrame1.getContentPane().add(txtSDT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1161, 155, 453, 47));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setText("Mã Chức Vụ:");
        jInternalFrame1.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 215, -1, 43));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setText("Mã Phòng Ban:");
        jInternalFrame1.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 285, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel23.setText("Trạng Thái:");
        jInternalFrame1.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 348, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setText("Hình Ảnh:");
        jInternalFrame1.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 409, -1, -1));
        jInternalFrame1.getContentPane().add(jdcNgayVaoLam1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1161, 99, 453, 43));

        cboMaPhongBan1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jInternalFrame1.getContentPane().add(cboMaPhongBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1161, 283, 453, 43));

        cboMaChucVu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jInternalFrame1.getContentPane().add(cboMaChucVu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1161, 215, 453, 43));
        jInternalFrame1.getContentPane().add(txtTrangThai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1161, 344, 453, 47));
        jInternalFrame1.getContentPane().add(lblHinhAnh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1161, 409, 140, 130));

        txtTimKiem1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTimKiem1.setPlaceholder("Tìm kiếm tại đây...");
        jInternalFrame1.getContentPane().add(txtTimKiem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 640, 460, 40));

        rSButtonMetro6.setText("Thêm");
        jInternalFrame1.getContentPane().add(rSButtonMetro6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 120, 50));

        rSButtonMetro7.setText("Sửa");
        jInternalFrame1.getContentPane().add(rSButtonMetro7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 570, 120, 50));

        rSButtonMetro8.setText("Xóa");
        jInternalFrame1.getContentPane().add(rSButtonMetro8, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 570, 120, 50));

        rSButtonMetro9.setText("Lưu");
        jInternalFrame1.getContentPane().add(rSButtonMetro9, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 570, 120, 50));

        rSButtonMetro10.setText("Hủy");
        jInternalFrame1.getContentPane().add(rSButtonMetro10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 570, 120, 50));

        lblSearch1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jInternalFrame1.getContentPane().add(lblSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 640, 40, 40));

        tblNhanVien1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Ngày sinh", "Địa chỉ", "Giới tính", "Ngày vào làm", "Số điện thoại", "Mã chức vụ", "Mã phòng ban", "Trạng thái", "Hình ảnh"
            }
        ));
        jScrollPane3.setViewportView(tblNhanVien1);

        jInternalFrame1.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 698, 1560, 262));

        btnHuy.setText("Hủy");
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnIn.setText("In");
        btnIn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(593, 593, 593)
                .addComponent(jLabel1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addGap(59, 59, 59)
                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247)
                .addComponent(jLabel7)
                .addGap(56, 56, 56)
                .addComponent(jdcNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel3)
                .addGap(97, 97, 97)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247)
                .addComponent(jLabel8)
                .addGap(56, 56, 56)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel4)
                .addGap(101, 101, 101)
                .addComponent(jdcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247)
                .addComponent(jLabel11)
                .addGap(68, 68, 68)
                .addComponent(cboMaChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel5)
                .addGap(131, 131, 131)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247)
                .addComponent(jLabel12)
                .addGap(43, 43, 43)
                .addComponent(cboMaPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel6)
                .addGap(113, 113, 113)
                .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247)
                .addComponent(jLabel13)
                .addGap(89, 89, 89)
                .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(963, 963, 963)
                .addComponent(jLabel14)
                .addGap(109, 109, 109)
                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnThemfile, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1118, 1118, 1118)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1580, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8))
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jdcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMaChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel12))
                    .addComponent(cboMaPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel13))
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnThemfile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                String hoten = txtHoTen.getText();
                String ngaysinh = dateFormat.format(jdcNgaySinh.getDate());
                String diachi = txtDiaChi.getText();
                String gioitinh = txtGioiTinh.getText();
                String ngayvaolam = dateFormat.format(jdcNgayVaoLam.getDate());
                Integer sdt = Integer.parseInt(txtSDT.getText());
                TblChucvu kh = (TblChucvu) cboMaChucVu.getSelectedItem();
                String machucvu = String.valueOf(kh.getMaCV());
                TblPhongban pb = (TblPhongban) cboMaPhongBan.getSelectedItem();
                String mapb = String.valueOf(pb.getMaPB());
                String trangthai = txtTrangThai.getText();
                String hinhanh = tenFile;

                try {
                    Connection conn = dao.getConnect();
                    String caulenh = "INSERT INTO tbl_nhanvien VALUES(null,N'" + hoten + "','" + ngaysinh + "',N'" + diachi + "',N'" + gioitinh + "','" + ngayvaolam + "','" + sdt + "','" + machucvu + "','" + mapb + "','" + hinhanh + "',N'" + trangthai + "')";
                    boolean a = dao.Lenh(caulenh, conn);
                    if (a) {
                        tblNhanVien.removeAll();
                        table.setRowCount(0);
                        
                    loadNV("");
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm KHÔNG thành công");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (sua) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Integer manv = Integer.parseInt(txtMaNhanVien.getText());
            String hoten = txtHoTen.getText();
            String ngaysinh = dateFormat.format(jdcNgaySinh.getDate());
            String diachi = txtDiaChi.getText();
            String gioitinh = txtGioiTinh.getText();
            String ngayvaolam = dateFormat.format(jdcNgayVaoLam.getDate());
            Integer sdt = Integer.parseInt(txtSDT.getText());
            TblChucvu kh = (TblChucvu) cboMaChucVu.getSelectedItem();
            String machucvu = String.valueOf(kh.getMaCV());
            TblPhongban pb = (TblPhongban) cboMaPhongBan.getSelectedItem();
            String mapb = String.valueOf(pb.getMaPB());
            String trangthai = txtTrangThai.getText();
            String hinhanh = tenFile;
            String caulenh ="";
            if(hinhanh!=null && !hinhanh.equals("")){
                caulenh = "UPDATE tbl_nhanvien SET HoTen=N'" + hoten + "',NgaySinh='" + ngaysinh + "',DiaChi=N'" + diachi + "',GioiTinh=N'" + gioitinh + "',NgayVaoLam='" + ngayvaolam + "',SDT='" + sdt + "',MaCV='" + machucvu + "',MaPB='" + mapb + "',HinhAnh='" + tenFile + "',TrangThai=N'" + trangthai + "'where MaNV=" + manv;
            }else{
                caulenh = "UPDATE tbl_nhanvien SET HoTen=N'" + hoten + "',NgaySinh='" + ngaysinh + "',DiaChi=N'" + diachi + "',GioiTinh=N'" + gioitinh + "',NgayVaoLam='" + ngayvaolam + "',SDT='" + sdt + "',MaCV='" + machucvu + "',MaPB='" + mapb + "',TrangThai=N'" + trangthai + "'where MaNV=" + manv;
            }
            try {
                Connection conn = dao.getConnect();
                boolean a = dao.Lenh(caulenh, conn);
                if (a) {
                    tblNhanVien.removeAll();
                   table.setRowCount(0);
                    loadNV("");
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa KHÔNG thành công");
                }
            } catch (SQLException ex) {
                Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (xoa) {
            Integer manv = Integer.parseInt(txtMaNhanVien.getText());
            try {
                Connection conn = dao.getConnect();
                String caulenh = "DELETE FROM tbl_nhanvien WHERE MaNV=" + manv;
                boolean a = dao.Lenh(caulenh, conn);
                if (a) {
                    tblNhanVien.removeAll();
                    table.setRowCount(0);
                    loadNV("");
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa KHÔNG thành công");
                }
            } catch (SQLException ex) {
                Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed
    String tenFile;
    private void btnThemfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemfileActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        loadhinh(lblHinhAnh, f.toString());
        String fileName = f.getAbsolutePath();
        String newPath = "src/img/";
        File diretory = new File(newPath);
        if (!diretory.exists()) {
            diretory.mkdirs();
        }
        File sourceFile = null;
        File destinationFile = null;
        String extension = fileName.substring(fileName.lastIndexOf('\\') + 1);
        sourceFile = new File(fileName);
        destinationFile = new File(newPath + extension);
        tenFile = extension;
        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath());
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_btnThemfileActionPerformed
String anh;
    void hienthitxt(int i) {
        TableModel model = tblNhanVien.getModel();
        txtMaNhanVien.setText(model.getValueAt(i, 0).toString());
        txtHoTen.setText(model.getValueAt(i, 1).toString());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(i, 2).toString());
        } catch (ParseException ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        jdcNgaySinh.setDate(date);
        txtDiaChi.setText(model.getValueAt(i, 3).toString());
        txtGioiTinh.setText(model.getValueAt(i, 4).toString());

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(i, 5).toString());
        } catch (ParseException ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        jdcNgayVaoLam.setDate(date);

        txtSDT.setText(model.getValueAt(i, 6).toString());
        String cbomacv = model.getValueAt(i, 7).toString();
        for (int j = 0; j < cboMaChucVu.getItemCount(); j++) {
            if (cboMaChucVu.getItemAt(j).toString().equalsIgnoreCase(cbomacv)) {
                cboMaChucVu.setSelectedIndex(j);
            }
        }
        String cbomapb = model.getValueAt(i, 8).toString();
        for (int j = 0; j < cboMaPhongBan.getItemCount(); j++) {
            if (cboMaPhongBan.getItemAt(j).toString().equalsIgnoreCase(cbomapb)) {
                cboMaPhongBan.setSelectedIndex(j);
            }
        }
        anh=model.getValueAt(i, 9).toString();
        String src = "src/img/" + model.getValueAt(i, 9).toString();
        loadhinh(lblHinhAnh, src);
        txtTrangThai.setText(model.getValueAt(i, 10).toString());
    }
    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int i = tblNhanVien.getSelectedRow();
        hienthitxt(i);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        txtMaNhanVien.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtGioiTinh.setText("");
        txtSDT.setText("");
        txtTrangThai.setText("");
        loadhinh(lblHinhAnh, "");
        tblNhanVien.removeAll();
        table.setRowCount(0);
        loadNV("");
        tenFile="";
        btnThem.setEnabled(true);
        btnThem.setBackground(mauxanh);
        btnSua.setEnabled(true);
        btnSua.setBackground(mauxanh);
        btnXoa.setEnabled(true);
        btnXoa.setBackground(mauxanh);
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.gray);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked
        // TODO add your handling code here:
        tblNhanVien.removeAll();
        table.setRowCount(0);

        String tk = txtTimKiem.getText();
        ArrayList<TblNhanvien> list;
        try {
            list = nvDL.getListNhanVien(tk);
            table = (DefaultTableModel) tblNhanVien.getModel();
            for (TblNhanvien nv : list) {
                table.addRow(new Object[]{nv.getMaNV(), nv.getHoTen(), nv.getNgaySinh(), nv.getDiaChi(), nv.getGioiTinh(), nv.getNgayVaoLam(), nv.getSdt(), nv.getTenCV(), nv.getTenPB(), nv.getHinhAnh(), nv.getTrangThai()});
            }
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_lblSearchMouseClicked

    //report
    private static JasperReport report;
    
    
   
       private static Collection getData(String ma,String ten, String diachi, String ngaysinh, String gioitinh, String sdt,String hinhanh) throws Exception {
        ArrayList<InTheNhanVien> arr = new ArrayList<>();
        //     arr.add(new DataPrint("001", "Iamge 001", imageToInpuStream(new ImageIcon(PrintImage.class.getResource("printImages/i1.jpg")))));
      InTheNhanVien nv= new InTheNhanVien();
              //(InputStream) (new FileInputStream("D:\\Java\\ThucHanhCSDL\\src\\img\\qr.png"))
      nv.setTenNV(ten);
      nv.setDiaChi(diachi);
      nv.setMaNV(Long.parseLong(ma));
      nv.setGioiTinh(gioitinh);
      nv.setHinhAnh((InputStream) (new FileInputStream("src/img/"+ hinhanh)));
      nv.setImage((InputStream) (new FileInputStream("src/img/qr.png")));
      nv.setSdt(sdt);
      nv.setNgaySinh(ngaysinh);
      arr.add(nv);
        return arr;
    }

    
    public static void print(String ma,String ten, String diachi, String ngaysinh, String gioitinh, String sdt,String hinhanh) {
        try {
            JRBeanCollectionDataSource jcd = new JRBeanCollectionDataSource(getData(ma,ten,diachi,ngaysinh,gioitinh,sdt,hinhanh));
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
    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        
          try {
            String QrCodeData =""+ txtMaNhanVien.getText();
            String filePath = "src/img/qr.png";
            String charset = "UTF-8";
              Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
              BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(QrCodeData.getBytes(charset), charset),
                      BarcodeFormat.CODE_128,347 , 125,hintMap);

              MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
            System.out.println("Qr code has been generated at the location " + filePath);

           
           report = JasperCompileManager.compileReport("src/View/TheNhanVien.jrxml");
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
           String ngaysinh = dateFormat.format(jdcNgaySinh.getDate());
            print(txtMaNhanVien.getText(),txtHoTen.getText(),txtDiaChi.getText(),ngaysinh,txtGioiTinh.getText(),txtSDT.getText(),anh);
        } catch (Exception e) {
            System.out.println(e);

        }
    }//GEN-LAST:event_btnInActionPerformed

    private void cboMaPhongBanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaPhongBanItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED){
           TblPhongban ch = (TblPhongban) cboMaPhongBan.getSelectedItem();
            long mapb = ch.getMaPB();
            table.setRowCount(0);
            loadNV(mapb+"");
        }
    }//GEN-LAST:event_cboMaPhongBanItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonMetro btnHuy;
    private rojerusan.RSButtonMetro btnIn;
    private rojerusan.RSButtonMetro btnLuu;
    private rojerusan.RSButtonMetro btnSua;
    private rojerusan.RSButtonMetro btnThem;
    private rojerusan.RSButtonMetro btnThemfile;
    private rojerusan.RSButtonMetro btnXoa;
    private javax.swing.JComboBox<TblChucvu> cboMaChucVu;
    private javax.swing.JComboBox<String> cboMaChucVu1;
    private javax.swing.JComboBox<TblPhongban> cboMaPhongBan;
    private javax.swing.JComboBox<String> cboMaPhongBan1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdcNgaySinh;
    private com.toedter.calendar.JDateChooser jdcNgaySinh1;
    private com.toedter.calendar.JDateChooser jdcNgayVaoLam;
    private com.toedter.calendar.JDateChooser jdcNgayVaoLam1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblHinhAnh1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSearch1;
    private rojerusan.RSButtonMetro rSButtonMetro10;
    private rojerusan.RSButtonMetro rSButtonMetro6;
    private rojerusan.RSButtonMetro rSButtonMetro7;
    private rojerusan.RSButtonMetro rSButtonMetro8;
    private rojerusan.RSButtonMetro rSButtonMetro9;
    private rojeru_san.complementos.RSTableMetro tblNhanVien;
    private rojeru_san.complementos.RSTableMetro tblNhanVien1;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChi1;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtGioiTinh1;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtHoTen1;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMaNhanVien1;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDT1;
    private app.bolivia.swing.JCTextField txtTimKiem;
    private app.bolivia.swing.JCTextField txtTimKiem1;
    private javax.swing.JTextField txtTrangThai;
    private javax.swing.JTextField txtTrangThai1;
    // End of variables declaration//GEN-END:variables
}
