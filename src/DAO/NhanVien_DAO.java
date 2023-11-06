package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Connection.ConnectSQL;
import Entity.NhaCungCap;

import Entity.NhanVien;
import java.util.Date;
import java.util.List;

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
                        rs.getString("gioiTinh"),
                        rs.getString("ngaySinh"),
                        rs.getString("soDienThoai"),
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
            stmt.setString(3, p.getGioiTinh());
            stmt.setString(4, p.getNgaySinh());
            stmt.setString(5, p.getSDT());
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
            stmt.setString(3, p.getGioiTinh());
            stmt.setString(4, p.getNgaySinh());
            stmt.setString(5, p.getSDT());
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

     public List<NhanVien> searchEmployee(String maNhanVien, String tenNhanVien, String gioiTinh, String ngaySinh, String sdt, String diaChi, String chucVu) throws SQLException {
        List<NhanVien> danhSachNhanVien = new ArrayList<>();
        Connection con = ConnectSQL.getInstance().getConnection();

        String sql = "SELECT * FROM NhanVien WHERE 1=1";
        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (maNhanVien != null && !maNhanVien.isEmpty()) {
            conditions.add("maNhanVien = ?");
            params.add(maNhanVien);
        }
        if (tenNhanVien != null && !tenNhanVien.isEmpty()) {
            conditions.add("tenNhanVien = ?");
            params.add(tenNhanVien);
        }
        if (sdt != null && !sdt.isEmpty()) {
            conditions.add("soDienThoai = ?");
            params.add(sdt);
        }
        if (diaChi != null && !diaChi.isEmpty()) {
            conditions.add("diaChi = ?");
            params.add(diaChi);
        }
        if (ngaySinh != null) {
            conditions.add("ngaySinh = ?");
            params.add(ngaySinh);
        }
        if (chucVu != null && !chucVu.isEmpty()) {
            conditions.add("chucVu = ?");
            params.add(chucVu);
        }

        conditions.add("gioiTinh = ?");
        params.add(gioiTinh);

        if (!conditions.isEmpty()) {
            sql += " AND " + String.join(" AND ", conditions);
        }

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String ma = rs.getString("maNhanVien");
                String ten = rs.getString("tenNhanVien");
                String gioiTinhResult = rs.getString("gioiTinh");
                String soDienThoai = rs.getString("soDienThoai");
                String resultDiaChi = rs.getString("diaChi");
                String ngaySinhResult = rs.getString("ngaySinh");
                String chucVuResult = rs.getString("chucVu");
                
                NhanVien nhanVien = new NhanVien(ma, ten, gioiTinhResult, ngaySinhResult, soDienThoai, resultDiaChi, chucVuResult);
                danhSachNhanVien.add(nhanVien);
            }
        }

        return danhSachNhanVien;
    }

}


