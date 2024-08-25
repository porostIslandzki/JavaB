package org.example.interface_mouse_listener;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Frame extends JFrame implements MouseListener{
    private JLabel label;
    public Frame(){
        label = new JLabel("------");
        label.setBounds(10, 10, 250, 50);
        this.add(label);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String str = "Clicked x: " + x + " y: " + y;
        label.setText(str);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setSize(300, 300);
        frame.setTitle("Przykład");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addMouseListener(frame);
    }
}
