package com.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WelcomePanel extends ClientFrame{
    public WelcomePanel(){
        this.FrameInit(WPInit(), "Welcome to the Westminster Shopping Manager");
        /*this.WPInit();*/
    }
    JPanel WPInit(){
        JPanel panelFull = new JPanel();
        panelFull.setLayout(new BoxLayout(panelFull,BoxLayout.Y_AXIS));
        panelFull.setBounds(115,145,270,155);

        JPanel panelbtn = new JPanel();
        panelbtn.setLayout(new FlowLayout());

        JLabel label1 = new JLabel("Welcome");
        JLabel label2 = new JLabel("to the");
        JLabel label3 = new JLabel("Westminster Shopping Manager");
        label1.setAlignmentX(label1.CENTER_ALIGNMENT);
        label2.setAlignmentX(label2.CENTER_ALIGNMENT);
        label3.setAlignmentX(label3.CENTER_ALIGNMENT);
        JPanel label = new JPanel();
        label.setLayout(new BoxLayout(label,BoxLayout.Y_AXIS));
        label.add(label1);
        label.add(label2);
        label.add(label3);

        label.setBounds(115, 145,270,70);

        panelFull.add(label);


        JButton signinBtn = new JButton("Sign-IN");
        signinBtn.addActionListener(e -> {
            SignInPanel signInPanel = new SignInPanel();
            FrameInit(signInPanel.SIPInit(), signInPanel.title);


        });
        signinBtn.setBounds(140,250,90,50);
        panelbtn.add(signinBtn);

        JButton signupBtn = new JButton("Sign-UP");
        signupBtn.setBounds(270,250,90,50);
        signupBtn.addActionListener(e -> {
            SignUpPanel signUpPanel = new SignUpPanel();
            try {
                FrameInit(signUpPanel.SUPInit(), "Westminster Shopping Center - Sign Up");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panelbtn.add(signupBtn);

        panelFull.add(panelbtn);
        //panelFull.setBounds(140,250,220,500);

        return panelFull;
    }
}
