package edu.neu.csye6200.views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author SaiAkhil
 */
public class LoginPageLayout extends LandingPageLayout {
    public LoginPageLayout(String imagePathOrColor, int backgroundType) {
        super(imagePathOrColor, backgroundType);
    }

    @Override
    public Component getRightView() throws IOException {
        return new JPanel();
    }
}
