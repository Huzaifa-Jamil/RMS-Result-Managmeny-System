package RMSResultManagementSystem;
import SplashScreen.SplashScreen;

public class Main {
    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true);                                                                                                  
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splashScreen.dispose();
        RMSResultManagementSystem gui = new RMSResultManagementSystem();
        gui.setVisible(true);
    }
}