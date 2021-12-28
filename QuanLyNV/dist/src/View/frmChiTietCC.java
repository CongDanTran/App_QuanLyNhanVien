/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ChamCongXLDL;
import Controller.ChiTietChamCongXLDL;
import Controller.DAO;
import Controller.KNCSDL;
import Model.TblChamcong;
import Model.TblChitietchamcong;
import Model.TblChucvu;
import Model.TblNhanvien;
import Model.TblPhongban;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Cong Dan
 */
public class frmChiTietCC extends javax.swing.JFrame implements Runnable, ThreadFactory {

    ChamCongXLDL ccDL;
    ChiTietChamCongXLDL ctccDL;
    DefaultTableModel table;
    DAO dao;
    Color mauxanh = new Color(0, 112, 192);
    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    /**
     * Creates new form frmChiTietCC
     */
    static String macc;

    public frmChiTietCC(String id) {
        initComponents();
        setLocationRelativeTo(null);
        ctccDL = new ChiTietChamCongXLDL();
        ccDL = new ChamCongXLDL();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        dao = new DAO("jdbc:mysql://localhost:3306/qlnhanvien");
        Date dt = new Date();
        String ngay = dateFormat.format(dt);
        loadCTCC(ngay);
        macc = id;
        txtMaCC.setText(id);
        initWebcam();
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    void loadAnh(JLabel lbl, String fileanh) {
        //TODO add your handling code here:
        ImageIcon icon = new ImageIcon(fileanh);
        Image image = icon.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
        lbl.setIcon(new ImageIcon(image));
    }
    static Date today = new Date();

    void loadCTCC(String ngay) {

        ArrayList<TblChitietchamcong> list;
        try {
            list = ctccDL.getListNV(ngay);
            table = (DefaultTableModel) tblChiTietCC.getModel();
            for (TblChitietchamcong nv : list) {
                table.addRow(new Object[]{nv.getMaCC(), nv.getNgay(), nv.getGioVao(), nv.getGioRa(), nv.getGhiChu()});
            }
        } catch (SQLException ex) {

        }
    }

    Integer getchamcong(String ma) throws SQLException {
        KNCSDL kn = new KNCSDL();
        ArrayList<TblChamcong> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_chamcong.* FROM tbl_chamcong where Thang= month(Now()) AND Nam= year(Now()) and MaNV=" + ma;
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        int a = 0;
        while (result.next()) {

            a++;
        }
        return a;
    }

    private boolean KTDL() throws HeadlessException {
        StringBuilder sb = new StringBuilder();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String ngay = dateFormat.format(jdcNgay.getDate());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đúng định dạng ngày");
            return false;
        }
        if (txtGioVao.getText().equals("")) {
            sb.append("Vui lòng nhập giờ vào\n");
            txtGioVao.requestFocus();
        }
        if (txtGioRa.getText().equals("")) {
            sb.append("Vui lòng nhập giờ ra\n");
            txtGioRa.requestFocus();
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    void loadhd(String mahd) {

    }
    private static JasperReport report;

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 310));

        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }

            if (result != null) {
                txtMaNhanVien.setText(result.getText());
                if (webcam.isOpen()) {
                    webcam.close();
                }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        txtMaCC = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        txtGioVao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtGioRa = new javax.swing.JTextField();
        jdcNgay = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rSButtonMetro5 = new rojerusan.RSButtonMetro();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietCC = new rojeru_san.complementos.RSTableMetro();
        btnRa = new rojerusan.RSButtonMetro();
        jPanel1 = new javax.swing.JPanel();
        btnMo = new rojerusan.RSButtonMetro();
        btnVao = new rojerusan.RSButtonMetro();
        rSButtonMetro1 = new rojerusan.RSButtonMetro();
        rSButtonMetro2 = new rojerusan.RSButtonMetro();
        rSButtonMetro3 = new rojerusan.RSButtonMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Mã Nhân Viên:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, -1, -1));

        txtMaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaNhanVien.setEnabled(false);
        jPanel2.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, 453, 47));

        txtMaCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaCC.setEnabled(false);
        jPanel2.add(txtMaCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, 453, 47));

        txtGhiChu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1420, 260, 450, 47));

        txtGioVao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtGioVao, new org.netbeans.lib.awtextra.AbsoluteConstraints(1422, 103, 453, 47));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Ghi Chú:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 270, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("Chi Tiết Chấm Công");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(712, 13, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Giờ Ra:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 190, -1, -1));

        txtGioRa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtGioRa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1422, 183, 453, 47));

        jdcNgay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(jdcNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 453, 47));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Ngày:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Mã Chấm Công:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Giờ Vào:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 110, -1, -1));

        rSButtonMetro5.setText("Trở về");
        rSButtonMetro5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro5ActionPerformed(evt);
            }
        });
        jPanel2.add(rSButtonMetro5, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 23, 120, 50));

        tblChiTietCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã chấm công", "Ngày", "Giờ vào", "Giờ ra", "Ghi chú"
            }
        ));
        tblChiTietCC.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblChiTietCC.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblChiTietCC.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblChiTietCC.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblChiTietCC.setRowHeight(25);
        tblChiTietCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietCCMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTietCC);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 493, 1820, 500));

        btnRa.setText("Điểm danh ra");
        btnRa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRaActionPerformed(evt);
            }
        });
        jPanel2.add(btnRa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 330, 140, 50));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 103, 540, 310));

        btnMo.setText("Mở");
        btnMo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnMo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoActionPerformed(evt);
            }
        });
        jPanel2.add(btnMo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, 120, 50));

        btnVao.setText("Điểm danh vào");
        btnVao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaoActionPerformed(evt);
            }
        });
        jPanel2.add(btnVao, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 330, 160, 50));

        rSButtonMetro1.setText(">");
        rSButtonMetro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro1ActionPerformed(evt);
            }
        });
        jPanel2.add(rSButtonMetro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 30, 40));

        rSButtonMetro2.setText("II");
        rSButtonMetro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro2ActionPerformed(evt);
            }
        });
        jPanel2.add(rSButtonMetro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 30, 40));

        rSButtonMetro3.setText("<");
        rSButtonMetro3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro3ActionPerformed(evt);
            }
        });
        jPanel2.add(rSButtonMetro3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 30, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonMetro5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro5ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_rSButtonMetro5ActionPerformed

    private void btnRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRaActionPerformed
        // TODO add your handling code here:
        try {
            Connection conn = dao.getConnect();
            int getcc = getchamcong(txtMaNhanVien.getText());
            if (getcc == 0) {
                String caulenh = "INSERT INTO tbl_chamcong VALUES(null,'" + txtMaNhanVien.getText() + "',month(now()),year(now()),'" + 0 + "')";
                boolean a = dao.Lenh(caulenh, conn);

            } else {

            }
            String caulenh = "select tbl_chamcong.* from tbl_chamcong where MaNV=" + txtMaNhanVien.getText() + " and Thang=month(now()) and Nam=year(now())";
            Statement statement = (Statement) conn.createStatement();
            ResultSet result = statement.executeQuery(caulenh);
            long ma = 0;
            while (result.next()) {
                ma = result.getLong(1);

            }

            String cl = "Update tbl_chitietchamcong set GioRa=time(now()) where MaCC=" + ma + " and Ngay=date(now())";
            boolean a = dao.Lenh(cl, conn);
            tblChiTietCC.removeAll();
            table.setRowCount(0);
            Date dt = new Date();
            String ngay = dateFormat.format(dt);
            loadCTCC(ngay);
            String sogio = "Select hour(GioRa-GioVao) as gio FROM tbl_chitietchamcong WHERE MaCC= " + ma + " AND Ngay=date(now()) ";
            statement = (Statement) conn.createStatement();
            result = statement.executeQuery(sogio);
            long gio = 0;
            while (result.next()) {
                gio = result.getInt(1);

            }
            String clenh = "Update tbl_chamcong set SoGioLam = SoGioLam +" + gio + " where MaCC= " + ma;
            boolean c = dao.Lenh(clenh, conn);

        } catch (SQLException ex) {
            Logger.getLogger(frmChiTietCC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnRaActionPerformed
    void hienthitxt(int i) {
        TableModel model = tblChiTietCC.getModel();
        txtMaCC.setText(model.getValueAt(i, 0).toString());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(i, 1).toString());
        } catch (ParseException ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        jdcNgay.setDate(date);
        txtGioVao.setText(model.getValueAt(i, 2).toString());
        txtGioRa.setText(model.getValueAt(i, 3).toString());
        txtGhiChu.setText(model.getValueAt(i, 4).toString());
    }
    private void tblChiTietCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietCCMouseClicked
        // TODO add your handling code here:
        int i = tblChiTietCC.getSelectedRow();
        hienthitxt(i);
    }//GEN-LAST:event_tblChiTietCCMouseClicked

    private void btnMoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoActionPerformed
        // TODO add your handling code here:
        initWebcam();
    }//GEN-LAST:event_btnMoActionPerformed

    private void btnVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaoActionPerformed

        try {
            Connection conn = dao.getConnect();
            int getcc = getchamcong(txtMaNhanVien.getText());
            if (getcc == 0) {
                String caulenh = "INSERT INTO tbl_chamcong VALUES(null,'" + txtMaNhanVien.getText() + "',month(now()),year(now()),'" + 0 + "')";
                boolean a = dao.Lenh(caulenh, conn);

            } else {

            }
            String caulenh = "select tbl_chamcong.* from tbl_chamcong where MaNV=" + txtMaNhanVien.getText() + " and Thang=month(now()) and Nam=year(now())";
            Statement statement = (Statement) conn.createStatement();
            ResultSet result = statement.executeQuery(caulenh);
            long ma = 0;
            while (result.next()) {
                ma = result.getLong(1);

            }
            String cl = "INSERT INTO tbl_chitietchamcong VALUES(" + ma + ",date(now()),time(now()),time(now()),'" + "Đúng Giờ" + "')";
            boolean a = dao.Lenh(cl, conn);
            tblChiTietCC.removeAll();
            table.setRowCount(0);
            Date dt = new Date();
            String ngay = dateFormat.format(dt);
            loadCTCC(ngay);

        } catch (SQLException ex) {
            Logger.getLogger(frmChiTietCC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVaoActionPerformed

    private void rSButtonMetro3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro3ActionPerformed
        // TODO add your handling code here:SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());

        Date tomorrow = new Date(today.getTime() - (1000 * 60 * 60 * 24));
        today = tomorrow;
        String ngay = dateFormat.format(tomorrow);
        try {
            tblChiTietCC.removeAll();
            table.setRowCount(0);
            jdcNgay.setDate(today);
            loadCTCC(ngay);
        } catch (Exception e) {
          
            loadCTCC(ngay);
        }
    }//GEN-LAST:event_rSButtonMetro3ActionPerformed

    private void rSButtonMetro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro1ActionPerformed
        // TODO add your handling code here:
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        today = tomorrow;
        String ngay = dateFormat.format(tomorrow);
          try {
            tblChiTietCC.removeAll();
            table.setRowCount(0);
             jdcNgay.setDate(today);
            loadCTCC(ngay);
        } catch (Exception e) {
          
            loadCTCC(ngay);
        }
    }//GEN-LAST:event_rSButtonMetro1ActionPerformed

    private void rSButtonMetro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro2ActionPerformed
        // TODO add your handling code here: Date tomorrow = new Date(today.getTime() - (1000 * 60 * 60 * 24));
        try {
            tblChiTietCC.removeAll();
            table.setRowCount(0);
            today = new Date();
             jdcNgay.setDate(today);
            String ngay = dateFormat.format(today);
            loadCTCC(ngay);
        } catch (Exception e) {
            today = new Date();
            String ngay = dateFormat.format(today);
            loadCTCC(ngay);
        }

    }//GEN-LAST:event_rSButtonMetro2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmChiTietCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmChiTietCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmChiTietCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmChiTietCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmChiTietCC(macc).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonMetro btnMo;
    private rojerusan.RSButtonMetro btnRa;
    private rojerusan.RSButtonMetro btnVao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcNgay;
    private rojerusan.RSButtonMetro rSButtonMetro1;
    private rojerusan.RSButtonMetro rSButtonMetro2;
    private rojerusan.RSButtonMetro rSButtonMetro3;
    private rojerusan.RSButtonMetro rSButtonMetro5;
    private rojeru_san.complementos.RSTableMetro tblChiTietCC;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGioRa;
    private javax.swing.JTextField txtGioVao;
    private javax.swing.JTextField txtMaCC;
    private javax.swing.JTextField txtMaNhanVien;
    // End of variables declaration//GEN-END:variables

}
