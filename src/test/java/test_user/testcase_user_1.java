package test_user;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.user;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_user_1 {



    /////////////////////
    //Test-Data
    int ID = 257;
    String USERNAME = "Kuchen99";
    String FIRSTNAME = "Hans";
    String LASTNAME = "Peter";
    String EMAIL = "HansP@mail.com";
    String PASSWORD = "classic1234";
    String PHONE = "06991234";
    /////////////////////



    private user pet_store;

    @BeforeEach
    public void init() { pet_store = new user();}

    @Test
    @Order(1)
    @DisplayName("Create User")
    void create_user() {
        String[] output = pet_store.post_new_user(ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, PHONE);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(2)
    @DisplayName("Check if user is created")
    void get_user_by_username() {
        String[] output = pet_store.get_user_by_username(USERNAME);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        Assertions.assertEquals(200, status);

        String CHECK_NAME = JsonPath.read(return_message, "$.username");

        Assertions.assertEquals(USERNAME, CHECK_NAME);

    }

    @Test
    @Order(3)
    @DisplayName("Delete user by username")
    void delete_user_by_username(){
        String[] output = pet_store.delete_user(USERNAME);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(4)
    @DisplayName("Check if user is deleted")
    void check_if_user_is_deleted(){
        String[] output = pet_store.get_user_by_username(USERNAME);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(404, status);
    }
}
