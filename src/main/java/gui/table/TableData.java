package gui.table;

import gui.Refresh;
import gui.handler.FunctionsHandler;
import gui.menu.TablePopupMenu;
import gui.table.model.MainTableModel;
import gui.table.renderer.MainTableCellRenderer;
import gui.table.renderer.TableHeaderIconRenderer;
import settings.Style;
import settings.Text;

import javax.swing.*;
import java.awt.*;

// класс вывода (демонстрации) модели таблицы в правой панели
abstract public class TableData extends JTable implements Refresh {

    private final FunctionsHandler handler;
    private final TablePopupMenu popup;
    private final String[] columns;
    private final ImageIcon[] icons;


    public TableData(MainTableModel model, FunctionsHandler handler, String[] columns, ImageIcon[] icons) {
        super(model);
        this.handler = handler;
        this.popup = new TablePopupMenu(handler);
        this.columns = columns;
        this.icons = icons;

        // внешний вид таблицы
        getTableHeader().setFont(Style.FONT_TABLE_HEADER);
        setFont(Style.FONT_TABLE);
        setRowHeight(getRowHeight() + Style.TABLE_ADD_ROW_HEIGHT);

        setAutoCreateRowSorter(true); // добавляем сортировки по столбцам
        setPreferredScrollableViewportSize(Style.DIMENSION_TABLE_SHOW_SIZE); // размер таблицы
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // запрет на выбор нескольких строк

        addMouseListener(handler);
        addKeyListener(handler);

        // применяем рендеринг
        for (int i = 0; i < columns.length; i++) {
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }

        MainTableCellRenderer renderer = new MainTableCellRenderer();
        setDefaultRenderer(String.class, renderer);
        setComponentPopupMenu(popup);
    }

    @Override
    public JPopupMenu getComponentPopupMenu() {
        Point p = getMousePosition();
        if (p != null) {
            int row = rowAtPoint(p);
            if (isRowSelected(row)) return super.getComponentPopupMenu();
            else return null;
        }
        return super.getComponentPopupMenu();
    }

    @Override
    public void refresh() {
        int selectedRow = getSelectedRow(); // сохраняем выделенную строку
        ((MainTableModel) getModel()).refresh(); // обновляем модель
        for (int i = 0; i < columns.length; i++) {
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }
        if (selectedRow != -1 && selectedRow < getRowCount()) { // возвращаем выделенную строку
            setRowSelectionInterval(selectedRow, selectedRow);
            requestFocus();
        }
        init();
    }

    protected void init() {

    }

    public FunctionsHandler getFunctionHandler() {
        return handler;
    }

}
