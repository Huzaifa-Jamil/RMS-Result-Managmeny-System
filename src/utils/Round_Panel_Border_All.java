package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.border.AbstractBorder;

public class Round_Panel_Border_All extends AbstractBorder{

    private int radius;
    private Color bgColor;

    public Round_Panel_Border_All(int radius, Color bgColor) {
        this.radius = radius;
        this.bgColor = bgColor;
    }

    private static final Insets PADDING = new Insets(0, 0, 0, 0);

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(
                PADDING.top,
                PADDING.left,
                PADDING.bottom,
                PADDING.right);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.top = PADDING.top;
        insets.left = PADDING.left;
        insets.bottom = PADDING.bottom;
        insets.right = PADDING.right;
        return insets;
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

        // START after top-left curve
        path.moveTo(r, 0);

        // Top edge → top-right sharp
        path.lineTo(w, 0);

        // Right edge down until curve start
        path.lineTo(w, h - r);

        // Bottom-right rounded corner
        path.quadTo(w, h, w - r, h);

        // Bottom edge → bottom-left sharp
        path.lineTo(0, h);

        // Left edge up until curve start
        path.lineTo(0, r);

        // Top-left rounded corner
        path.quadTo(0, 0, r, 0);

        path.closePath();

        // Fill background
        g2d.setColor(c.getBackground());
        g2d.fill(path);

        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // Border
        g2d.setColor(bgColor);
        g2d.draw(path);

        g2d.dispose();
    }
}