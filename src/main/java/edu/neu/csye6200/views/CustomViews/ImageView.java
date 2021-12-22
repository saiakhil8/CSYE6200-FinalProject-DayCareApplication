package edu.neu.csye6200.views.CustomViews;

import org.springframework.lang.NonNull;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author SaiAkhil
 */
public class ImageView extends JPanel {
    private final CustomPainter customPainter;
    private final String imagePathOrColor;
    private Dimension dimension;

    public ImageView(CustomPainter customPainter, String imagePathOrColor) {
        super(new BorderLayout());
        this.customPainter = customPainter;
        this.imagePathOrColor = imagePathOrColor;
    }

    protected ImageView(CustomPainter customPainter, String imagePathOrColor, Dimension dimension){
        super(new BorderLayout());
        this.customPainter = customPainter;
        this.imagePathOrColor = imagePathOrColor;
        this.dimension = dimension;
    }

    @Override
    public void paintComponent(Graphics g) {
        assert this.customPainter!=null;
        this.customPainter.paint(g,this.imagePathOrColor,
                (dimension==null)?new Dimension(this.getWidth(),this.getHeight()):dimension);
    }

    @FunctionalInterface
    interface CustomPainter{
        void paint(Graphics g, @NonNull String s, Dimension dimension);
    }

    public static CustomPainter IMAGE_PAINTER = (graphics, imagePath, dimension) -> {
        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File(imagePath));
            graphics.drawImage(backgroundImage, 0, 0, (int) dimension.getWidth(), (int) dimension.getHeight(), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    public static CustomPainter GRADIENT_PAINTER = (graphics, color, dimension) -> {
        Graphics2D g2d = (Graphics2D) graphics;
        Color color1 = Color.decode(color);
        Color color2 = color1.darker();
        double w = dimension.getWidth();
        double h = dimension.getHeight();
        GradientPaint gp = new GradientPaint(
                0.0f, 0.0f, color1, 0, (float) h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, (int) w, (int) h);
    };
}
