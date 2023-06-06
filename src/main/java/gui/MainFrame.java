package gui;

import gui.handler.MainToolBarHandler;
import gui.handler.MainWindowHandler;
import gui.menu.MainMenu;
import gui.panel.LeftPanel;
import gui.panel.OverviewPanel;
import gui.panel.RightPanel;
import gui.toolbar.MainToolBar;
import settings.Style;
import settings.Text;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Refresh {

    private final GridBagConstraints constraints; // поле для последующих ограничений компонентов
    private final MainMenu mb;
    private final LeftPanel leftPanel;
    private RightPanel rightPanel;
    private final MainToolBar tb;

    public MainFrame() {
        super(Text.get("PROGRAM_NAME"));



        setResizable(false); // не меняется размер
//        setSize(850, 500); // размер окна
        setIconImage(Style.ICON_MAIN.getImage()); // выбираем иконку для главного окна
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Закрываем программу при закрытии окна
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        mb = new MainMenu(this);
        setJMenuBar(mb);

        setLayout(new GridBagLayout()); // компоновка блоков в главном окне

        constraints = new GridBagConstraints();

        constraints.gridx = 0; // координаты ячеек (первой ячейки на первой строке)
        constraints.gridy = 0;
        constraints.gridwidth = 2;

        tb = new MainToolBar(new MainToolBarHandler(this));
        add(tb, constraints);



        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTH; // привязка панели

// календарь
//        add(new MainDatePicker().getDatePicker(), constraints);

        leftPanel = new LeftPanel(this);
        add(leftPanel,constraints);

        setRightPanel(new OverviewPanel(this));

        pack();

        addWindowListener(new MainWindowHandler());

    }

    // метод обновления окон интерфейса (перерисовался)
    @Override
    public void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
        mb.refresh();
        leftPanel.refresh();
        rightPanel.refresh();
        pack();
    }

    public MainMenu getMenu() {
        return mb;
    }

    public void setRightPanel(RightPanel panel) {
        if (rightPanel != null) remove(rightPanel);
        constraints.gridy = 1;
        constraints.gridx = 1;
        rightPanel = panel;
        panel.setBorder((Style.BORDER_PANEL));
        add(rightPanel, constraints);
        pack();
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }
}
