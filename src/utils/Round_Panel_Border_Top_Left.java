package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.border.AbstractBorder;

public class Round_Panel_Border_Top_Left extends AbstractBorder {

    private final int radius;
    private final Color color;

    // Padding like EmptyBorder(20, 20, 10, 20)
    private static final Insets PADDING = new Insets(20, 20, 10, 20);

    public Round_Panel_Border_Top_Left(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g,
            int x, int y, int width, int height) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int r = radius;
        int w = width - 1;
        int h = height - 1;

        Path2D.Double path = new Path2D.Double();

        // Top edge - only top-left is rounded
        path.moveTo(r, 0);
        path.lineTo(w, 0); // top-right: sharp

        // Right side down
        path.lineTo(w, h); // bottom-right: sharp

        // Bottom edge
        path.lineTo(0, h); // bottom-left: sharp

        // Left side up to top-left curve
        path.lineTo(0, r);

        // Curved top-left corner
        path.quadTo(0, 0, r, 0);

        path.closePath();

        // Fill the background inside the rounded border
        g2d.setColor(c.getBackground());
        g2d.fill(path);

        // Draw the red border on top
        g2d.setColor(color);
        g2d.draw(path);

        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(
                PADDING.top + 2,
                PADDING.left + 2,
                PADDING.bottom,
                PADDING.right);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.top = PADDING.top + 2;
        insets.left = PADDING.left + 2;
        insets.bottom = PADDING.bottom;
        insets.right = PADDING.right;
        return insets;
    }
}