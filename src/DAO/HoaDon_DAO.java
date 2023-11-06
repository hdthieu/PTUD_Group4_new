/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectSQL;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;
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
public class HoaDon_DAO {
    ArrayList<HoaDon> dsHoaDon;
    
    public HoaDon_DAO(){
        dsHoaDon= new ArrayList<HoaDon>();
    }
    public ArrayList<HoaDon> getListHoaDon() {
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sql = "SELECT * FROM HoaDon";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("maHoaDon");
                java.sql.Date sqlDate = rs.getDate("ngayTao"); // Lấy giá trị kiểu java.sql.Date từ ResultSet
                LocalDate ngayTao = sqlDate.toLocalDate(); // Chuyển đổi sang kiểu java.time.LocalDate
                String maKH = rs.getString("maKH");
                String maNV = rs.getString("maNhanVien");
                
                HoaDon hd = new HoaDon(maHD, ngayTao, new KhachHang(maKH), new NhanVien(maNV));
                dsHoaDon.add(hd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dsHoaDon;
    }
    
    public boolean themHD(HoaDon hd) {
        Connection con = ConnectSQL.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO HoaDon (maHoaDon,ngayTao,maKH,maNhanVien) VALUES (?, ?, ?, ?)");
            stmt.setString(1, hd.getMaHD());
            stmt.setDate(2, java.sql.Date.valueOf(hd.getNgayTao()));
            stmt.setString(3, hd.getMaKh().getMaKH());
            stmt.setString(4, hd.getMaNV().getMaNhanVien());
            // Set other attributes as needed
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
