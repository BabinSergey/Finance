package gui.table.model;


import model.Article;
import saveload.SaveDate;

// вывод таблицы со стятьями в правом окне
public class ArticleTableModel extends MainTableModel {

    private static final int TITLE = 0;

    public ArticleTableModel(String[] columns) {
        super(SaveDate.getInstance().getArticles(), columns);
    }

    @Override
    protected void updateData() {
        data = SaveDate.getInstance().getArticles();
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (data.isEmpty()) return null;
        Article article = (Article) data.get(row);
        switch (column) {
            case TITLE:
                return article.getTitle();
        }
        return null;
    }

}

