/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectSQL;
import Entity.NhaCungCap;
import java.beans.Statement;
import java.util.List;

public class NhaCungCap_DAO {

    ArrayList<NhaCungCap> dsNhaCungCap;

    public NhaCungCap_DAO() {
        dsNhaCungCap = new ArrayList<NhaCungCap>();
    }

    public ArrayList<NhaCungCap> docTuBang() {
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sql = "SELECT * FROM NhaCungCap";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maNhaCungCap = rs.getString("maNhaCungCap");
                String tenNhaCungCap = rs.getString("tenNhaCungCap");
                int SDT = rs.getInt("soDienThoai");
                String diaChi = rs.getString("diaChi");
                // You may need to retrieve other attributes as well

                NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap, tenNhaCungCap, SDT, diaChi);
                dsNhaCungCap.add(nhaCungCap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhaCungCap;
    }

    public boolean create(NhaCungCap nhaCungCap) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO NhaCungCap (maNhaCungCap,tenNhaCungCap, soDienThoai, diaChi) VALUES (?, ?, ?, ?)");
            stmt.setString(1, nhaCungCap.getMaNhaCungCap());
            stmt.setString(2, nhaCungCap.getTenNhaCungCap());
            stmt.setInt(3, nhaCungCap.getSdt());
            stmt.setString(4, nhaCungCap.getDiaChi());
            // Set other attributes as needed
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean update(NhaCungCap nhaCungCap) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE NhaCungCap SET tenNhaCungCap = ?, soDienThoai = ?, diaChi = ? WHERE maNhaCungCap = ?");
            stmt.setString(1, nhaCungCap.getTenNhaCungCap());
            stmt.setInt(2, nhaCungCap.getSdt());
            stmt.setString(3, nhaCungCap.getDiaChi());
            stmt.setString(4, nhaCungCap.getMaNhaCungCap());
            // Update other attributes as needed
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean delete(String maNhaCungCap) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM NhaCungCap WHERE maNhaCungCap = ?");
            stmt.setString(1, maNhaCungCap);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<NhaCungCap> getNhaCungCapTheoMaNhaCungCap(String maNhaCungCap) throws SQLException {
        ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<>();
        Connection con = ConnectSQL.getInstance().getConnection();

        String sql = "SELECT * FROM NhaCungCap WHERE maNhaCungCap = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maNhaCungCap);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String ma = rs.getString("maNhaCungCap");
                String ten = rs.getString("tenNhaCungCap");
                int sdt = rs.getInt("soDienThoai");
                String diaChi = rs.getString("diaChi");
                NhaCungCap nhaCungCap = new NhaCungCap(ma, ten, sdt, diaChi);
                dsNhaCungCap.add(nhaCungCap);
            }
        }

        return dsNhaCungCap;
    }

    public ArrayList<NhaCungCap> getNhaCungCapTheoTenNhaCungCap(String tenNhaCungCap) throws SQLException {
        ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<>();
        Connection con = ConnectSQL.getInstance().getConnection();

        String sql = "SELECT * FROM NhaCungCap WHERE tenNhaCungCap = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tenNhaCungCap);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String ma = rs.getString("maNhaCungCap");
                String ten = rs.getString("tenNhaCungCap");
                int sdt = rs.getInt("soDienThoai");
                String diaChi = rs.getString("diaChi");
                NhaCungCap nhaCungCap = new NhaCungCap(ma, ten, sdt, diaChi);
                dsNhaCungCap.add(nhaCungCap);
            }
        }

        return dsNhaCungCap;
    }

    public List<NhaCungCap> searchNhaCungCap(String maNhaCungCap, String tenNhaCungCap, String sdt, String diaChi) throws SQLException {
        List<NhaCungCap> danhSachNhaCungCap = new ArrayList<>();
        Connection con = ConnectSQL.getInstance().getConnection();

        String sql = "SELECT * FROM NhaCungCap WHERE 1=1";

        if (!maNhaCungCap.isEmpty()) {
            sql += " AND maNhaCungCap = '" + maNhaCungCap + "'";
        }
        if (!tenNhaCungCap.isEmpty()) {
            sql += " AND tenNhaCungCap = '" + tenNhaCungCap + "'";
        }
        if (!sdt.isEmpty()) {
            sql += " AND soDienThoai = " + sdt;
        }
        if (!diaChi.isEmpty()) {
            sql += " AND diaChi = '" + diaChi + "'";
        }

        try (java.sql.Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String ma = rs.getString("maNhaCungCap");
                String ten = rs.getString("tenNhaCungCap");
                int soDienThoai = rs.getInt("soDienThoai");
                String resultDiaChi = rs.getString("diaChi");
                NhaCungCap nhaCungCap = new NhaCungCap(ma, ten, soDienThoai, resultDiaChi);
                danhSachNhaCungCap.add(nhaCungCap);
            }
        }

        return danhSachNhaCungCap;
    }

}
