package petstore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class user {
    public static  HttpURLConnection connection;

    //POST /user
    public String[] post_new_user(int id, String username, String firstname, String lastname, String email, String password, String phone) {
        String URL = ("https://petstore.swagger.io/v2/user");
        String data = "{\"id\":" + id + ",\"username\":\"" + username + "\",\"firstName\":\"" + firstname + "\",\"lastName\":\"" + lastname + "\",\"email\":\"" + email + "\",\"password\":\"" + password + "\",\"phone\":\"" + phone + "\",\"userStatus\":0}";
        String output[] = post_request(URL,data);
        return output;

    }

    //GET /user/{username}
    public String[] get_user_by_username(String username){
     String URL = ("https://petstore.swagger.io/v2/user/" + username);
     String output[] = get_request(URL);
     return output;
    }

    //DELETE /user/{username}
    public String[] delete_user(String username){
        String URL = ("https://petstore.swagger.io/v2/user/" + username);
        String output[] = delete_request(URL);
        return output;
    }

    //PUT /user/{username}
    public String[] update_user(int id, String username, String new_firstname, String new_lastname, String new_email, String new_password, String new_phone){
        String URL = ("https://petstore.swagger.io/v2/user/" + username);
        String data = "{\"id\":" + id + ",\"username\":\"" + username + "\",\"firstName\":\"" + new_firstname + "\",\"lastName\":\"" + new_lastname + "\",\"email\":\"" + new_email + "\",\"password\":\"" + new_password + "\",\"phone\":\"" + new_phone +"\",\"userStatus\":0}";
        String output[] = put_request(URL, data);
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

}
