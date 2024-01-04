package com.ClientSide;

import javax.swing.*;
import java.awt.*;


public class ClientFrame extends JFrame{
    JFrame frame;

    JFrame FrameCreate() {
        frame = new JFrame();
        return frame;
    }

    void FrameInit(JPanel panel, String title) {
//        this.frame = new JFrame();
        frame = FrameCreate();
        frame.setTitle(title);
        frame.setSize(700, 500);
//        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.toFront();
        frame.add(panel);
        new Window(frame);

    }

    void Dispose() {
        for(Window window:Window.getWindows()){
            window.dispose();
        }
    }
}
