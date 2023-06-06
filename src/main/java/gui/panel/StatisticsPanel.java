package gui.panel;

import gui.Chart;
import gui.MainFrame;
import model.Statistics;
import saveload.SaveDate;
import settings.Style;

import javax.swing.*;


// панель со статистикой
public class StatisticsPanel extends RightPanel {

    public static final int TYPE_INCOME = 0; // доходы по статьям
    public static final int TYPE_EXP = 1; // расходы по статьям
    private int type = TYPE_INCOME; // текущие расходы

    public StatisticsPanel(MainFrame frame) {
        super(frame, null, "STATISTICS", Style.ICON_PANEL_STATISTICS,
                new JPanel[]{
                        new FilterPanel(frame),
                        new StatisticsTypePanel(frame, "CHART_INCOME"),
                        new Chart(Statistics.getDataForChartOnIncomeArticles(), "CHART_INCOME", SaveDate.getInstance().getBaseCurrency().getCode()).getPanel()
                });
    }

    // метод смены типа (расходов -> доходы -> рас...)
    public void nextType() {
        type++;
        if (type > TYPE_EXP) type = TYPE_INCOME;
    }

    // обновляем
    @Override
    public void refresh() {
        Chart chart = null;
        String title = null;
        if (type == TYPE_INCOME) {
            title = "CHART_INCOME";
            chart =  new Chart(Statistics.getDataForChartOnIncomeArticles(), title, SaveDate.getInstance().getBaseCurrency().getCode());
        }
        else if (type == TYPE_EXP) {
            title = "CHART_EXP";
            chart =  new Chart(Statistics.getDataForChartOnExpArticles(), title, SaveDate.getInstance().getBaseCurrency().getCode());
        }
        setPanels(new JPanel[]{
                new FilterPanel(frame),
                new StatisticsTypePanel(frame, title),
                chart.getPanel()
        });
        super.refresh();
    }

}

