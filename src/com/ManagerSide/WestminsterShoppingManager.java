package com.ManagerSide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    public static int electCount = 0;
    public static int clothCount = 0;
    public static int productCount = electCount + clothCount;
    public static Product[] products = new Product[50];

    public static String warrantyUpdate() {
        String warrantyPeriod = "00";
        Scanner input = new Scanner(System.in);
        Scanner inputINT = new Scanner(System.in); /*This is to avoid the error that occurs when you enter a string instead of an integer*/
        System.out.println("What kind of a warranty does this product have?");
        System.out.println("""
                1. Life time warranty
                2. Limited warranty
                3. No warranty""");
        try {
            int warrantyType = inputINT.nextInt();
            if (warrantyType > 0 && warrantyType < 4) {
                if (warrantyType == 1) {
                    warrantyPeriod = "life time";
                } else if (warrantyType == 2) {
                    System.out.println("Enter the warranty period: (mm/yy)");
                    warrantyPeriod = input.nextLine();
                    String[] warrantyPeriodArray = warrantyPeriod.split("/");
                    int warrantyPeriodMonth = Integer.parseInt(warrantyPeriodArray[0]);
                    int warrantyPeriodYear = Integer.parseInt(warrantyPeriodArray[1]);
                    if (warrantyPeriodMonth >= 0 && warrantyPeriodMonth <= 12 && warrantyPeriodYear >= 0 && warrantyPeriodYear < 100) {
                        warrantyPeriod = String.valueOf(warrantyPeriodMonth + warrantyPeriodYear * 12);
                    }
                } else if (warrantyType == 3) {
                    System.out.println("This product has no warranty.");
                }
            } else {
                System.err.println("Please enter a valid option.");
                warrantyPeriod = warrantyUpdate();
            }
        } catch (Exception e) {
            System.err.println("Please enter a valid option.");
            warrantyPeriod = warrantyUpdate();
        }
        return warrantyPeriod;
    }

    public static Clothing clothingDetails() throws ParseException {
        Scanner input = new Scanner(System.in);
        Scanner inputINT = new Scanner(System.in);

        String prodID = clothCount+1 + "CL";

        System.out.println("Enter the product name:");
        String prodName = input.nextLine();

        System.out.println("Enter the product quantity:");
        int prodQuantity = inputINT.nextInt();

        System.out.println("Enter the product price:");
        double prodPrice = inputINT.nextDouble();

        System.out.println("Enter the product size:");
        String prodSize = input.nextLine();

        System.out.println("Enter the product color:");
        String prodColor = input.nextLine();

        Clothing clothing = new Clothing(prodID, prodName, prodQuantity, prodPrice, prodSize, prodColor);

        return clothing;
    }

    public static Electronics electronicDetails() throws ParseException {
        Scanner input = new Scanner(System.in);
        Scanner inputINT = new Scanner(System.in);

        String prodID = electCount + 1 + "EL";

        System.out.println("Enter the product name:");
        String prodName = input.nextLine();

        System.out.println("Enter the product quantity:");
        int prodQuantity = inputINT.nextInt();

        System.out.println("Enter the product price:");
        double prodPrice = inputINT.nextDouble();

        System.out.println("Enter the product brand name:");
        String prodBrandName = input.nextLine();

        String warrantyPeriod = warrantyUpdate();

        Electronics electronics = new Electronics(prodID, prodName, prodQuantity, prodPrice, prodBrandName, warrantyPeriod);

        return electronics;
    }

    public void addProduct() throws ParseException, IOException {
        //products should need to add to the null indexes in the array
        //search for null indexes nd if not print not enough storage
        //when a product gets deleted assign its position to the next product
        Scanner inputINT = new Scanner(System.in);

        System.out.println("What type of product would you like to add?");
        System.out.println("1.Clothing\n2.Electronics");
        int prodType = inputINT.nextInt();
        if (prodType == 1) {
            Clothing clothing = clothingDetails();
            for (int i = 0; i < 50; i++) {
                try {
                    if (products[i] == null) {
                        products[i] = clothing;
                        clothCount++;
                        TextFileDBHandler.addProduct(clothing);
                        break;
                    }
                } catch (Exception e) {
                    System.err.println("The product list is full. Please delete a data queue to add this data row.");
                    try {
                        managerMenu();
                    } catch (ParseException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        } else if (prodType == 2) {
            Electronics electronics = electronicDetails();
            for (int i = 0; i < 50; i++) {
                try {
                    if (products[i] == null) {
                        products[i] = electronics;
                        electCount++;
                        TextFileDBHandler.addProduct(electronics);
                        break;
                    }
                } catch (Exception e) {
                    System.err.println("The product list is full. Please delete a data queue to add this data row.");
                    try {
                        managerMenu();
                    } catch (ParseException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        } else {
            System.err.println("Invalid input");
        }

    }

    public void deleteProduct() {
        Scanner input = new Scanner(System.in);
        //Scanner inputINT = new Scanner(System.in);
        System.out.println("What type of product would you like to delete?");
        String prodType = input.nextLine().toLowerCase();
        if (prodType.equals("clothing") || prodType.equals("electronics")) {
            if (prodType.equals("clothing")) {
                for (Product product : products) {
                    if (product instanceof Clothing) {
                        System.out.println(product.getProdID() + " - " + product.getProdName());
                    }
                }
            } else if (prodType.equals("electronics")) {
                for (Product product : products) {
                    if (product instanceof Electronics) {
                        System.out.println(product.getProdID() + " - " + product.getProdName());
                    }
                }
            }
            System.out.println("Enter the product ID:");
            String prodID = input.nextLine();
            for (int i = 0; i < productCount; i++) {
                if (products[i].getProdID().equals(prodID)) {
                    products[i] = null;
                    productCount--;
                    System.out.println("Product deleted");
                }
            }
        } else {
            System.err.println("Invalid input");
        }
    }

    /**
     * This method is used to update the quantity of the product
     *
     * @return the quantity of the product
     */
    public int quantity() {
        //return the quantity
        int Quantity = 0;
        Scanner inputINT = new Scanner(System.in);

        System.out.println("What do you want to do to the quantity of the product?");
        System.out.println("""
                1. Add to stock
                2. Remove from stock
                """);
        try {
            int option = inputINT.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("How much stock that you want to add?");
                    int addStock = inputINT.nextInt();
                    Quantity = Quantity + addStock;
                    return Quantity;
                }
                case 2 -> {
                    System.out.println("How much stock that you want to reduce?");
                    int deductStock = inputINT.nextInt();
                    Quantity -= deductStock;
                    return Quantity;
                }
                default -> throw new IllegalStateException("Unexpected value: " + option);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Double price() {
        //update the price
        // just ask the price
        //return the price
        Scanner inputINT = new Scanner(System.in);
        System.out.println("What is the new price of the product?");
        Double Price = inputINT.nextDouble();
        return Price;
    }

    public void updateProduct() {
        Scanner input = new Scanner(System.in);
        Scanner inputINT = new Scanner(System.in);
        System.out.println("What type of product you want to update?");
        System.out.println("(Clothing or Electronics)");
        String prodType = input.nextLine().toLowerCase();
        if (prodType.equals("clothing")) {
            System.out.println("what do you want to update in a clothing product?");
            System.out.println("""
                    1. Quantity
                    2. Price
                    """);
            try {
                int option = inputINT.nextInt();
                for (Product product : products) {
                    if (product != null && product instanceof Clothing) {
                        System.out.println(product.getProdID() + " - " + product.getProdName() + " - " + product.getProdQuantity() + " - " + product.getProdPrice());
                        System.out.println("Enter the productID");
                        String prodID = input.nextLine().toUpperCase();
                        for (Product clothes : products) {
                            if (clothes != null) {
                                //check for the project id and update the requested part of the object
                                if (Objects.equals(clothes.getProdID(), prodID)) {
                                    switch (option) {
                                        case 1 -> {
                                            //int Quantity = quantity();
                                            //quantity(Quantity);
                                            clothes.setProdQuantity(quantity());
                                            TextFileDBHandler.updateClothes((Clothing) clothes, prodID);

                                        }
                                        case 2 -> {
                                            Double Price = 0.00;
                                            price();
                                            clothes.setProdPrice(Price);
                                            TextFileDBHandler.updateClothes((Clothing) clothes, prodID);
                                        }
                                        default -> throw new IllegalStateException("Unexpected value: " + option);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("enter a valid input");
            }

        } else if (prodType.equals("electronics")) {
            System.out.println("what do you want to update in a electronic product?");
            System.out.println("""
                    1. Quantity
                    2. Price
                    3. Warranty Period
                    """);
            try {
                int option = inputINT.nextInt();
                for (Product product : products) {
                    if (product != null && product instanceof Electronics) {
                        System.out.println(product.getProdID() + " - " + product.getProdName() + " - " + product.getProdQuantity() + " - " + product.getProdPrice());
                        System.out.println("Enter the productID");
                        String prodID = input.nextLine();
                        for (Product electronics : products) {
                            if (electronics != null) {
                                //check for the project id and update the requested part of the object
                                if (electronics.getProdID() == prodID) {
                                    switch (option) {
                                        case 1 -> {
                                            //int Quantity = 0;
                                            quantity();
                                            electronics.setProdQuantity(quantity());
                                            TextFileDBHandler.updateElectronics((Electronics) electronics, prodID);

                                        }
                                        case 2 -> {
                                            electronics.setProdPrice(price());
                                            TextFileDBHandler.updateElectronics((Electronics) electronics, prodID);

                                        }
                                        case 3 -> {
                                            ((Electronics) electronics).setWarrantyDate(warrantyUpdate());
                                            TextFileDBHandler.updateElectronics((Electronics) electronics, prodID);


                                        }
                                        default -> throw new IllegalStateException("Unexpected value: " + option);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("enter a valid input");
            }

        } else {
            System.err.println("Invalid input");
        }
    }

    public void printProductList() {
        for (Product product : products) {
            if (product != null) {
                System.out.println(product);
            }
        }
    }

    public void sortProductList() {
        // TODO implement here
    }

    //TODO: find why does this not print the first line (0EL)
    //it only prints the last line
    //fix print***
/*
    public static Product[] electronicObject(String[] productArray, String prodID, String prodName, int prodQuantity, double prodPrice, int productCount) {
        String prodBrandName = productArray[4];
        String warrantyPeriod = productArray[5];
        Electronics electronics = new Electronics(prodID, prodName, prodQuantity, prodPrice, prodBrandName, warrantyPeriod);
        products[productCount] = electronics;
        return products;
    }
    public static Product[] clothingObject(String[] productArray, String prodID, String prodName, int prodQuantity, double prodPrice, int productCount) {
        String prodSize = productArray[4];
        String prodColor = productArray[5];
        Clothing clothing = new Clothing(prodID, prodName, prodQuantity, prodPrice, prodSize, prodColor);
        products[productCount] = clothing;
        return products;
    }
*/

    public void saveProductList() {

    }

    public void loadProductList() throws IOException {
        // TODO implement here
        // Reading the person and ticket data
        BufferedReader line_person = new BufferedReader(new FileReader("productList.txt"));

        int product_count = 0;

        try {
            File Product = new File("productList.txt");
            Scanner prodScanner = new Scanner(Product);
            while (prodScanner.hasNextLine()) {
                prodScanner.nextLine();
                product_count++;
            }
/*            for (int i =0; i<product_count ;i++){
                String prodLine = line_person.readLine();
                String[] productArray = prodLine.split("-");
                String prodID = productArray[0];
                String prodName = productArray[1];
                int prodQuantity = Integer.parseInt(productArray[2]);
                //String prodPrice = productArray[3];
                double prodPrice = Double.parseDouble(productArray[3]);
                if (prodID.contains("CL")){
                    clothingObject(productArray, prodID, prodName, prodQuantity, prodPrice, product_count);
                } else if (prodID.contains("EL")) {
                    electronicObject(productArray, prodID, prodName, prodQuantity, prodPrice, product_count);
                }
                else{
                    System.err.println("Invalid input");
                }
                //setting_person_object(person_id, name, surname, email, full_cost, Customers);
            }*/

            // close scanner
            //prodScanner.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

/*        for (int i =0; i<product_count ;i++){
            String prodLine = line_person.readLine();
            String[] productArray = prodLine.split("-");
            String prodID = productArray[0];
            String prodName = productArray[1];
            int prodQuantity = Integer.parseInt(productArray[2]);
            //String prodPrice = productArray[3];
            double prodPrice = Double.parseDouble(productArray[3]);
            if (prodID.contains("CL")){
                clothingObject(productArray, prodID, prodName, prodQuantity, prodPrice, product_count);
            } else if (prodID.contains("EL")) {
                electronicObject(productArray, prodID, prodName, prodQuantity, prodPrice, product_count);
            }
            else{
                System.err.println("Invalid input");
            }
            //setting_person_object(person_id, name, surname, email, full_cost, Customers);
        }*/
        //person_id=product_count;
        line_person.close();
        for (Product product : products) {
            if (product != null) {
                System.out.println(product);
            }
        }
    }

    public void managerMenu() throws ParseException, IOException {
        System.out.println("""

                Please select an option:
                1. Add a product
                2. Update a product
                3. Delete a product
                4. Print the product list
                5. Save the product list
                6. Load the product list
                7. Exit""");
        Scanner input = new Scanner(System.in);
        Scanner inputINT = new Scanner(System.in);
        int option = inputINT.nextInt();
        switch (option) {
            case 1 -> {
                addProduct();

            }
            case 2 -> {
                updateProduct();
            }
            case 3 -> {
                deleteProduct();

            }
            case 4 -> {
                printProductList();
            }
            case 5 -> {
                TextFileDBHandler.saveProductList();
            }
            case 6 -> {
                if (products.length == TextFileDBHandler.countProduct()){
                    System.err.println("DataBase is already loaded to the software");
                }else {
                    TextFileDBHandler.loadProductList();
                }
            }
            case 7 -> {
                System.exit(0);
            }
            default -> throw new IllegalStateException("Unexpected value: " + option);
        }

    }
//    public void clientMenu() {
//        // TODO implement here
//    }

    /*public static void main(String[] args) throws ParseException, IOException {
        Scanner input_main = new Scanner(System.in);
        System.out.println("Are you a customer or an admin?");
        String userType = input_main.nextLine().toLowerCase();
        if (userType.equals("customer")) {
            //clientMenu();
        } else if (userType.equals("admin")) {
            do {
                managerMenu();
            } while (true);
        } else {
            System.out.println("Invalid input");
        }

    }*/
}
