package test_user;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.user;

public class testcase_user_2 {
    private user petstore;

    //Test-Data
    int id = 257;
    String username = "Kuchen99";
    String firstname = "Franz";
    String lastname  = "Glanz";
    String email = "FranzGlanz@email.com";
    String password = "old1234";
    String phone = "06761234";

    String new_firstname = "Tom";
    String new_lastname = "Arte";
    String new_email = "TomArte@email.com";
    String new_password = "new1234";
    String new_phone = "06641234";

    @BeforeEach
    public void init() { petstore = new user();}

    @Test
    @Order(1)
    @DisplayName("Create User")
    void create_user() {
        String output[] = petstore.post_new_user(id, username, firstname,lastname,email,password,phone);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);

    }


    @Test
    @Order(2)
    @DisplayName("Update user with put")
    void update_user_with_put() {
        String output[] = petstore.update_user(id, username,new_firstname,new_lastname,new_email,new_password,new_phone);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(3)
    @DisplayName("Check if user is updated")
    void check_if_updated() {
        String output[] = petstore.get_user_by_username(username);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        String current_firstname = JsonPath.read(return_message, "$.firstName");
        String current_lastname = JsonPath.read(return_message, "$.lastName");
        String current_email = JsonPath.read(return_message, "$.email");
        String current_password = JsonPath.read(return_message, "$.password");
        String current_phone = JsonPath.read(return_message, "$.phone");

        Assertions.assertEquals(new_firstname, current_firstname);
        Assertions.assertEquals(new_lastname, current_lastname);
        Assertions.assertEquals(new_email, current_email);
        Assertions.assertEquals(new_password, current_password);
        Assertions.assertEquals(new_phone, current_phone);
    }

    @Test
    @Order(4)
    @DisplayName("Delete updated user")
    void delete_updated_user() {
        String output[] = petstore.delete_user(username);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        Assertions.assertEquals(200, status);
    }




}
