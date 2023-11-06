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
public class ThongKeHoaDon {
    private String maHD;
    private String tenNV;
    private String tenKH;
    private Date ngayTao;
    private double dThu;
    private int soLBan;
    private int sLHD;
    public ThongKeHoaDon(String maHD, String tenNV, String tenKH, Date ngayTao, double dThu, int soLBan) {
        this.maHD = maHD;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.ngayTao = ngayTao;
        this.dThu = dThu;
        this.soLBan = soLBan;
    }

    public ThongKeHoaDon(Date ngayTao, int soLBan) {
        this.ngayTao = ngayTao;
        this.soLBan = soLBan;
    }

    public ThongKeHoaDon(String maHD, String tenNV, String tenKH, Date ngayTao, double dThu, int soLBan, int sLHD) {
        this.maHD = maHD;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.ngayTao = ngayTao;
        this.dThu = dThu;
        this.soLBan = soLBan;
        this.sLHD = sLHD;
    }

    public int getsLHD() {
        return sLHD;
    }

    public void setsLHD(int sLHD) {
        this.sLHD = sLHD;
    }
    
    public String getMaHD() {
        return maHD;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public double getdThu() {
        return dThu;
    }

    public int getSoLBan() {
        return soLBan;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setdThu(double dThu) {
        this.dThu = dThu;
    }

    public void setSoLBan(int soLBan) {
        this.soLBan = soLBan;
    }
    
    
    
}
