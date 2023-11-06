
package DAO;

import Connection.ConnectSQL;
import Entity.ChiTietHoaDon;

import java.util.ArrayList;

import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.SanPham;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author ROG
 */
public class ChiTietHoaDon_DAO {
    ArrayList<ChiTietHoaDon> dsCTHoaDon;
    
    public ChiTietHoaDon_DAO(){
        dsCTHoaDon= new ArrayList<ChiTietHoaDon>();
    }
    public ArrayList<ChiTietHoaDon> getListHoaDon() {
        try {
            Connection con = (Connection) ConnectSQL.getInstance().getConnection();
            String sql = "SELECT * FROM ChiTietHoaDon";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("maHoaDon");
                String maSP = rs.getString("maSP");
                int soLuong = rs.getInt("soLuong");
                Double donGia = rs.getDouble("donGiaBan");
                
                ChiTietHoaDon ct = new ChiTietHoaDon(new HoaDon(maHD), new SanPham(maSP), soLuong, soLuong);
                dsCTHoaDon.add(ct);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dsCTHoaDon;
    }
    
    public ArrayList<ChiTietHoaDon> getListHoaDonTheoMaHD(String maHD) {
        Connection con = (Connection) ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM ChiTietHoaDon where maHoaDon=?";
            stmt =con.prepareStatement(sql);
            stmt.setString(1, maHD);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("maHoaDon");
                String maSP = rs.getString("maSP");
                int soLuong = rs.getInt("soLuong");
                Double donGia = rs.getDouble("donGiaBan");
                
                ChiTietHoaDon ct = new ChiTietHoaDon(new HoaDon(maHD), new SanPham(maSP), soLuong, soLuong);
                dsCTHoaDon.add(ct);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dsCTHoaDon;
    }
    
    public boolean themHD(ChiTietHoaDon hd) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO ChiTietHoaDon (maHoaDon,maSP,soLuong,donGiaBan) VALUES (?, ?, ?, ?)");
            stmt.setString(1, hd.getMaHD().getMaHD());
            stmt.setString(2, hd.getMaSP().getMaSP());
            stmt.setInt(3, hd.getSoLuong());
            stmt.setDouble(4, hd.getDonGia());
            // Set other attributes as needed
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
