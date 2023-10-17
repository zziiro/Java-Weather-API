package WeatherAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame{

    // Frame Constants
    private final JTextField locationField;
    private final JButton searchButton;
    private final JButton closeButton;

    public Frame(){

        // set the frame
        JFrame homeFrame = new JFrame();
        homeFrame.setTitle("Weather");
        homeFrame.add(new JLabel("Check The Weather!"), BorderLayout.CENTER);
        homeFrame.setSize(400,200);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setLayout(new FlowLayout());

        // create input field and search button
        locationField = new JTextField("Location", 10);
        searchButton = new JButton("Search");
        closeButton = new JButton("Close Application");

        // action event for when the search button is clicked
        searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                // Create a variable for each input field
                String locationSearch = locationField.getText().trim();
                if(ValidateSearch(locationSearch)){
                    // Create the Result frame if the input is validated
                    new ResultFrame(locationSearch);
                } else {
                    // if the inputs were deemed invalid
                    JOptionPane.showMessageDialog(null,
                            "Location does not exist with the given data!",
                            "Validation Result", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        // close the application
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.setVisible(false);
                System.exit(0);
            }
        });

        // add input fields and buttons to the frame
        homeFrame.add(locationField);
        homeFrame.add(searchButton);
        homeFrame.add(closeButton);

        // whatever this is
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);
    }

    // method for input validation
    public boolean ValidateSearch(String locationSearch){
        // check if the city or place they selected is provided by the api
        return locationSearch.length() > 0;
    }
}

