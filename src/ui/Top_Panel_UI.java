package ui;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import utils.UI_Styles;

public class Top_Panel_UI extends JPanel {

    public Top_Panel_UI() {
        createTopTitlePanel();
    }

    private void createTopTitlePanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(0, 10));
        this.setBorder(BorderFactory.createEmptyBorder());
        UI_Styles.makeFocusStealerRecursive(this);
    }
}
