package com.example.shop.model;

import java.io.Serializable;

public class new_product implements Serializable {
    int ID;
    String tenSP;
    String hinhAnhSP;
    String moTa;
    int IDLoaiSanPham;
    int giaSP;
    int ID_HangSX;

    public new_product() {
    }

    public new_product(int ID, String tenSP, String hinhAnhSP, String moTa, int IDLoaiSanPham, int giaSP, int ID_HangSX) {
        this.ID = ID;
        this.tenSP = tenSP;
        this.hinhAnhSP = hinhAnhSP;
        this.moTa = moTa;
        this.IDLoaiSanPham = IDLoaiSanPham;
        this.giaSP = giaSP;
        this.ID_HangSX = ID_HangSX;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getIDLoaiSanPham() {
        return IDLoaiSanPham;
    }

    public void setIDLoaiSanPham(int IDLoaiSanPham) {
        this.IDLoaiSanPham = IDLoaiSanPham;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getID_HangSX() {
        return ID_HangSX;
    }

    public void setID_HangSX(int ID_HangSX) {
        this.ID_HangSX = ID_HangSX;
    }
}
