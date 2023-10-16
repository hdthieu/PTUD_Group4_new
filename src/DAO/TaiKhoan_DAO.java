/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Connection.ConnectSQL;
import Entity.NhanVien;
import Entity.TaiKhoan;
import java.sql.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dinhh
 */
public class TaiKhoan_DAO {
    NhanVien_DAO nvDao= new NhanVien_DAO();
    ConnectSQL cn = new ConnectSQL();
    Connection conn;
    ResultSet rs;

    public ArrayList<TaiKhoan> Listtk() {
        ArrayList<TaiKhoan> Listtk = new ArrayList<>();
        try {
            conn = cn.getConnection();
            String sql = "select *from TaiKhoan";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            TaiKhoan listtk;
            while (rs.next()) {
                listtk = new TaiKhoan(rs.getString("tenTaiKhoan"), rs.getString("matKhau"), rs.getString("quyenTruyCap"), rs.getString("maNhanVien"));
                Listtk.add(listtk);
            }
        } catch (Exception e) {
        }
        return Listtk;
    }
        public ArrayList<TaiKhoan> taikhoanDN(String user) {
        ArrayList<TaiKhoan> Listtk = new ArrayList<>();
        try {
            conn = cn.getConnection();
            String sql = "select *from TaiKhoan where TenTaiKhoan=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery(sql);
            TaiKhoan listtk;
            while (rs.next()) {
                listtk = new TaiKhoan(rs.getString("tenTaiKhoan"), rs.getString("matKhau"), rs.getString("quyenTruyCap"), rs.getString("maNhanVien"));
                Listtk.add(listtk);
            }
        } catch (Exception e) {
        }
        return Listtk;
    }

    public void themnv(String maNV, String tenTK, String matKhau, String quyenTC, String tenNV, JTable table) throws ClassNotFoundException {
        conn = cn.getConnection();

        String sql = "insert into TaiKhoan(MaCongNhan,TenTaiKhoan,MatKhau,QuyenTruyCap,TenNhanVien) values(?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, maNV);
            pst.setString(2, tenTK);
            pst.setString(3, matKhau);

            pst.setString(4, quyenTC);

            pst.setString(5, tenNV);

            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            show_TK(table);
            JOptionPane.showMessageDialog(null, "them thanh cong");
        } catch (SQLException ex) {

        }

    }
    public void show_DSNV(JTable table) {
        ArrayList<NhanVien> list = nvDao.docTuBang();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getTenNhanVien();
    
            
            model.addRow(row);
        }
    }
     public void show_TK(JTable table) {
        ArrayList<TaiKhoan> list = Listtk();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getTenTaiKhoan();
            row[1] = list.get(i).getMatKhau();
             row[2] = list.get(i).getQuyenTruyCap();
            row[3] = list.get(i).getMaNhanVien();
            model.addRow(row);
        }
    }
    public void xoaTK(String tentk, JTable table) throws ClassNotFoundException{
          conn = cn.getConnection();

        String sql = "DELETE FROM TaiKhoan WHERE TenTaiKhoan=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tentk);
            MyDialog dlg = new MyDialog("Bạn có muốn xóa không", MyDialog.OK_OPTION);
            if (dlg.getAction() == MyDialog.OK_OPTION) {

                pst.executeUpdate();
                new MyDialog("Xóa thành công", MyDialog.SUCCESS_DIALOG);
            }

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            show_TK(table);
        } catch (SQLException ex) {
           
        }
    }
    public void suaTK(String maNV,String tenTaiKhoan,String matKhau,String tenNV,String quyen,JTable table) throws ClassNotFoundException{
        conn = cn.getConnection();
        String sql = "UPDATE TaiKhoan SET MaCongNhan=?,TenTaiKhoan=?,MatKhau=?,QuyenTruyCap=?,TenNhanVien=? where TenTaiKhoan =? ";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, maNV);
            pst.setString(2, tenTaiKhoan);
            pst.setString(3, matKhau);
            pst.setString(4, quyen);
            pst.setString(5, tenNV);
            pst.setString(6, tenTaiKhoan);
            

            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            show_TK(table);
            new MyDialog("Sửa thành công", MyDialog.SUCCESS_DIALOG);

        } catch (SQLException ex) {
         
        }
    }

    private static class MyDialog {

        private static boolean OK_OPTION;
        private static boolean SUCCESS_DIALOG;

        public MyDialog() {
        }

        private MyDialog(String bạn_có_muốn_xóa_không, boolean OK_OPTION) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private boolean getAction() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
    
}

