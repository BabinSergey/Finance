package gui.handler;

import gui.MainFrame;
import gui.dialog.AddEditDialog;
import gui.dialog.ConfirmDialog;
import gui.table.TableData;
import gui.table.model.MainTableModel;
import model.Common;
import saveload.SaveDate;
import settings.HandlerCode;

import javax.swing.*;
import java.awt.event.*;

// обработчик событий тулбара (изменить, добавить ...)
public class FunctionsHandler extends Handler implements MouseListener, KeyListener {

    // вывод диалогового окна для редактирования
    private final AddEditDialog dialog;

    public FunctionsHandler(MainFrame frame, AddEditDialog dialog) {
        super(frame);
        this.dialog = dialog;
    }

    // выбор метода от нажатой кнопки
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.ADD:
                add();
                break;
            case HandlerCode.EDIT:
                edit();
                break;
            case HandlerCode.DELETE:
                delete();
        }
        super.actionPerformed(ae);
    }

    // вызываем диалоговое окно по двойному нажатии кнопки для редактирования
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() instanceof TableData) {
            if (me.getClickCount() == 2 && me.getButton() == MouseEvent.BUTTON1)
                showAddEditDialog(getSelectedCommon());
        }
    }

    // выделение строки при нажатии правой кнопкой мышки
    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() instanceof TableData) {
            if (me.getButton() == MouseEvent.BUTTON3) {
                TableData td = frame.getRightPanel().getTableData();
                int row = td.rowAtPoint(me.getPoint());
                td.setRowSelectionInterval(row, row);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) { // удаление клавишей Delete
        if (ke.getKeyCode() == KeyEvent.VK_DELETE) delete();
        frame.refresh(); // обновляем после удаления
    }

    // добавление
    public void add() {
        showAddEditDialog(null);
    }

    // изменение
    public void edit() {
        showAddEditDialog(getSelectedCommon());
    }

    // удаление
    public void delete() {
        Common c = getSelectedCommon();
        if (c != null) { // проверяем на выделение
            int result = ConfirmDialog.show(frame, "CONFIRM_DELETE_TEXT", "CONFIRM_DELETE_TITLE");
            if (result == JOptionPane.YES_OPTION) { // если да то удаляем объект из SaveDate
                SaveDate.getInstance().remove(c);
            }
        }
    }


    // получение выделенной строки для Edit
    private Common getSelectedCommon() {
        TableData td = frame.getRightPanel().getTableData(); // получение таблицы
        Common c = ((MainTableModel) td.getModel()).getCommonByRow(td.getSelectedRow()); // получение объекта
        return c;
    }

    // диалоговое окно от метода
    private void showAddEditDialog(Common c) {
        dialog.setCommon(c);
        dialog.showDialog();
    }

}
