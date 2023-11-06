
package GUI;

import DAO.LoaiSanPham_DAO;
import DAO.NhaCungCap_DAO;
import DAO.SanPham_DAO;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;
import Entity.SanPham;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TriHieu
 */
public class GUI_QuanLySP extends javax.swing.JPanel {

    /**
     * Creates new form GUI_QuanLySP
     */
    SanPham_DAO dsSP = new SanPham_DAO();
    DefaultTableModel dataModel;
    File fileAnhSP;
    public GUI_QuanLySP() {
        initComponents();
<<<<<<< HEAD
        
=======
        String[] sanPham ={"Mã Sản Phẩm","Tên Sản Phẩm","Loại Sản Phẩm","Số Lượng","Giá Nhập","Giá Bán","Nhà Cung Cấp","Hình Ảnh"};
        dataModel =new DefaultTableModel(sanPham,0);
        tblSanPham.setModel(dataModel);
        Connection.ConnectSQL.getInstance().connect();
        loadLoaiSP();
        loadNCC();
        updateTable();
>>>>>>> 78513f62ecc32600449ee16a105d0fc5a625d264
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlNorth = new javax.swing.JPanel();
        lblMaSP = new javax.swing.JLabel();
        lblLoaiSP = new javax.swing.JLabel();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        txtTenSP = new javax.swing.JTextField();
        lblTenSP = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        lblGiaNhap = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        lblGiaBan = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        cboLoaiSP = new javax.swing.JComboBox<>();
        lblNhaCungCap = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnHinhAnh = new javax.swing.JButton();
        lblHinhAnhSP = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();
        lblQuanLySanPham = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        lblMaSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaSP.setText("Mã Sản Phẩm");

        lblLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblLoaiSP.setText("Loại Sản Phẩm");

        lblTenSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenSP.setText("Tên Sản Phẩm");

        lblGiaNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblGiaNhap.setText("Giá Nhập");

        lblGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblGiaBan.setText("Giá Bán");

        lblNhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNhaCungCap.setText("Nhà Cung Cấp");

        lblSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoLuong.setText("Số Lượng");

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_change.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_remove.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnHinhAnh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHinhAnh.setText("Chọn ảnh");
        btnHinhAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHinhAnhActionPerformed(evt);
            }
        });

        lblHinhAnhSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHinhAnhSP.setText("Hình ảnh Sản Phẩm");

        lblHinhAnh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblQuanLySanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblQuanLySanPham.setText("Quản Lý Sản Phẩm");

        javax.swing.GroupLayout pnlNorthLayout = new javax.swing.GroupLayout(pnlNorth);
        pnlNorth.setLayout(pnlNorthLayout);
        pnlNorthLayout.setHorizontalGroup(
            pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNorthLayout.createSequentialGroup()
                .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNorthLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNhaCungCap)
                            .addComponent(lblTenSP)
                            .addComponent(lblMaSP)
                            .addComponent(lblLoaiSP)
                            .addComponent(lblSoLuong)
                            .addComponent(lblGiaNhap)
                            .addComponent(lblGiaBan))
                        .addGap(77, 77, 77)
                        .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cboLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSoLuong))
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(194, 194, 194)
                        .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNorthLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblQuanLySanPham)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlNorthLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnHinhAnh))
                    .addGroup(pnlNorthLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(lblHinhAnhSP)))
                .addGap(112, 112, 112))
            .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNorthLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlNorthLayout.setVerticalGroup(
            pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNorthLayout.createSequentialGroup()
                .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNorthLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaSP)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSP)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLoaiSP)
                            .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSoLuong)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGiaNhap)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGiaBan)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNhaCungCap)
                            .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNorthLayout.createSequentialGroup()
                        .addComponent(lblQuanLySanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem)
                        .addGap(27, 27, 27)
                        .addComponent(btnXoa)
                        .addGap(27, 27, 27)
                        .addComponent(btnSua)
                        .addGap(28, 28, 28)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(pnlNorthLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblHinhAnhSP)
                .addGap(18, 18, 18)
                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnHinhAnh)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(pnlNorthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNorthLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Số lượng", "Giá nhập", "Giá bán", "Nhà Cung Cấp", "Hình Ảnh"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNorth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlNorth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try {
            SanPham spThem = themSPFromTextFile();
            // Call a DAO method to add the supplier to the database and update the table.
            if (dsSP.create(spThem)) {
                
                //String anh = fileAnhSP.getName();
                Object[] rowData = {
                    spThem.getMaSP(),
                    spThem.getTenSP(),
                    spThem.getLoaiSP().getMaLoai(),
                    spThem.getSoLuong()+"",
                    spThem.getGiaNhap()+"",
                    spThem.getGiaBan()+"",
                    spThem.getNhaCungCap().getMaNhaCungCap(),
                    //anh
                    spThem.getHinhAnh()
                    // Add other fields.
                    
                };
                dataModel.addRow(rowData);
                JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                xoaRong();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblSanPham.getSelectedRow();
        if (row >= 0) {
            String khXoa = tblSanPham.getValueAt(row, 0).toString();
            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khách hàng này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION && dsSP.delete(khXoa)) {
                dataModel.removeRow(row);
                JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                xoaRong();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = tblSanPham.getSelectedRow();
        if (row >= 0) {
            SanPham spCapNhap = suaSPFromTextFile();
            if (dsSP.update(spCapNhap)) { // Replace 'supplierDAO' with your actual supplier DAO instance.
                tblSanPham.setValueAt(spCapNhap.getTenSP(), row, 1);
                tblSanPham.setValueAt(spCapNhap.getLoaiSP().getMaLoai(), row, 2);
                tblSanPham.setValueAt(spCapNhap.getSoLuong()+"", row, 3);
                tblSanPham.setValueAt(spCapNhap.getGiaNhap()+"", row, 4);
                tblSanPham.setValueAt(spCapNhap.getGiaBan()+"", row, 5);
                tblSanPham.setValueAt(spCapNhap.getNhaCungCap().getMaNhaCungCap(), row, 6);
                //String anh = fileAnhSP.getName();
                tblSanPham.setValueAt(spCapNhap.getHinhAnh(), row, 7);
                JOptionPane.showMessageDialog(null, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                xoaRong();
            }
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        xoaRong();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnHinhAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHinhAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new MyFileChooser("image/SanPham/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            lblHinhAnh.setIcon(getAnhSP(fileAnhSP.getPath()));
        }
    }//GEN-LAST:event_btnHinhAnhActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        int row = tblSanPham.getSelectedRow();
        txtMaSP.setText(tblSanPham.getValueAt(row, 0).toString());
        txtTenSP.setText(tblSanPham.getValueAt(row, 1).toString());
        cboLoaiSP.setSelectedItem((tblSanPham.getValueAt(row, 2).toString()));
        txtSoLuong.setText(tblSanPham.getValueAt(row, 3).toString());
        txtGiaNhap.setText(tblSanPham.getValueAt(row, 4).toString());
        txtGiaBan.setText(tblSanPham.getValueAt(row, 5).toString());
        cboNhaCungCap.setSelectedItem((tblSanPham.getValueAt(row, 6).toString()));
        loadAnh("image/SanPham/" + tblSanPham.getValueAt(row, 7).toString());
        
    }//GEN-LAST:event_tblSanPhamMouseClicked
    private void loadLoaiSP(){
        LoaiSanPham_DAO loai = new LoaiSanPham_DAO();
        List<LoaiSanPham> list = loai.docTuBang();
        for (LoaiSanPham loaiSP : list) {
            cboLoaiSP.addItem(loaiSP.getMaLoai());
        }
    }
    private void loadNCC(){
        NhaCungCap_DAO ncc = new NhaCungCap_DAO();
        List<NhaCungCap> list = ncc.docTuBang();
        for (NhaCungCap nhaCC : list) {
            cboNhaCungCap.addItem(nhaCC.getMaNhaCungCap());
        }
    }
    private void updateTable(){
        SanPham_DAO ds = new SanPham_DAO();
        List<SanPham> list = ds.docTuBang();
        for (SanPham sp : list){
            String [] rowData={sp.getMaSP(),sp.getTenSP(),sp.getLoaiSP().getMaLoai(),sp.getSoLuong()+"",sp.getGiaNhap()+"",sp.getGiaBan()+"",sp.getNhaCungCap().getMaNhaCungCap()+"",sp.getHinhAnh()};
                dataModel.addRow(rowData);
        }
         tblSanPham.setModel(dataModel);
    }
    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("image/SanPham/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSP = new File(src);
        } catch (IOException e) {
            fileAnhSP = new File("imgs/anhthe/avatar.jpg");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }
    private void loadAnh(String anh) {
        lblHinhAnh.setIcon(getAnhSP(anh));
    }
    private void luuFileAnh() {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(fileAnhSP.getPath());
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "png", new File("image/SanPham/" + fileAnhSP.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }
    private SanPham themSPFromTextFile(){
        SanPham_DAO ds = new SanPham_DAO();
        List<SanPham> list = ds.docTuBang();
        String ma= phatSinhMaKH(list);
        String ten=txtTenSP.getText();
        int soLuong=Integer.parseInt(txtSoLuong.getText());
        double giaBan=Double.parseDouble(txtGiaBan.getText());
        double giaNhap=Double.parseDouble(txtGiaNhap.getText());
        String loai = cboLoaiSP.getSelectedItem().toString();
        String ncc = cboNhaCungCap.getSelectedItem().toString();
        String anh = fileAnhSP.getName();
        SanPham sp = new SanPham(ma, ten, new LoaiSanPham(loai), giaNhap, giaBan, soLuong, new NhaCungCap(ncc), anh);
        return sp;
    }
    private SanPham suaSPFromTextFile(){
        String ma= txtMaSP.getText();
        String ten=txtTenSP.getText();
        int soLuong=Integer.parseInt(txtSoLuong.getText());
        double giaBan=Double.parseDouble(txtGiaBan.getText());
        double giaNhap=Double.parseDouble(txtGiaNhap.getText());
        String loai = cboLoaiSP.getSelectedItem().toString();
        String ncc = cboNhaCungCap.getSelectedItem().toString();
        String anh = fileAnhSP.getName();
        SanPham sp = new SanPham(ma, ten, new LoaiSanPham(loai), giaNhap, giaBan, soLuong, new NhaCungCap(ncc), anh);
        return sp;
    }
    public static String phatSinhMaKH(List<SanPham> sp) {
        int maxCode = 0;
        for (SanPham sanPham : sp) {
            String maSanPham = sanPham.getMaSP();
            int code = Integer.parseInt(maSanPham.substring(2));
            if (code > maxCode) {
                maxCode = code;
            }
        }
        // Tạo mã khách hàng mới bằng cách tăng số lên 1 đơn vị
        maxCode++;
        if (maxCode < 1000) {
        String newCode = "SP" + String.format("%03d", maxCode);
        return newCode;
    } else if (maxCode < 10000) {
        String newCode = "SP" + String.format("%04d", maxCode);
        return newCode;
    } else {
        // Xử lý khi mã vượt quá 9999
        JOptionPane.showMessageDialog(null, "Mã số đã đạt tối đa, cần cập nhật thêm", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    }
    private void xoaRong(){
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtSoLuong.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        loadAnh("");
        cboLoaiSP.setSelectedIndex(0);
        cboNhaCungCap.setSelectedIndex(0);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHinhAnh;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaNhap;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblHinhAnhSP;
    private javax.swing.JLabel lblLoaiSP;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblNhaCungCap;
    private javax.swing.JLabel lblQuanLySanPham;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNorth;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
