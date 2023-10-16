/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author dinhh
 */
public class NhanVien {
    private String maNhanVien;
    private String tenNhanVien;
    private String chucVu;

   
    private boolean gioiTinh;
    private int SDT;
    private Date ngaySinh;
    private String diaChi;
    private String hinhAnh;

    public NhanVien(){
        super();
    }
    public NhanVien(String maNhanVien, String tenNhanVien, boolean gioiTinh, int SDT, Date ngaySinh, String diaChi, String hinhAnh) {
        super();
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.SDT = SDT;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.hinhAnh = hinhAnh;
    }
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }
    public String getChucVu() {
        return chucVu;
    }
    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }
    public boolean isGioiTinh() {
        return gioiTinh;
    }
    

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    // Create a getGioiTinh method based on the existing isGioiTinh method
    public boolean getGioiTinh() {
        return isGioiTinh();
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
     public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", gioiTinh=" + gioiTinh + ", SDT=" + SDT + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", hinhAnh=" + hinhAnh + '}';
    }

   
    
}
