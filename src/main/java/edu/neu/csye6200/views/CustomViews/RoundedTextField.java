package edu.neu.csye6200.views.CustomViews;

import org.springframework.lang.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

/**
 * @author SaiAkhil
 */
public class RoundedTextField extends JTextField implements FocusListener {
    private Shape shape;
    private String placeHolder;
    private boolean isPassword = false;
    private String actualText = null;

    public RoundedTextField(int size) {
        super(size);
        setOpaque(false);
        this.addFocusListener(this);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }

    public void setPlaceHolder(@Nullable String placeHolder) {
        this.placeHolder = placeHolder;
        this.setPlaceHolder();
    }

    private void setPlaceHolder() {
        this.setText(this.placeHolder);
        this.setForeground(Color.GRAY);
    }

    public void setIsPassword(boolean isPassword) {
        this.isPassword = isPassword;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().equals(placeHolder)) {
            this.actualText = "";
            this.setText("");
            this.setForeground(Color.BLACK);
        } else if (isPassword) {
            this.setText(this.actualText);
            this.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            this.setPlaceHolder();
        } else if (this.isPassword) {
            this.actualText = this.getText();
            this.setText("*******");
        }
    }

    public String getPasswordText() {
        if (actualText != null && !actualText.isEmpty() && actualText.equals(placeHolder) && this.getText().equals(placeHolder)) {
            return "";
        } else if (actualText == null || actualText.isEmpty()) {
            return this.getText();
        } else return actualText;
    }

    public String getActualText() {
        String text = super.getText();
        return (text.equalsIgnoreCase(placeHolder) ? "" : text);
    }

    public void reset() {
        this.setText("");
        this.actualText = "";
        this.setPlaceHolder(this.placeHolder);
    }

}
