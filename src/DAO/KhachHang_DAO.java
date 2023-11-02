/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhachHang;
import java.util.ArrayList;
import Connection.ConnectSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ROG
 */
public class KhachHang_DAO {
    ArrayList<KhachHang> dsKhachHang;
    
    public KhachHang_DAO(){
        dsKhachHang= new ArrayList<KhachHang>();
    }
    public ArrayList<KhachHang> docTuBang(){
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sql = "SELECT * FROM KhachHang";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("tenKH");
                String diaChi = rs.getString("diaChi");
                int SDT = rs.getInt("soDienThoai");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                // You may need to retrieve other attributes as well
                KhachHang khachHang = new KhachHang(maKH, tenKH, diaChi, SDT, gioiTinh);
                dsKhachHang.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhachHang;
    }
    
    public boolean create(KhachHang khachHang) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO KhachHang (maKH,tenKH,diaChi,soDienThoai,gioiTinh) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, khachHang.getMaKH());
            stmt.setString(2, khachHang.getTenKH());
            stmt.setString(3, khachHang.getDiaChi());
            stmt.setInt(4, khachHang.getSoDienThoai());
            stmt.setBoolean(5, khachHang.isGioiTinh());
            // Set other attributes as needed
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean update(KhachHang khachHang) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE KhachHang SET tenKH = ?, diaChi = ?, soDienThoai = ?,gioiTinh = ? WHERE maKH = ?");
            stmt.setString(1, khachHang.getTenKH());
            stmt.setString(2, khachHang.getDiaChi());
            stmt.setInt(3, khachHang.getSoDienThoai());
            stmt.setBoolean(4,khachHang.isGioiTinh());
            stmt.setString(5, khachHang.getMaKH());
            // Update other attributes as needed
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean delete(String maKH) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM KhachHang WHERE maKH = ?");
            stmt.setString(1, maKH);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
