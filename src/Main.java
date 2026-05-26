import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import ui.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Splash_Screen_UI splashScreen = new Splash_Screen_UI();
            JProgressBar progressBar = splashScreen.getProgressBar();

            int duration = 3000;
            int delay = 30;
            int steps = duration / delay;

            Timer timer = new Timer(delay, null);
            timer.addActionListener(e -> {
                int value = progressBar.getValue();
                if (value < 100) {
                    progressBar.setValue(value + 100 / steps);
                } else {
                    ((Timer)e.getSource()).stop();
                    RMS_Result_Management_System_UI gui = new RMS_Result_Management_System_UI();
                    gui.setVisible(true);
                    splashScreen.dispose();
                }
            });
            timer.start();
        });
    }
}