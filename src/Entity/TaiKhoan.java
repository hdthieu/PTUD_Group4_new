/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author dinhh
 */
public class TaiKhoan {
   private String tenTaiKhoan;
   private String matKhau;
   private String quyenTruyCap;
   private String maNhanVien;

   
   public TaiKhoan(){
       super();
   }
    public TaiKhoan(String tenTaiKhoan, String matKhau, String quyenTruyCap, String maNhanVien) {
        super();
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.quyenTruyCap = quyenTruyCap;
        this.maNhanVien = maNhanVien;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

   
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyenTruyCap() {
        return quyenTruyCap;
    }

    public void setQuyenTruyCap(String quyenTruyCap) {
        this.quyenTruyCap = quyenTruyCap;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
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
        final TaiKhoan other = (TaiKhoan) obj;
        if (!Objects.equals(this.tenTaiKhoan, other.tenTaiKhoan)) {
            return false;
        }
        if (!Objects.equals(this.matKhau, other.matKhau)) {
            return false;
        }
        if (!Objects.equals(this.quyenTruyCap, other.quyenTruyCap)) {
            return false;
        }
        return Objects.equals(this.maNhanVien, other.maNhanVien);
    }
}
