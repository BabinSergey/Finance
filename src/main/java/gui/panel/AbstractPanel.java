package gui.panel;

import gui.MainFrame;
import gui.Refresh;

import javax.swing.*;

// астрактный класс для создания панелей
abstract public class AbstractPanel extends JPanel implements Refresh {

    protected final MainFrame frame;

    public AbstractPanel(MainFrame frame) {
        this.frame = frame;
    }


    // реализация интерфейса
    @Override
    public void refresh() {
        removeAll();
        init();
    }

    abstract protected void init();

}

