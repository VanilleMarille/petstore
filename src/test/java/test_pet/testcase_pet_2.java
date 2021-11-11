package test_pet;


import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.pet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_pet_2 {

    private pet petstore;

    //Test-Data
    int id = 255;
    String post_name = "KATZE";
    String post_status = "sold";
    String update_name = "HUND";
    String update_status = "pending";

    @BeforeEach
    public void init() { petstore = new pet();}

    @Test
    @Order(1)
    @DisplayName("Post Pet")
    void post_pet() {
        String output[] = petstore.post_pet(id, post_name, post_status);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }


    @Test
    @Order(2)
    @DisplayName("Update Pet Name and Status with PUT")
    void update_pet() {
        String output[] = petstore.update_pet_put(id, update_name, update_status);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(3)
    @DisplayName("Check if Pet ist updated")
    void check_updated_pet() {
        String output[] = petstore.get_pet_byID(id);
        int status =  Integer.parseInt(output[1]);
        String return_message = output[0];

        String current_name = JsonPath.read(return_message, "$.name");
        String current_status = JsonPath.read(return_message, "$.status");

        Assertions.assertEquals(update_name, current_name);
        Assertions.assertEquals(update_status, current_status);
    }

    @Test
    @Order(4)
    @DisplayName("Delete Pet")
    void delete_pet() {
        String output[] = petstore.delete_pet(id);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        Assertions.assertEquals(200, status);
    }
}
