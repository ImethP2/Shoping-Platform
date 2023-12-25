package com.ManagerSide;

import java.io.*;

/**
 * TODO
 * do a QA
 *
 */
public class TextFileDBHandler {
    public static void createFile() throws IOException{
        File file = new File("productList.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

    }

    /**
     * This method saves the product list to the text file
     * @throws IOException
     * @see Product
     * @see Clothing
     * @see Electronics
     */
    public static void saveProductList() throws IOException {
        deleteFile();
        createFile();
        sorting();
        File file = new File("productList.txt");
        if (file.exists()) {
            for (Product product : WestminsterShoppingManager.products) {
                if (product != null){
                    if (product.getProdID().contains("CL") || product.getProdID().contains("EL")){
                        product.save();
                    }
                }
            }
        }else{
            createFile();
        }
    }
    public static void loadProductList() throws IOException {
        int product_count = 0;

        File file = new File("productList.txt");

        if (file.exists()){

            BufferedReader prodLineReader = new BufferedReader(new FileReader("productList.txt"));

            try {

                product_count = countProduct();

                for (int i =0; i<product_count ;i++) {

                    String prodLine = prodLineReader.readLine();
                    String[] productArray = prodLine.split("-");

                    String prodID = productArray[0];

                    String prodName = productArray[1];

                    int prodQuantity = Integer.parseInt(productArray[2]);

                    double prodPrice = Double.parseDouble(productArray[3]);

                    if (prodID.contains("CL")) {
                        //WestminsterShoppingManager.clothCount++;
                        clothingObject(productArray, prodID, prodName, prodQuantity, prodPrice, i);
                    } else if (prodID.contains("EL")) {
                        //WestminsterShoppingManager.electCount++;
                        electronicObject(productArray, prodID, prodName, prodQuantity, prodPrice, i);
                    } else {
                        System.err.println("Text File load Error");
                    }

                }

                /*System.out.println(WestminsterShoppingManager.clothCount);
                System.out.println(WestminsterShoppingManager.electCount);
                System.out.println(WestminsterShoppingManager.productCount);*/
                prodLineReader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            System.err.println("File Not Found");
        }
    }
    public static void electronicObject(String[] productArray, String prodID, String prodName, int prodQuantity, double prodPrice, int productCount) {
        String prodBrandName = productArray[4];
        String warrantyPeriod = productArray[5];
        Electronics electronics = new Electronics(prodID, prodName, prodQuantity, prodPrice, prodBrandName, warrantyPeriod);
        WestminsterShoppingManager.products[productCount] = electronics;
//        WestminsterShoppingManager.electCount++;electCount

    }
    public static void clothingObject(String[] productArray, String prodID, String prodName, int prodQuantity, double prodPrice, int productCount) {
        String prodSize = productArray[4];
        String prodColor = productArray[5];
        Clothing clothing = new Clothing(prodID, prodName, prodQuantity, prodPrice, prodSize, prodColor);
        WestminsterShoppingManager.products[productCount] = clothing;
//        WestminsterShoppingManager.clothCount++;
    }
    public static int countProduct() throws IOException {
        File file = new File("productList.txt");
        WestminsterShoppingManager.clothCount=0;
        WestminsterShoppingManager.electCount=0;

        int product_count = 0;
        String prodLine;
        if (file.exists()) {
            try {
                BufferedReader prodLineReader = new BufferedReader(new FileReader("productList.txt"));
                while ((prodLine = prodLineReader.readLine()) != null) {
                    String[] productArray = prodLine.split("-");
                    String prodID = productArray[0];
                    if (prodID.contains("CL")) {
                        WestminsterShoppingManager.clothCount++;
                    } else if (prodID.contains("EL")) {
                        WestminsterShoppingManager.electCount++;
                    }

                }
                product_count = WestminsterShoppingManager.electCount + WestminsterShoppingManager.clothCount;
                WestminsterShoppingManager.productCount = product_count;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            createFile();
        }
        return product_count;
    }
    public static void addProduct( Electronics electronics) throws IOException {
        //TODO add product to text file
        File file = new File("productList.txt");
        if (file.exists()){
            try{
                boolean list = checkProduct();
                if (list){
                    electronics.save();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            createFile();
            addProduct(electronics);
        }
    }
    public static void addProduct( Clothing clothing) throws IOException {
        //TODO add product to text file
        File file = new File("productList.txt");
        if (file.exists()){
            try{
                boolean list = checkProduct();
                if (list) {
                    clothing.save();
                    //not sure.
                    //check it
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            createFile();
            addProduct(clothing);
        }
    }
    public static void deleteFile(){
        //TODO delete product from text file
        File file = new File("productList.txt");
        if (file.exists()){
            file.delete();
            System.out.println("File Deleted Successfully");
        }
    }

    public static void UpdateTempFile(){
        //TODO create a temp file
        File file1 = new File("productList.txt");
        File file2 = new File("tempProductList.txt");

        file1.delete();
        file2.renameTo(file1);
    }
    public static void updateClothes(Clothing clothing, String ClotheProdID){
        //TODO update quantity of a record in text file
        /**
         * 1. Read the file
         * 2. Find the record
         * 3. Delete the record
         * 4. Add the record with updated quantity
         **/
        /**
         * since java cant delete lines from a text file update the following line to a new line
         * then create a temp file and add the records to it without the new lines
         * delete the previous file
         * rename the temp file to original name
         **/
        File file = new File("productList.txt");
        File file2 = new File("tempProductList.txt");
        if (file.exists()){
            try {
                BufferedReader prodLineReader = new BufferedReader(new FileReader(file));
                BufferedWriter prodLineWriter = new BufferedWriter(new FileWriter(file2));

                for (int i = 0; i<countProduct(); i++){
                    String prodLine = prodLineReader.readLine();
                    String[] productArray = prodLine.split("-");
                    String prodID = productArray[0];
                    if (prodID.equals(ClotheProdID)){
                        //prodLineWriter.write(clothing.getProdID() + "-" + clothing.getProdName() + "-" + clothing.getProdQuantity() + "-" + clothing.getProdPrice() + "-" + clothing.getSize() + "-" + clothing.getColor());
                        //prodLineWriter.newLine();
                        clothing.save();
                    }else{
                        prodLineWriter.write(prodLine);
                        prodLineWriter.newLine();
                    }

                }
                prodLineReader.close();
                prodLineWriter.close();

                UpdateTempFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.err.println("File Not Found");
        }
    }
    public static void updateElectronics(Electronics electronics, String ElectProdID){
        //TODO update quantity of a record in text file
        File file = new File("productList.txt");
        File file2 = new File("tempProductList.txt");
        if (file.exists()){
            try {
                BufferedReader prodLineReader = new BufferedReader(new FileReader(file));
                BufferedWriter prodLineWriter = new BufferedWriter(new FileWriter(file2));

                for (int i = 0; i<countProduct(); i++){
                    String prodLine = prodLineReader.readLine();
                    String[] productArray = prodLine.split("-");
                    String prodID = productArray[0];
                    if (prodID.equals(ElectProdID)){
                        //prodLineWriter.write(electronics.getProdID() + "-" + electronics.getProdName() + "-" + electronics.getProdQuantity() + "-" + electronics.getProdPrice() + "-" + electronics.getBrandName() + "-" + electronics.getWarrantyDate());
                        //prodLineWriter.newLine();
                        electronics.save();
                    }else{
                        prodLineWriter.write(prodLine);
                        prodLineWriter.newLine();
                    }

                }
                prodLineReader.close();
                prodLineWriter.close();

                UpdateTempFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.err.println("File Not Found");
        }
    }

    public static void printProductList(){
        //TODO print product list from text file

    }
    public static boolean checkProduct(){
        boolean listFull = true;
        //TODO check if the system has more than 50 products
        File file = new File("productList.txt");
        if (file.exists()){
            try {
                if (countProduct() >= 50){
                    System.err.println("The system has more than 50 products");
                    listFull = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listFull;
    }
    public static void sorting(){
        int n = WestminsterShoppingManager.productCount;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (WestminsterShoppingManager.products[j].compare(WestminsterShoppingManager.products[j + 1])) {
                    Product temp = WestminsterShoppingManager.products[j];
                    WestminsterShoppingManager.products[j] = WestminsterShoppingManager.products[j + 1];
                    WestminsterShoppingManager.products[j + 1] = temp;
                    swapped = true;
                }
            }


            // If no two elements were swapped in the inner loop, the array is already sorted
            if (!swapped) {
                break;
            }
        }
/*
        for (int i = 0; i < n; i++) {
            System.out.println(n);
            System.out.println(WestminsterShoppingManager.products[i].getProdID());
        }
*/


    }
}
