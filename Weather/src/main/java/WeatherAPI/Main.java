package WeatherAPI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new Frame();
            }
        });
    }
}
// TODO - make a new APIConnection class and have the resultFrame class inherit from it
