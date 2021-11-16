package test_user;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.user;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_user_2 {


    /////////////////////
    //Test-Data
    int ID = 257;
    String USERNAME = "obst123";
    String FIRSTNAME = "Ban";
    String LASTNAME  = "Anne";
    String EMAIL = "BanAnne@email.com";
    String PASSWORD = "old1234";
    String PHONE = "06761234";

    String NEW_FIRSTNAME = "Tom";
    String NEW_LASTNAME = "Arte";
    String NEW_EMAIL = "TomArte@email.com";
    String NEW_PASSWORD = "new1234";
    String NEW_PHONE = "06641234";
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
    @DisplayName("Update user with put")
    void update_user() {
        String[] output = pet_store.update_user(ID, USERNAME, NEW_FIRSTNAME, NEW_LASTNAME, NEW_EMAIL, NEW_PASSWORD, NEW_PHONE);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(3)
    @DisplayName("Check if user is updated")
    void check_if_updated() {
        String[] output = pet_store.get_user_by_username(USERNAME);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        String CURRENT_FIRSTNAME = JsonPath.read(return_message, "$.firstName");
        String CURRENT_LASTNAME = JsonPath.read(return_message, "$.lastName");
        String CURRENT_EMAIL = JsonPath.read(return_message, "$.email");
        String CURRENT_PASSWORD = JsonPath.read(return_message, "$.password");
        String CURRENT_PHONE = JsonPath.read(return_message, "$.phone");

        Assertions.assertEquals(200, status);
        Assertions.assertEquals(NEW_FIRSTNAME, CURRENT_FIRSTNAME);
        Assertions.assertEquals(NEW_LASTNAME, CURRENT_LASTNAME);
        Assertions.assertEquals(NEW_EMAIL, CURRENT_EMAIL);
        Assertions.assertEquals(NEW_PASSWORD, CURRENT_PASSWORD);
        Assertions.assertEquals(NEW_PHONE, CURRENT_PHONE);
    }

    @Test
    @Order(4)
    @DisplayName("Delete updated user")
    void delete_updated_user() {
        String[] output = pet_store.delete_user(USERNAME);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }




}
