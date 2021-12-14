package edu.neu.csye6200.views;

import edu.neu.csye6200.Utils.Constants;
import edu.neu.csye6200.Utils.FunctionalUtilities;
import edu.neu.csye6200.views.CustomViews.RoundedTextField;

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
public class LoginPageLayout extends LandingPageLayout {

    public LoginPageLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    /**
     * Create and Add Components to the current Frame
     *
     * @return RightSide View Component
     * @throws IOException when file is not present
     */
    @Override
    public Component getRightView() throws IOException {
        JPanel currentPanel = new JPanel(new GridLayout(3, 1, 0, 8));
        currentPanel.setOpaque(false);

        //Add Icon to the current Panel
        JLabel iconLabel = new JLabel(new ImageIcon(ImageIO.read(new File("./src/main/resources/icons/login.png"))));
        iconLabel.setVerticalAlignment(JLabel.BOTTOM);
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        currentPanel.add(iconLabel);

        //Add A New GridBagLayout with 2 Input Fields And Buttons to current panel
        currentPanel.add(this.getLoginFields());

        currentPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentPanel.requestFocus();
            }
        });

        return currentPanel;
    }


    /**
     * Add TextField and Buttons to the current Frame
     *
     * @return component containing login textfields and buttons
     */
    private Component getLoginFields() {
        //New Panel which acts a root
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        //Create rounded text fields for Password and email
        RoundedTextField roundedTextField = new RoundedTextField(40);
        roundedTextField.setPreferredSize(new Dimension(80, 50));
        roundedTextField.setPlaceHolder("Enter your Email-ID");
        RoundedTextField roundedPasswordTextField = new RoundedTextField(40);
        roundedPasswordTextField.setIsPassword(true);
        roundedPasswordTextField.setPlaceHolder("Enter your password");
        roundedPasswordTextField.setPreferredSize(new Dimension(80, 50));

        //Create new GridBagConstraints
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2, 50, 2, 12);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel.add(roundedTextField, gridBagConstraints);

        //Create and add a dummy panel to current panel for vertical spacing
        JPanel dummyPanel = new JPanel();
        dummyPanel.setOpaque(false);
        dummyPanel.setPreferredSize(new Dimension(80, 18));
        gridBagConstraints.gridy++;
        panel.add(dummyPanel, gridBagConstraints);

        //Add PasswordTextField to current Panel
        gridBagConstraints.gridy++;
        panel.add(roundedPasswordTextField, gridBagConstraints);

        //Create Login Button with required size
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(150, 50));
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!Constants.VALIDATE_EMAIL_ADDRESS.apply(roundedTextField.getText())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Enter a valid Email-Id", "Error!!",
                            JOptionPane.ERROR_MESSAGE);
                    roundedTextField.requestFocus();
                    return;
                }
                if (roundedPasswordTextField.getActualText().isEmpty() || roundedPasswordTextField.getActualText().length() < 6) {
                    JOptionPane.showMessageDialog(new JFrame(), "Password should be atleast 6 characters", "Error!!",
                            JOptionPane.ERROR_MESSAGE);
                    roundedPasswordTextField.requestFocus();
                    return;
                }

                if (loginListener != null) {
                    JDialog dialog = Constants.geLoadingDialog("Test");
                    dialog.setVisible(true);
                    loginListener.accept(roundedTextField.getText(),
                            roundedPasswordTextField.getActualText(), (result, message) -> {
                                //on result
                                dialog.setVisible(false);
                                dialog.dispose();
                                if (result) LoginPageLayout.this.goToNextPage();
                                else {
                                    JOptionPane.showMessageDialog(new JFrame(), message, "Error!!",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            });
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Could'nt SignYou In", "Error!!",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        //Create cancel button --Mark:// OnClick triggerGoBack
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(150, 50));
        //Add Listener of onMouseClicked -- Marked call onBackPressed
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoginPageLayout.this.onNewEvent(Constants.EVENT_LOGOUT);
            }
        });

        //Grid Layout with 2 columns for buttons
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 40, 40));
        buttonsPanel.add(loginButton);
        buttonsPanel.add(cancelButton);

        //Update insets to mingle with Inputs
        gridBagConstraints.insets = new Insets(2, 16, 2, 16);

        //Add one more dummy for vertical spacing and add it to currentPanel
        dummyPanel = new JPanel();
        dummyPanel.setOpaque(false);
        dummyPanel.setPreferredSize(new Dimension(80, 24));
        gridBagConstraints.gridy++;
        panel.add(dummyPanel, gridBagConstraints);

        //Add ButtonsGrid to currentPanel
        gridBagConstraints.gridy++;
        panel.add(buttonsPanel, gridBagConstraints);
        return panel;
    }

    private FunctionalUtilities.TriFunction<String, String, FunctionalUtilities.BiFunction<Boolean, String>> loginListener;

    public void setLoginListener(FunctionalUtilities.TriFunction<String, String, FunctionalUtilities.BiFunction<Boolean, String>> listener) {
        this.loginListener = listener;
    }

}
