package gui.handler;

import gui.MainFileChooser;
import gui.MainFrame;
import gui.dialog.ConfirmDialog;
import gui.dialog.ErrorDialog;
import saveload.SaveDate;
import settings.HandlerCode;
import settings.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;


// обработчки меню файл
public class MenuFileHandler extends Handler {

    private final MainFileChooser fc;

    public MenuFileHandler(MainFrame frame) {
        super(frame);
        fc = new MainFileChooser(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.MENU_FILE_NEW: { // создаем новый файл и очищаем все данные
                Settings.setFileSave(null);
                SaveDate.getInstance().clear();
                break;
            }
            case HandlerCode.MENU_FILE_OPEN: { // проверяем выбран ли файл и открываем его
                int result = fc.open();
                if (result == JFileChooser.APPROVE_OPTION) {
                    Settings.setFileSave(fc.getSelectedFile()); // установили файл в настройках
                    SaveDate.getInstance().clear(); // очистили это файл из объекта сохранения
                    SaveDate.getInstance().load(); // и загрузили новые данные
                }
                break;
            }
            case HandlerCode.MENU_FILE_SAVE: {
                if (Settings.getFileSave() == null) { // проверяем есть ли сохраненный файл
                    int result = fc.save();
                    if (result == JFileChooser.APPROVE_OPTION) {
                        String path = fc.getSelectedFile().getAbsolutePath(); // путь к файлу
                        String ext = path.substring(path.lastIndexOf(".") + 1); // проверяем указано ли расширение файла
                        if (ext.equals(Settings.SAVE_FILE_EXT)) Settings.setFileSave(new File(path)); // проверяем расширение файла
                        else Settings.setFileSave(new File(path + "." + Settings.SAVE_FILE_EXT)); // добавляем расширение файла если не совпадает
                    }
                }
                if (Settings.getFileSave() != null) SaveDate.getInstance().save(); // сохраняем если пользователь выбрал файл
                break;
            }
            case HandlerCode.MENU_FILE_UPDATE_CURRENCIES: { // обновляем валюты
                try {
                    SaveDate.getInstance().updateCurrencies();
                } catch (Exception ex) {
                    ErrorDialog.show(frame, "ERROR_UPDATE_CURRENCIES");
                }
                break;
            }
            case HandlerCode.MENU_FILE_EXIT: { // проверяем сохранены ли данные, закрываем если сохранены
                if (SaveDate.getInstance().isSaved()) System.exit(0);
                else {
                    int result = ConfirmDialog.show(frame, "CONFIRM_EXIT_TEXT", "CONFIRM_EXIT_TITLE");
                    if (result == JOptionPane.YES_OPTION) System.exit(0); // выход без сохранения
                }
            }

        }
        super.actionPerformed(ae);
    }

}

