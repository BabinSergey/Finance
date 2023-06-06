package gui;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import settings.Text;

import java.util.Date;
import java.util.Properties;

public class MainDatePicker {

    // создаем календарь для управления датами
    private final JDatePickerImpl datePicker;

    public MainDatePicker() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", Text.get("TODAY"));
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        model.setValue(new Date());

    }

    public JDatePickerImpl getDatePicker() {
        return datePicker;
    }

    // метод передающий в модель дату с календаря (если есть значение берется оно)
    public void setValue(Date date) {
        ((UtilDateModel) datePicker.getModel()).setValue(date);
    }

}