/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author TriHieu
 */
public class ThongKeKH {
    private String maKH;
    private String tenKH;
    private Boolean gioiTinh;
    private int sLMua;
    private Double tTDaMua;

    public ThongKeKH(String maKH, String tenKH, Boolean gioiTinh, int sLMua, Double tTDaMua) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.sLMua = sLMua;
        this.tTDaMua = tTDaMua;
    }

    public ThongKeKH(String tenKH, int sLMua) {
        this.tenKH = tenKH;
        this.sLMua = sLMua;
    }

    public ThongKeKH() {
    }
    
    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public int getsLMua() {
        return sLMua;
    }

    public void setsLMua(int sLMua) {
        this.sLMua = sLMua;
    }

    public Double gettTDaMua() {
        return tTDaMua;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void settTDaMua(Double tTDaMua) {
        this.tTDaMua = tTDaMua;
    }
    
    
}
