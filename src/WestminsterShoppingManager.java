import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class WestminsterShoppingManager {
    public static void addProduct(Product product) {
        // TODO implement here
    }
    public void deleteProduct(Product product) {
        // TODO implement here
    }
    public void printProductList() {
        // TODO implement here
    }
    public void sortProductList() {
        // TODO implement here
    }
    public void saveProductList() {
        // TODO implement here
    }
    public void loadProductList() {
        // TODO implement here
    }
    public static void checkProdTypeAndAdd(String prodType) throws ParseException {
        Scanner input = new Scanner(System.in);
        if (prodType.equals("clothing") || prodType.equals("electronics")){
            System.out.println("Enter the product ID:");
            String prodID = input.nextLine();
            System.out.println("Enter the product name:");
            String prodName = input.nextLine();
            System.out.println("Enter the product quantity:");
            int prodQuantity = input.nextInt();
            System.out.println("Enter the product price:");
            double prodPrice = input.nextDouble();
            if (prodType.equals("clothing")) {
                System.out.println("Enter the product size:");
                String prodSize = input.nextLine();
                System.out.println("Enter the product color:");
                String prodColor = input.nextLine();
                Clothing clothing = new Clothing(prodID, prodName, prodQuantity, prodPrice, prodSize, prodColor);
                addProduct(clothing);
            } else if (prodType.equals("electronics")) {
                System.out.println("Enter the product brand name:");
                String prodBrandName = input.nextLine();
                System.out.println("Enter the product warranty date: (dd/mm/yyyy)");
                String warrantyDate = input.nextLine();
                Date prodWarrantyDate = new SimpleDateFormat("dd/MM/yyyy").parse(warrantyDate);
                Electronics electronics = new Electronics(prodID, prodName, prodQuantity, prodPrice, prodBrandName, prodWarrantyDate);
                addProduct(electronics);
            }
        }else {
            System.out.println("Invalid input");
        }
    }
    public static void
    managerMenu() throws ParseException {
        System.out.println("""
                Welcome to Westminster Shopping Manager
                Please select an option:
                1. Add a product
                2. Delete a product
                3. Print the product list
                4. Save the product list
                5. Load the product list
                6. Exit""");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        switch (option) {
            case 1 -> {
                System.out.println("What type of product would you like to add?");
                String prodType = input.nextLine().toLowerCase();
                checkProdTypeAndAdd(prodType);

            }
            case 2 -> {
                System.out.println("What type of product would you like to delete?");
                String prodType = input.nextLine().toLowerCase();
                checkProdTypeAndAdd(prodType);
            }
        }
    }
//    public void clientMenu() {
//        // TODO implement here
//    }

    public static void main(String[] args) throws ParseException {
        Scanner input_main = new Scanner(System.in);
        System.out.println("Are you a customer or an admin?");
        String userType = input_main.nextLine().toLowerCase();
        if (userType.equals("customer")) {
            //clientMenu();
        } else if (userType.equals("admin")) {
            managerMenu();
        } else {
            System.out.println("Invalid input");
        }

    }
}
