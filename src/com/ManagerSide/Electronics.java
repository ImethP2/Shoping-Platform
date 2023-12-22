package com.ManagerSide;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Electronics extends Product{
    private String brandName;
    private String warrantyDate;


    public Electronics(String prodID, String prodName, int prodQuantity, double prodPrice, String brandName, String warrantyDate) {
        super(prodID, prodName, prodQuantity, prodPrice);
        this.brandName = brandName;
        this.warrantyDate = warrantyDate;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(String warrantyDate) {
        this.warrantyDate = warrantyDate;
    }



    @Override
    public String toString() {
        return "Product ID: " + getProdID() +
                ", Product Name: " + getProdName() +
                ", Product Quantity: " + getProdQuantity() +
                ", Product Price: " + getProdPrice() +
                ", Brand Name: " + getBrandName() +
                ", Warranty Date: " + getWarrantyDate();
    }

    @Override
    void save() {
        try {
            FileWriter writer = new FileWriter("productList.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(getProdID() + "-" + getProdName() + "-" + getProdQuantity() + "-" + getProdPrice() + "-" + getBrandName() + "-" + getWarrantyDate());
            bufferedWriter.newLine();
            bufferedWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
