package gui.handler;

import gui.MainFrame;
import gui.dialog.AboutDialog;
import settings.HandlerCode;

import java.awt.event.ActionEvent;

public class MenuHelpHandler extends Handler {

    public MenuHelpHandler(MainFrame frame) {
        super(frame);
    }

    // обработчик меню Help - выводит окно о программе
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.MENU_HELP_ABOUT: {
                new AboutDialog().setVisible(true);
            }
        }
        super.actionPerformed(ae);
    }

}

