package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * @author SaiAkhil
 */
public abstract class NavBarLayout extends ApplicationLayout {
    protected JPanel mainPanel;
    protected JPanel navBar;
    private Component leftSideButton;
    private Component rightSideButton;

    /**
     * Main Constructor of the class
     *
     * @param imagePathOrColor which needs to be used as background
     * @param backgroundType   is type of background {Example: Image or Color}
     */
    public NavBarLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    protected Component getMainLayoutComponent() {
        this.mainPanel = new JPanel(new BorderLayout());
        this.mainPanel.setOpaque(false);
        navBar = new JPanel(new BorderLayout());
        navBar.setPreferredSize(new Dimension(Constants.APP_PREFERRED_WIDTH, 80));
        navBar.setBackground(Color.decode("#343A3F"));
        navBar.setOpaque(true);
        this.mainPanel.add(navBar, BorderLayout.PAGE_START);
        this.setNavBarTitle(this.getNavBarTitle());
        return this.mainPanel;
    }

    @Override
    protected void initComponents() {
        this.setLogoutButton();
    }

    /**
     * Set NavBarTitle -- ThreadSafe {Class variables will be initialized in initial threads not EventThreads}
     *
     * @return -= title that needs to be set -- mark://can be nullable
     */
    protected abstract String getNavBarTitle();

    /**
     * Set a NavBar Titile --Mark://Will be set at the center of the page with predefined Font and size
     *
     * @param navBarTitle -= Title of the page
     */
    private void setNavBarTitle(@Nullable String navBarTitle) {
        if (navBarTitle == null) return;
        JLabel jLabel = new JLabel(navBarTitle.toUpperCase());
        jLabel.setPreferredSize(new Dimension(80, 40));
        jLabel.setHorizontalAlignment(SwingUtilities.CENTER);
        jLabel.setForeground(Color.white);
        jLabel.setFont(new Font("amarnath", Font.BOLD, 14));
        this.navBar.add(jLabel, BorderLayout.CENTER);
    }

    /**
     * Set a button at the extreme left end of the nav bar with given iconPath
     *
     * @param path of Icon which needs to used as Icon
     */
    protected void setLeftComponent(@Nullable String path) {
        this.setLeftComponent(path, "");
    }

    /**
     * Set a button at the extreme left end of the nav bar with given iconPath
     *
     * @param path        of Icon which needs to used as Icon
     * @param placeHolder that needs to be used as placeholder for the icon
     */
    protected void setLeftComponent(@Nullable String path, @NonNull String placeHolder) {
        if (path == null) return;
        JPanel jPanel = new JPanel(new GridBagLayout());
        jPanel.setOpaque(false);
        jPanel.setPreferredSize(new Dimension(60, 80));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2, 16, 2, 8);
        JLabel iconLabel = null;
        try {
            iconLabel = new JLabel(new ImageIcon(ImageIO.read(new File((path)))));
        } catch (IOException e) {
            iconLabel = new JLabel(placeHolder.toUpperCase());
            iconLabel.setFont(new Font("catamarca", Font.BOLD, 14));
            e.printStackTrace();
        }
        jPanel.add(iconLabel, gridBagConstraints);
        this.leftSideButton = jPanel;
        this.navBar.add(jPanel, BorderLayout.LINE_START);
    }

    /**
     * Set logout button at the right end of the nav
     */
    private void setLogoutButton() {
        this.setRightButton(Constants.getIconPathFromName("logout.png"), "Logout");
    }

    /**
     * Set a button at the extreme right end of the nav bar with given iconPath
     *
     * @param path        of Icon which needs to used as Icon
     * @param placeHolder that needs to be used as placeholder for the icon
     */
    protected void setRightButton(@Nullable String path, @NonNull String placeHolder) {
        if (path == null) return;
        JPanel jPanel = new JPanel(new GridBagLayout());
        jPanel.setOpaque(false);
        jPanel.setPreferredSize(new Dimension(60, 80));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2, 4, 2, 20);
        JLabel iconLabel = null;
        try {
            iconLabel = new JLabel(new ImageIcon(ImageIO.read(new File((path)))));
        } catch (IOException e) {
            iconLabel = new JLabel(placeHolder.toUpperCase());
            iconLabel.setFont(new Font("catamarca", Font.BOLD, 14));
            e.printStackTrace();
        }
        jPanel.add(iconLabel, gridBagConstraints);
        this.rightSideButton = jPanel;
        this.navBar.add(jPanel, BorderLayout.LINE_END);
    }


    @Override
    protected void onCreate() {
        super.onCreate();
        if (this.leftSideButton != null) {
            this.leftSideButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    NavBarLayout.this.onLeftButtonClicked();
                }
            });
        }

        if (this.rightSideButton != null) {
            this.rightSideButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    NavBarLayout.this.onRightButtonClicked();
                }
            });
        }

    }

    /**
     * Called when left side button is clicked
     */
    protected void onLeftButtonClicked() {
    }

    ;

    /**
     * Called when right side button is clicked
     * By default logout event will be triggered {Override for changing this event trigger}
     */
    protected void onRightButtonClicked() {
        this.eventListener.onEvent(Constants.EVENT_LOGOUT);
    }

    ;
}
