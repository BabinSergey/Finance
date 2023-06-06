package gui.table.model;

import model.Currency;
import saveload.SaveDate;
import settings.Format;

// создаем модель таблицы для валют
public class CurrencyTableModel extends MainTableModel {

    private static final int TITLE = 0;
    private static final int CODE = 1;
    private static final int RATE = 2;
    private static final int ON = 3;
    private static final int BASE = 4;

    public CurrencyTableModel(String[] columns) {
        super(SaveDate.getInstance().getCurrencies(), columns);
    }

    @Override
    protected void updateData() {
        data = SaveDate.getInstance().getCurrencies();
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (data.isEmpty()) return null;
        Currency currency = (Currency) data.get(row);
        switch (column) {
            case TITLE:
                return currency.getTitle();
            case CODE:
                return currency.getCode();
            case RATE:
                return Format.rate(currency.getRate(), SaveDate.getInstance().getBaseCurrency());
            case ON:
                return Format.yesNo(currency.isOn());
            case BASE:
                return Format.yesNo(currency.isBase());
        }
        return null;
    }

}
