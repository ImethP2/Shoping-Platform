package com.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class WelcomePanel extends ClientFrame{

    public WelcomePanel(){
        this.FrameInit(WPInit(), "Welcome to the Westminster Shopping Manager");

    }
    JPanel WPInit(){
        JPanel panelAll = new JPanel();
        JPanel panelNorth = new JPanel();
        JPanel panelFull = new JPanel();
        panelFull.setLayout(new BoxLayout(panelFull,BoxLayout.Y_AXIS));

        panelAll.setLayout(new BorderLayout());
//        panelAll.setBackground(Color.RED);

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
        label.setPreferredSize(new Dimension(700,75));
        label.add(label1);
        label.add(label2);
        label.add(label3);



        panelFull.add(label);


        JButton signinBtn = new JButton("Sign-IN");
        signinBtn.addActionListener(e -> {

            Dispose();
            SignInPanel signInPanel = new SignInPanel();
            FrameInit(signInPanel.SIPInit(), signInPanel.title);


        });

        panelbtn.add(signinBtn);

        JButton signupBtn = new JButton("Sign-UP");

        signupBtn.addActionListener(e -> {
            SignUpPanel signUpPanel = new SignUpPanel();
            try {
                Dispose();
                FrameInit(signUpPanel.SUPInit(), "Westminster Shopping Center - Sign Up");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        panelbtn.add(signupBtn);

        panelFull.add(panelbtn);
        panelNorth.setPreferredSize(new Dimension(700,150));
        panelAll.add(panelNorth,BorderLayout.NORTH);
        panelAll.add(panelFull,BorderLayout.CENTER);

        return panelAll;
    }
}
