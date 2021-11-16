package test_pet;


import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.pet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_pet_2 {


    /////////////////////
    //Test-Data
    int ID = 255;
    String NAME = "CAT";
    String PET_STATUS = "sold";
    String UPDATE_NAME = "HUND";
    String UPDATE_STATUS = "pending";
    /////////////////////

    private pet pet_store;

    @BeforeEach
    public void init() { pet_store = new pet();}

    @Test
    @Order(1)
    @DisplayName("Add Pet")
    void post_pet() {
        String[] output = pet_store.add_pet(ID, NAME, PET_STATUS);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }


    @Test
    @Order(2)
    @DisplayName("Update Pet Name and Status with PUT")
    void update_pet() {
        String[] output = pet_store.update_pet(ID, UPDATE_NAME, UPDATE_STATUS);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(3)
    @DisplayName("Check if Pet ist updated")
    void check_updated_pet() {
        String[] output = pet_store.get_pet_byID(ID);
        String return_message = output[0];

        String CURRENT_NAME = JsonPath.read(return_message, "$.name");
        String CURRENT_STATUS = JsonPath.read(return_message, "$.status");

        Assertions.assertEquals(UPDATE_NAME, CURRENT_NAME);
        Assertions.assertEquals(UPDATE_STATUS, CURRENT_STATUS);
    }

    @Test
    @Order(4)
    @DisplayName("Delete Pet")
    void delete_pet() {
        String[] output = pet_store.delete_pet(ID);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }
}
