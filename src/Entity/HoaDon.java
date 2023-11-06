/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author ROG
 */
public class HoaDon {
    private String maHD;
    private LocalDate ngayTao;
    private KhachHang maKh;
    private NhanVien maNV;

    public HoaDon(String maHD, LocalDate ngayTao, KhachHang maKh, NhanVien maNV) {
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.maKh = maKh;
        this.maNV = maNV;
    }

    public HoaDon() {
    }

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public KhachHang getMaKh() {
        return maKh;
    }

    public void setMaKh(KhachHang maKh) {
        this.maKh = maKh;
    }

    public NhanVien getMaNV() {
        return maNV;
    }

    public void setMaNV(NhanVien maNV) {
        this.maNV = maNV;
    }


    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", ngayTao=" + ngayTao + ", maKh=" + maKh + ", maNV=" + maNV + '}';
    }
    
    
}
