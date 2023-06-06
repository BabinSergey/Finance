package gui.panel;

import gui.MainFrame;
import gui.dialog.TransferAddEditDialog;
import gui.handler.FunctionsHandler;
import gui.table.TransferTableData;
import gui.toolbar.FunctionToolBar;
import settings.Style;

import javax.swing.*;

// выводим панель переводов в правую панель
public class TransferPanel extends RightPanel {

    public TransferPanel(MainFrame frame) {
        super(frame, new TransferTableData(new FunctionsHandler(frame, new TransferAddEditDialog(frame))),
                "TRANSFERS", Style.ICON_PANEL_TRANSFERS,
                new JPanel[] {new FunctionToolBar(new FunctionsHandler(frame, new TransferAddEditDialog(frame))), new FilterPanel(frame)});
    }

}

