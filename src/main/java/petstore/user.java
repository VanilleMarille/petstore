package petstore;

import REST.request;


public class user {

    String URL = ("https://petstore.swagger.io/v2/user/");

    //POST /user
    public String[] post_new_user(int ID, String USERNAME, String FIRSTNAME, String LASTNAME, String EMAIL, String PASSWORD, String PHONE) {
        String request_type = ("POST");
        String data_out = "{\"id\":" + ID + ",\"username\":\"" + USERNAME + "\",\"firstName\":\"" + FIRSTNAME + "\",\"lastName\":\"" + LASTNAME + "\",\"email\":\"" + EMAIL + "\",\"password\":\"" + PASSWORD + "\",\"phone\":\"" + PHONE + "\",\"userStatus\":0}";
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //GET /user/{username}
    public String[] get_user_by_username(String USERNAME){
        URL = (URL + USERNAME);
        String request_type = ("GET");
        String data_out = ("Nothing");
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //DELETE /user/{username}
    public String[] delete_user(String USERNAME){
        URL = (URL + USERNAME);
        String request_type = ("DELETE");
        String data_out = ("Nothing");
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }

    //PUT /user/{username}
    public String[] update_user(int ID, String USERNAME, String NEW_FIRSTNAME, String NEW_LASTNAME, String NEW_EMAIL, String NEW_PASSWORD, String NEW_PHONE){
        URL = (URL + USERNAME);
        String request_type = ("PUT");
        String data_out = "{\"id\":" + ID + ",\"username\":\"" + USERNAME + "\",\"firstName\":\"" + NEW_FIRSTNAME + "\",\"lastName\":\"" + NEW_LASTNAME + "\",\"email\":\"" + NEW_EMAIL + "\",\"password\":\"" + NEW_PASSWORD + "\",\"phone\":\"" + NEW_PHONE +"\",\"userStatus\":0}";
        String content_type = ("json");

        return request.rest_request(URL, data_out, content_type, request_type);
    }
}
