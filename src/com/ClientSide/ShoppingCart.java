package com.ClientSide;


import com.ManagerSide.Product;
import com.ManagerSide.TextFileDBHandler;
import com.ManagerSide.WestminsterShoppingManager;

import static com.ManagerSide.WestminsterShoppingManager.productCount;
import static com.ManagerSide.WestminsterShoppingManager.products;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.ClientSide.SignUpPanel.userArrayList;

public class ShoppingCart extends ClientFrame{

    JPanel SCInit(ArrayList<String> cart, String[] user) throws IOException {




        JPanel panelAll = new JPanel();
        JPanel panelFull = new JPanel();
        JPanel CartPanel = new JPanel();
        JPanel PricePanel = new JPanel();


        JTable CartTable;
        String[][] data = new String[cart.size()][3];

        int row = 0;
        Double total = 0.0;
        int SCDCloth = 0;
        int SCDElect = 0;



        String[] columnNames = {"Product", "Quantity", "Price"};

        for (int i = 0; i < cart.size(); i++) {

            String[] productArray = cart.get(i).split("-");
            System.out.println(productArray[0]);
            if (productArray[0].contains("CL") ) {
                SCDCloth +=Integer.parseInt(productArray[2]);
            }else if (productArray[0].contains("EL")){
                SCDElect +=Integer.parseInt(productArray[2]);
                System.out.println(SCDElect);
            }

            for (int x = 0; x<productCount; x++){
                if(products[x].getProdID().equals(productArray[0])){
                    products[x].setProdQuantity(products[x].getProdQuantity() - Integer.parseInt(productArray[2]));
                }
            }

            total += Integer.parseInt(productArray[2]) * Double.parseDouble(productArray[5]);
            String column1 = productArray[0]+"\n"+productArray[1]+"\n"+productArray[3]+" , "+productArray[4];
            String column2 = productArray[2];
            String column3 = String.valueOf(total);
            data[row][0] = column1;
            data[row][1] = column2;
            data[row][2] = column3;
            row++;

        }
        TextFileDBHandler.saveProductList();
        CartTable = new JTable(data, columnNames){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        CartTable.setGridColor(Color.BLACK);


        CartTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        CartTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        CartTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        CartTable.setRowHeight(30);
        CartTable.setLayout(new FlowLayout(FlowLayout.CENTER));
        {
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);

            TableModel tableModel = CartTable.getModel();

            for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
                CartTable.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
            }
        }
        JScrollPane scrollPane = new JScrollPane(CartTable);
        scrollPane.setPreferredSize(new Dimension(500, 250));
        scrollPane.setVisible(true);
        CartPanel.add(scrollPane);
        CartPanel.setPreferredSize(new Dimension(500, 250));

        panelFull.add(CartPanel);
        DiscountPanel(user, total, SCDCloth, SCDElect, PricePanel);
        panelFull.add(PricePanel);
        panelFull.setPreferredSize(new Dimension(500, 400));
        panelFull.setLayout(new BoxLayout(panelFull, BoxLayout.Y_AXIS));

        panelAll.add(panelFull);
        panelAll.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelAll.setPreferredSize(new Dimension(700, 400));




        return panelAll;
    }
    private JPanel DiscountPanel(String[] user, Double total, int SCDCloth, int SCDElect, JPanel PricePanel) throws IOException {

        JPanel TotalPanel = new JPanel();
        JPanel TotalPricePanel = new JPanel();
        JPanel TotalLabelPanel = new JPanel();
        JPanel FirstPurchPanel = new JPanel();
        JPanel FPPricePanel = new JPanel();
        JPanel FPLabelPanel = new JPanel();
        JPanel SameCat3Panel = new JPanel();
        JPanel SC3PricePanel = new JPanel();
        JPanel SC3LabelPanel = new JPanel();
        JPanel FinalTotalPanel = new JPanel();
        JPanel FTPricePanel = new JPanel();
        JPanel FTLabelPanel = new JPanel();

        Double FPD = 0.0;
        Double SC3D = 0.0;

        JLabel TotalLabel = new JLabel("Total");
        TotalLabelPanel.add(TotalLabel);
        TotalLabelPanel.setPreferredSize(new Dimension(250, 30));
        TotalLabelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel TotalPrice = new JLabel(total +"£");
        TotalPricePanel.add(TotalPrice);
        TotalPricePanel.setPreferredSize(new Dimension(150, 30));
        TotalPricePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        TotalPanel.add(TotalLabelPanel);
        TotalPanel.add(TotalPricePanel);
//        TotalPanel.setPreferredSize(new Dimension(300, 60));
        TotalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));


        JLabel FirstPurchLabel = new JLabel("First Purchase Discount (10%)");
        FPLabelPanel.add(FirstPurchLabel);
        FPLabelPanel.setPreferredSize(new Dimension(250, 30));
        FPLabelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel FirstPurchPrice;
        System.out.println(user[5]);
        if (user[5].equals(String.valueOf(true))){

            FPD = total /10;
            FirstPurchPrice = new JLabel(FPD+"£");
            for (int i = 0; i < userArrayList.size(); i++) {
                String userID = userArrayList.get(i).getUserName();
                if (userID.equals(user[0])){
                    boolean newuser = false;
                    User updatedUser = new User(userID, userArrayList.get(i).getPassword(), userArrayList.get(i).getFirstName(), userArrayList.get(i).getLastName(), userArrayList.get(i).getEmail(), newuser);
                    userArrayList.set(i, updatedUser);
                    User.updateUser(updatedUser);
                    break;
                }
            }

        }else{
            FirstPurchPrice = new JLabel("0.0£");
        }
        FPPricePanel.add(FirstPurchPrice);
        FPPricePanel.setPreferredSize(new Dimension(150, 30));
        FPPricePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        FirstPurchPanel.add(FPLabelPanel);
        FirstPurchPanel.add(FPPricePanel);
//        FirstPurchPanel.setPreferredSize(new Dimension(300, 60));
        FirstPurchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));


        JLabel SameCat3Label = new JLabel("Three Items in same Category Discount (20%)");
        SC3LabelPanel.add(SameCat3Label);
        System.out.println(SameCat3Label.getFont());
        SC3LabelPanel.setPreferredSize(new Dimension(250, 30));
        SC3LabelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel SameCat3Price;
        if (SCDCloth >= 3 || SCDElect >= 3){
            SC3D = total / 5;
            SameCat3Price = new JLabel(SC3D+"£");
        }else{
            SameCat3Price = new JLabel("0.0£");
        }
        SC3PricePanel.add(SameCat3Price);
        SC3PricePanel.setPreferredSize(new Dimension(150, 30));
        SC3PricePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        SameCat3Panel.add(SC3LabelPanel);
        SameCat3Panel.add(SC3PricePanel);
//        SameCat3Panel.setPreferredSize(new Dimension(300, 60));
        SameCat3Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        SameCat3Panel.setLayout(new BoxLayout(SameCat3Panel, BoxLayout.X_AXIS));


        JLabel FinalTotalLabel = new JLabel("Final Total");
        FinalTotalLabel.setFont(new Font("Lucida", Font.BOLD, 13));
        FTLabelPanel.add(FinalTotalLabel);
        FTLabelPanel.setPreferredSize(new Dimension(250, 30));
        FTLabelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel FinalTotalPrice = new JLabel(String.valueOf(total - FPD - SC3D)+"£");
        FinalTotalPrice.setFont(new Font("Lucida", Font.BOLD, 13));
        FTPricePanel.add(FinalTotalPrice);
//        FTPricePanel.setSize(new Dimension(150, 30));
        FTPricePanel.setPreferredSize(new Dimension(150, 30));
        FTPricePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        FTLabelPanel.setBackground(Color.WHITE);
        FinalTotalPanel.add(FTLabelPanel);
//        FTPricePanel.setBackground(Color.RED);
        FinalTotalPanel.add(FTPricePanel);
//        FinalTotalPanel.setPreferredSize(new Dimension(300, 60));
        FinalTotalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        FinalTotalPanel.setBackground(Color.PINK);



        PricePanel.add(TotalPanel);
        PricePanel.add(FirstPurchPanel);
        PricePanel.add(SameCat3Panel);
        PricePanel.add(FinalTotalPanel);
//        PricePanel.setPreferredSize(new Dimension(250, 240));
        PricePanel.setSize(new Dimension(400, 240));
        PricePanel.setLayout(new BoxLayout(PricePanel, BoxLayout.Y_AXIS));

        return PricePanel;
    }

}
