import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws ParseException, IOException {

        ShoppingManager shoppingManager = new WestminsterShoppingManager();

        System.out.println("""
                =================================================
                ==== Welcome to Westminster Shopping Manager ====
                =================================================
                """);
        System.out.println("Do you want to load the previous product list? (Y/N)");
        Scanner input = new Scanner(System.in);
        String load = input.nextLine().toUpperCase();
        if (load.equalsIgnoreCase("Y")){
            shoppingManager.loadProductList();
        }else if (load.equalsIgnoreCase("N")){
            System.out.println("You can add products to the list");
        }else {
            System.err.println("Invalid Input");
        }
    }
}
