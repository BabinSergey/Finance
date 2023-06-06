package finance;

import gui.MainFrame;
import saveload.SaveDate;
import settings.Text;
import settings.Settings;

public class Finance {
    public static void main(String[] args) throws Exception {
        init();
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        SaveDate sd = SaveDate.getInstance();
//        sd.updateCurrencies();
//        System.out.println(sd);
//        testModel();
    }

    private static void init() {
        Settings.init();
        Text.init();
    }

}