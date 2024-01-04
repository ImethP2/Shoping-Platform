package com.ClientSide;

import com.ManagerSide.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SignInPanel extends ClientFrame{
    public String title;


    JPanel SIPInit(){
        title = "Westminster Shopping Manager - Sign In";

        JPanel panelFull = new JPanel();
        panelFull.setLayout(new BoxLayout(panelFull,BoxLayout.Y_AXIS));

        JPanel labelPanel = new JPanel();
        JPanel userPanel = new JPanel();

        JPanel passPanel = new JPanel();
        JPanel btnPanel = new JPanel();


        JLabel titleLabel = new JLabel("Sign IN");
//        labelPanel.setLayout(new BoxLayout(labelPanel,BoxLayout.Y_AXIS));
        labelPanel.add(titleLabel);
        labelPanel.setMaximumSize(new Dimension(250,50));

        JLabel userLabel = new JLabel("Username");
        JLabel passLabel = new JLabel("Password");

        JTextField userTF = new JTextField();
        userTF.setPreferredSize(new Dimension(150,30));

        JPasswordField passwordF = new JPasswordField();
        passwordF.setPreferredSize(new Dimension(150,30));

        JPanel ForgotPassPanel = new JPanel();
        JButton ForgotPassBtn = new JButton("Forgot Password");
        ForgotPassBtn.addActionListener(e -> {
            //TODO : Forgot Password
            ForgetPwPanel rePwPanel = new ForgetPwPanel();
            FrameInit(rePwPanel.FPInit(), "Westminster Shopping Center - Reset Password");
        });
        ForgotPassPanel.add(ForgotPassBtn);
        ForgotPassPanel.setVisible(false);

        JButton signinBtn = new JButton("Sign-IN");
        signinBtn.addActionListener(e -> {
            //TODO : Sign In

            try {
                String UserName = userTF.getText();
                String Password = passwordF.getText();
                signIn(UserName, Password, ForgotPassPanel, btnPanel);

            } catch (IOException ex) {

                throw new RuntimeException(ex);
            }

        });
        btnPanel.add(signinBtn);
//        btnPanel.setMaximumSize(new Dimension(250,40));
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        userPanel.add(userLabel);
        userPanel.add(userTF);
        userPanel.setMaximumSize(new Dimension(250,40));
        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //userPanel.setAlignmentY(userPanel.CENTER_ALIGNMENT);

        passPanel.add(passLabel);
        passPanel.add(passwordF);
        passPanel.setMaximumSize(new Dimension(250,40));
        passPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //passPanel.setAlignmentY(passPanel.CENTER_ALIGNMENT);
        JPanel TopPanel = new JPanel();
        TopPanel.setPreferredSize(new Dimension(250,75));


        panelFull.add(TopPanel);
        panelFull.add(labelPanel);
        panelFull.add(userPanel);
        panelFull.add(passPanel);
        panelFull.add(btnPanel);
        panelFull.add(ForgotPassPanel);
        panelFull.setMaximumSize(new Dimension(250,500));
        panelFull.setLayout(new BoxLayout(panelFull, BoxLayout.Y_AXIS));



        return panelFull;
    }
    private void signIn(String username, String password, JPanel ForgotPassPanel, JPanel btnPanel) throws IOException {

        File file = new File("UserList.txt");

        if (file.exists()){

            CountUser();
            BufferedReader UserLineReader = new BufferedReader(new FileReader("UserList.txt"));
            for (int i = 0; i < WestminsterShoppingManager.UserCount; i++) {

                String[] user = UserLineReader.readLine().split("∆");

                if (username.equals(user[0]) && password.equals(user[1])) {
                    ShoppingCenterPanel shoppingCenterPanel = new ShoppingCenterPanel();

                    try{
                        Dispose();
                        FrameInit(shoppingCenterPanel.SCPInit(user), "Westminster Shopping Center");
                        System.out.println("Sign In Success");
                    }catch (NullPointerException ex) {
                        throw new NullPointerException();
                    }
                } else {
                    btnPanel.setMaximumSize(new Dimension(250,40));
                    ForgotPassPanel.setVisible(true);
                }
            }
        }else {
            file.createNewFile();
        }
    }
    public static int CountUser() throws IOException {
        File file = new File("productList.txt");
        WestminsterShoppingManager.UserCount = 0;

        if (file.exists()) {
            try {
                BufferedReader prodLineReader = new BufferedReader(new FileReader("UserList.txt"));
                while ((prodLineReader.readLine()) != null) {
                    WestminsterShoppingManager.UserCount++;
                }
                prodLineReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("File not found");
        }
        return WestminsterShoppingManager.UserCount;
    }
}
