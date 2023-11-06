/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectSQL;
import Entity.KhachHang;
import Entity.LoaiSanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ROG
 */
public class LoaiSanPham_DAO {
    ArrayList<LoaiSanPham> dsLoaiSP;
    
    public LoaiSanPham_DAO(){
        dsLoaiSP= new ArrayList<LoaiSanPham>();
    }
    public ArrayList<LoaiSanPham> docTuBang(){
        try {
            Connection con = ConnectSQL.getInstance().getConnection();
            String sql = "SELECT * FROM LoaiSP";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maLoai = rs.getString("maLoai");
                String tenLoai = rs.getString("tenLoai");
                LoaiSanPham loai = new LoaiSanPham(maLoai, tenLoai);
                dsLoaiSP.add(loai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsLoaiSP;
    }
}
