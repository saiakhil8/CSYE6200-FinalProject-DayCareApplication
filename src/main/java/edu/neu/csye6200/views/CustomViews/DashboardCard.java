package edu.neu.csye6200.views.CustomViews;

import javax.swing.*;
import java.awt.*;

/**
 * @author SaiAkhil
 * <p>
 * {{Part of code obtained from https://www.codeproject.com/Articles/114959/Rounded-Border-JPanel-JPanel-graphics-improvements}}
 */
public class DashboardCard extends JPanel {
    private String title;
    private int count;
    private Color backgroundColor;
    private Color titleColor;
    private Color countColor;

    private JLabel countLabel;
    private JLabel titleLabel;

    /**
     * Stroke size. it is recommended to set it to 1 for better view
     */
    protected int strokeSize = 1;
    /**
     * Color of shadow
     */
    protected Color shadowColor = Color.black;
    /**
     * Sets if it drops shadow
     */
    protected boolean shady = true;
    /**
     * Sets if it has an High Quality view
     */
    protected boolean highQuality = true;
    /**
     * Double values for Horizontal and Vertical radius of corner arcs
     */
    protected Dimension arcs = new Dimension(20, 20);
    /**
     * Distance between shadow border and opaque panel border
     */
    protected int shadowGap = 5;
    /**
     * The offset of shadow.
     */
    protected int shadowOffset = 4;
    /**
     * The transparency value of shadow. ( 0 - 255)
     */
    protected int shadowAlpha = 150;

    public DashboardCard(String title, int count, Color backgroundColor, Color titleColor, Color countColor) {
        super(new GridBagLayout());
        this.setOpaque(false);
        this.title = title;
        this.count = count;
        this.titleColor = titleColor;
        this.backgroundColor = backgroundColor;
        this.countColor = countColor;
        this.initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setPreferredSize(new Dimension(200, 150));
        this.setBackground(backgroundColor);
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(8, 4, 8, 4);

        this.titleLabel = new JLabel(title.toUpperCase());
        this.titleLabel.setFont(new Font("amarnath", Font.BOLD, 16));
        this.titleLabel.setForeground(titleColor);
        this.add(titleLabel, gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.weightx = 1;
        this.countLabel = new JLabel(Integer.toString(count));
        this.countLabel.setFont(new Font("oswald", Font.BOLD, 40));
        this.countLabel.setForeground(countColor);
        this.add(countLabel, gridBagConstraints);
        this.add(countLabel, gridBagConstraints);
    }

    public void setCount(int count) {
        this.count = count;
        this.countLabel.setText(Integer.toString(count));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(),
                shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        //Sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //Draws shadow borders if any.
        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowOffset,// X position
                    shadowOffset,// Y position
                    width - strokeSize - shadowOffset, // width
                    height - strokeSize - shadowOffset, // height
                    arcs.width, arcs.height);// arc Dimension
        } else {
            shadowGap = 1;
        }

        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);

        //Sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }
}
