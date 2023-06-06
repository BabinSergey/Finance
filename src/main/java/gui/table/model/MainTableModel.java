package gui.table.model;


import gui.Refresh;
import model.Common;
import settings.Text;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// модель таблиц
abstract public class MainTableModel extends AbstractTableModel implements Refresh {

    protected List<? extends Common> data; // может быть любой класс от Common
    protected List<String> columns = new ArrayList();

    public MainTableModel(List data, String[] columns) {
        this.data = data;
        this.columns = new ArrayList(Arrays.asList(columns));
    }

    //метод возвращает кол-во строк
    @Override
    public int getRowCount() {
        return data.size();
    }

    // метод возвращает кол-во столбцов
    @Override
    public int getColumnCount() {
        return columns.size();
    }

    // метод получение названия столбца
    @Override
    public String getColumnName(int columnIndex) {
        return Text.get(columns.get(columnIndex));
    }

    // метод получения класса по столбцу
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object obj = getValueAt(0, columnIndex);
        if (obj == null) return Object.class;
        return obj.getClass();
    }

    @Override
    public void refresh() {
        updateData();
        fireTableStructureChanged();
        fireTableDataChanged();
    }

    public Common getCommonByRow(int row) {
        return data.get(row);
    }

    public Object getObjectByRow(int row) {
        return data.get(row);
    }

    abstract protected void updateData();

}
