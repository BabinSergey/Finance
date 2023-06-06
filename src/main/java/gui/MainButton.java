package gui;

import settings.Style;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// класс кнопок
public class MainButton extends JButton implements MouseListener {

    public MainButton(String title, ImageIcon icon, ActionListener listener, String action) {
        super(title);
        setIcon(icon);
        setActionCommand(action);
        addActionListener(listener);
        addMouseListener(this);

        setFocusPainted(false); // убираем рамку
        setFont(Style.FONT_MAIN_BUTTON);
        setBackground(Style.COLOR_BUTTON_BG_NORMAL);
    }

    // кнопка если есть текст, но нет иконки
    public MainButton(String title, ActionListener listener, String action) {
        this(title, null, listener, action);
    }

    // кнопка если есть картинка, но нет текста
    public MainButton(ImageIcon icon, ActionListener listener, String action) {
        this("", icon, listener, action);
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        ((MainButton) e.getSource()).setBackground(Style.COLOR_BUTTON_BG_hOVER);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((MainButton) e.getSource()).setBackground(Style.COLOR_BUTTON_BG_NORMAL);
    }
}
