package gui.handler;

import gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// общий класс для всех обработчиков
public abstract class Handler implements ActionListener {

    protected final MainFrame frame;

    public Handler(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        frame.refresh();
    }
}

