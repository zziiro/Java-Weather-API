package WeatherAPI;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultFrame{

    private final String location;

     ResultFrame(String location){
         this.location = location;

         String apiKey = "56013fa0e257f3dadcfff06f33cb38f2";
         String apiURL = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + location + "&units=f";

         HttpClient httpClient = HttpClients.createDefault();
         HttpGet getRequest = new HttpGet(apiURL);

         try{
             // get http response
             HttpResponse response = httpClient.execute(getRequest);
             int statusCode = response.getStatusLine().getStatusCode();

             // if the status code is 200 ok
             if(statusCode == 200){
                 System.out.println("[" + statusCode + "]");

                 // read the json response from the api
                 String jsonResponse = EntityUtils.toString(response.getEntity());

                 // parse the json data from the response
                 Gson gson = new Gson();
                 WeatherData weatherData = gson.fromJson(jsonResponse, WeatherData.class);

                 // use the gson data
                 double temperature = weatherData.getCurrent().getTemperature();
                 double feelsLike = weatherData.getCurrent().getFeelsLike();
                 double windSpeed = weatherData.getCurrent().getWindSpeed();
                 String windDirection = weatherData.getCurrent().getWindDirection();
                 String country = weatherData.getLocation().getCountry();
                 String region = weatherData.getLocation().getRegion();

                 // confirm api data via console
                 ApiDataConfirmation(temperature, feelsLike, windSpeed, windDirection, country, region);

                 // set the ResultFrame
                 JFrame resultFrame = new JFrame("Results! " + this.location);
                 resultFrame.setSize(400, 450);
                 resultFrame.add(new JLabel("Weather Results for " + this.location + ", " + region), BorderLayout.CENTER);
                 resultFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
                 resultFrame.setResizable(false);

                 // add gson data to the frame
                 resultFrame.add(new JLabel("Temperature: " + temperature + "F"));
                 resultFrame.add(new JLabel("Feels Like: " + feelsLike));
                 resultFrame.add(new JLabel("Wind Speed: " + windSpeed));
                 resultFrame.add(new JLabel("Wind Direction: " + windDirection));
                 resultFrame.add(new JLabel("Location: " + country + ", " + region));

                 // close this frame
                 JButton closeBtn = new JButton("Close");
                 closeBtn.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent event){
                         resultFrame.dispose();
                     }
                 });

                 // close button
                 resultFrame.add(closeBtn);

                 // show frame
                 resultFrame.setVisible(true);
             } else {
                 System.err.println("Error in relation to API Connection");
                 System.err.println("[ERROR CODE] " + statusCode);
             }
         } catch(Exception exception){
             JFrame errorFrame = new JFrame("ERROR");
             errorFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
             errorFrame.add(new JLabel("There has been an error"));
             errorFrame.setSize(300,100);

             // close this frame
             JButton closeBtn = new JButton("Close");
             closeBtn.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent event){
                     errorFrame.dispose();
                 }
             });

             errorFrame.add(closeBtn);
             errorFrame.setResizable(false);
             errorFrame.setVisible(true);
             exception.printStackTrace();
         }
    }

    public void ApiDataConfirmation(double temperature, double feelsLike, double windSpeed,
                                    String windDirection, String country, String region){
        System.out.println("City Search: " + this.location);
        System.out.println("Temperature (F): " + temperature);
        System.out.println("Feels Like: " + feelsLike);
        System.out.println("Wind Speed: " + windSpeed);
        System.out.println("Wind Direction: " + windDirection);
        System.out.println("Country: " + country);
        System.out.println("Region: " + region);
    }
}

