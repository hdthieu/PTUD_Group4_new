package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Connection.ConnectSQL;

import Entity.NhanVien;
import java.util.Date;
public class NhanVien_DAO {

    ArrayList<NhanVien> dsNhanVien;

    public NhanVien_DAO() {
        dsNhanVien = new ArrayList<NhanVien>();
    }

    public ArrayList<NhanVien> docTuBang() {
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sql = "SELECT * FROM NhanVien";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien"); 
                String tenNV = rs.getString("tenNhanVien"); 
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String diaChi = rs.getString("diaChi");
                Integer SDT = rs.getInt("SDT");
                Date ngaySinh = rs.getDate("ngaySinh"); 
                String hinhAnh = rs.getString("hinhAnh");
                NhanVien nhanVien = new NhanVien(maNV, tenNV, gioiTinh, SDT,ngaySinh,diaChi, hinhAnh);
                dsNhanVien.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public static boolean create(NhanVien p) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO NhanVien (MaNV, TenNV, DiaChi, SDT, GioiTinh, NgaySinh, HinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, p.getMaNhanVien());
            stmt.setString(2, p.getTenNhanVien());
            stmt.setString(3, p.getDiaChi());
            stmt.setInt(4, p.getSDT());
            stmt.setBoolean(5, p.getGioiTinh());
            stmt.setDate(6, new java.sql.Date(p.getNgaySinh().getTime()));
            stmt.setString(7, p.getHinhAnh());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean update(NhanVien p) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE NhanVien SET TenNV = ?, DiaChi = ?, SDT = ?, GioiTinh = ?, NgaySinh = ?, HinhAnh = ? WHERE MaNV = ?");
            stmt.setString(1, p.getTenNhanVien());
            stmt.setString(2, p.getDiaChi());
            stmt.setInt(3, p.getSDT());
            stmt.setBoolean(4, p.getGioiTinh());
            stmt.setDate(5, new java.sql.Date(p.getNgaySinh().getTime()));
            stmt.setString(6, p.getHinhAnh());
            stmt.setString(7, p.getMaNhanVien());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean delete(String maNV) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM NhanVien WHERE MaNV = ?");
            stmt.setString(1, maNV);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean checkTrungMaNV(String maNV) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
