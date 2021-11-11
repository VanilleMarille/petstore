package petstore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class store {
    public static HttpURLConnection connection;



    //GET /store/inventory
    public String[] get_store_inventory(){
        String URL = ("https://petstore.swagger.io/v2/store/inventory");
        String output[] = get_request(URL);
        return  output;
    }

    //POST /store/order
    public String[] post_order_for_pet(int id, int quantity){
        String URL = ("https://petstore.swagger.io/v2/store/order");
        String data = "{\"id\":" + id + ",\"petID\":0,\"quantity\":" + quantity + ",\"shipDate\":\"2021-12-10T10:38:00.055+0000\",\"status\":\"placed\",\"complete\":true}";
        String output[] = post_request(URL, data);
        return output;
    }

    //GET /store/order
    public String[] get_order_by_id(int id){
        String URL = ("https://petstore.swagger.io/v2/store/order/" + id);
        String output[] = get_request(URL);
        return output;

    }

    //DELETE /store/order/{orderId]
    public String[] delete_order_byID(int id){
        String URL = ("https://petstore.swagger.io/v2/store/order/" + id);
        String output[] = delete_request(URL);
        return output;
    }



    public String[] post_request(String URL, String data){
        int status = 0;

        BufferedReader reader;
        String line;
        String message = "Error";
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL(URL);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");


            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = connection.getOutputStream();
            stream.write(out);

            status = connection.getResponseCode();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }

            reader.close();

            message = responseContent.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] output = new String [2];

        output[0] = message;
        output[1] = Integer.toString(status);

        return output;
    }


    public String[] get_request(String URL){
        int status = 0;
        BufferedReader reader;
        String line;
        String message = "Error";
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL (URL);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            status = connection.getResponseCode();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            reader.close();

            message = responseContent.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] output = new String[2];

        output[0] = message;
        output[1] = Integer.toString(status);

        return output;
    }

    public String[] delete_request(String URL){
        int status = 0;

        BufferedReader reader;
        String line;
        String message = ("Error");
        StringBuffer responseContent = new StringBuffer();


        try {
            URL url = new URL(URL);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }

            reader.close();

            message = (responseContent.toString());

            status = connection.getResponseCode();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] output = new String[2];

        output[0] = message;
        output[1] = Integer.toString(status);

        return output;
    }


}
