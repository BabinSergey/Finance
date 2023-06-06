package gui.dialog;

import settings.Style;
import settings.Text;

import javax.swing.*;


// создаем окно "О программе"
public class AboutDialog extends JDialog {

    public AboutDialog() {
        super();
        init();
        setTitle(Text.get("DIALOG_ABOUT_TITLE"));
        setIconImage(Style.ICON_ABOUT.getImage());
        setResizable(false);
    }

    private void init() {
        JEditorPane pane = new JEditorPane("text/html", Text.get("ABOUT"));
        pane.setEditable(false); // отключаем изменения
        pane.setOpaque(false); // отключаем фон

        // делаем ссылку в окне кликабельной
//        pane.addHyperlinkListener(new HyperlinkListener() {
//            @Override
//            public void hyperlinkUpdate(HyperlinkEvent he) {
//                if (HyperlinkEvent.EventType.ACTIVATED.equals(he.getEventType())) {
//                    try {
//                        Desktop.getDesktop().browse(he.getURL().toURI());
//                    } catch (URISyntaxException | IOException ex) {}
//                }
//            }
//        });

        add(pane);
        pack();
        setLocationRelativeTo(null);
    }

}
