package gui.table;

import gui.handler.FunctionsHandler;
import gui.table.model.TransferTableModel;
import gui.table.renderer.MainTableCellRenderer;
import settings.Style;
import settings.Text;

import javax.swing.*;
import java.awt.*;

// создание таблицы с переводами
public class TransferTableData extends TableData {

    private static final String[] columns = new String[]{"DATE", "FROM_ACCOUNT", "TO_ACCOUNT", "FROM_AMOUNT", "TO_AMOUNT", "NOTICE"};
    private static final ImageIcon[] icons = new ImageIcon[]{Style.ICON_DATE, Style.ICON_ACCOUNT, Style.ICON_ACCOUNT, Style.ICON_AMOUNT, Style.ICON_AMOUNT, Style.ICON_NOTICE};

    public TransferTableData(FunctionsHandler handler) {
        super(new TransferTableModel(columns), handler, columns, icons);
        init();
    }

    @Override
    protected final void init() {
        getColumn(Text.get("FROM_AMOUNT")).setCellRenderer(new TableCellAmountRenderer(Style.COLOR_EXP));
        getColumn(Text.get("TO_AMOUNT")).setCellRenderer(new TableCellAmountRenderer(Style.COLOR_INCOME));
    }

    private class TableCellAmountRenderer extends MainTableCellRenderer {

        private final Color color;

        public TableCellAmountRenderer(Color color) {
            this.color = color;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            renderer.setForeground(color);
            return renderer;
        }

    }

}
