package gui.toolbar;

import gui.MainButton;
import gui.Refresh;
import gui.handler.Handler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// класс для двух тулбаров
abstract public class AbstractToolBar extends JPanel implements Refresh {

    private final Handler handler;

    public AbstractToolBar(EmptyBorder border, Handler handler) {
        super();
        this.handler = handler;
        setBorder(border);
    }

    abstract protected void init();

    // метод добавления на панель кнопок
    protected MainButton addButton(String title, ImageIcon icon, String action, boolean topIcon) {
        MainButton button = new MainButton(title, icon, handler, action);
        if (topIcon) {                      // делаем текст по середине и внизу
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
        }
        else {  // иначе текст делаем справа
            button.setHorizontalTextPosition(SwingConstants.RIGHT);
            button.setVerticalTextPosition(SwingConstants.CENTER);
        }
        add(button);
        return button;
    }

    @Override
    public void refresh() {
        removeAll();
        init();
    }

}
