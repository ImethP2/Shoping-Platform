package com.ClientSide;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.ManagerSide.*;
public class SchppingCenterPanel {
    public JPanel SCPInit(){
        JPanel SCPAll = new JPanel();
        JPanel SCPMain1 = new JPanel();
        JPanel SCPMain2 = new JPanel();
        JPanel SCPMain1_1 = new JPanel();
        JPanel SCPMain1_2 = new JPanel();
        JPanel SCPMain2_1 = new JPanel();
        JPanel SCPMain2_2 = new JPanel();

        SCPAll.add(SCPMain1Init(SCPMain1_1,SCPMain1_2,SCPMain1));
        return SCPAll;
    }
    private JPanel SCPMain1Init(JPanel SCPMain1_1,JPanel SCPMain1_2, JPanel SCPMain1) {
        JLabel label1 = new JLabel("Select Product Category");
        String[] category = {"All", "Clothing", "Electronics"};
        JComboBox<String> categoryBox = new JComboBox<>(category);
        categoryBox.setSelectedIndex(0);
        SCPMain1_1.add(label1);
        SCPMain1_1.add(categoryBox);
        SCPMain1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
        SCPMain1_1.setPreferredSize(new Dimension(500, 50));
        SCPMain1.add(SCPMain1_1);
        SCPMain1.add(SCPMain1_2Init(SCPMain1_2, categoryBox));
        return SCPMain1;
    }
    private JPanel SCPMain1_2Init(JPanel SCPMain1_2, JComboBox<String> categoryBox) {
        categoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String selectedCategory = (String) categoryBox.getSelectedItem();
                System.out.println(selectedCategory);
                if (selectedCategory.equals("All")) {
                    System.out.println("All products selected");
                    try {
                        SCPMain1_2.add(SCPTableInit("All"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (selectedCategory.equals("Clothing")) {
                    System.out.println("Clothing selected");
                    try {
                        SCPMain1_2.add(SCPTableInit("Clothing"));
                    } catch (IOException ex) {

                        System.out.println("Clothing selected");
                        throw new RuntimeException(ex);

                    }
                } else if (selectedCategory.equals("Electronics")) {
                    System.out.println("Electronics selected");
                    try {
                        SCPMain1_2.add(SCPTableInit("Electronics"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        return SCPMain1_2;
    }
    private JTable SCPTableInit(String type) throws IOException {
        String[] columnNames = {"Product ID", "Product Name", "Category", "Price", "Info"};
        String[][] data = new String[50][5];
        File file = new File("productList.txt");
        BufferedReader prodLineReader = new BufferedReader(new FileReader(file));
        JTable table = null;
        if (type.equals("All")) {
            for (int i = 0; i < TextFileDBHandler.countProduct(); i++) {

                String prodLine = prodLineReader.readLine();
                String[] productArray = prodLine.split("-");
                data[i][0] = productArray[0];
                data[i][1] = productArray[1];
                data[i][2] = productArray[2];
                data[i][3] = productArray[3];
                data[i][4] = productArray[4];
                data[i][5] = productArray[5] + " , " + productArray[6] + " Weeks";

            }prodLineReader.close();
            table = new JTable(data, columnNames){
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            table.setGridColor(Color.BLACK);
            return table;

        } else if (type.equals("Clothing")) {

            for (int i = 0; i < TextFileDBHandler.countProduct(); i++) {
                String prodLine = prodLineReader.readLine();
                String[] productArray = prodLine.split("-");
                if (productArray[0].contains("CL")) {
                    data[i][0] = productArray[0];
                    data[i][1] = productArray[1];
                    data[i][2] = productArray[2];
                    data[i][3] = productArray[3];
                    data[i][4] = productArray[4] + " , " + productArray[5];

                }prodLineReader.close();
            }
            table = new JTable(data, columnNames){
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            table.setGridColor(Color.BLACK);
            return table;

        } else if (type.equals("Electronics")) {
            for (int i = 0; i < TextFileDBHandler.countProduct(); i++) {

                String prodLine = prodLineReader.readLine();
                String[] productArray = prodLine.split("-");
                if (productArray[0].contains("EL")) {
                    data[i][0] = productArray[0];
                    data[i][1] = productArray[1];
                    data[i][2] = productArray[2];
                    data[i][3] = productArray[3];
                    data[i][4] = productArray[4] + " , " + productArray[5] + " Weeks";

                }prodLineReader.close();
            }
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

}
