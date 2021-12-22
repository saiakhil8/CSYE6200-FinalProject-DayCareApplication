package edu.neu.csye6200.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author SaiAkhil
 */
public class LandingPageLayout extends ApplicationLayout {

    private JPanel mainViewPanel;
    private Timer timer;
    private TimerTask flashingLabelTask;

    public LandingPageLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected Component getMainLayoutComponent() {
        this.mainViewPanel = new JPanel(new GridLayout(1, 2, 0, 0));
        this.mainViewPanel.setOpaque(false);
        return mainViewPanel;
    }

    @Override
    protected void initComponents() {
        try {
            this.mainViewPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("./src/main/resources/daycare_landing_page.png")))));
            this.mainViewPanel.add(this.getRightView());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Component getRightView() throws IOException {
        JPanel currentPanel = new JPanel(new GridLayout(2, 1, 0, 16));
        currentPanel.setOpaque(false);
        JLabel iconLabel = new JLabel(new ImageIcon(ImageIO.read(new File("./src/main/resources/icons/login.png"))));
        iconLabel.setVerticalAlignment(JLabel.BOTTOM);
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        currentPanel.add(iconLabel);
        JLabel loginLabel = new JLabel("Click to Login into daycare application".toUpperCase());
        loginLabel.setVerticalAlignment(JLabel.TOP);
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        currentPanel.add(loginLabel);
        this.timer = new Timer();
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                timer.purge();
                LandingPageLayout.this.goToNextPage();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                loginLabel.setForeground(Color.RED);
                loginLabel.setFont(getLoginToFont(Font.BOLD, 22));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                loginLabel.setForeground(Color.BLACK);
                loginLabel.setFont(getLoginToFont(Font.BOLD, 16));
            }
        };
        loginLabel.setFont(getLoginToFont(Font.BOLD, 16));

        this.flashingLabelTask = new TimerTask() {
            @Override
            public void run() {
                int currentFontSize = loginLabel.getFont().getSize();
                if (currentFontSize != 22) {
                    loginLabel.setFont(getLoginToFont(Font.BOLD, (currentFontSize == 20) ? 16 : 20));
                    loginLabel.setForeground((currentFontSize == 20) ? Color.BLACK : Color.BLUE);
                }
            }
        };

        this.timer.schedule(flashingLabelTask, 500, 500);
        loginLabel.addMouseListener(mouseAdapter);
        currentPanel.addMouseListener(mouseAdapter);
        return currentPanel;
    }

    private Font getLoginToFont(int fontType, int size) {
        return new Font("calibri", fontType, size);
    }

}
