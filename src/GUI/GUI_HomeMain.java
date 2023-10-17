
package GUI;

//import GUI.GUI_DonHang;
import menu.Menu;
import GUI.GUI_PhieuThongKe;
import GUI.GUI_QuanLySP;
import GUI.GUI_ThongKeSP;
import GUI.GUI_TimKiemSP;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Panel;
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
import javax.swing.JPanel;
public class GUI_HomeMain extends javax.swing.JFrame {

    

    Connection conn;
    ResultSet rs;

//    public void clock() {
//        javax.swing.Timer Time;
//
//        //date  
//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        String date = sdf.format(d);
//
//        //time
//        Time = new javax.swing.Timer(0, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Date dt = new Date();
//                SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss a");
//                String time = st.format(dt);
//
//                lblClock.setText("Ngày: " + date + "    Giờ: " + time);
//            }
//        });
//        Time.start();
//    }

    public GUI_HomeMain() {
    initComponents();
    setLocationRelativeTo(null);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    execute();
  // lỏ phú
}
   
   public void scaleImage() {
         ImageIcon icon =  new ImageIcon("D:\\Group4_PTUD\\BanHangThoiTrang\\src\\img\\bg_homeNew.png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(bgHome.getWidth(), bgHome.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaleIcon  = new ImageIcon(imgScale);
        bgHome.setIcon(scaleIcon);
    }
   
    private void execute() {
        //icon
        ImageIcon iconAccount = new ImageIcon(getClass().getResource("/img/icon_account.png"));
        ImageIcon iconSubMenu = new ImageIcon(getClass().getResource("/img/icon_item.png"));
        ImageIcon iconThongKe = new ImageIcon(getClass().getResource("/img/icon_tke.png"));
        ImageIcon iconHoaDon = new ImageIcon(getClass().getResource("/img/icon_order.png"));
//        ImageIcon iconDangXuat = new ImageIcon(getClass().getResource("/img/icon_exit.png"));
        ImageIcon iconNhanVien = new ImageIcon(getClass().getResource("/img/icon_admin.png"));
        ImageIcon iconSupplier = new ImageIcon(getClass().getResource("/img/icon_supplier.png"));
        ImageIcon iconProduct = new ImageIcon(getClass().getResource("/img/icon_product.png"));
        ImageIcon iconCustomer = new ImageIcon(getClass().getResource("/img/icon_customer.png"));
        ImageIcon icondonDHang = new ImageIcon(getClass().getResource("/img/icon_donDH.png"));
        
                                //create menu
        // menu item sản phẩm
        Menu menuQLySP  = new Menu(iconSubMenu, "Quản Lý Sản phẩm", new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_QuanLySP());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
        
        //menu item Tìm Kiếm Sản Phẩm
        Menu menuTimKiemSP = new Menu(iconSubMenu, "Tìm Kiếm Sản phẩm", new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_TimKiemSP());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
        
        //menu item Thống kê Sản Phẩm
        Menu menuThongKeSP = new Menu(iconSubMenu, "Thống Kê Sản phẩm", new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_ThongKeSP());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
        // menu Item Quản Lý Khách Hàng
        Menu menuQuanLyKH = new Menu(iconSubMenu, "Quản Lý KH", new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_QuanLyKhachHang());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
         // menu Item Tìm Kiếm Khách Hàng
        Menu menuTimKiemKH = new Menu(iconSubMenu, "Tìm Kiếm KH", new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_TimKiemKH());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
        // menu thống kê Khách Hàng
        Menu menuThongKeKH = new Menu(iconSubMenu, "Thống Kê KH", new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_ThongKeKH());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });

        // menu Nhà Cung Cấp
        Menu menuNCC = new Menu(iconSupplier, "Nhà Cung Cấp", new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_NhaCungCap());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
        //menuItem Lập Hóa Đơn
        Menu menuLapHD = new Menu(iconSubMenu, "Lập Hóa Đơn", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_LapHoaDon());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
        //menuItem Tìm Kiếm Hóa Đơn
        Menu menuTimKiemHD = new Menu(iconSubMenu, "Tìm Kiếm Hóa Đơn", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_TimKiemHD());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
        //menuItem Thống Kê Hóa Đơn
        Menu menuThongKeHD = new Menu(iconSubMenu, "Thống Kê Hóa Đơn", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_ThongKeHoaDon());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
        //menuItem Thống Kê Doanh Số Bán Hàng Của Nhân Viên
        Menu menuDSBH = new Menu(iconSubMenu, "DSBH Của NV", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_ThongKeDoanhSoBHCuaNhanVien());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
        // menu Đơn Đặt Hàng
        Menu menuDonDatHang = new Menu(icondonDHang, "Đơn Đặt Hàng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_DonDatHang());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
      
        // menu Tài khoản
        Menu menuTaiKhoan = new Menu(iconAccount, "Tài Khoản", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBody.removeAll();
                pnlBody.add(new GUI_TaiKhoan());
                pnlBody.repaint();
                pnlBody.revalidate();
            }
        });
        
        Menu menuSanPham = new Menu(iconProduct, "Sản Phẩm", null, menuQLySP, menuTimKiemSP, menuThongKeSP);
        Menu menuKhachHang = new Menu(iconCustomer, "Khách Hàng", null, menuQuanLyKH, menuTimKiemKH , menuThongKeKH);
        Menu menuHoaDon = new Menu(iconHoaDon, "Hóa Đơn", null, menuLapHD,menuTimKiemHD,  menuThongKeHD);
        Menu menuNhanVien = new Menu(iconNhanVien, "Nhân Viên", null, menuDSBH);

        
        addMenu(menuSanPham, menuKhachHang, menuNCC, menuHoaDon, menuDonDatHang, menuNhanVien, menuTaiKhoan);
   
    }

    private void addMenu(Menu... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<Menu> subMenu = menu[i].getSubMenu();
            for (Menu m : subMenu) {
                addMenu(m);
            }
        }
        menus.revalidate();
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        LOGO = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlBody = new javax.swing.JPanel();
        bgHome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fashion_Phần Mềm Quản Lý Bán Hàng Quần Áo Thời Trang");
        setBackground(new java.awt.Color(0, 204, 51));

        pnlMenu.setBackground(new java.awt.Color(102, 102, 102));
        pnlMenu.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setBorder(null);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setHorizontalScrollBar(null);

        menus.setBackground(new java.awt.Color(102, 102, 102));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        pnlMenu.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        LOGO.setBackground(new java.awt.Color(0, 204, 153));
        LOGO.setFont(new java.awt.Font("Segoe UI Symbol", 1, 30)); // NOI18N
        LOGO.setForeground(new java.awt.Color(255, 255, 255));
        LOGO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        LOGO.setText("FashionPro");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LOGO, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LOGO, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );

        pnlMenu.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new java.awt.BorderLayout());
        pnlMenu.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pnlBody.setBackground(new java.awt.Color(102, 102, 102));
        pnlBody.setLayout(new java.awt.BorderLayout());

        bgHome.setBackground(new java.awt.Color(255, 102, 102));
        bgHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bgHome_new.png"))); // NOI18N
        pnlBody.add(bgHome, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBody, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 940, Short.MAX_VALUE)
                    .addComponent(pnlMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        setSize(new java.awt.Dimension(1493, 857));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LOGO;
    private javax.swing.JLabel bgHome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration//GEN-END:variables
}
