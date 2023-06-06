package gui.handler;

import gui.MainFrame;
import gui.panel.StatisticsPanel;
import settings.HandlerCode;

import java.awt.event.ActionEvent;

// обработчи переключения доход/расход в статистике
public class ChartHandler extends Handler {

    public ChartHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.TYPE: {
                ((StatisticsPanel) frame.getRightPanel()).nextType();
            }
        }
        super.actionPerformed(ae);
    }

}
