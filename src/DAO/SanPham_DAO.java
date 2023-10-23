/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectSQL;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;
import Entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ROG
 */
public class SanPham_DAO {
    ArrayList<SanPham> dsSanPham;
    
    public SanPham_DAO(){
        dsSanPham= new ArrayList<SanPham>();
    }
    
    public ArrayList<SanPham> docTuBang(){
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sql = "SELECT * FROM SanPham";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString("maSP");
                String tenKH = rs.getString("tenSP");
                double giaBan = rs.getInt("giaBan");
                double giaNhap = rs.getInt("giaNhap");
                int soLuong = rs.getInt("soLuong");
                String maloaiSP = rs.getString("maloaiSP");
                String maNhaCungCap = rs.getString("maNhaCungCap");
                String hinhAnh = rs.getString("hinhAnh");
                // You may need to retrieve other attributes as well
                SanPham sanPham = new SanPham(maKH, tenKH, new LoaiSanPham(maloaiSP), giaNhap, giaBan, soLuong, new NhaCungCap(maNhaCungCap), hinhAnh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsSanPham;
    }
    
    public boolean create(SanPham sanPham) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO SanPham (maSP,tenSP,giaBan,giaNhap,soLuong,maloaiSP,maNhaCungCap,hinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?,?)");
            stmt.setString(1, sanPham.getMaSP());
            stmt.setString(2, sanPham.getTenSP());
            stmt.setDouble(3, sanPham.getGiaBan());
            stmt.setDouble(4, sanPham.getGiaNhap());
            stmt.setInt(5, sanPham.getSoLuong());
            stmt.setString(6, sanPham.getLoaiSP().getMaLoai());
            stmt.setString(7, sanPham.getNhaCungCap().getMaNhaCungCap());
            stmt.setString(8, sanPham.getHinhAnh());
            // Set other attributes as needed
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
    public boolean update(SanPham sanPham) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE SanPham SET tenSP = ?, giaBan = ?, giaNhap = ?, soLuong = ?, maloaiSP = ?, hinhAnh = ? WHERE maSP = ?");
            stmt.setString(1, sanPham.getTenSP());
            stmt.setDouble(2, sanPham.getGiaBan());
            stmt.setDouble(3, sanPham.getGiaNhap());
            stmt.setInt(4, sanPham.getSoLuong());
            stmt.setString(5, sanPham.getLoaiSP().getMaLoai());
            stmt.setString(6, sanPham.getNhaCungCap().getMaNhaCungCap());
            stmt.setString(7, sanPham.getHinhAnh());
            stmt.setString(8, sanPham.getMaSP());
            // Update other attributes as needed
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    public boolean delete(String maSP) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM SanPham WHERE maSP = ?");
            stmt.setString(1, maSP);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
