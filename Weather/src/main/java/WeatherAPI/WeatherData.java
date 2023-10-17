package WeatherAPI;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    private Current current;
    private Location location;

    public Current getCurrent(){return current;}
    public void setCurrentWeather(Current current){this.current = current;}

    public Location getLocation(){return location;}
    public void setLocation(Location location){this.location = location;}
}

class Current {
    private double temperature;
    @SerializedName("feelslike")
    private double feelsLike;
    @SerializedName("wind_speed")
    private double windSpeed;
    @SerializedName("wind_dir")
    private String windDirection;

    // Get the Current Temperature
    public double getTemperature() {return temperature;}
    public void setTemperature(double temperature) {this.temperature = temperature;}

    // Get the Feels Like Temperature
    public double getFeelsLike() {return feelsLike;}
    public void setFeelsLike(double feelsLike) {this.feelsLike = feelsLike;}

    // Get the Current Wind Speed
    public double getWindSpeed() {return windSpeed;}
    public void setWind_Speed(double windSpeed) {this.windSpeed = windSpeed;}

    // Get the Current Wind Direction
    public String getWindDirection(){return windDirection;}
    public void setWindDirection(String winDirection){this.windDirection = windDirection;}

}

class Location{
    private String region;
    private String country;

    public String getRegion(){return region;}
    public void setRegion(String region){this.region = region;}

    public String getCountry(){return country;}
    public void setCountry(String country){this.country = country;}
}

class WeatherDescriptions{
    private String descriptions;

    public String getDescriptions(){return descriptions;}
    public void setDescriptions(String decriptions){this.descriptions = decriptions;}
}