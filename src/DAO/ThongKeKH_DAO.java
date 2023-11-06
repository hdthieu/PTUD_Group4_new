/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectSQL;
import Entity.NhanVien;
import Entity.ThongKeKH;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;

/**
 *
 * @author TriHieu
 */
public class ThongKeKH_DAO {
//    ArrayList<ThongKeKH> dsTKKH;
//
//    public ThongKeKH_DAO() {
//        dsTKKH = new ArrayList<ThongKeKH>();
//    }
    private Boolean convertGenderToBoolean(String genderString) {
        if ("Nam".equals(genderString)) {
            return true; 
        }
        return false;
      
    }
    public List<ThongKeKH> docTuBang() {
        List<ThongKeKH> tKSP = new ArrayList<ThongKeKH>();
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sql = "SELECT kh.maKH, kh.tenKH, kh.gioiTinh,  COUNT(DISTINCT hd.maHoaDon) as SoLanMua, SUM(cthd.donGiaBan * cthd.soLuong) as TongTienMua " +
            "FROM KhachHang kh " +
            "JOIN HoaDon hd ON hd.maKH = kh.maKH " +
            "JOIN ChiTietHoaDon cthd ON cthd.maHoaDon = hd.maHoaDon " +
            "GROUP BY kh.maKH, kh.tenKH, kh.gioiTinh ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maKH = rs.getString("maKH"); 
                String tenKH = rs.getString("tenKH"); 
                Boolean gioiTinh = convertGenderToBoolean(rs.getString("gioiTinh"));
                int sLMua = rs.getInt("SoLanMua");
                Double tongTienMua = rs.getDouble("TongTienMua");
                tKSP.add(new ThongKeKH(maKH, tenKH, gioiTinh, sLMua, tongTienMua));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tKSP;
    }
    
    public List<ThongKeKH> locDLLenBDoKH(){
        Connection con = ConnectSQL.getInstance().getConnection();
        List<ThongKeKH> tKSP = new ArrayList<ThongKeKH>();
        try {
            String sqlChart =  "SELECT kh.tenKH, COUNT(DISTINCT hd.maHoaDon) as SoLanMua " +
                                "FROM KhachHang kh " +
                                "JOIN HoaDon hd ON hd.maKH = kh.maKH " +
                                "GROUP BY kh.tenKH ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlChart);
            while(rs.next()){
                String tenKH = rs.getString("tenKH");
                int SLuotMua = rs.getInt("SoLanMua");
                tKSP.add(new ThongKeKH(tenKH, SLuotMua));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tKSP;
    }
    
    // Hàm để lọc ngày tháng năm thống kê sản phẩm 
    public List<ThongKeKH> locDateTKKH(Date fromDate, Date toDate) {
    Connection con = ConnectSQL.getInstance().getConnection();
    List<ThongKeKH> tKSP = new ArrayList<ThongKeKH>();
    String sQLLoc = "SELECT kh.maKH, kh.tenKH, kh.gioiTinh,  COUNT(DISTINCT hd.maHoaDon) as SoLanMua, SUM(cthd.donGiaBan * cthd.soLuong) as TongTienMua " +
            "FROM KhachHang kh " +
            "JOIN HoaDon hd ON hd.maKH = kh.maKH " +
            "JOIN ChiTietHoaDon cthd ON cthd.maHoaDon = hd.maHoaDon " +
            "WHERE hd.ngayTao BETWEEN ? AND ? " +
            "GROUP BY kh.maKH, kh.tenKH, kh.gioiTinh ";

    try (PreparedStatement stmt = con.prepareStatement(sQLLoc)) {
        // Set parameters for prepared statement
        stmt.setDate(1, new java.sql.Date(fromDate.getTime()));
        stmt.setDate(2, new java.sql.Date(toDate.getTime()));

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String maKH = rs.getString("maKH"); 
                String tenKH = rs.getString("tenKH"); 
                Boolean gioiTinh = convertGenderToBoolean(rs.getString("gioiTinh"));
                int sLMua = rs.getInt("SoLanMua");
                Double tongTienMua = rs.getDouble("TongTienMua");
                tKSP.add(new ThongKeKH(maKH, tenKH, gioiTinh, sLMua, tongTienMua));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return tKSP;
    };  
}
