package model;

import saveload.SaveDate;

import java.util.HashMap;
import java.util.List;

public class Statistics {

    // возвращает сумму по всем счетам
    public static double getBalanceCurrency(Currency currency) {
        SaveDate saveDate = SaveDate.getInstance();
        double amount = 0;
        for (Account account : saveDate.getAccounts()) {
            if (currency.equals(account.getCurrency())) amount += account.getAmount();
        }
        return amount;
    }

    public static double getBalance(Currency currency) {
        SaveDate saveDate = SaveDate.getInstance();
        double amount = 0;
        for (Account account : saveDate.getAccounts()) {
            amount += account.getAmount() * account.getCurrency().getRateByCurrency(currency);
        }
        return amount;
    }

    public static HashMap<String, Double> getDataForChartOnIncomeArticles() {
        return getDataForChartOnArticles(true);
    }

    public static HashMap<String, Double> getDataForChartOnExpArticles() {
        return getDataForChartOnArticles(false);
    }

    private static HashMap<String, Double> getDataForChartOnArticles(boolean income) {
        List<Transaction> transactions = SaveDate.getInstance().getFilterTransactions();
        HashMap<String, Double> data = new HashMap();
        for (Transaction t : transactions) {
            if ((income && t.getAmount() > 0) || (!income && t.getAmount() < 0)) {
                String key = t.getArticle().getTitle();
                double summa = 0;
                double amount = t.getAmount();
                if (!income) amount *= -1;
                if (data.containsKey(key)) summa = data.get(key);
                summa += amount * t.getAccount().getCurrency().getRateByCurrency(SaveDate.getInstance().getBaseCurrency());
                data.put(key, round(summa));
            }
        }
        return data;
    }

    private static double round(double value) {
        return (double) Math.round(value * 100) / 100;
    }
}
