package model;

import exception.ModelException;
import saveload.SaveDate;

import java.util.Objects;

public class Currency extends Common {

    private String title;
    private String code;
    private double rate;
    private boolean on;
    private boolean base;

    public Currency(String title, String code, double rate, boolean on, boolean base) throws ModelException {
        if (title.length() == 0) throw new ModelException(ModelException.TITLE_EMPTY);
        if (rate <= 0) throw new ModelException(ModelException.RATE_INCORRECT);

        this.title = title;
        this.code = code;
        this.rate = rate;
        this.on = on;
        this.base = base;
        if (this.base) this.on = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean isBase() {
        return base;
    }

    public void setBase(boolean base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", rate=" + rate +
                ", on=" + on +
                ", base=" + base +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return code.equals(currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public Currency() {}

    public String getValueForCombobox() {
        return title;
    }
    public double getRateByCurrency (Currency currency) { // получение курса относительно базового курса
        return rate / currency.rate;
    }

    @Override
    public void postAdd(SaveDate sd) {
        clearBase(sd);
    }

    @Override
    public void postEdit(SaveDate sd) {
        clearBase(sd);
        for (Currency c : sd.getCurrencies()) {
            for (Account a : sd.getAccounts()) {
                if (a.getCurrency().equals(c)) a.setCurrency(c);
            }
            for (Transaction t : sd.getTransactions())
                if (t.getAccount().getCurrency().equals(c)) t.getAccount().setCurrency(c);
            for (Transfer t : sd.getTransfers()) {
                if (t.getFromAccount().getCurrency().equals(c)) t.getFromAccount().setCurrency(c);
                if (t.getToAccount().getCurrency().equals(c)) t.getToAccount().setCurrency(c);
            }
        }

    }

    private void clearBase(SaveDate sd) {
        if (base) {
            rate = 1;
            Currency old = (Currency) sd.getOldCommon();
            for (Currency c : sd.getCurrencies()) {
                if (!this.equals(c)) {
                    c.setBase(false);
                    if (old != null) {
                        c.setRate(c.rate / old.rate);
                    }
                }
            }
        }
    }
}