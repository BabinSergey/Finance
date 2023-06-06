package gui.handler;

import gui.MainFrame;
import settings.HandlerCode;

import java.awt.event.ActionEvent;

// обработчик для меню правка
public class MenuEditHandler extends Handler {

    public MenuEditHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        FunctionsHandler handler = frame.getRightPanel().getTableData().getFunctionHandler();
        switch (ae.getActionCommand()) {
            case HandlerCode.MENU_EDIT_ADD: {
                handler.add();
                break;
            }
            case HandlerCode.MENU_EDIT_EDIT: {
                handler.edit();
                break;
            }
            case HandlerCode.MENU_EDIT_DELETE: {
                handler.delete();
            }

        }
        super.actionPerformed(ae);
    }

}
