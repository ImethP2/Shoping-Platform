package com.ManagerSide;

import java.util.Date;
 abstract class Product {
    private String prodID;
    private String prodName;
    private int prodQuantity;
    private double prodPrice;



    public Product(String prodID, String prodName, int prodQuantity, double prodPrice) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.prodQuantity = prodQuantity;
        this.prodPrice = prodPrice;
    }


    public String getProdID() {
        return prodID;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(int prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

     abstract void save();
 }
