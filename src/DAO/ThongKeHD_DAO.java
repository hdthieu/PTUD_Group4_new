/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectSQL;
import Entity.ThongKeHoaDon;
import Entity.ThongKeSP;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TriHieu
 */
public class ThongKeHD_DAO {
    public List<ThongKeHoaDon> docTuBang(){
        List<ThongKeHoaDon> tKHD= new ArrayList<ThongKeHoaDon>();
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sQL = "SELECT hd.maHoaDon, hd.ngayTao, nv.tenNhanVien, kh.tenKH, cthd.donGiaBan, SUM(cthd.soLuong) AS soLuongBan " +
             "FROM HoaDon hd " +
             "JOIN NhanVien nv ON nv.maNhanVien = hd.maNhanVien " +
             "JOIN KhachHang kh ON kh.maKH = hd.maKH " + 
             "JOIN ChiTietHoaDon cthd ON cthd.maHoaDon = hd.maHoaDon " +
             "GROUP BY hd.maHoaDon, nv.tenNhanVien, kh.tenKH, cthd.donGiaBan, hd.ngayTao ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sQL);
            while (rs.next()) {
                String maHD = rs.getString("maHoaDon");
                String tenNV = rs.getString("tenNhanVien");
                String tenKH = rs.getString("tenKH");
                int soLuongBan = rs.getInt("soLuongBan");
                double giaBan = rs.getDouble("donGiaBan");
                double doanhThu = giaBan * soLuongBan;
                Date ngayTao = rs.getDate("ngayTao");
                tKHD.add(new ThongKeHoaDon(maHD, tenNV, tenKH, ngayTao, doanhThu, soLuongBan));
            }
        } catch (SQLException e) {
            e.printStackTrace();   
        }
        return tKHD;
    }
    public List<ThongKeHoaDon> locDLieuBDHD(){
        Connection con = ConnectSQL.getInstance().getConnection();
        List<ThongKeHoaDon> tKHD = new ArrayList<ThongKeHoaDon>();
        try {
            String sqlChart = "SELECT hd.ngayTao, COUNT(hd.maHoaDon) AS soLuongHD " +
            " FROM HoaDon hd"  +
            " GROUP BY  hd.ngayTao";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlChart);
            while(rs.next()){
                int soLuongHD = rs.getInt("soLuongHD");
                Date ngayTao = rs.getDate("ngayTao");
                tKHD.add(new ThongKeHoaDon(ngayTao, soLuongHD));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tKHD;
    }
    
    // Hàm để lọc ngày tháng năm thống kê sản phẩm 
    public List<ThongKeHoaDon> locDateTKHD(Date fromDate, Date toDate) {
    Connection con = ConnectSQL.getInstance().getConnection();
    List<ThongKeHoaDon> tKHD = new ArrayList<ThongKeHoaDon>();
    String sQLLoc = "SELECT hd.maHoaDon, hd.ngayTao, nv.tenNhanVien, kh.tenKH, cthd.donGiaBan, SUM(cthd.soLuong) AS soLuongBan, COUNT(hd.maHoaDon) AS soLuongHD " +
             "FROM HoaDon hd " +
             "JOIN NhanVien nv ON nv.maNhanVien = hd.maNhanVien " +
             "JOIN KhachHang kh ON kh.maKH = hd.maKH " + 
             "JOIN ChiTietHoaDon cthd ON cthd.maHoaDon = hd.maHoaDon " +
             "WHERE hd.ngayTao BETWEEN ? AND ? " +
             "GROUP BY hd.maHoaDon, nv.tenNhanVien, kh.tenKH, cthd.donGiaBan, hd.ngayTao ";

    try (PreparedStatement stmt = con.prepareStatement(sQLLoc)) {
        // Set parameters for prepared statement
        stmt.setDate(1, new java.sql.Date(fromDate.getTime()));
        stmt.setDate(2, new java.sql.Date(toDate.getTime()));

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String maHD = rs.getString("maHoaDon");
                String tenNV = rs.getString("tenNhanVien");
                String tenKH = rs.getString("tenKH");
                int soLuongBan = rs.getInt("soLuongBan");
                double giaBan = rs.getDouble("donGiaBan");
                double doanhThu = giaBan * soLuongBan;
                Date ngayTao = rs.getDate("ngayTao");
                int soLHD = rs.getInt("soLuongHD");
                tKHD.add(new ThongKeHoaDon(maHD, tenNV, tenKH, ngayTao, doanhThu, soLuongBan, soLHD));
         
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return tKHD;
    };  
}
