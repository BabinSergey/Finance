package gui.panel;

import gui.MainFrame;
import gui.dialog.TransactionAddEditDialog;
import gui.handler.FunctionsHandler;
import gui.table.TransactionTableData;
import gui.toolbar.FunctionToolBar;
import settings.Style;

import javax.swing.*;

// выводим таблицу с транзакциями в правой панели
public class TransactionPanel extends RightPanel {

    public TransactionPanel(MainFrame frame) {
        super(frame, new TransactionTableData(new FunctionsHandler(frame, new TransactionAddEditDialog(frame))),
                "TRANSACTIONS", Style.ICON_PANEL_TRANSACTIONS,
                new JPanel[] {new FunctionToolBar(new FunctionsHandler(frame, new TransactionAddEditDialog(frame))), new FilterPanel(frame)});
    }

}
