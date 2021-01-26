/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.DAONguoihoc;
import Model.Nguoihoc;
import Model.Nhanvien;
import SEVICE.ISVNguoihoc;
import SEVICE.ISVNhanvien;
import SEVICE.SVNguoihoc;
import SEVICE.SVNhanvien;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dell
 */
public class NguoihocGui extends javax.swing.JFrame {

    DefaultTableModel tbModel = new DefaultTableModel();
    DAO.DAONguoihoc dAONguoihoc = new DAO.DAONguoihoc();
    List<Nguoihoc> _lstdao = dAONguoihoc.lstNguoihocs();
    List<Nguoihoc> _lstdaoadd = dAONguoihoc.lstNguoihocs();
    ISVNguoihoc Service = new SVNguoihoc();
    int _i = tbModel.getRowCount() - 1;

    /**
     * Creates new form NguoihocGui
     */
    public NguoihocGui() {
        initComponents();
        groupradio();
        setLocationRelativeTo(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        tbModel = (DefaultTableModel) tb_nguoihoc.getModel();
        loadtb();
        blockBtn();
    }

    private Nhanvien nvlogin() {
        try {
            Nhanvien nhanvienDN = (Nhanvien) Help.writeandread.read("D:\\hoc tap\\DUANMAU\\DA\\object.txt");
            return nhanvienDN;
        } catch (Exception e) {
            return null;
        }
    }

    private void blockBtn() {
        _i = 0;
        loadtxt();
        txt_id.setEditable(false);
        btn_add.setEnabled(false);
        if (tbModel.getRowCount() == 0) {
            btn_next.setEnabled(false);
            btn_fist.setEnabled(false);
            btn_last.setEnabled(false);
            btn_prev.setEnabled(false);
        }
        if (nvlogin().getChucvu() == 0) {
            btn_delete.setEnabled(false);
        }
    }

    void loadtxt() {
        Nguoihoc x = new Nguoihoc();
        x = dAONguoihoc.lstNguoihocs().get(_i);
        txt_birth.setText(x.getNgaysinh());
        txt_email.setText(x.getEmail());
        txt_id.setText(x.getId());
        txt_name.setText(x.getName());
        txt_note.setText(x.getGhichu());
        txt_phone.setText(x.getSdt());
        rb_nam.setSelected(x.getGioitinh() == 1);
        rb_nu.setSelected(x.getGioitinh() == 0);
    }

    void refresh() {
        txt_birth.setText("");
        txt_email.setText("");
        txt_id.setText("");
        txt_name.setText("");
        txt_note.setText("");
        txt_phone.setText("");
        rb_nam.setSelected(true);
    }

    void groupradio() {
        ButtonGroup group = new ButtonGroup();
        group.add(rb_nu);
        group.add(rb_nam);
        rb_nam.setSelected(true);
    }

    void loadtb() {
        tbModel.setRowCount(0);
        int i = 1;
        for (Nguoihoc x : dAONguoihoc.lstNguoihocs()) {
            tbModel.addRow(new Object[]{
                x.getId(), x.getName(), x.getGioitinh() == 1 ? "nam" : "nữ",
                x.getNgaysinh(), x.getSdt(), x.getEmail(), x.getNgayDK(), x.getMaNV()
            });
        }
    }

    String updateNH() {
        Nguoihoc x = new Nguoihoc();
        x = _lstdao.get(Help.Checkloi.checkmaNH(txt_id.getText(), _lstdao));
        x.setId(txt_id.getText());
        x.setName(txt_name.getText());
        x.setGioitinh(rb_nam.isSelected() == true ? 1 : 0);
        x.setSdt(txt_phone.getText());
        x.setEmail(txt_email.getText());
        x.setGhichu(txt_note.getText());
        x.setCheck(true);
        x.setNgaysinh(txt_birth.getText());
        if (x.getId().isBlank()) {
            return "Mời nhập lại mã người học";
        }
        if (x.getName().isBlank()) {
            return "Mời nhập lại họ và tên";
        }
        if (x.getSdt().isBlank()) {
            return "Mời nhập lại số điện thoại";
        }
        if (x.getEmail().isBlank()) {
            return "Mời nhập lại email";
        }
        if (Help.Checkloi.checkEmail(x.getEmail())==false) {
            return "Mời nhập email đúng định dạng";
        }
        if (Help.Checkloi.checkNgay(x.getNgaysinh())==false) {
            return "Mời nhập kiểu (dd/mm/yyyy)";
        }
        if (Help.Checkloi.checkSDT(x.getSdt())==false) {
            return "Mời nhập đúng SDT";
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn sửa không?", "OK",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            return "Ok sửa rồi đấy";
        }
        return "";
    }

    String insertNguoihoc() {
        Nguoihoc x = new Nguoihoc();
        x.setId(txt_id.getText());
        x.setName(txt_name.getText());
        x.setGioitinh(rb_nam.isSelected() == true ? 1 : 0);
        x.setSdt(txt_phone.getText());
        x.setEmail(txt_email.getText());
        x.setGhichu(txt_note.getText());
        x.setMaNV(nvlogin().getId());
        x.setCheck(true);
        x.setNgayDK(Help.FormatDate.dinhDangNgayThangNam(new Date()));
        x.setNgaysinh(txt_birth.getText());
        if (x.getId().isBlank()) {
            return "Mời nhập lại mã người học";
        }
        if (Help.Checkloi.checkmaNH(x.getId(), _lstdao) > -1) {
            return "Mã người học đã tồn tại";
        }
        if (x.getName().isBlank()) {
            return "Mời nhập lại họ và tên";
        }
        if (x.getSdt().isBlank()) {
            return "Mời nhập lại số điện thoại";
        }
        if (x.getEmail().isBlank()) {
            return "Mời nhập lại email";
        }
        if (Help.Checkloi.checkEmail(x.getEmail())==false) {
            return "Mời nhập email đúng định dạng";
        }
        if (Help.Checkloi.checkNgay(x.getNgaysinh())==false) {
            return "Mời nhập kiểu (dd/mm/yyyy)";
        }
        if (Help.Checkloi.checkSDT(x.getSdt())==false) {
            return "Mời nhập đúng SDT";
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thêm không?", "OK",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            _lstdaoadd.add(x);
            return "Ok thêm rồi đấy";
        }
        return "";
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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_phone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        rb_nu = new javax.swing.JRadioButton();
        rb_nam = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txt_birth = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_note = new javax.swing.JTextArea();
        btn_last = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_change = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        btn_fist = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_nguoihoc = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Quản lý người học");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel1.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jTabbedPane3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Mã người học :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));
        jPanel2.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 500, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Ngày sinh :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, -1));
        jPanel2.add(txt_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 230, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Họ và tên :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Ghi chú :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));
        jPanel2.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 500, -1));

        rb_nu.setText("Nữ");
        jPanel2.add(rb_nu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        rb_nam.setText("Nam");
        jPanel2.add(rb_nam, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Giới tinh :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        txt_birth.setText("dd/mm/yyyy");
        txt_birth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_birthMouseClicked(evt);
            }
        });
        jPanel2.add(txt_birth, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 250, -1));

        txt_email.setText("abc@email.com");
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        jPanel2.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 250, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Email :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Điện thoại :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        txt_note.setColumns(20);
        txt_note.setRows(5);
        jScrollPane2.setViewportView(txt_note);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 500, -1));

        btn_last.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_last.setText("|>");
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });
        jPanel2.add(btn_last, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 50, -1));

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_delete.setText("Xóa");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel2.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 70, -1));

        btn_add.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_add.setText("Thêm");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        jPanel2.add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 70, -1));

        btn_change.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_change.setText("Sửa");
        btn_change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_changeActionPerformed(evt);
            }
        });
        jPanel2.add(btn_change, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 70, -1));

        btn_refresh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_refresh.setText("Mới");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        jPanel2.add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 70, -1));

        btn_fist.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_fist.setText("<|");
        btn_fist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fistActionPerformed(evt);
            }
        });
        jPanel2.add(btn_fist, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 50, -1));

        btn_prev.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_prev.setText("<<");
        btn_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prevActionPerformed(evt);
            }
        });
        jPanel2.add(btn_prev, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 50, -1));

        btn_next.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_next.setText(">>");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        jPanel2.add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, 50, -1));

        jTabbedPane3.addTab("CẬP NHẬT", jPanel2);

        tb_nguoihoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã người học", "Họ và tên", "Giới tính", "Ngày sinh", "Điện thoại", "Email", "Ngày ĐKy", "Mã nhân viên"
            }
        ));
        jScrollPane1.setViewportView(tb_nguoihoc);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jTextField6))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("DANH SÁCH", jPanel3);

        jPanel1.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 580, 430));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        _i = tbModel.getRowCount() - 1;
        loadtxt();
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_changeActionPerformed
        String tb = updateNH();
        JOptionPane.showMessageDialog(rootPane, tb);
        if (tb.equals("Ok sửa rồi đấy")) {
            dAONguoihoc.UpdateNguoihoc(_lstdao);
        }
        loadtb();
    }//GEN-LAST:event_btn_changeActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        DAONguoihoc dao = new DAONguoihoc();
        String tb = insertNguoihoc();
        JOptionPane.showMessageDialog(rootPane, tb);
        if (tb.equals("Ok thêm rồi đấy")) {
            dao.InsertNguoihoc(_lstdaoadd);
        }
        loadtb();
        refresh();
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        if (btn_add.isEnabled()) {
            btn_add.setEnabled(false);
            btn_change.setEnabled(true);
            btn_delete.setEnabled(true);
            txt_id.setEditable(false);
            if (nvlogin().getChucvu() == 0) {
                btn_delete.setEnabled(false);
            }
            return;
        } else {
            refresh();
            txt_id.setEditable(true);
            btn_add.setEnabled(true);
            btn_change.setEnabled(false);
            btn_delete.setEnabled(false);
        }
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_fistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fistActionPerformed
        _i = 0;
        loadtxt();
    }//GEN-LAST:event_btn_fistActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        if (_i > 0) {
            _i--;
            loadtxt();
        }
    }//GEN-LAST:event_btn_prevActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        if (_i < (tbModel.getRowCount() - 1)) {
            _i++;
            loadtxt();
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_birthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_birthMouseClicked
        txt_birth.setText("");
    }//GEN-LAST:event_txt_birthMouseClicked

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
            java.util.logging.Logger.getLogger(NguoihocGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NguoihocGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NguoihocGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NguoihocGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NguoihocGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_change;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_fist;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JRadioButton rb_nam;
    private javax.swing.JRadioButton rb_nu;
    private javax.swing.JTable tb_nguoihoc;
    private javax.swing.JTextField txt_birth;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextArea txt_note;
    private javax.swing.JTextField txt_phone;
    // End of variables declaration//GEN-END:variables
}
