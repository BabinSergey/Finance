package gui.panel;

import gui.MainFrame;
import model.Currency;
import model.Statistics;
import saveload.SaveDate;
import settings.Format;
import settings.Style;
import settings.Text;

import javax.swing.*;
import java.awt.*;

// создание левой панели
public final class LeftPanel extends AbstractPanel {

    public LeftPanel(MainFrame frame) { // вызываем конструктор инициализации
        super(frame);
        init();
    }

    @Override
    protected void init() {
        // вывод заголовка
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // вертикальное выравнивание
        setBorder(Style.BORDER_LEFT_PANEL); // выводим заголовок
        JLabel headerBC = new JLabel(Text.get("BALANCE_CURRENCIES"));// выводим заголовок, делаем фон, иконку
        headerBC.setFont(Style.FONT_LABEL_HEADER);
        headerBC.setIcon(Style.ICON_LEFT_PANEL_BALANCE_CURRENCIES);
        headerBC.setAlignmentX(JComponent.CENTER_ALIGNMENT); // выравниваем заголовок по центру
        add(headerBC); // добавляем на панель

        addBalanceCurrency();

        add(Box.createVerticalStrut(Style.PADDING_PANEL_BIG));

        JLabel headerB = new JLabel(Text.get("BALANCE"));
        headerB.setFont(Style.FONT_LABEL_HEADER);
        headerB.setIcon(Style.ICON_LEFT_PANEL_BALANCE);
        headerB.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(headerB);

        addBalance();
    }

    // методы добавление валют
    private void addBalanceCurrency() {
        for (Currency currency : SaveDate.getInstance().getEnableCurrencies()) {
            add(Box.createVerticalStrut(Style.PADDING_BALANCE));
            add(new PanelBalanceCurrency(currency, Statistics.getBalanceCurrency(currency)));
        }
    }

    private void addBalance() {
        for (Currency currency : SaveDate.getInstance().getEnableCurrencies()) {
            add(Box.createVerticalStrut(Style.PADDING_BALANCE));
            add(new PanelBalanceCurrency(currency, Statistics.getBalance(currency))); // добавляем панельку методом
        }
    }

    // выводим горизонтальные панели
    private class PanelBalanceCurrency extends JPanel {

        public PanelBalanceCurrency(Currency currency, double amount) {
            super();
            setLayout(new BorderLayout());
            setBackground(Style.COLOR_LEFTPANEL_BALANCE);
            setBorder(Style.BORDER_PANEL);

            // метки для вывода название и суммы с валютой
            JLabel currencyLabel = new JLabel(currency.getTitle());
            JLabel amountLabel = new JLabel(Format.amount(amount, currency));

            // шрифт для элементов
            currencyLabel.setFont(Style.FONT_LABEL_LEFT_PANEL_CURRENCY);
            amountLabel.setFont(Style.FONT_LABEL_LEFT_PANEL_AMOUNT);

            // добавляем элементы
            add(currencyLabel, BorderLayout.WEST);
            add(Box.createRigidArea(Style.DIMENSION_PADDING_BALANCE));
            add(amountLabel, BorderLayout.EAST);
        }
    }
}

