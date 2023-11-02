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
    private boolean gioiTinh;
    private Date ngaySinh;
    private int SDT;
    private String diaChi;
    private String chucVu;
    private byte[] hinhAnh;;

    public NhanVien(String maNhanVien, String tenNhanVien, boolean gioiTinh,Date ngaySinh,int SDT, String diaChi, String chucVu, byte[] hinhAnh ) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.hinhAnh = hinhAnh;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public int getSDT() {
        return SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", SDT=" + SDT + ", diaChi=" + diaChi + ", chucVu=" + chucVu + ", hinhAnh=" + hinhAnh + '}';
    }
    

   
    
}
