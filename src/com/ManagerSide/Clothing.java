package com.ManagerSide;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Clothing extends Product{

    private String size;
    private String color;
    public Clothing(String prodID, String prodName, int prodQuantity, double prodPrice, String size, String color) {
        super(prodID, prodName, prodQuantity, prodPrice);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void save() {
        try {
            FileWriter writer = new FileWriter("productList.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(getProdID() + "-" + getProdName() + "-" + getProdQuantity() + "-" + getProdPrice() + "-" + getSize() + "-" + getColor());
            bufferedWriter.newLine();
            bufferedWriter.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();


        }
    }
    @Override
        public String toString() {
            return "Product ID: " + getProdID() +
                    ", Product Name: " + getProdName() +
                    ", Product Quantity: " + getProdQuantity() +
                    ", Product Price: " + getProdPrice() +
                    ", Size: " + getSize() +
                    ", Color: " + getColor();
        }
 /*       public static void save(){
            try {
                FileWriter writer = new FileWriter("productList.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(getProdID() + "-" + getProdName() + "-" + getProdQuantity() + "-" + getProdPrice() + "-" + getSize() + "-" + getColor());
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
}
