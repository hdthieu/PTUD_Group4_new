/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhachHang;
import java.util.ArrayList;
import Connection.ConnectSQL;
import Entity.ThongKeSP;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ROG
 */
public class ThongKeSP_DAO {

    public List<ThongKeSP> docTuBang(){
        List<ThongKeSP> tKSP= new ArrayList<ThongKeSP>();
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sQL = "SELECT sp.maSP, sp.tenSP, cthd.donGiaBan, hd.ngayTao, hd.maHoaDon, SUM(cthd.soLuong) AS soLuongBan, lSP.tenLoai, COUNT(sp.maSP) AS totalSP " +
             "FROM SanPham sp " +
             "JOIN ChiTietHoaDon cthd ON sp.maSP = cthd.maSP " +
             "JOIN LoaiSP lSP ON sp.maloaiSP = lSP.maLoai " + 
             "JOIN HoaDon hd ON hd.maHoaDon = cthd.maHoaDon " + 
             "GROUP BY sp.maSP, sp.tenSP, cthd.donGiaBan, lSP.tenLoai, hd.ngayTao, hd.maHoaDon"; 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sQL);
            while (rs.next()) {
                String maSP = rs.getString("maSP");
                String tenSP = rs.getString("tenSP");
                double giaBan = rs.getDouble("donGiaBan");
                int soLuongBan = rs.getInt("soLuongBan");
                String tenLoaiSP = rs.getString("tenLoai");
                double doanhThu = giaBan * soLuongBan;
                Date ngayBan = rs.getDate("ngayTao");
                tKSP.add(new ThongKeSP(maSP, tenSP, tenLoaiSP, ngayBan, doanhThu, soLuongBan));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();   
        }
        return tKSP;
    }
    
    // Hàm để load biểu đồ của sản phẩm
    public List<ThongKeSP> locDLLenBieuDoSP(){
        Connection con = ConnectSQL.getInstance().getConnection();
        List<ThongKeSP> tKSP = new ArrayList<ThongKeSP>();
        try {
            String sqlChart =  "SELECT sp.tenSP, SUM(cthd.soLuong) AS soLuongBan, SUM(cthd.donGiaBan * cthd.soLuong) AS doanhThu " +
                                "FROM SanPham sp " +
                                "JOIN ChiTietHoaDon cthd ON sp.maSP = cthd.maSP " +
                                "GROUP BY sp.tenSP";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlChart);
            while(rs.next()){
                String tenSP = rs.getString("tenSP");
                int soLuongBan = rs.getInt("soLuongBan");
                double doanhThu = rs.getDouble("doanhThu");
                tKSP.add(new ThongKeSP(tenSP, doanhThu, soLuongBan));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tKSP;
    };      
    
    // Hàm để lọc ngày tháng năm thống kê sản phẩm 
    public List<ThongKeSP> locDateTKSP(Date fromDate, Date toDate) {
    Connection con = ConnectSQL.getInstance().getConnection();
    List<ThongKeSP> tKSP = new ArrayList<ThongKeSP>();
    String sQLLoc = "SELECT sp.maSP, sp.tenSP, lSP.tenLoai, cthd.donGiaBan, hd.ngayTao, hd.maHoaDon, " +
                    "SUM(cthd.soLuong) AS soLuongBan, SUM(cthd.donGiaBan * cthd.soLuong) AS doanhThu " +
                    "FROM SanPham sp " +
                    "JOIN LoaiSP lSP ON sp.maloaiSP = lSP.maLoai " + 
                    "JOIN ChiTietHoaDon cthd ON sp.maSP = cthd.maSP " +
                    "JOIN HoaDon hd ON hd.maHoaDon = cthd.maHoaDon " +
                    "WHERE hd.ngayTao BETWEEN ? AND ? " +
                    "GROUP BY sp.maSP, sp.tenSP, lSP.tenLoai, cthd.donGiaBan, hd.ngayTao, hd.maHoaDon";

    try (PreparedStatement stmt = con.prepareStatement(sQLLoc)) {
        // Set parameters for prepared statement
        stmt.setDate(1, new java.sql.Date(fromDate.getTime()));
        stmt.setDate(2, new java.sql.Date(toDate.getTime()));

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String maSP = rs.getString("maSP"); 
                String tenSP = rs.getString("tenSP");
                String tenLoaiSP = rs.getString("tenLoai");
                double giaBan = rs.getDouble("donGiaBan");
                int soLuongBan = rs.getInt("soLuongBan");
                Date ngayBan = rs.getDate("ngayTao");
                double doanhThu = rs.getDouble("doanhThu"); // Sử dụng trực tiếp giá trị đã tính từ SQL
                tKSP.add(new ThongKeSP(maSP, tenSP, tenLoaiSP, ngayBan, doanhThu, soLuongBan));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return tKSP;
    };  
};
   
   