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
public class NhaCungCap {
    private String maNhaCungCap;
    private String tenNhaCungCap;
    private int sdt;
    private String diaChi;
    

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public int getSdt() {
        return sdt;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, int sdt, String diaChi) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.sdt = sdt;
        this.diaChi = diaChi;
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.maNhaCungCap);
        hash = 37 * hash + Objects.hashCode(this.tenNhaCungCap);
        hash = 37 * hash + Objects.hashCode(this.diaChi);
        hash = 37 * hash + this.sdt;
        return hash;
    }

    @Override
    public String toString() {
        return "NhaCungCap{" + "maNhaCungCap=" + maNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap + ", sdt=" + sdt + ", diaChi=" + diaChi + '}';
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
        final NhaCungCap other = (NhaCungCap) obj;
        if (this.sdt != other.sdt) {
            return false;
        }
        if (!Objects.equals(this.maNhaCungCap, other.maNhaCungCap)) {
            return false;
        }
        if (!Objects.equals(this.tenNhaCungCap, other.tenNhaCungCap)) {
            return false;
        }
        return Objects.equals(this.diaChi, other.diaChi);
    }
    
}
