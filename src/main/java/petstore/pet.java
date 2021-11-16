package petstore;

import REST.request;


public class pet {

    String URL =  ("https://petstore.swagger.io/v2/pet/");

    //GET /pet/{petID}
    public String[] get_pet_byID(int ID){
        URL = (URL + ID);
        String request_type = ("GET");
        String data_out = ("Nothing");
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //GET /pet/findByStatus
    public String[] get_pet_byStatus(String FIND_STATUS){
        URL = (URL + "findByStatus?status=" + FIND_STATUS);
        String request_type = ("GET");
        String data_out = ("Nothing");
        String content_type = "json";

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //POST /pet
    public String[] add_pet(int ID, String NAME, String PET_STATUS){
        String request_type = ("POST");
        String data_out = "{\"id\":" + ID + ",\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"" + NAME + "\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"" + PET_STATUS + "\"}";
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //POST /pet/{petID}
    public String[] update_pet_formData(int ID, String UPDATE_NAME, String UPDATE_STATUS){
        URL = (URL + ID);
        String request_type = ("POST");
        String data_out = ("name=" + UPDATE_NAME + "&status=" + UPDATE_STATUS);
        String content_type = ("urlencoded");

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //DELETE /pet/{petID}
    public String[] delete_pet(int ID){
        URL = (URL + ID);
        String request_type = ("DELETE");
        String data_out = ("Nothing");
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //PUT /pet
    public String[] update_pet(int ID, String UPDATE_NAME, String UPDATE_STATUS){
        String request_type = ("PUT");
        String data_out = "{\"id\":" + ID + ",\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"" + UPDATE_NAME + "\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"" + UPDATE_STATUS + "\"}";
        String content_type =  ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }
}
