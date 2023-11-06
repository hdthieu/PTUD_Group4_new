package GUI;
import GUI.GUI_HomeMain;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
//import Connection.ConnectSQL;
import Connection.ConnectSQL;


/**
 *
 * @author TriHieu
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
     
    public Login() {
        initComponents();
//        scaleImage();
       
    }
     public void scaleImage() {
        ImageIcon icon =  new ImageIcon("D:\\Group4_PTUD\\BanHangThoiTrang\\src\\img\\logo.jpg");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaleIcon  = new ImageIcon(imgScale);
        jLabel5.setIcon(scaleIcon);
    }

     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        jRadioButton2.setText("jRadioButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng Nhập");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_pass.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        btnLogin.setBackground(new java.awt.Color(204, 153, 255));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Đăng Nhập");
        btnLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 100, 40));

        jLabel4.setBackground(new Color(80, 53, 26)
        );
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Mật Khẩu:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jLabel3.setBackground(new Color(80, 53, 26)
        );
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tên Đăng Nhập:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_user.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 40, 40));

        jLabel1.setBackground(new Color(80, 53, 26)
        );
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Đăng nhập tài khoản của bạn");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));
        getContentPane().add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 180, 30));
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 180, 30));

        jLabel5.setBackground(new java.awt.Color(153, 51, 255));
        jLabel5.setForeground(new java.awt.Color(153, 51, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg_login.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        ConnectSQL cn = ConnectSQL.getInstance(); // Sử dụng instance đã có
    try {
        Connection conn = cn.getConnection();
        ResultSet rs;
        String sql = "Select * from TaiKhoan where tenTaiKhoan = ? and matKhau = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, txtUserName.getText());
        ps.setString(2, txtPass.getText());
        rs = ps.executeQuery();
        if (txtUserName.getText().equals("") || txtPass.getText().equals(""))
            JOptionPane.showMessageDialog(this, "Chưa nhập user và pass");
        else if (rs.next()) {
            GUI_HomeMain h = new GUI_HomeMain();
            h.setVisible(true);
            this.dispose();
            JOptionPane.showMessageDialog(this, "Đăng Nhập thành công");
        } else
            JOptionPane.showMessageDialog(this, "Đăng Nhập thất bại");
        cn.disconnect(); // Đóng kết nối
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    }//GEN-LAST:event_btnLoginActionPerformed
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GUI_HomeMain().setVisible(true);
//            }
//        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
