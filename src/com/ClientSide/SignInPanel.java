package com.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        labelPanel.add(titleLabel);

        JLabel userLabel = new JLabel("Username");
        JLabel passLabel = new JLabel("Password");

        JTextField userTF = new JTextField();
        userTF.setPreferredSize(new Dimension(150,30));

        JPasswordField passwordF = new JPasswordField();
        passwordF.setPreferredSize(new Dimension(150,30));

        JButton signinBtn = new JButton("Sign-IN");
        signinBtn.addActionListener(e -> {
            //TODO : Sign In
            SchppingCenterPanel shoppingCenterPanel = new SchppingCenterPanel();
            FrameInit(shoppingCenterPanel.SCPInit(), "Westminster Shopping Center");
            System.out.println("Sign In");
            System.out.println(userTF.getText());
            System.out.println(passwordF.getText());
        });
        btnPanel.add(signinBtn);
        btnPanel.setPreferredSize(new Dimension(250,40));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        userPanel.add(userLabel);
        userPanel.add(userTF);
        userPanel.setPreferredSize(new Dimension(250,40));
        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //userPanel.setAlignmentY(userPanel.CENTER_ALIGNMENT);

        passPanel.add(passLabel);
        passPanel.add(passwordF);
        passPanel.setSize(250,40);
        passPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //passPanel.setAlignmentY(passPanel.CENTER_ALIGNMENT);

        panelFull.add(labelPanel);
        panelFull.add(userPanel);
        panelFull.add(passPanel);
        panelFull.add(btnPanel);
        panelFull.setPreferredSize(new Dimension(250,200));

        return panelFull;
    }
}
