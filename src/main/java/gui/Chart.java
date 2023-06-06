package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import settings.Style;
import settings.Text;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


// класс отвечающий за гистограмму в статистике
public final class Chart {

    private DefaultPieDataset dataset;
    private final String title; // заголовок
    private final String currencyTitle; // заголовок валюты

    public Chart(HashMap<String, Double> data, String title, String currencyTitle) {
        setData(data);
        this.title = Text.get(title);
        this.currencyTitle = currencyTitle;
    }

    // создаем конструктор и заполяем данные
    private void setData(HashMap<String, Double> data) {
        dataset = new DefaultPieDataset();
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
    }


    // строим график и загружаем в panel
    public JPanel getPanel() {
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setToolTipGenerator(new StandardPieToolTipGenerator("{0}: {1} " + currencyTitle + " ({2})")); // вид всплывающих подсказок
        JPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(Style.DIMENSION_CHART); // делаем размер
        if (dataset.getItemCount() == 0) {
            panel.setLayout(new GridBagLayout());
            JLabel label = new JLabel(Text.get("CHART_NO_DATA"));
            label.setFont(Style.FONT_LABEL_HEADER);
            panel.add(label);
        }
        return panel;
    }

}
