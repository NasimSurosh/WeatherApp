import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    private static final String API_KEY ="13cd9f123d81a259ff3f513b9ca55294";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    public static String getWeatherData(String city) throws IOException {
        String url = String.format(API_URL, city, API_KEY);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;

        StringBuilder response = new StringBuilder();
        while ((inputLine = reader.readLine()) !=null){
            response.append(inputLine);
        }

        Gson gson = new Gson();



        reader.close();
        connection.disconnect();
        return response.toString();

    }
    public static void main(String[] args) {



       Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the city name to get weather data");
       String city = scanner.nextLine();
       scanner.close();

       try{
           String weatherData = getWeatherData(city);
           System.out.println("Weather data for: " +city +" ");
           System.out.println(weatherData);

       }catch (IOException e){
           e.printStackTrace();
       }



    }
}