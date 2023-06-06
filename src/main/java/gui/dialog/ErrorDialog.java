package gui.dialog;


import gui.MainFrame;
import settings.Text;

import javax.swing.*;

// окно с информацие об ошибке
public class ErrorDialog {

    public static void show(MainFrame frame, String text) {
        JOptionPane.showMessageDialog(frame, Text.get(text), Text.get("ERROR"), JOptionPane.ERROR_MESSAGE);
    }

}