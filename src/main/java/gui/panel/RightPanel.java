package gui.panel;

import gui.EnableEditDelete;
import gui.MainFrame;
import gui.Refresh;
import gui.table.TableData;
import gui.toolbar.AbstractToolBar;
import settings.Style;
import settings.Text;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


// астрактный класс для создания правой панели
abstract public class RightPanel extends AbstractPanel {

    protected TableData td;

    private String title;
    private ImageIcon icon;
    private JPanel[] panels; // массив панелей

    public RightPanel(MainFrame frame, TableData td, String title, ImageIcon icon, JPanel[] panels) {
        super(frame);
        this.td = td;
        this.title = title;
        this.icon = icon;
        this.panels = panels;
        init();
    }

    // перегрузка когда нет таблицы
    public RightPanel(MainFrame frame, TableData td, String title, ImageIcon icon, AbstractToolBar tb) {
        this(frame, td, title, icon, new JPanel[]{tb});
    }

    // конструктор без панелей
    public RightPanel(MainFrame frame, TableData td, String title, ImageIcon icon) {
        this(frame, td, title, icon, new JPanel[]{});
    }

    // метод для статистики
    protected void setPanels(JPanel[] panels) {
        this.panels = panels;
    }

    // переопределяем метод для перерисовки таблиц
    @Override
    public void refresh() {
        super.refresh();
        if (td != null) td.refresh();
        for (JPanel panel : panels) {
            if (panel instanceof Refresh) ((Refresh) panel).refresh();
        }
    }


    // метод доступности пуктов в меню
    private void enableEditDelete() {
        for (JPanel panel : panels) {
            if (panel instanceof EnableEditDelete) ((EnableEditDelete) panel).setEnableEditDelete(false);
        }
        frame.getMenu().setEnableEditDelete(false);

        // проверка какую строку выбрал пользователь
        if (td != null) {
            if (td.getSelectedRow() != -1) {
                for (JPanel panel : panels) {
                    if (panel instanceof EnableEditDelete) ((EnableEditDelete) panel).setEnableEditDelete(true);
                }
                frame.getMenu().setEnableEditDelete(true);
            }
        }
    }

    // иницилизируем
    @Override
    protected final void init() {
        enableEditDelete();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel header = new JLabel(Text.get(title));
        header.setFont(Style.FONT_LABEL_HEADER);
        header.setIcon(icon);
        header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(header);

        // если нет таблицы добавляем отступ
        if (panels.length == 0) add(Box.createVerticalStrut(Style.PADDING_PANEL_EMPTY));

        for (JPanel panel : panels) {
            add(panel);
            add(Box.createVerticalStrut(Style.PADDING_PANEL));
        }

        // выводим таблицу если она есть
        if (td != null) {
            JScrollPane scroll = new JScrollPane(td);
            add(scroll);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            ListSelectionModel selectionModel = td.getSelectionModel();
            selectionModel.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    enableEditDelete();
                }
            });
        }

    }

    public TableData getTableData() {
        return td;
    }

}

