package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.AbstractBorder;

public class Round_Tab_Border extends AbstractBorder{

    private int radius;
    private Color color;

    public Round_Tab_Border(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);

        int r = radius;
        java.awt.geom.Path2D.Double path = new java.awt.geom.Path2D.Double();

        // 1. Start top-right
        path.moveTo(width, 0);

        // 2. Line to top-left (start of curve)
        path.lineTo(r, 0);

        // 3. Curve at top-left (Keep this for the WhatsApp look)
        path.quadTo(0, 0, 0, r);

        // 4. Line straight down to the absolute bottom (No curve here)
        path.lineTo(0, height);

        // 5. Line across the bottom to the right
        path.lineTo(width, height);

        // Note: The right side remains open/straight as it touches the edge

        g2d.draw(path);
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        // Reduced left padding slightly since the bottom-left is now square
        return new Insets(1, 1, 0, 0);
    }
}
