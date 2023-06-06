package gui.table.model;

import model.Transaction;
import saveload.SaveDate;
import settings.Format;


// вывод последних транзакций в таблице
public class TransactionTableModel extends MainTableModel {

    private static final int DATE = 0;
    private static final int ACCOUNT = 1;
    private static final int ARTICLE = 2;
    private static final int AMOUNT = 3;
    private static final int NOTICE = 4;

    private int count = -1;


    // конструктор выводит по фильтру
    public TransactionTableModel(String[] columns) {
        super(SaveDate.getInstance().getFilterTransactions(), columns);
    }

    // конструктор выводит по кол-ву
    public TransactionTableModel(String[] columns, int count) {
        super(SaveDate.getInstance().getTransactionsOnCount(count), columns);
        this.count = count;
    }

    // обновляем данные
    @Override
    protected void updateData() {
        if (count == -1) data = SaveDate.getInstance().getFilterTransactions();
        else data = SaveDate.getInstance().getTransactionsOnCount(count);
    }

    // получение содержимого ячеек
    @Override
    public Object getValueAt(int row, int column) {
        if (data.isEmpty()) return null;
        Transaction transaction = (Transaction) data.get(row);
        switch (column) {
            case DATE:
                return Format.date(transaction.getDate());
            case ACCOUNT:
                return transaction.getAccount().getTitle();
            case ARTICLE:
                return transaction.getArticle().getTitle();
            case AMOUNT:
                return Format.amount(transaction.getAmount(), transaction.getAccount().getCurrency());
            case NOTICE:
                return transaction.getNotice();
        }
        return null;
    }

}
