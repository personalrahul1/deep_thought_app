package com.rahultask.deepthoughtproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Create a new class to handle the network request
public class NetworkRequest {
    //Create a method to make the network request
    public static String makeNetworkRequest(String url) {
        //Create a new StringBuilder to store the response
        StringBuilder response = new StringBuilder();
        try {
            //Create a new URL object from the given url
            URL urlObject = new URL(url);
            //Open a connection to the url
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            //Set the request method to GET
            connection.setRequestMethod("GET");
            //Set the connection timeout
            connection.setConnectTimeout(5000);
            //Set the read timeout
            connection.setReadTimeout(5000);
            //Connect to the url
            connection.connect();
            //Get the response code
            int responseCode = connection.getResponseCode();
            //Check if the response code is 200 (OK)
            if (responseCode == 200) {
                //Create a new BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //Read the response line by line
                String line;
                while ((line = reader.readLine()) != null) {
                    //Append the response to the StringBuilder
                    response.append(line);
                }
            } else {
                response.append(responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Return the response as a String
        return response.toString();
    }

}