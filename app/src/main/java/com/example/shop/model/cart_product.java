package com.example.shop.model;

public class cart_product {
    int ID;
    String tenSP;
    String hinhAnhSP;
    int giaSP;
    int soLuong;

    public cart_product(int ID, String tenSP, String hinhAnhSP, int giaSP, int soLuong) {
        this.ID = ID;
        this.tenSP = tenSP;
        this.hinhAnhSP = hinhAnhSP;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinhAnhSP() {
        return hinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        this.hinhAnhSP = hinhAnhSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
