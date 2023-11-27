import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class WestminsterShoppingManager {
    public static int productCount = 0;
    public static Product[] products = new Product[50];
    public static void addProduct() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("What type of product would you like to add?");
        System.out.println("(Clothing or Electronics)");
        String prodType = input.nextLine().toLowerCase();
        if (prodType.equals("clothing")) {
            Clothing clothing = clothingDetails();
            products[productCount] = clothing;
            productCount++;
        } else if (prodType.equals("electronics")) {
            Electronics electronics = electronicDetails();
            products[productCount] = electronics;
            productCount++;
        } else {
            System.err.println("Invalid input");
        }


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
    public static Clothing clothingDetails() throws ParseException {
        Scanner input = new Scanner(System.in);
        Scanner inputINT = new Scanner(System.in);
        String prodID = "CL" + productCount;
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
        String prodID = "EL" + productCount;
        System.out.println("Enter the product name:");
        String prodName = input.nextLine();
        System.out.println("Enter the product quantity:");
        int prodQuantity = inputINT.nextInt();
        System.out.println("Enter the product price:");
        double prodPrice = inputINT.nextDouble();
        System.out.println("Enter the product brand name:");
        String prodBrandName = input.nextLine();
        System.out.println("Enter the product warranty date: (dd/mm/yyyy)");
        String warrantyDate = input.nextLine();
        Date prodWarrantyDate = new SimpleDateFormat("dd/MM/yyyy").parse(warrantyDate);
        Electronics electronics = new Electronics(prodID, prodName, prodQuantity, prodPrice, prodBrandName, prodWarrantyDate);
        return electronics;
    }
    public static void managerMenu() throws ParseException {
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
        Scanner inputINT = new Scanner(System.in);
        int option = inputINT.nextInt();
        switch (option) {
            case 1 -> {
                addProduct();

            }
            case 2 -> {
                System.out.println("What type of product would you like to delete?");
                String prodType = input.nextLine().toLowerCase();
                if (prodType.equals("clothing")||prodType.equals("electronics")) {
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
                            System.out.println("Product deleted");
                        }
                    }
                } else {
                    System.err.println("Invalid input");
                }
            }case 3 -> {
            }
            case 4 -> {
            }
            case 5 -> {
            }
            case 6 -> {
                System.exit(0);
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
            do {
                managerMenu();
            } while (true);
        } else {
            System.out.println("Invalid input");
        }

    }
}
