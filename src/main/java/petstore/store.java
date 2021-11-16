package petstore;

import REST.request;


public class store {

    String URL = ("https://petstore.swagger.io/v2/store/");

    //GET /store/inventory
    public String[] get_store_inventory(){
        URL = (URL + "inventory");
        String request_type = ("GET");
        String data_out = ("Nothing");
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //POST /store/order
    public String[] post_order_for_pet(int ID, int PET_ID, int QUANTITY){
        URL = (URL + "order");
        String request_type = ("POST");
        String data_out = "{\"id\": " + ID + ",\"petId\":" + PET_ID + ",\"quantity\":" + QUANTITY + ",\"shipDate\":\"2021-11-16T19:16:49.926+0000\",\"status\":\"placed\",\"complete\":true}\n";
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //GET /store/order
    public String[] get_order_by_id(int ID){
        URL = (URL + "order/" + ID);
        String request_type = ("GET");
        String data_out = ("Nothing");
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //DELETE /store/order/{orderId]
    public String[] delete_order_byID(int ID){
        URL = (URL + "order/" + ID);
        String request_type = ("DELETE");
        String data_out = ("Nothing");
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }


}
