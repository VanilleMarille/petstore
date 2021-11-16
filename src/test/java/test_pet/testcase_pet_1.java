package test_pet;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.pet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_pet_1 {


    /////////////////////
    //Test-Data
    int ID = 355;
    String NAME = "Kuchen";
    String PET_STATUS = "pending";
    /////////////////////

    private pet pet_store;

    @BeforeEach
    public void init() { pet_store = new pet(); }


    @Test
    @Order(1)
    @DisplayName("Add pet")
    void post_pet() {
        String[] output = pet_store.add_pet(ID, NAME, PET_STATUS);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(2)
    @DisplayName("Get and Check Pet")
    void get_and_check(){
       String[] output = pet_store.get_pet_byID(ID);
       int status = Integer.parseInt(output[1]);
       String return_message = output[0];

       Assertions.assertEquals(200, status);

       String CURRENT_NAME = JsonPath.read(return_message, "$.name");
       String CURRENT_STATUS = JsonPath.read(return_message, "$.status");

       Assertions.assertEquals(NAME, CURRENT_NAME);
       Assertions.assertEquals(PET_STATUS, CURRENT_STATUS);
    }

    @Test
    @Order(3)
    @DisplayName("Delete Pet")
    void delete_pet(){
        String[] output = pet_store.delete_pet(ID);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }


    @Test
    @Order(4)
    @DisplayName("Check if Pet is deleted")
    void check_deleted_pet(){
        String[] output = pet_store.get_pet_byID(ID);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(404, status);
    }

}
