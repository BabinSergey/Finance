package settings;


import model.Currency;
import model.Filter;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

// класс отвечающий за использование констант
final public class Format {

    public static String amount(double amount) { // метод принимает сумму и возвращает строку в нашем формате
        return String.format(Settings.FORMAT_AMOUNT, amount);
    }

    public static String amount(double amount, Currency currency) { // метод добавляет валюту к сумме
        return amount(amount) + " " + currency.getCode();
    }

    public static String rate(double rate) { // метод принимает курс и возвращает строку в нашем формате
        return String.format(Settings.FORMAT_RATE, rate);
    }

    public static String rate(double rate, Currency currency) { // метод добавляет валюту к курсу
        return amount(rate) + " " + currency.getCode();
    }

    public static String date(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE); // метод перевода даты в строку
    }

    public static String dateMonth(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE_MONTH); // метод перевода даты в строку
    }

    public static String dateYear(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE_YEAR); // метод перевода даты в строку
    }

    private static String dateFormat(Date date, String format) { // обрабатываем методы перевода даты в строку
        SimpleDateFormat sdf = new SimpleDateFormat(format, new MainDateFormatSymbols());
        return sdf.format(date);
    }

    public static double fromAmountToNumber(String amount) throws NumberFormatException { // метод перевода строки в число
        amount = amount.replaceAll(",", ".");  // заменяем запятые на точки для корректной обработки
        return Double.parseDouble(amount);
    }

    public static String yesNo(boolean yes) { // Вывод формата слов Да и Нет
        if (yes) return Text.get("YES");
        return Text.get("NO");
    }

    public static String getTitleFilter(Filter filter) { // форматирование фильтра
        Date time = filter.getTo();
        switch (filter.getStep()) {
            case Filter.STEP_DAY:
                return date(time);
            case Filter.STEP_MONTH:
                return dateMonth(time);
            case Filter.STEP_YEAR:
                return dateYear(time);
        }
        return null;
    }

    // меняем формат даты (делаем с большой буквы месяц и убираем лишние символы)
    private static class MainDateFormatSymbols extends DateFormatSymbols {
        @Override
        public String[] getMonths() {
            return Text.getMonths();
        }
    }
}
