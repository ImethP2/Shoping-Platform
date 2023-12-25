package com.ManagerSide;

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
    public boolean compare(Product p){
        String thisID = this.getProdID();
        String nextID = p.getProdID();
//        System.out.println(thisID+" , "+nextID);

        String[] thisIDArray = thisID.split("@");
        String[] nextIDArray = nextID.split("@");
        int thisIDInt = Integer.parseInt(thisIDArray[0]);
        int nextIDInt = Integer.parseInt(nextIDArray[0]);

        Boolean result = true;

        if (thisID.contains("CL")&&nextID.contains("EL")){
            result = false;
        } else if (thisID.contains("EL")&&nextID.contains("CL")){
            result = true;
        } else if (thisID.contains("EL")&&nextID.contains("EL")){
            if (thisIDInt>nextIDInt){
                result = true;
            }else if (thisIDInt<nextIDInt){
                result = false;
            }
        } else if (thisID.contains("CL")&&nextID.contains("CL")){
            if (thisIDInt>nextIDInt){
                result = true;
            }else if (thisIDInt<nextIDInt){
                result = false;
            }
        }
        return result;
    }
 }
