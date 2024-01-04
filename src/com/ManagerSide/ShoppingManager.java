package com.ManagerSide;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public interface ShoppingManager {
    public abstract void addProduct() throws ParseException, IOException;
    public abstract void deleteProduct();
    public abstract void printProductList();
    public abstract void sortProductList();
    public abstract void managerMenu() throws ParseException, IOException;
}
