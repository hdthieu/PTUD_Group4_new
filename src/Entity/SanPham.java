/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author ROG
 */
public class SanPham {
    private String maSP, tenSP, hinhAnh;
    private LoaiSanPham loaiSP;
    private double giaNhap, giaBan;
    private int soLuong;
    private NhaCungCap nhaCungCap;

    public SanPham(String maSP, String tenSP, LoaiSanPham loaiSP, double giaNhap, double giaBan, int soLuong, NhaCungCap nhaCungCap, String hinhAnh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.nhaCungCap = nhaCungCap;
        this.hinhAnh = hinhAnh;
    }

    public SanPham(String maSP) {
        this.maSP = maSP;
    }
    public SanPham(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public LoaiSanPham getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(LoaiSanPham loaiSP) {
        this.loaiSP = loaiSP;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.maSP);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SanPham other = (SanPham) obj;
        return Objects.equals(this.maSP, other.maSP);
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", hinhAnh=" + hinhAnh + ", loaiSP=" + loaiSP + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", soLuong=" + soLuong + ", nhaCungCap=" + nhaCungCap + '}';
    }

    
}
