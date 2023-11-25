public class Clothing extends Product{

    private String size;
    private String color;
    public Clothing(String prodID, String prodName, int prodQuantity, double prodPrice, String size, String color) {
        super(prodID, prodName, prodQuantity, prodPrice);
        this.size = size;
        this.color = color;
    }
}
