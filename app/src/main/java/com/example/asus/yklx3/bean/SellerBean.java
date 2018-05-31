package com.example.asus.yklx3.bean;

/**
 * Created by asus on 2018/5/29.
 */

public class SellerBean {
    private String sellerName;
    private String sellerid;
    private boolean selected;//商家是否选中
    private String bg="编辑";

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}

