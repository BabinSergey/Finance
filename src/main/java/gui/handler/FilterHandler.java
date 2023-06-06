package gui.handler;

import gui.MainFrame;
import saveload.SaveDate;
import settings.HandlerCode;

import java.awt.event.ActionEvent;

// обработчик фильтра
public class FilterHandler extends Handler {

    public FilterHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.LEFT: { // кнопка влево
                SaveDate.getInstance().getFilter().prev();
                break;
            }
            case HandlerCode.RIGHT: { // кнопка вправо
                SaveDate.getInstance().getFilter().next();
                break;
            }
            case HandlerCode.STEP: { // кнопка шаг
                SaveDate.getInstance().getFilter().nextPeriod();
            }
        }
        super.actionPerformed(ae);
    }

}

