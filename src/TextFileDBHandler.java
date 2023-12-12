import java.io.*;

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
            saveProductList();
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
                        WestminsterShoppingManager.clothingObject(productArray, prodID, prodName, prodQuantity, prodPrice, i);
                    } else if (prodID.contains("EL")) {
                        WestminsterShoppingManager.electronicObject(productArray, prodID, prodName, prodQuantity, prodPrice, i);
                    } else {
                        System.err.println("Text File Save Error");
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static int countProduct() throws IOException {
        File file = new File("productList.txt");

        int product_count = 0;

        if (file.exists()) {
            BufferedReader prodLineReader = new BufferedReader(new FileReader("productList.txt"));
            while (prodLineReader.readLine() != null) {
                product_count++;
            }
        }
        return product_count;
    }
    public static void addProduct(){
        //TODO add product to text file
        File file = new File("productList.txt");
        if (file.exists()){
            try{
                boolean list = checkProduct();
                if (list = false){



                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void deleteProduct(){
        //TODO delete product from text file
    }
    public static void updateElectronics(){
        //TODO update electronics in text file
    }
    public static void updateClothing(){
        //TODO update clothing in text file
    }
    public static void printProductList(){
        //TODO print product list from text file
    }
    public static boolean checkProduct(){
        boolean listFull = false;
        //TODO check if the system has more than 50 products
        File file = new File("productList.txt");
        if (file.exists()){
            try {
                if (countProduct() >= 50){
                    System.err.println("The system has more than 50 products");
                    listFull = true;
                    return listFull;
                }else{
                    return listFull;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listFull;
    }
}
