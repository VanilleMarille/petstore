package REST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class request {

    public static HttpURLConnection connection;


    public static String[] rest_request(String URL, String data_out, String content_type, String request_type){
        int status = 0;

        BufferedReader reader;
        String line;
        String message = "ERROR";
        StringBuffer responseContent = new StringBuffer();


       try {
           URL url = new URL(URL);
           connection = (HttpURLConnection) url.openConnection();

           connection.setRequestMethod(request_type);
           connection.setConnectTimeout(5000);
           connection.setReadTimeout(5000);
           connection.setRequestProperty("Accept", "application/json");
           connection.setDoOutput(true);

           if(content_type.equals("urlencoded")){
               connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
           }else if (content_type.equals("json")){
               connection.setRequestProperty("Content-Type", "application/json");
           }else{
               System.out.println("Error - content_type not known");
           }




           if(request_type.equals("POST") || request_type.equals("PUT")){
               byte[] out = data_out.getBytes(StandardCharsets.UTF_8);
               OutputStream stream = connection.getOutputStream();
               stream.write(out);
           }else if((!(request_type.equals("DELETE") || request_type.equals("GET")))){
               System.out.println("Error - request_type not known");
           }

           status = connection.getResponseCode();

           reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

           while ((line = reader.readLine()) != null) {
               responseContent.append(line);
           }

           reader.close();

           message = responseContent.toString();

       }catch (MalformedURLException e) {
           e.printStackTrace();
       }catch (IOException e) {
           e.printStackTrace();
       }

       String[] output = new String[2];

       output[0] = message;
       output[1] = Integer.toString(status);

       return output;

    }
}
