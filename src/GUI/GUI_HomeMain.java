
package GUI;

import Connection.ConnectSQL;
import GUI.GUI_QuanLySP;
import GUI.GUI_TimKiemSP;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.management.timer.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class GUI_HomeMain extends javax.swing.JFrame {

    // Hello

    Connection conn;
    ConnectSQL cn = new ConnectSQL();
    ResultSet rs;
    private String tenTaiKhoan;


    public GUI_HomeMain() {
    initComponents();
    setLocationRelativeTo(null);
    this.setSize(new Dimension(1275, 840));
    this.setLocationRelativeTo(null);
    
  
}
   
   public void scaleImage() {
         ImageIcon icon =  new ImageIcon("D:\\Group4_PTUD\\BanHangThoiTrang\\src\\img\\bg_homeNew.png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(bgHome.getWidth(), bgHome.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaleIcon  = new ImageIcon(imgScale);
        bgHome.setIcon(scaleIcon);
    }
   


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBody = new javax.swing.JPanel();
        bgHome = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuSP = new javax.swing.JMenu();
        menuQLSP = new javax.swing.JMenuItem();
        menuTKSP = new javax.swing.JMenuItem();
        menuQLNCC = new javax.swing.JMenuItem();
        menuTKNCC = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuQLKH = new javax.swing.JMenuItem();
        menuTKKH = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuLHD = new javax.swing.JMenuItem();
        menuTKHD = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuQLNV = new javax.swing.JMenuItem();
        menuTKNV = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuDonDH = new javax.swing.JMenuItem();
        menuTKhoan = new javax.swing.JMenu();
        menuQLTK = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fashion_Phần Mềm Quản Lý Bán Hàng Quần Áo Thời Trang");
        setBackground(new java.awt.Color(0, 204, 51));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnlBody.setBackground(new java.awt.Color(102, 102, 102));
        pnlBody.setLayout(new java.awt.BorderLayout());

        bgHome.setBackground(new java.awt.Color(255, 102, 102));
        bgHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bgHome_new.png"))); // NOI18N
        pnlBody.add(bgHome, java.awt.BorderLayout.CENTER);

        LOGO.setBackground(new java.awt.Color(0, 204, 153));
        LOGO.setFont(new java.awt.Font("Segoe UI Symbol", 1, 30)); // NOI18N
        LOGO.setForeground(new java.awt.Color(255, 255, 255));
        LOGO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        LOGO.setText("FashionPro");
        pnlBody.add(LOGO, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(0, 0, 213));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblUser.setText("Admin");
        jPanel1.add(lblUser);

        btnDangXuat.setText("Đăng Xuất");
        jPanel1.add(btnDangXuat);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(1235, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlBody.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(pnlBody);

        menuSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_product.png"))); // NOI18N
        menuSP.setText("Sản Phẩm");
        menuSP.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuQLSP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuQLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuQLSP.setText("Quản Lý Sản Phẩm");
        menuQLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLSPActionPerformed(evt);
            }
        });
        menuSP.add(menuQLSP);

        menuTKSP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKSP.setText("Tìm Kiếm Sản Phẩm");
        menuTKSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKSPActionPerformed(evt);
            }
        });
        menuSP.add(menuTKSP);

        menuQLNCC.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuQLNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuQLNCC.setText("Quản Lý Nhà Cung Cấp");
        menuQLNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLNCCActionPerformed(evt);
            }
        });
        menuSP.add(menuQLNCC);

        menuTKNCC.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKNCC.setText("Tìm Kiếm Nhà Cung Cấp");
        menuTKNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKNCCActionPerformed(evt);
            }
        });
        menuSP.add(menuTKNCC);

        jMenuBar1.add(menuSP);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_KH.png"))); // NOI18N
        jMenu2.setText("Khách Hàng");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuQLKH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuQLKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuQLKH.setText("Quản Lý Khách Hàng ");
        menuQLKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLKHActionPerformed(evt);
            }
        });
        jMenu2.add(menuQLKH);

        menuTKKH.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKKH.setText("Tìm Kiếm Khách Hàng");
        menuTKKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKKHActionPerformed(evt);
            }
        });
        jMenu2.add(menuTKKH);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_hoaDon.png"))); // NOI18N
        jMenu3.setText("Hóa Đơn");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuLHD.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuLHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuLHD.setText("Lập Hóa Đơn");
        menuLHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLHDActionPerformed(evt);
            }
        });
        jMenu3.add(menuLHD);

        menuTKHD.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKHD.setText("Tìm Kiếm Hóa Đơn");
        menuTKHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKHDActionPerformed(evt);
            }
        });
        jMenu3.add(menuTKHD);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_staff.png"))); // NOI18N
        jMenu4.setText("Nhân Viên");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuQLNV.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuQLNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuQLNV.setText("Quản Lý Nhân VIên");
        menuQLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLNVActionPerformed(evt);
            }
        });
        jMenu4.add(menuQLNV);

        menuTKNV.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        menuTKNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuTKNV.setText("Tìm Kiếm Nhân Viên");
        menuTKNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTKNVActionPerformed(evt);
            }
        });
        jMenu4.add(menuTKNV);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        jMenuItem1.setText("Thống Kê");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        menuDonDH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuDonDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuDonDH.setText("Đơn Đặt Hàng");
        menuDonDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDonDHActionPerformed(evt);
            }
        });
        jMenu4.add(menuDonDH);

        jMenuBar1.add(jMenu4);

        menuTKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_account.png"))); // NOI18N
        menuTKhoan.setText("Tài Khoản");
        menuTKhoan.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        menuQLTK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuQLTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_item.png"))); // NOI18N
        menuQLTK.setText("Quản Lý Tài Khoản");
        menuQLTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLTKActionPerformed(evt);
            }
        });
        menuTKhoan.add(menuQLTK);

        jMenuBar1.add(menuTKhoan);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1493, 857));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 private void addMenu(JMenu... menus) {
    for (JMenu menu : menus) {
        jMenuBar1.add((PopupMenu) menu);
        Component[] menuComponents = menu.getMenuComponents();
        for (Component subMenu : menuComponents) {
            if (subMenu instanceof JMenu) {
                addMenu((JMenu) subMenu);
            } else if (subMenu instanceof JMenuItem) {
                jMenuBar1.add((JMenuItem) subMenu);
            }
        }
    }
    jMenuBar1.revalidate();
}
    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
        lblUser.setText(tenTaiKhoan);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                execute();
            }
        });
    }
    

    public String getTenTaiKhoan() {
        return this.tenTaiKhoan;
    }
    
    public String quyentruycap(){
       conn = cn.getConnection();
        
      
         String query = "Select quyenTruyCap as quyen from TaiKhoan where tenTaiKhoan=? ";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            System.out.println("tenTaiKhoan: " + getTenTaiKhoan());
            st.setString(1, getTenTaiKhoan());
            ResultSet rs=st.executeQuery();
            while (rs.next()) {
               return  rs.getString("quyen"); 
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI_HomeMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return "CN";
    }
    public boolean checkquyen(MenuItem menuQLSP,MenuItem menuQLNV){
        String quyen= quyentruycap();
        if(quyen.equals("Nhân Viên Bán Hàng")){
             addMenu(menuQLSP);
             return false;
        }else if(quyen.equals("Nhân Viên Quản Lý")){
             addMenu(menuQLNV);
             return false;
        }
        
        
        
        
        return true;
    }

    private void menuQLNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLNCCActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_NhaCungCap());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuQLNCCActionPerformed

    private void menuQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLSPActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_QuanLySP());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuQLSPActionPerformed

    private void menuTKSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKSPActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_TimKiemSP());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuTKSPActionPerformed

    private void menuQLKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLKHActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_QuanLyKhachHang());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuQLKHActionPerformed

    private void menuTKKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKKHActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_TimKiemKH());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuTKKHActionPerformed

    private void menuLHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLHDActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_LapHoaDon());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuLHDActionPerformed

    private void menuTKHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKHDActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_TimKiemHD());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuTKHDActionPerformed

    private void menuQLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLNVActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_QuanLyNhanVien());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuQLNVActionPerformed

    private void menuTKNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKNVActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_TimKiemNhanVien());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuTKNVActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_ThongKe());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuQLTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLTKActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_TaiKhoan());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuQLTKActionPerformed


    private void menuTKNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTKNCCActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_TimKiemNhaCungCap());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuTKNCCActionPerformed

    private void menuDonDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDonDHActionPerformed
        pnlBody.removeAll();
        pnlBody.add(new GUI_DonDatHang());
        pnlBody.repaint();
        pnlBody.revalidate();
    }//GEN-LAST:event_menuDonDHActionPerformed

    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LOGO;
    private javax.swing.JLabel bgHome;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblUser;
    private javax.swing.JMenuItem menuDonDH;
    private javax.swing.JMenuItem menuLHD;
    private javax.swing.JMenuItem menuQLKH;
    private javax.swing.JMenuItem menuQLNCC;
    private javax.swing.JMenuItem menuQLNV;
    private javax.swing.JMenuItem menuQLSP;
    private javax.swing.JMenuItem menuQLTK;
    private javax.swing.JMenu menuSP;
    private javax.swing.JMenuItem menuTKHD;
    private javax.swing.JMenuItem menuTKKH;
    private javax.swing.JMenuItem menuTKNCC;
    private javax.swing.JMenuItem menuTKNV;
    private javax.swing.JMenuItem menuTKSP;
    private javax.swing.JMenu menuTKhoan;
    private javax.swing.JPanel pnlBody;
    // End of variables declaration//GEN-END:variables
}
