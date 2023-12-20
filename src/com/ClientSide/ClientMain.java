package com.ClientSide;

import javax.swing.*;

public class ClientMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WelcomePanel clientFrame = new WelcomePanel();
            }
        });
    }
}
