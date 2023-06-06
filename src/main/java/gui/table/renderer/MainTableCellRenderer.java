package gui.table.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// стили для ячеек (выравнивание и т.д.)
public class MainTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBorder(noFocusBorder);
        setHorizontalAlignment(SwingConstants.CENTER);
        return this;
    }

}

