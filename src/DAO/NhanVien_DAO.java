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
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sql = "SELECT * FROM NhanVien";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(
                        rs.getString("maNhanVien"),
                        rs.getString("tenNhanVien"),
                        rs.getBoolean("gioiTinh"),
                        rs.getDate("ngaySinh"),
                        rs.getInt("soDienThoai"),
                        rs.getString("diaChi"),
                        rs.getString("chucVu"),
                        rs.getBytes("hinhAnh")
                    
                );
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
            stmt = con.prepareStatement("INSERT INTO NhanVien (maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, diaChi, chucVu, hinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, p.getMaNhanVien());
            stmt.setString(2, p.getTenNhanVien());
            stmt.setBoolean(3, p.isGioiTinh());
            stmt.setDate(4, new java.sql.Date(p.getNgaySinh().getTime()));
            stmt.setInt(5, p.getSDT());
            stmt.setString(6, p.getDiaChi());
            stmt.setString(7, p.getChucVu());
            stmt.setBytes(8, p.getHinhAnh());

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
            stmt = con.prepareStatement("UPDATE NhanVien SET tenNhanVien = ?, gioiTinh = ?, ngaySinh = ?, soDienThoai = ?, diaChi = ?, chucVu = ?, hinhAnh = ? where maNhanVien = ?");
            stmt.setString(1, p.getMaNhanVien());
            stmt.setString(2, p.getTenNhanVien());
            stmt.setBoolean(3, p.isGioiTinh());
            stmt.setDate(4, new java.sql.Date(p.getNgaySinh().getTime()));
            stmt.setInt(5, p.getSDT());
            stmt.setString(6, p.getDiaChi());
            stmt.setString(7, p.getChucVu());
            stmt.setBytes(8, p.getHinhAnh());
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
            stmt = con.prepareStatement("DELETE FROM NhanVien WHERE maNhanVien = ?");
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
