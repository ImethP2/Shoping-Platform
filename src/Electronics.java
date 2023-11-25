import java.util.Date;

public class Electronics extends Product{
    private String brandName;
    private Date warrantyDate;


    public Electronics(String prodID, String prodName, int prodQuantity, double prodPrice, String brandName, Date warrantyDate) {
        super(prodID, prodName, prodQuantity, prodPrice);
        this.brandName = brandName;
        this.warrantyDate = warrantyDate;
    }
}
