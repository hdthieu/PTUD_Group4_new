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

    private ArrayList<TaiKhoan> dsTaiKhoan;

    public TaiKhoan_DAO() {
        dsTaiKhoan = new ArrayList<>();
    }

    public ArrayList<TaiKhoan> docTuBang() {
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sql = "SELECT * FROM TaiKhoan";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("maNhanVien");
                String tenNhanVien = rs.getString("tenNhanVien");
                String tenTaiKhoan = rs.getString("tenTaiKhoan");
                String matKhau = rs.getString("matKhau");
                String quyenTruyCap = rs.getString("quyenTruyCap");

                TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, tenNhanVien, tenTaiKhoan, matKhau, quyenTruyCap);
                dsTaiKhoan.add(taiKhoan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTaiKhoan;
    }

    public boolean create(TaiKhoan taiKhoan) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO TaiKhoan (maNhanVien, tenNhanVien, tenTaiKhoan, matKhau, quyenTruyCap) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, taiKhoan.getMaNhanVien());
            stmt.setString(2, taiKhoan.getTenNhanVien());
            stmt.setString(3, taiKhoan.getTenTaiKhoan());
            stmt.setString(4, taiKhoan.getMatKhau());
            stmt.setString(5, taiKhoan.getQuyenTruyCap());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean update(TaiKhoan taiKhoan) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE TaiKhoan SET tenNhanVien = ?, tenTaiKhoan = ?, matKhau = ?, quyenTruyCap = ? WHERE maNhanVien = ?");
            stmt.setString(1, taiKhoan.getTenNhanVien());
            stmt.setString(2, taiKhoan.getTenTaiKhoan());
            stmt.setString(3, taiKhoan.getMatKhau());
            stmt.setString(4, taiKhoan.getQuyenTruyCap());
            stmt.setString(5, taiKhoan.getMaNhanVien());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean delete(String tenTaiKhoan) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM TaiKhoan WHERE maNhanVien = ?");
            stmt.setString(1, tenTaiKhoan);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

//    public boolean checkTrungTenTaiKhoan(String tenTaiKhoan) {
//        Connection con = ConnectSQL.getInstance().getConnection();
//        PreparedStatement stmt = null;
//        try {
//            String sql = "SELECT * FROM TaiKhoan WHERE tenTaiKhoan = ?";
//            stmt = con.prepareStatement(sql);
//            stmt.setString(1, tenTaiKhoan);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
