package test_user;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.user;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_user_1 {

    private user petstore;

    //Test-Data
    int id = 257;
    String username = "Kuchen99";
    String firstname = "Hans";
    String lastname = "Peter";
    String email = "HansP@mail.com";
    String password = "classic1234";
    String phone = "06991234";

    @BeforeEach
    public void init() { petstore = new user();}

    @Test
    @Order(1)
    @DisplayName("Create User")
    void create_user() {
        String output[] = petstore.post_new_user(id, username, firstname, lastname, email, password, phone);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(2)
    @DisplayName("Check if user is created")
    void get_user_by_username() {
        String output[] = petstore.get_user_by_username(username);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        Assertions.assertEquals(200, status);

        String check_name = JsonPath.read(return_message, "$.username");

        Assertions.assertEquals(username, check_name);

    }

    @Test
    @Order(3)
    @DisplayName("Delete user by username")
    void delete_user_by_username(){
        String output[] = petstore.delete_user(username);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(4)
    @DisplayName("Check if user is deleted")
    void check_if_user_is_deleted(){
        String output[] = petstore.get_user_by_username(username);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        Assertions.assertEquals(404, status);
    }
}
