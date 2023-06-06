package exception;

public class ModelException extends Exception {

    public static final int TITLE_EMPTY = 1; // пустой заголовок
    public static final int IS_EXISTS = 2; //  объект уже существует
    public static final int CURRENCY_EMPTY = 3; // не указана валюта
    public static final int ARTICLE_EMPTY = 4; // не указана статья
    public static final int ACCOUNT_EMPTY = 5; // не указан счет
    public static final int RATE_INCORRECT = 6; // не корректное значение курса
    public static final int AMOUNT_FORMAT = 7; // не правильный ввод
    public static final int NO_BASE_CURRENCY = 8; // не указана базовая валюта


    private final int code;

    public ModelException(int code) {
        this.code = code;
    }

    public String getMessage() {
        switch (code) {
            case TITLE_EMPTY:
                return "ERROR_TITLE_EMPTY";
            case IS_EXISTS:
                return "ERROR_IS_EXISTS";
            case CURRENCY_EMPTY:
                return "ERROR_CURRENCY_EMPTY";
            case ARTICLE_EMPTY:
                return "ERROR_ARTICLE_EMPTY";
            case ACCOUNT_EMPTY:
                return "ERROR_ACCOUNT_EMPTY";
            case RATE_INCORRECT:
                return "ERROR_RATE_INCORRECT";
            case AMOUNT_FORMAT:
                return "ERROR_AMOUNT_FORMAT";
            case NO_BASE_CURRENCY:
                return "ERROR_NO_BASE_CURRENCY";
            }
            return "";
        }
}
