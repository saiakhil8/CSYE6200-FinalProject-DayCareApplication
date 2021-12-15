package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * @author SaiAkhil
 */
public abstract class NavBarLayout extends ApplicationLayout {
    protected JPanel mainPanel;
    protected JPanel navBar;

    public NavBarLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected Component getMainLayoutComponent() {
        this.mainPanel = new JPanel(new BorderLayout());
        this.mainPanel.setOpaque(false);
        navBar = new JPanel(new FlowLayout());
        navBar.setPreferredSize(new Dimension(Constants.APP_PREFERRED_WIDTH, 80));
        navBar.setBackground(Color.decode("#343A3F"));
        navBar.setOpaque(true);
        this.mainPanel.add(navBar, BorderLayout.PAGE_START);
        return this.mainPanel;
    }

}
