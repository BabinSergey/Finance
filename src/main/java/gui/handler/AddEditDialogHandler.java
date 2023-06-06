package gui.handler;

import exception.ModelException;
import gui.MainFrame;
import gui.dialog.AddEditDialog;
import gui.dialog.ErrorDialog;
import saveload.SaveDate;
import settings.HandlerCode;

import java.awt.event.*;

// обработчик кнопок диалоговых окон
public class AddEditDialogHandler extends Handler implements WindowListener, KeyListener {

    private final AddEditDialog dialog;

    public AddEditDialogHandler(MainFrame frame, AddEditDialog dialog) {
        super(frame);
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.ADD:
                addEdit(true);
                break;
            case HandlerCode.EDIT:
                addEdit(false);
                break;
            case HandlerCode.CANCEL:
                closeDialog();
        }
        super.actionPerformed(ae);
    }

    private void addEdit(boolean add) {
        try {
            if (add) SaveDate.getInstance().add(dialog.getCommonFromForm());
            else SaveDate.getInstance().edit(dialog.getCommon(), dialog.getCommonFromForm());
            closeDialog();
        }
        catch (ModelException ex) {
            ErrorDialog.show(frame, ex.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {}


    // нажатие на клавишу Enter
    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            addEdit(dialog.isAdd());
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {}

    @Override
    public void windowOpened(WindowEvent we) {}

    @Override
    public void windowClosing(WindowEvent we) {
        closeDialog();
    }

    @Override
    public void windowClosed(WindowEvent we) { }

    @Override
    public void windowIconified(WindowEvent we) {}

    @Override
    public void windowDeiconified(WindowEvent we) {}

    @Override
    public void windowActivated(WindowEvent we) {}

    @Override
    public void windowDeactivated(WindowEvent we) {}

    private void closeDialog() {
        dialog.closeDialog();
    }

}
