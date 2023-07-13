package com.example.project2.Model;


import java.sql.Time;
import java.util.Date;

public class TranLuyen {
    private int LuyenID;
    private Date NgayThang;
    private  int DoKho;
    private Time ThoiGian;
    private String KetQua;
    private int UserID;


    public TranLuyen(int luyenID, Date ngayThang, int doKho, Time thoiGian, String ketQua, int userID) {
        LuyenID = luyenID;
        NgayThang = ngayThang;
        DoKho = doKho;
        ThoiGian = thoiGian;
        KetQua = ketQua;
        UserID = userID;
    }

    public int getLuyenID() {
        return LuyenID;
    }

    public void setLuyenID(int luyenID) {
        LuyenID = luyenID;
    }

    public Date getNgayThang() {
        return NgayThang;
    }

    public void setNgayThang(Date ngayThang) {
        NgayThang = ngayThang;
    }

    public int getDoKho() {
        return DoKho;
    }

    public void setDoKho(int doKho) {
        DoKho = doKho;
    }

    public Time getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(Time thoiGian) {
        ThoiGian = thoiGian;
    }

    public String getKetQua() {
        return KetQua;
    }

    public void setKetQua(String ketQua) {
        KetQua = ketQua;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }


}

