package com.ClientSide;

import javax.swing.*;

public class ClientFrame {
    private JFrame frame;
    /*public ClientFrame(){
        FrameInit();
    }*/
    void FrameInit(JPanel panel, String title) {
        this.frame = new JFrame();
        this.frame.setTitle(title);
        this.frame.setSize(700, 500);
//        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.add(panel);

    }
    public void Dispose(){
        this.frame.dispose();
    }

}
