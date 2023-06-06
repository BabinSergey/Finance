package gui.handler;

import gui.MainFrame;
import gui.dialog.ConfirmDialog;
import saveload.SaveDate;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


// обработчик закрытия окна через крестик
public class MainWindowHandler extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent we) {
        if (SaveDate.getInstance().isSaved()) System.exit(0);
        else {
            int result = ConfirmDialog.show((MainFrame) we.getWindow(), "CONFIRM_EXIT_TEXT", "CONFIRM_EXIT_TITLE");
            if (result == JOptionPane.YES_OPTION) System.exit(0);
        }
    }

}
