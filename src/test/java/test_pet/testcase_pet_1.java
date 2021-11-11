package test_pet;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.pet;


@Tag("development")
@Tag("stable")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_pet_1 {

    private pet petstore;

    //Test-Data
    int id = 355;
    String post_name = "HUND";
    String post_status = "sold";

    @BeforeEach
    public void init() { petstore = new pet(); }


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
    @DisplayName("Get and Check Pet")
    void get_and_check(){
       String output[] = petstore.get_pet_byID(id);
       int status = Integer.parseInt(output[1]);
       String return_message = output[0];

       Assertions.assertEquals(200, status);

       String current_name = JsonPath.read(return_message, "$.name");
       String current_status = JsonPath.read(return_message, "$.status");

       Assertions.assertEquals(post_name, current_name);
       Assertions.assertEquals(post_status, current_status);
    }

    @Test
    @Order(3)
    @DisplayName("Delete Pet")
    void delete_pet(){
        String output[] = petstore.delete_pet(id);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        Assertions.assertEquals(200, status);

    }


    @Test
    @Order(4)
    @DisplayName("Check if Pet is deleted")
    void check_deleted_pet(){
        String output[] = petstore.get_pet_byID(id);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        Assertions.assertEquals(404, status);
    }

}
