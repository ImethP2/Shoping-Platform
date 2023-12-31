package com.ClientSide;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.ManagerSide.*;
public class ShoppingCenterPanel extends ClientFrame {
    public static ArrayList<String> CartList = new ArrayList<>();
    public JPanel SCPInit(String[] user) throws RuntimeException, IOException {



        JPanel SCPAll = new JPanel();
        JPanel SCPMain1 = new JPanel();
        JPanel SCPMain2 = new JPanel();
        JPanel SCPMain1_0 = new JPanel();
        JPanel SCPMain1_1 = new JPanel();
        JPanel SCPMain1_2 = new JPanel();



        SCPAll.add(SCPMain1Init(SCPMain1_0, SCPMain1_1,SCPMain1_2,SCPMain1, SCPMain2, user));
        SCPAll.add(SCPMain2);
        SCPAll.setLayout(new BoxLayout(SCPAll, BoxLayout.Y_AXIS));
        SCPAll.setPreferredSize(new Dimension(600, 500));

        return SCPAll;
    }
    private JPanel SCPMain1Init(JPanel SCPMain1_0, JPanel SCPMain1_1,JPanel SCPMain1_2, JPanel SCPMain1, JPanel SCPMain2, String[] user) throws RuntimeException {
        JLabel label1 = new JLabel("Select Product Category");
        String[] category = {"All", "Clothing", "Electronics"};
        JComboBox<String> categoryBox = new JComboBox<>(category);

        JButton ShoppingCartBtn = new JButton("Shopping Cart");
        ShoppingCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShoppingCart shoppingCart = new ShoppingCart();
                try {
                    Dispose();
                    FrameInit(shoppingCart.SCInit(CartList, user), "Shopping Cart");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });


        SCPMain1_0.add(ShoppingCartBtn);
        SCPMain1_0.setLayout(new FlowLayout(FlowLayout.RIGHT));
        SCPMain1_0.setPreferredSize(new Dimension(550, 50));

        SCPMain1_1.add(label1);
        SCPMain1_1.add(categoryBox);
        SCPMain1_1.setLayout(new FlowLayout(FlowLayout.CENTER));
        SCPMain1_1.setPreferredSize(new Dimension(550, 50));

        //SCPMain1_2.setLayout(new BoxLayout(SCPMain1_2, BoxLayout.Y_AXIS));
        SCPMain1_2.setPreferredSize(new Dimension(550, 200));

        SCPMain1.setLayout(new BoxLayout(SCPMain1, BoxLayout.Y_AXIS));
        SCPMain1.add(SCPMain1_0);
        SCPMain1.add(SCPMain1_1);
        SCPMain1.add(SCPMain1_2Init(SCPMain1_2, categoryBox, SCPMain2));
        return SCPMain1;
    }
    private JPanel SCPMain1_2Init(JPanel SCPMain1_2, JComboBox<String> categoryBox, JPanel SCPMain2) throws RuntimeException {
        categoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String selectedCategory = (String) categoryBox.getSelectedItem();

                if (selectedCategory.equals("All")) {

                    SCPMain1_2.removeAll();
                    SCPMain1_2.revalidate();
                    SCPMain1_2.repaint();
                    try {
                        JTable table = SCPTableInit("All");
                        table.getSelectedRow();
                        SCPMain1_2.add(new JScrollPane(table));
                        table.getSelectionModel().addListSelectionListener(e1 -> {
                            if (!e1.getValueIsAdjusting()) {

                                try {
                                    int row = table.getSelectedRow();
                                    String prodID = table.getValueAt(row, 0).toString();

                                    //System.out.println(table.getValueAt(row, 0));
                                    if (prodID.contains("CL")) {
                                        SCPMain2.removeAll();
                                        SCPMain2.revalidate();
                                        SCPMain2.repaint();
                                        SCPMain2Init("Clothing", prodID, SCPMain2);
                                    } else if (prodID.contains("EL")) {
                                        SCPMain2.removeAll();
                                        SCPMain2.revalidate();
                                        SCPMain2.repaint();
                                        SCPMain2Init("Electronics", prodID, SCPMain2);
                                    }
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (selectedCategory.equals("Clothing")) {

                    SCPMain1_2.removeAll();
                    SCPMain1_2.revalidate();
                    SCPMain1_2.repaint();
                    try {
                        JTable table = SCPTableInit("Clothing");
                        //int row ;
                        SCPMain1_2.add(new JScrollPane(table));
                        table.getSelectionModel().addListSelectionListener(e1 -> {
                            if (!e1.getValueIsAdjusting()) {

                                try {
                                    int row = table.getSelectedRow();
                                    String prodID = table.getValueAt(row, 0).toString();
                                    SCPMain2.removeAll();
                                    SCPMain2.revalidate();
                                    SCPMain2.repaint();

                                    SCPMain2Init("Clothing", prodID, SCPMain2);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (selectedCategory.equals("Electronics")) {

                    SCPMain1_2.removeAll();
                    SCPMain1_2.revalidate();
                    SCPMain1_2.repaint();
                    try {
                        JTable table = SCPTableInit("Electronics");
                        SCPMain1_2.add(new JScrollPane(table));
                        table.getSelectionModel().addListSelectionListener(e1 -> {
                            if (!e1.getValueIsAdjusting()) {

                                try {
                                    int row = table.getSelectedRow();
                                    String prodID = table.getValueAt(row, 0).toString();
                                    SCPMain2.removeAll();
                                    SCPMain2.revalidate();
                                    SCPMain2.repaint();

                                    SCPMain2Init("Electronics", prodID,  SCPMain2);
                                } catch (IOException ex) {
                                    System.err.println(ex);
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        return SCPMain1_2;
    }
    private JTable SCPTableInit(String type) throws RuntimeException, IOException {

        TextFileDBHandler.countProduct();
        String[] columnNames = {"Product ID", "Product Name", "Category", "Price", "Info"};

        File file = new File("productList.txt");
        BufferedReader prodLineReader = new BufferedReader(new FileReader(file));
        JTable table = null;
        int row = 0;
        if (type.equals("All")) {
            String[][] data = new String[WestminsterShoppingManager.productCount][5];
            for (int i = 0; i < TextFileDBHandler.countProduct(); i++) {

                String prodLine = prodLineReader.readLine();
                String[] productArray = prodLine.split("-");

                data[i][0] = productArray[0];
                data[i][1] = productArray[1];
                data[i][2] = productArray[2];
                data[i][3] = productArray[3];
                if (productArray[0].contains("EL")) {
                    data[i][4] = productArray[4] + " , " + productArray[5] + " Weeks";
                }else if (productArray[0].contains("CL")){
                    data[i][4] = productArray[4] + " , " + productArray[5];
                }

            }prodLineReader.close();
            table = new JTable(data, columnNames){
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            table.setGridColor(Color.BLACK);
            table.getColumnModel().getColumn(4).setPreferredWidth(150);
            return table;

        } else if (type.equals("Clothing")) {
                String[][] data = new String[WestminsterShoppingManager.clothCount][5];


                for (int i = 0; i < TextFileDBHandler.countProduct(); i++) {
                    String prodLine = prodLineReader.readLine();
                    String[] productArray = prodLine.split("-");
                    if (productArray[0].contains("CL")) {
                        data[row][0] = productArray[0];
                        data[row][1] = productArray[1];
                        data[row][2] = productArray[2];
                        data[row][3] = productArray[3];
                        data[row][4] = productArray[4] + " , " + productArray[5];
                        row++;

                    }
                }prodLineReader.close();
            table = new JTable(data, columnNames){
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            table.setGridColor(Color.BLACK);
            return table;

        } else if (type.equals("Electronics")) {
            String[][] data = new String[WestminsterShoppingManager.electCount][5];

            for (int i = 0; i < TextFileDBHandler.countProduct(); i++) {

                String prodLine = prodLineReader.readLine();
                String[] productArray = prodLine.split("-");
                if (productArray[0].contains("EL")) {
                    data[row][0] = productArray[0];
                    data[row][1] = productArray[1];
                    data[row][2] = productArray[2];
                    data[row][3] = productArray[3];
                    data[row][4] = productArray[4] + " , " + productArray[5] + " Weeks";
                    row++;

                }
            }prodLineReader.close();
            table = new JTable(data, columnNames){
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            table.setGridColor(Color.BLACK);
            return table;

        }
        return table;
    }
    private JPanel SCPMain2Init(String type, String ProdID, JPanel SCPMain2) throws IOException {

        JPanel SCPMain2_1 = new JPanel();
        JPanel SCPMain2_2 = new JPanel();

        JButton addToCartBtn = new JButton("Add to Shopping Cart");
        SCPMain2_2.add(addToCartBtn);
        SCPMain2_2.setPreferredSize(new Dimension(500, 50));
        SCPMain2_2.setLayout(new FlowLayout(FlowLayout.CENTER));


        File file = new File("productList.txt");
        if (type.equals("Clothing")) {

            if (file.exists()) {
                try {
                    BufferedReader prodLineReader = new BufferedReader(new FileReader(file));

                    for (int i = 0; i < TextFileDBHandler.countProduct(); i++) {
                        String prodLine = prodLineReader.readLine();
                        String[] productArray = prodLine.split("-");
                        String prodID = productArray[0];
                        if (prodID.equals(ProdID)) {
                            JLabel ProdId = new JLabel("Product ID: " + prodID);
                            JLabel ProdCategory = new JLabel("Product Category: " + type);
                            JLabel ProdName = new JLabel("Product Name: " + productArray[1]);
                            JLabel ProdSize = new JLabel("Product Size: " + productArray[4]);
                            JLabel ProdColour = new JLabel("Product Color: " + productArray[5]);
                            JLabel ProdQuantity = new JLabel("Product Quantity: " + productArray[2]);

                            SCPMain2_1.add(ProdId);
                            SCPMain2_1.add(ProdCategory);
                            SCPMain2_1.add(ProdName);
                            SCPMain2_1.add(ProdSize);
                            SCPMain2_1.add(ProdColour);
                            SCPMain2_1.add(ProdQuantity);
                            SCPMain2_1.setLayout(new BoxLayout(SCPMain2_1, BoxLayout.Y_AXIS));
                            SCPMain2_1.setPreferredSize(new Dimension(700, 150));

                            SCPMain2.add(SCPMain2_1);
                            SCPMain2.add(SCPMain2_2);
                            SCPMain2.setLayout(new BoxLayout(SCPMain2, BoxLayout.Y_AXIS));

                            SpinnerNumberModel getQuantiy = new SpinnerNumberModel(1, 1, Integer.parseInt(productArray[2]), 1);
                            JSpinner quant = new JSpinner(getQuantiy);

                            addToCartBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    //System.out.println("Add to cart");
                                    //TODO: Add to cart

                                    //JOptionPane.showMessageDialog(SCPMain2, quant);
                                    int quantiy = JOptionPane.showOptionDialog(SCPMain2, quant, "How much do you need?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                                    if (quantiy == JOptionPane.OK_OPTION) {
                                        String[] prod = new String[6];
                                        prod[0] = productArray[0];
                                        prod[1] = productArray[1];
                                        prod[2] = String.valueOf(quant.getValue());
                                        prod[3] = productArray[4];
                                        prod[4] = productArray[5];
                                        prod[5] = productArray[3];
                                        String item = (prod[0] + "-" + prod[1] + "-" + prod[2] + "-" + prod[3] + "-" + prod[4]+ "-" + prod[5]);
                                        CartList.add(item);

                                    } else if (quantiy == JOptionPane.CANCEL_OPTION) {

                                    }
                                }
                            });
                            /*for (String[] prod : CartList) {
                                System.out.println(prod[0] + "-" + prod[1] + "-" + prod[2] + "-" + prod[3] + "-" + prod[4]+ "-" + prod[5]);
                            }*/

                            return SCPMain2;

                        }
                    }
                    prodLineReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (type.equals("Electronics")) {

            if (file.exists()) {
                try {
                    BufferedReader prodLineReader = new BufferedReader(new FileReader(file));


                    for (int i = 0; i < TextFileDBHandler.countProduct(); i++) {

                        String prodLine = prodLineReader.readLine();
                        String[] productArray = prodLine.split("-");
                        String prodID = productArray[0];
                        if (prodID.equals(ProdID)) {

                            JLabel ProdId = new JLabel("Product ID: " + prodID);
                            JLabel ProdCategory = new JLabel("Product Category: " + type);
                            JLabel ProdName = new JLabel("Product Name: " + productArray[1]);
                            JLabel ProdBrand = new JLabel("Product Brand: " + productArray[4]);
                            JLabel ProdWarranty = new JLabel("Product Warranty: " + productArray[5] + " Weeks");
                            JLabel ProdQuantity = new JLabel("Product Quantity: " + productArray[2]);

                            SCPMain2_1.add(ProdId);
                            SCPMain2_1.add(ProdCategory);
                            SCPMain2_1.add(ProdName);
                            SCPMain2_1.add(ProdBrand);
                            SCPMain2_1.add(ProdWarranty);
                            SCPMain2_1.add(ProdQuantity);
                            SCPMain2_1.setLayout(new BoxLayout(SCPMain2_1, BoxLayout.Y_AXIS));
                            SCPMain2_1.setPreferredSize(new Dimension(700, 150));


                            SCPMain2.add(SCPMain2_1);
                            SCPMain2.add(SCPMain2_2);
                            SCPMain2.setLayout(new BoxLayout(SCPMain2, BoxLayout.Y_AXIS));

                            SpinnerNumberModel getQuantiy = new SpinnerNumberModel(1, 1, Integer.parseInt(productArray[2]), 1);
                            JSpinner quant = new JSpinner(getQuantiy);



                            addToCartBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                   // System.out.println("Add to cart");
                                    //TODO: Add to cart

//                                    JOptionPane.showMessageDialog(SCPMain2, quant);
                                    int quantiy = JOptionPane.showOptionDialog(SCPMain2, quant, "How much do you need?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                                    if (quantiy == JOptionPane.OK_OPTION) {
                                        String[] prod = new String[6];
                                        prod[0] = productArray[0];
                                        prod[1] = productArray[1];
                                        prod[2] = String.valueOf(quant.getValue());
                                        prod[3] = productArray[4];
                                        prod[4] = productArray[5] + " Weeks";
                                        prod[5] = productArray[3];
                                        String item = (prod[0] + "-" + prod[1] + "-" + prod[2] + "-" + prod[3] + "-" + prod[4]+ "-" + prod[5]);
                                        CartList.add(item);

                                    } else if (quantiy == JOptionPane.CANCEL_OPTION) {

                                    }
                                }
                            });
                            /*System.err.println("----");
                            for (String[] prod : CartList) {
//                                System.err.println("----");
                                System.out.println(prod[0] + "-" + prod[1] + "-" + prod[2] + "-" + prod[3] + "-" + prod[4]+ "-" + prod[5]);
                            }
                            System.err.println("----");*/
                            return SCPMain2;

                        }

                    }
                    prodLineReader.close();
                    /*for (String[] prod : CartList) {
                        System.out.println(prod[0] + "-" + prod[1] + "-" + prod[2] + "-" + prod[3] + "-" + prod[4]);
                    }
                    System.out.println(CartList.size());*/

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /*System.err.println("----");
        for (String[] prod : CartList) {
//                                System.err.println("----");
            System.out.println(prod[0] + "-" + prod[1] + "-" + prod[2] + "-" + prod[3] + "-" + prod[4]+ "-" + prod[5]);
        }
        System.err.println("----");*/
        return SCPMain2;
    }


}
