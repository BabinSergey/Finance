package settings;

import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.prefs.Preferences;

final public class Settings {

    public static final File SAVE_DIR = new File("saves/"); // сохранение по умолчанию
    public static final String SAVE_FILE_EXT = "xml"; // расширение для файлов

    public static final String FORMAT_AMOUNT = "%.2f"; // формат вывода суммы
    public static final String FORMAT_RATE = "%.4f"; // формат вывода курсов валют
    public static final String FORMAT_DATE = "dd.MM.yyyy"; // вывод даты день/месяц/год
    public static final String FORMAT_DATE_MONTH = "MMMM.yyyy"; // вывод даты месяц/год
    public static final String FORMAT_DATE_YEAR = "yyyy"; // вывод даты год

    public static final int COUNT_OVERVIEW_ROWS = 10; // кол-во последних транзакций выведенных на экран

    public static final String[] CURRENCIES_CODES = new String[] {"RUB", "USD", "EUR"}; // коды валют

    private static final File FILE_SETTINGS = new File("C:\\Users\\Пользователь\\IdeaProjects\\Finance\\saves\\settings.ini"); // путь сохранения файла

    private static File FILE_SAVE = new File("C:\\Users\\Пользователь\\IdeaProjects\\Finance\\saves\\default.xml"); // путь к последнему открытому файлу

    public static void init() {
        try {
            Ini ini = new Ini(FILE_SETTINGS);
            Preferences prefs = new IniPreferences(ini);
            String file = prefs.node("Settings").get("FILE_SAVE", null);
            if (file != null) {
                FILE_SAVE = new File(file);
            }
            setLocale();
        } catch (IOException e) {  // если файл не существует создаем новый
            System.out.println("INI не нашел файл");
//            save();
        }
    }
    // метод начальной локали устанавливает на русскую версию
    private static void setLocale() {
        Locale.setDefault(new Locale("ru"));
    }

    // добавляем гет и сет для добавления и изменения в файлах
    public static File getFileSave() {
        return FILE_SAVE;
    }

    public static void setFileSave(File file) {
        FILE_SAVE = file;
        save();
    }
    // метод сохранения изменений
    private static void save() {
        try {
            Wini ini = new Wini(FILE_SETTINGS);
            if (FILE_SAVE != null) {
                ini.put("Settings", "FILE_SAVE", FILE_SAVE.getAbsolutePath().replace("\\", "\\\\"));
                ini.store();
            }
        } catch (IOException e) {
            System.out.println("Не возможно сохранить!");
        }
    }
}
