/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Connection.ConnectSQL;
import DAO.ThongKeHD_DAO;
import DAO.ThongKeKH_DAO;
import DAO.ThongKeSP_DAO;
import Entity.ThongKeHoaDon;
import Entity.ThongKeKH;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
 import Entity.ThongKeSP;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author TriHieu
 */

public class GUI_ThongKe extends javax.swing.JPanel {
    private boolean isFiltered = false;
    private ConnectSQL connection = ConnectSQL.getInstance();
    private String patternCurrency = "###,###.###VND";   
    private DecimalFormat dcfCurrency = new DecimalFormat(patternCurrency);
    private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    private ThongKeSP_DAO tkSPDAO = new ThongKeSP_DAO();
    private ThongKeHD_DAO tkHDDAO = new ThongKeHD_DAO();
    private ThongKeKH_DAO tkKHDAO = new ThongKeKH_DAO();
    private DefaultTableModel model;
    private List<ThongKeSP> tKSPList = tkSPDAO.docTuBang();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private List<ThongKeHoaDon> tkHDList = tkHDDAO.docTuBang();
    private List<ThongKeKH> tkKHList = tkKHDAO.docTuBang();
    
    private List<ThongKeSP> filteredData; // lọc create chart()
    private List<ThongKeKH> filteredDataKH; // lọc create chart()
    private List<ThongKeHoaDon> filteredDataHD;
    
    
    private DefaultCategoryDataset datasetSP = new DefaultCategoryDataset();
    private DefaultCategoryDataset datasetHD = new DefaultCategoryDataset();
    public GUI_ThongKe() {
        initComponents();
        ConnectSQL.getInstance();
        ConnectSQL.connect();
        txtTongSP.setEditable(false);
        txtTongDThu.setEditable(false);
        txtSPBC.setEditable(false);
        txtTongMua.setEditable(false);
        txtTongTien.setEditable(false);
        txtKHMuaNhieu.setEditable(false);
        txtTongKH.setEditable(false);
        
        updateTableSP(tKSPList);
        updateDThu (0, model.getRowCount(), txtTongDThu);
        updateSPNhieuNhat(model.getRowCount(), txtSPBC);
        createChartSP();
        
        // tabbed Hóa Đơn
        updateTableHD(tkHDList);
        createChartHD();
        updateDThu (0, model.getRowCount(), txtDThuHD);
        updateTableKH(tkKHList);

        // tabbed Khách Hàng
        int tM = 0;
                   updateDThu (0, model.getRowCount(), txtTongTien);
                    updateKHMuaNhieuNhat(model.getRowCount());
                    // update tổng mua của tất cả khách hàng
                    for(int i=0; i< model.getRowCount(); i++){
                        Integer tongMua = (Integer) tblTKKH.getValueAt(i, 3);
                        tM += tongMua;
                    }
                    txtTongMua.setText(tM+"");
                    createChartKH();
        
//        jTabbedPane1.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent e) {
//                int selectedIndex = jTabbedPane1.getSelectedIndex();
//                // tabbed Thống Kê Sản Phẩm
//                if(selectedIndex == 0){
//                    updateTableSP(tKSPList);
//                    updateDThu (0, model.getRowCount(), txtTongDThu);
//                    updateSPNhieuNhat(model.getRowCount(), txtSPBC);
//                    createChartSP();
//                }
//                // tabbed Thống Kê Hóa Đơn
////                if(selectedIndex == 1){
////                    updateTableHD(tkHDList);
////                    createChartHD();
////                    updateDThu (0, model.getRowCount(), txtDThuHD);
////                    
////                }
//                // tabbed Thống Kê Khách Hàng
//                if(selectedIndex == 2){
//                    int tM = 0;
////                    updateDThu (0, model.getRowCount(), txtTongTien);
////                    updateKHMuaNhieuNhat(model.getRowCount());
////                    // update tổng mua của tất cả khách hàng
////                    for(int i=0; i< model.getRowCount(); i++){
////                        Integer tongMua = (Integer) tblTKKH.getValueAt(i, 3);
////                        tM += tongMua;
////                    }
////                    txtTongMua.setText(tM+"");
////                    createChartKH();
//                }
//                // tabbed Thống Kê Doanh Số Bán Hàng Của Nhân Viên
//                if(selectedIndex == 3){
//                    System.out.println("checkDS");
//                }
//            }
//            });
    
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnTKSP = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTKeSP = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnTKe = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblTKSP = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDateTruoc = new com.toedter.calendar.JDateChooser();
        txtDateSau = new com.toedter.calendar.JDateChooser();
        btnXuatFile1 = new javax.swing.JButton();
        pnBieuDo = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtSPBC = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTongSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTongDThu = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnTKeHD = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDateHDTruoc = new com.toedter.calendar.JDateChooser();
        txtDateHDSau = new com.toedter.calendar.JDateChooser();
        pnlTKHD = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTKHD = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        txtTongHD = new javax.swing.JTextField();
        txtDThuHD = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnTKKH = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtDateKHTruoc = new com.toedter.calendar.JDateChooser();
        txtDateKHSau = new com.toedter.calendar.JDateChooser();
        pnlBDTKKH = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTKKH = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtKHMuaNhieu = new javax.swing.JTextField();
        txtTongKH = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtTongMua = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jDateChooser8 = new com.toedter.calendar.JDateChooser();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();

        tblTKeSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Ngày Bán", "Số Lượng SP Đã Bán", "Doanh Thu"
            }
        ));
        jScrollPane2.setViewportView(tblTKeSP);

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Xuất File Excel của Thống Kê");

        btnTKe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_chart.png"))); // NOI18N
        btnTKe.setText("Thống Kê");
        btnTKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKeActionPerformed(evt);
            }
        });

        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTKSP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTKSP.setText("Thống Kê Sản Phẩm");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Khoảng Thời Gian");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Từ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Đến");

        txtDateTruoc.setDateFormatString("yyyy-MM-dd\n");

        txtDateSau.setDateFormatString("yyyy-MM-dd\n");

        btnXuatFile1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXuatFile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_excel.png"))); // NOI18N
        btnXuatFile1.setText("File Excel");
        btnXuatFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFile1ActionPerformed(evt);
            }
        });

        pnBieuDo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnBieuDoLayout = new javax.swing.GroupLayout(pnBieuDo);
        pnBieuDo.setLayout(pnBieuDoLayout);
        pnBieuDoLayout.setHorizontalGroup(
            pnBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnBieuDoLayout.setVerticalGroup(
            pnBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDateSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDateTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(90, 90, 90)))
                        .addComponent(btnTKe))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(btnXuatFile1))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(626, Short.MAX_VALUE)
                .addComponent(lblTKSP)
                .addGap(446, 446, 446))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(lblTKSP)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(txtDateTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(txtDateSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(btnTKe, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 151, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 152, Short.MAX_VALUE)))
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel16.setText("Sản Phẩm Bán Chạy Nhất");

        txtSPBC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setText("Tổng Số Sản Phẩm");

        txtTongSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTongSP.setFocusCycleRoot(true);
        txtTongSP.setFocusTraversalPolicyProvider(true);
        txtTongSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongSPActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Tổng Doanh Thu");

        txtTongDThu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout pnTKSPLayout = new javax.swing.GroupLayout(pnTKSP);
        pnTKSP.setLayout(pnTKSPLayout);
        pnTKSPLayout.setHorizontalGroup(
            pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTKSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTKSPLayout.createSequentialGroup()
                        .addGroup(pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTongSP, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtSPBC)
                            .addComponent(txtTongDThu))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnTKSPLayout.setVerticalGroup(
            pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTKSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTKSPLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtSPBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(44, 44, 44)
                        .addGroup(pnTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTongDThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnTKSPLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(216, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", pnTKSP);

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Xuất File Excel của Thống Kê");

        btnTKeHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTKeHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_chart.png"))); // NOI18N
        btnTKeHD.setText("Thống Kê");
        btnTKeHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKeHDActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_excel.png"))); // NOI18N
        jButton4.setText("File Excel");

        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Thống Kê Hóa Đơn");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Khoảng Thời Gian");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("Từ");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Đến");

        pnlTKHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTKHD, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDateHDSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDateHDTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)
                                .addComponent(btnTKeHD))
                            .addComponent(jLabel18)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(jButton4))))
                    .addComponent(jLabel17))
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnTKeHD, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19)
                                    .addComponent(txtDateHDTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(txtDateHDSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(pnlTKHD, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tblTKHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Tên Nhân Viên", "Tên Khách Hàng", "Ngày Tạo", "Số Lượng Sản Phẩm", "Doanh Thu"
            }
        ));
        jScrollPane3.setViewportView(tblTKHD);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Tổng Hóa Đơn");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tổng Doanh Thu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(35, 35, 35)
                                .addComponent(txtDThuHD, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtDThuHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel2);

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Xuất File Excel của Thống Kê");

        btnTKKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTKKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_chart.png"))); // NOI18N
        btnTKKH.setText("Thống Kê");
        btnTKKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKKHActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_excel.png"))); // NOI18N
        jButton6.setText("File Excel");

        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setText("Thống Kê Khách Hàng");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText("Khoảng Thời Gian");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setText("Từ");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel26.setText("Đến");

        pnlBDTKKH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(640, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(413, 413, 413))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBDTKKH, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDateKHSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDateKHTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel24)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jButton6))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnTKKH)
                        .addComponent(jLabel7)))
                .addGap(71, 71, 71))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel23)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(pnlBDTKKH, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(txtDateKHTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(txtDateKHSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnTKKH, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tblTKKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "GIới Tính", "Tổng Lượt Mua", "Tổng Tiền Đã Mua"
            }
        ));
        jScrollPane4.setViewportView(tblTKKH);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel28.setText("Tổng Tiền ");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel29.setText("Khách Hàng Mua Nhiều Nhất");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel30.setText("Tổng Khách Hàng");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel51.setText("Tổng Lượt Mua");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel28)
                            .addComponent(jLabel30)
                            .addComponent(jLabel51))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongMua)
                            .addComponent(txtTongTien)
                            .addComponent(txtKHMuaNhieu)
                            .addComponent(txtTongKH))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(txtTongMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKHMuaNhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtTongKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(" Khách Hàng", jPanel3);

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel31.setText("Xuất File Excel của Thống Kê");

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_chart.png"))); // NOI18N
        jButton7.setText("Thống Kê");

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_excel.png"))); // NOI18N
        jButton8.setText("File Excel");

        jLabel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setText("Thống Kê Sản Phẩm");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel34.setText("Khoảng Thời Gian");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel35.setText("Từ");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel36.setText("Đến");

        jLabel37.setText("Biểu Đồ");
        jLabel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel36))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)
                                .addComponent(jButton7))
                            .addComponent(jLabel34)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(jButton8))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)))
                .addGap(95, 95, 95))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 616, Short.MAX_VALUE)
                    .addComponent(jLabel32)
                    .addGap(0, 616, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel34)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel35)
                                    .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addComponent(jDateChooser8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 201, Short.MAX_VALUE)
                    .addComponent(jLabel32)
                    .addGap(0, 200, Short.MAX_VALUE)))
        );

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Tổng Số Lượt Bán", "Doanh Số Bán Hàng"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel38.setText("Tổng Doanh Số");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel52.setText("Tổng Số Lượt Bán");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel53.setText("Tổng Số Nhân Viên");

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel38)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel52)
                                    .addGap(28, 28, 28)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(" Doanh Số Bán Hàng Của Nhân Viên", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed
    
    private void txtTongSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongSPActionPerformed
         
    }//GEN-LAST:event_txtTongSPActionPerformed

    private void btnXuatFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFile1ActionPerformed
        FileOutputStream fileOut;
        String excelFilePath = "D:/DanhSach.xlsx";
         
        try {
            fileOut = new FileOutputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Thống Kê SP");
                Row rowTuaDe = sheet.createRow(0);
                rowTuaDe.createCell(0, CellType.STRING).setCellValue("Danh Sách Sản Phẩm");               
                CellStyle headerStyle = createStyleForHeader(sheet);
                CellStyle itemStyle = createStyleForItem(sheet);
                Row rowTieuDe = sheet.createRow(1);
                for (int i = 0; i < tblTKeSP.getColumnCount(); i++) {
                    Cell cell = rowTieuDe.createCell(i, CellType.STRING);
                    cell.setCellValue(tblTKeSP.getColumnName(i));
                    cell.setCellStyle(headerStyle);
                }
                
                for (int rowtblSP = 0; rowtblSP < tblTKeSP.getRowCount(); rowtblSP++){
                    Row rowItem = sheet.createRow(2 + rowtblSP);
                    for(int coltblSP = 0; coltblSP < tblTKeSP.getColumnCount(); coltblSP ++){
                        Object valueSP = tblTKeSP.getValueAt(rowtblSP, coltblSP);       //System.out.println(valueSP);
                        Cell cell1 = rowItem.createCell(coltblSP, CellType.STRING);
                        cell1.setCellValue(String.valueOf(valueSP));
                        cell1.setCellStyle(itemStyle);
                    }
                }
                for (int i = 0; i < sheet.getLastRowNum(); i++) {
                    int numberOfColumn = sheet.getRow(i).getPhysicalNumberOfCells();
                    autosizeColumn(sheet, numberOfColumn);
                }
                workbook.write(fileOut);
                workbook.close();
                JOptionPane.showMessageDialog(this, "Excel file created successfully!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GUI_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_btnXuatFile1ActionPerformed

    private void btnTKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKeActionPerformed
        Date selectedDateTruoc = txtDateTruoc.getDate();
        Date selectedDateSau = txtDateSau.getDate();
        java.sql.Date sqlDateTruoc = new java.sql.Date(selectedDateTruoc.getTime());
        java.sql.Date sqlDateSau = new java.sql.Date(selectedDateSau.getTime());
        List<ThongKeSP> tkSP = tkSPDAO.locDateTKSP(sqlDateTruoc, sqlDateSau);
        if(!tkSP.isEmpty()){
            model = (DefaultTableModel) tblTKeSP.getModel();
            model.setRowCount(0);
            updateTableSP(tkSP);
            updateDThu (0, model.getRowCount(), txtTongDThu);
            updateSPNhieuNhat(model.getRowCount(), txtSPBC);
            JOptionPane.showMessageDialog(this, "Thống Kê Thành Công");
            
        }else{
            JOptionPane.showMessageDialog(this, "Thống Kê Không Thành Công");
        }
        
        isFiltered = true;
            filteredData = tkSPDAO.locDateTKSP(sqlDateTruoc, sqlDateSau);
            createChartSP();
    }//GEN-LAST:event_btnTKeActionPerformed

    private void btnTKKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKKHActionPerformed
//        Date selectedDateKHTruoc = txtDateKHTruoc.getDate();
//        Date selectedDateKHSau = txtDateKHSau.getDate();
//        java.sql.Date sqlDateKHTruoc = new java.sql.Date(selectedDateKHTruoc.getTime());
//        java.sql.Date sqlDateKHSau = new java.sql.Date(selectedDateKHSau.getTime());
//        List<ThongKeKH> tKKH = tkKHDAO.locDLLenBDoKH(sqlDateKHTruoc, sqlDateKHSau);
//        if(!tKKH.isEmpty()){
//            model = (DefaultTableModel) tblTKeSP.getModel();
//            model.setRowCount(0);
//            updateTableKH(tKKH);
//            updateDThu (0, model.getRowCount(), txtTongDThu);
//            updateSPNhieuNhat(model.getRowCount(), txtSPBC);
//            JOptionPane.showMessageDialog(this, "Thống Kê Thành Công");
//            
//        }else{
//            JOptionPane.showMessageDialog(this, "Thống Kê Không Thành Công");
//        }
//        
//        isFiltered = true;
//        filteredData = tkSPDAO.locDateTKSP(sqlDateTruoc, sqlDateSau);
//        createChartSP();
    }//GEN-LAST:event_btnTKKHActionPerformed
    
    // btn thống kê Hóa Đơn
    private void btnTKeHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKeHDActionPerformed
        Date selectedDateHDTruoc = txtDateHDTruoc.getDate();
        Date selectedDateHDSau = txtDateHDSau.getDate();
        java.sql.Date sqlDateHDTruoc = new java.sql.Date(selectedDateHDTruoc.getTime());
        java.sql.Date sqlDateHDSau = new java.sql.Date(selectedDateHDSau.getTime());
        List<ThongKeHoaDon> tKHD = tkHDDAO.locDateTKHD(sqlDateHDTruoc, sqlDateHDSau);
        if(!tKHD.isEmpty()){
            model = (DefaultTableModel) tblTKeSP.getModel();
            model.setRowCount(0);
            updateTableHD(tKHD);
            updateDThu (0, model.getRowCount(), txtDThuHD);
            JOptionPane.showMessageDialog(this, "Thống Kê Thành Công");
            
        }else{
            JOptionPane.showMessageDialog(this, "Thống Kê Không Thành Công");
        }
        
        isFiltered = true;
        filteredDataHD = tkHDDAO.locDateTKHD(sqlDateHDTruoc, sqlDateHDSau);
        createChartHD();
    }//GEN-LAST:event_btnTKeHDActionPerformed
    
    // Hàm Xóa các kí tự
    public String replace(String str){
        String strNew = str.replaceAll("VND", "");
        String strReplace = strNew.replaceAll(",", "");
        return strReplace;
    }
    
    public void updateDThu (int totalDThu, int rowCount, JTextField txtUpdate){
        for(int i=0; i< rowCount; i++){
              String str = (String) tblTKeSP.getValueAt(i, 5);
              int so = Integer.parseInt(replace(str));
              totalDThu += so;
          }
        txtUpdate.setText(dcfCurrency.format(totalDThu));
    };

    // Hàm  Tìm Sản Phẩm Bán Chạy Nhất
    public void updateSPNhieuNhat (int rowCount, JTextField txtUpdate){
        int max = (int) tblTKeSP.getValueAt(0, 4);
        String tenSP = new String(tblTKeSP.getValueAt(0, 1).toString());
        for(int i=1; i< rowCount; i++){
              int slBan =  (int) tblTKeSP.getValueAt(i, 4);
              if(slBan > max){
                  max = slBan;
                  tenSP = (String) tblTKeSP.getValueAt(i, 1);
              }
          }    
        txtUpdate.setText(tenSP);
    };
    
    // Hàm Tìm Khách Hàng Mua Nhiều Nhất 
    public void updateKHMuaNhieuNhat(int rowCount){
        int max = (int) tblTKKH.getValueAt(0, 3);
        String tenKH = tblTKKH.getValueAt(0, 1).toString();
        for (int i = 0; i < rowCount; i++) {
            int slMua = (int) tblTKKH.getValueAt(i, 3);
            if(slMua > max){
              max = slMua;
              tenKH = (String) tblTKKH.getValueAt(i, 1);
            }
        }
        txtKHMuaNhieu.setText(tenKH);
    }
    
    // Hàm Update Bảng Hóa Đơn
    private void updateTableHD(List<ThongKeHoaDon> tKHD){
        model = (DefaultTableModel) tblTKHD.getModel();
        model.setRowCount(0);
  
        for (ThongKeHoaDon thongKeHoaDon : tKHD) {
            String maHD = thongKeHoaDon.getMaHD();
            String tenNV = thongKeHoaDon.getTenNV();
            String tenKH = thongKeHoaDon.getTenKH();
            Date ngayTao = thongKeHoaDon.getNgayTao();
            int sLBan = thongKeHoaDon.getSoLBan();
            double dThu = thongKeHoaDon.getdThu();
            model.addRow(new Object[]{maHD, tenNV, tenKH, dateFormat.format(ngayTao), sLBan, dcfCurrency.format(dThu)}); 
            
        }
        txtTongHD.setText(model.getRowCount() + "");
    }
    
    // Hàm Update Bảng Khách Hàng
    private void updateTableKH(List<ThongKeKH> tKKH){
        model = (DefaultTableModel) tblTKKH.getModel();
        model.setRowCount(0);
        for (ThongKeKH thongKeKH : tKKH) {
            String maKH = thongKeKH.getMaKH();
            String tenKH = thongKeKH.getTenKH();
            Boolean gioiTinh = thongKeKH.getGioiTinh();
            int sLMua = thongKeKH.getsLMua();
            Double tTMua = thongKeKH.gettTDaMua();
//            String gioiTinhStr = gioiTinh ? "Nam" : "Nữ"; 
            model.addRow(new Object[]{maKH, tenKH, gioiTinh, sLMua, tTMua});
        }
        System.out.println(tKKH);
        txtTongKH.setText(model.getRowCount() + "");
    }
    
    // Hàm Update Bảng Sản Phẩm
    private void updateTableSP(List<ThongKeSP> tkSPList){
        model = (DefaultTableModel) tblTKeSP.getModel();
        model.setRowCount(0);
        for (ThongKeSP thongKeSP : tkSPList) {
            String maSP = thongKeSP.getMaSP();
            String tenSP = thongKeSP.getTenSP();
            String tenLoaiSP = thongKeSP.getLoaiSP();
            Date ngayTao = thongKeSP.getNgayBan();
            int sLBan = thongKeSP.getsLDaBan();
            double dThu = thongKeSP.getdThu();
            model.addRow(new Object[]{maSP, tenSP, tenLoaiSP, dateFormat.format(ngayTao), sLBan, dcfCurrency.format(dThu)}); 
            }
        // update txtSoLuong SP
        int totalSP = model.getRowCount();
        txtTongSP.setText(String.valueOf(totalSP));
    }
    
    private void createChartSP() {
         Date selectedDateTruoc = txtDateTruoc.getDate();
        Date selectedDateSau = txtDateSau.getDate();
        java.sql.Date sqlDateTruoc = new java.sql.Date(selectedDateTruoc.getTime());
        java.sql.Date sqlDateSau = new java.sql.Date(selectedDateSau.getTime());
  
        String title = "Thống kê Sản Phẩm từ ";
        List<ThongKeSP> dataToChart;
    
        if(isFiltered && sqlDateTruoc != null && sqlDateSau != null){
            title += dateFormat.format(sqlDateTruoc) + " tới " + dateFormat.format(sqlDateSau);
            dataToChart = filteredData;
        } else {
            title = "Thống Kê Sản Phẩm";
            dataToChart = tKSPList;
        }

        // Làm mới dataset trước khi thêm dữ liệu mới
        datasetSP.clear();
        for (ThongKeSP thongKeSP : dataToChart) {
            String tenSP = thongKeSP.getTenSP();
            double dThuSP = thongKeSP.getdThu();
            datasetSP.setValue(dThuSP, "Doanh Thu", tenSP);
        }
        JFreeChart chart = ChartFactory.createBarChart(title, "Sản Phẩm", "Doanh Thu", datasetSP, 
                            PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
            pnBieuDo.setLayout(new BorderLayout());
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(350, 350));

            chartPanel.setDomainZoomable(true);
            chartPanel.setRangeZoomable(true);
            pnBieuDo.removeAll();
            pnBieuDo.add(chartPanel, BorderLayout.CENTER);
            pnBieuDo.revalidate();
            pnBieuDo.repaint();
    }
    
    // Hàm tạo biểu đồ Khách Hàng
    private void createChartKH() {
        Date selectedDateKHTruoc = txtDateKHTruoc.getDate();
        Date selectedDateKHSau = txtDateKHSau.getDate();
        java.sql.Date sqlDateKHTruoc = new java.sql.Date(selectedDateKHTruoc.getTime());
        java.sql.Date sqlDateKHSau = new java.sql.Date(selectedDateKHSau.getTime());
      
        String title = "Thống kê Khách Hàng Từ ";
        List<ThongKeKH> chartKH;
        if(isFiltered && sqlDateKHTruoc != null && sqlDateKHSau != null){
            title += dateFormat.format(sqlDateKHTruoc) + " tới " + dateFormat.format(sqlDateKHSau);
            chartKH = filteredDataKH;
        }
        else{
            title = "Thống Kê Khách Hàng";
            chartKH = tkKHList;
        }
        dataset.clear();
        for (ThongKeKH TKKH : tkKHList) {
            String tenKH = TKKH.getTenKH();
            int sLMua = TKKH.getsLMua();
            dataset.setValue(sLMua, "Số Lượt Mua", tenKH);
        }
        JFreeChart chart = ChartFactory.createBarChart(title, "Khách Hàng", "Số Lượt Mua", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        pnlBDTKKH.setLayout(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(350, 350));

        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        pnlBDTKKH.removeAll();
        pnlBDTKKH.add(chartPanel, BorderLayout.CENTER);
        pnlBDTKKH.revalidate();
        pnlBDTKKH.repaint();
    }
    
    //Hàm tạo biểu đồ Hóa Đơn
    
    private void createChartHD() {
         Date selectedDateHDTruoc = txtDateHDTruoc.getDate();
        Date selectedDateHDSau = txtDateHDSau.getDate();
        java.sql.Date sqlDateHDTruoc = new java.sql.Date(selectedDateHDTruoc.getTime());
        java.sql.Date sqlDateHDSau = new java.sql.Date(selectedDateHDSau.getTime());
  
        String title = "Thống kê Sản Phẩm từ ";
        List<ThongKeHoaDon> dataToChartHD;
    
        if(isFiltered && sqlDateHDTruoc != null && sqlDateHDSau != null){
            title += dateFormat.format(sqlDateHDTruoc) + " tới " + dateFormat.format(sqlDateHDSau);
            dataToChartHD = filteredDataHD;
        } else {
            title = "Thống Kê Sản Phẩm";
            dataToChartHD = tkHDList;
        }
        
        // Làm mới dataset trước khi thêm dữ liệu mới
        datasetHD.clear();
        for (ThongKeHoaDon tKHD : dataToChartHD) {
            Date ngayDatHoaDon  = tKHD.getNgayTao();
            int sLDat = tKHD.getsLHD();
             System.out.println(ngayDatHoaDon + "      " + sLDat);
            datasetHD.setValue(sLDat, "Số Lượng Hóa Đơn", ngayDatHoaDon);
        }

        JFreeChart chart = ChartFactory.createBarChart(title, "Ngày", "Số Lượng Hóa Đơn", datasetHD, 
                            PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
            pnlTKHD.setLayout(new BorderLayout());
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(350, 350));

            chartPanel.setDomainZoomable(true);
            chartPanel.setRangeZoomable(true);
            pnlTKHD.removeAll();
            pnlTKHD.add(chartPanel, BorderLayout.CENTER);
            pnlTKHD.revalidate();
            pnlTKHD.repaint();
    }
   
    
    
    
     private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
     
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = (Font) sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        return cellStyle;
    }
    
    private static CellStyle createStyleForItem(Sheet sheet) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTKKH;
    private javax.swing.JButton btnTKe;
    private javax.swing.JButton btnTKeHD;
    private javax.swing.JButton btnXuatFile1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private com.toedter.calendar.JDateChooser jDateChooser8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JLabel lblTKSP;
    private javax.swing.JPanel pnBieuDo;
    private javax.swing.JPanel pnTKSP;
    private javax.swing.JLabel pnlBDTKKH;
    private javax.swing.JLabel pnlTKHD;
    private javax.swing.JTable tblTKHD;
    private javax.swing.JTable tblTKKH;
    private javax.swing.JTable tblTKeSP;
    private javax.swing.JTextField txtDThuHD;
    private com.toedter.calendar.JDateChooser txtDateHDSau;
    private com.toedter.calendar.JDateChooser txtDateHDTruoc;
    private com.toedter.calendar.JDateChooser txtDateKHSau;
    private com.toedter.calendar.JDateChooser txtDateKHTruoc;
    private com.toedter.calendar.JDateChooser txtDateSau;
    private com.toedter.calendar.JDateChooser txtDateTruoc;
    private javax.swing.JTextField txtKHMuaNhieu;
    private javax.swing.JTextField txtSPBC;
    private javax.swing.JTextField txtTongDThu;
    private javax.swing.JTextField txtTongHD;
    private javax.swing.JTextField txtTongKH;
    private javax.swing.JTextField txtTongMua;
    private javax.swing.JTextField txtTongSP;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
