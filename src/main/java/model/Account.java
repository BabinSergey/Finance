package model;

import exception.ModelException;
import saveload.SaveDate;

import java.util.List;
import java.util.Objects;

public class Account extends Common{

    private String title;
    private Currency currency;
    private double startAmount;
    private double amount;

    public Account(String title, Currency currency, double startAmount) throws ModelException {
        if (title.length() == 0) throw new ModelException(ModelException.TITLE_EMPTY);
        if (currency == null) throw new ModelException(ModelException.CURRENCY_EMPTY);

        this.title = title;
        this.currency = currency;
        this.startAmount = startAmount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return title.equals(account.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public Account() {}

    public double getAmount() {
        return amount;
    }

    public String getValueForCombobox() {
        return title;
    }

    public void setAmountFromTransactionsAndTransfers(List<Transaction> transactions, List<Transfer> transfers) {
        this.amount = startAmount;
        for (Transaction transaction : transactions) {
            if (transaction.getAccount().equals(this)) { // проверяем относиться к текущему счету
                this.amount += transaction.getAmount();
            }
        }
        for (Transfer transfer : transfers) {
            if (transfer.getFromAccount().equals(this)) {
                this.amount -= transfer.getFromAmount();
            }
            if (transfer.getToAccount().equals(this)) {
                this.amount += transfer.getToAmount();
            }
        }
    }

    @Override
    public void postAdd(SaveDate sd) {
        setAmountFromTransactionsAndTransfers(sd.getTransactions(), sd.getTransfers());
    }

    @Override
    public void postEdit(SaveDate sd) {
        for (Transaction t : sd.getTransactions()) {
            if (t.getAccount().equals(sd.getOldCommon())) t.setAccount(this);
        }
        for (Transfer t : sd.getTransfers()) {
            if (t.getFromAccount().equals(sd.getOldCommon())) t.setFromAccount(this);
            if (t.getToAccount().equals(sd.getOldCommon())) t.setToAccount(this);
        }
        setAmountFromTransactionsAndTransfers(sd.getTransactions(), sd.getTransfers());
    }

    @Override
    public String toString() {
        return "Account{" +
                "title='" + title + '\'' +
                ", currency=" + currency +
                ", startAmount=" + startAmount +
                ", amount=" + amount +
                '}';
    }
}
