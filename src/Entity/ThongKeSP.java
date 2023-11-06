/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author TriHieu
 */
public class ThongKeSP {
    private String maSP;
    private String tenSP;
    private String loaiSP;
    private Date ngayBan;
    private double dThu;
    private int sLDaBan;

    public ThongKeSP(String maSP, String tenSP, String loaiSP, Date ngayBan, double dThu, int sLDaBan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.ngayBan = ngayBan;
        this.dThu = dThu;
        this.sLDaBan = sLDaBan;
    }

    public ThongKeSP(String tenSP, double dThu, int sLDaBan) {
        this.tenSP = tenSP;
        this.dThu = dThu;
        this.sLDaBan = sLDaBan;
    }

    public ThongKeSP(Date ngayBan) {
        this.ngayBan = ngayBan;
    }
    
    public ThongKeSP() {
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

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public double getdThu() {
        return dThu;
    }

    public void setdThu(double dThu) {
        this.dThu = dThu;
    }

    public int getsLDaBan() {
        return sLDaBan;
    }

    public void setsLDaBan(int sLDaBan) {
        this.sLDaBan = sLDaBan;
    }
    
}
