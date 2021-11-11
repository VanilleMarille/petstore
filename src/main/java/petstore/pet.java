package petstore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class pet {
    public static HttpURLConnection connection;

    //GET /pet/{petID}
    public String[] get_pet_byID(int id) {
        String URL = ("https://petstore.swagger.io/v2/pet/" + id);
        String output[] = get_request(URL);
        return output;
    }

    //GET /pet/findByStatus
    public String[] get_pet_byStatus(String pet_status) {
        String URL = ("https://petstore.swagger.io/v2/pet/findByStatus?status=" + pet_status);
        String output[] = get_request(URL);
        return output;
    }

    //POST /pet
    public String[] post_pet(int id, String name, String pet_status) {
        String URL = ("https://petstore.swagger.io/v2/pet/");
        String data = "{\"id\":" + id + ",\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"" + name + "\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"" + pet_status + "\"}";
        String output[] = post_request(URL, data);
        return output;
    }


    //DELETE /pet/{petID}
    public String[] delete_pet(int id) {
        String URL = ("https://petstore.swagger.io/v2/pet/" + id);
        String output[] = delete_request(URL);
        return output;
    }


    //PUT /pet
    public String[] update_pet_put(int id, String update_name, String update_status) {
        String URL = ("https://petstore.swagger.io/v2/pet/");
        String data = "{\"id\":" + id + ",\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"" + update_name + "\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"" + update_status + "\"}";
        String output[] = put_request(URL, data);
        return output;
    }

    //POST /pet/{petID}
    public String[] post_pet_formData(int id, String update_name, String update_status){
        int status = 0;

        BufferedReader reader;
        String line;
        String message = ("Error");
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL("https://petstore.swagger.io/v2/pet/" + id);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String data = ("name=" + update_name + "&status=" + update_status);

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream =  connection.getOutputStream();
            stream.write(out);

            status = connection.getResponseCode();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }

            reader.close();

            message =  responseContent.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String output[] = new String [2];

        output[0] = message;
        output[1] = Integer.toString(status);

        return output;
    }

    public String[] put_request(String URL, String data) {
        int status = 0;

        BufferedReader reader;
        String line;
        String message = ("Error");
        StringBuffer responseContent = new StringBuffer();


        try {
            URL url = new URL(URL);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = connection.getOutputStream();
            stream.write(out);

            status = connection.getResponseCode();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line =  reader.readLine()) != null){
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

    public String[] post_request(String URL, String data){
        int status = 0;

        BufferedReader reader;
        String line;
        String message = "Error";
        StringBuffer responseContent = new StringBuffer();

        try {
            java.net.URL url = new URL(URL);
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


}
