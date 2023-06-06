package saveload;

import exception.ModelException;
import model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public final class SaveDate {

    private static SaveDate instance;

    private List<Article> articles = new ArrayList();
    private List<Currency> currencies = new ArrayList();
    private List<Account> accounts = new ArrayList();
    private List<Transaction> transactions = new ArrayList();
    private List<Transfer> transfers = new ArrayList();


    public List<Article> getArticles() {
        return articles;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setArticles(List<Article> articles) {
        if (articles != null) this.articles = articles;
    }

    public void setCurrencies(List<Currency> currencies) {
        if (currencies != null) this.currencies = currencies;
    }

    public void setAccounts(List<Account> accounts) {
        if (accounts != null) this.accounts = accounts;
    }

    public void setTransactions(List<Transaction> transactions) {
        if (transactions != null) this.transactions = transactions;
    }

    public void setTransfers(List<Transfer> transfers) {
        if (transfers != null) this.transfers = transfers;
    }

    public static SaveDate getInstance() {
        if (instance == null) instance = new SaveDate();
        return instance;
    }

    public Currency getBaseCurrency() {
        for (Currency c : currencies)
            if (c.isBase()) return c;
        return new Currency();
    }

    // Фильтр

    private Common oldCommon; // сохранение старых данных
    private final Filter filter;
    private boolean saved = true; // проверка на обновление данных

    private SaveDate() {
        load();
        this.filter = new Filter();
    }

    public void load() {
        SaveLoad.load(this);
        sort();
        for (Account a : accounts) {
            a.setAmountFromTransactionsAndTransfers(transactions, transfers);
        }
    }

    public void clear() {
        articles.clear();
        currencies.clear();
        accounts.clear();
        transactions.clear();
        transfers.clear();
    }

    public Filter getFilter() {
        return filter;
    }

    // сортировка для загрузки
    private void sort() {
        this.articles.sort((Article a, Article a1) -> a.getTitle().compareToIgnoreCase(a1.getTitle())); // сортировка статей по алфавиту
        this.accounts.sort((Account a, Account a1) -> a.getTitle().compareToIgnoreCase(a1.getTitle())); // сортировка счетов по алфавиту
        this.transactions.sort((Transaction t, Transaction t1) -> (int) t1.getDate().compareTo(t.getDate())); // сортировка транзакций по дате от последней
        this.transfers.sort((Transfer t, Transfer t1) -> (int) t1.getDate().compareTo(t.getDate())); // сортировка переводов по дате от последней
        this.currencies.sort(new Comparator<Currency>() {  // сортировка валют
            @Override
            public int compare(Currency c, Currency c1) {
                if (c.isBase()) return -1;
                if (c1.isBase()) return 1;
                if (c.isBase() ^ c1.isBase()) {
                    if (c1.isOn()) return 1;
                    else return -1;
                }
                return c.getTitle().compareToIgnoreCase(c1.getTitle());
            }
        });
    }

    // сохранение
    public void save() {
        SaveLoad.save(this);
        saved = true;
    }

    public boolean isSaved() {
        return saved;
    }

    // добавляем в лист все валюты которые включены
    public ArrayList<Currency> getEnableCurrencies() {
        ArrayList<Currency> list = new ArrayList();
        for (Currency c : currencies) {
            if (c.isOn()) list.add(c);
        }
        return list;
    }

    // методы возвращающие отфильтрованные данные
    public List<Transaction> getFilterTransactions() {
        ArrayList<Transaction> list = new ArrayList();
        for (Transaction t : transactions) {
            if (filter.check(t.getDate())) list.add(t);
        }
        return list;
    }

    public List<Transfer> getFilterTransfers() {
        ArrayList<Transfer> list = new ArrayList();
        for (Transfer t : transfers) {
            if (filter.check(t.getDate())) list.add(t);
        }
        return list;
    }

    public List<Transaction> getTransactionsOnCount(int count) { // возвращаем последние транзакции
        return new ArrayList(transactions.subList(0, Math.min(count, transactions.size())));
    }

    public Common getOldCommon() {
        return oldCommon;
    }

    // добавление в хранилище
    public void add(Common c) throws ModelException {
        List ref = getRef(c);
        if (ref.contains(c)) throw new ModelException(ModelException.IS_EXISTS);
        ref.add(c);
        c.postAdd(this);
        sort();
        saved = false;
    }

    private List getRef(Common c) { // метод получения ссылки на массив данных
        if (c instanceof Account) return accounts;
        else if (c instanceof Article) return articles;
        else if (c instanceof Currency) return currencies;
        else if (c instanceof Transaction) return transactions;
        else if (c instanceof Transfer) return transfers;
        return null;
    }

    // редактирование
    public void edit(Common oldC, Common newC) throws ModelException {
        List ref = getRef(oldC);
        if (ref.contains(newC) && oldC != ref.get(ref.indexOf(newC))) throw new ModelException(ModelException.IS_EXISTS);
        ref.set(ref.indexOf(oldC), newC);
        oldCommon = oldC;
        newC.postEdit(this);
        sort();
        saved = false;
    }

    // удаление
    public void remove(Common c) {
        getRef(c).remove(c);
        c.postDelete(this);
        saved = false;
    }

    @Override
    public String toString() {
        return "SaveDate{" +
                "articles=" + articles +
                ", currencies=" + currencies +
                ", accounts=" + accounts +
                ", transactions=" + transactions +
                ", transfers=" + transfers +
                '}';
    }

    // метод получения курса валют из RateCurrency
    public void updateCurrencies() throws Exception {
        HashMap<String, Double> rates = RateCurrency.getRates(getBaseCurrency());
        for (Currency c : currencies) {
            c.setRate(rates.get(c.getCode()));
        }
        for (Account a : accounts) {
            a.getCurrency().setRate(rates.get(a.getCurrency().getCode()));
        }
        saved = false;
    }
}
