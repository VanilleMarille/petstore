package test_pet;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.pet;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_pet_3 {


    /////////////////////
    //Test-Data
    int ID = 255;
    String NAME = "Hund";
    String PET_STATUS = "pending";
    String UPDATE_NAME = "Cat";
    String UPDATE_STATUS = "sold";
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
    @DisplayName("Update Pet Name and Status with form data")
    void update_pet_formData (){
        String[] output = pet_store.update_pet_formData(ID, UPDATE_NAME, UPDATE_STATUS);
        int status = Integer.parseInt(output[1]);

        String return_message = output[0];


        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(3)
    @DisplayName("Find Pet by Status and check if updated")
    void find_by_status () {
        String FIND_STATUS = UPDATE_STATUS;
        String[] output = pet_store.get_pet_byStatus(FIND_STATUS);
        String return_message = output[0];


        List<String> UPDATED_PET = JsonPath.read(return_message, "$..[?(@.id==" + ID + ")]");

        List<String> get_status = JsonPath.read(UPDATED_PET, "$..status");
        String CURRENT_STATUS = get_status.toString();
        Assertions.assertEquals("[\"" + FIND_STATUS + "\"]", CURRENT_STATUS );

        List<String> get_names = JsonPath.read(UPDATED_PET, "$..name");
        String CURRENT_NAME = get_names.toString().substring(2).split("\"")[0];
        Assertions.assertEquals(UPDATE_NAME, CURRENT_NAME);

    }

    @Test
    @Order(4)
    @DisplayName("Delete Pet")
    void delete_pet(){
        String[] output = pet_store.delete_pet(ID);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);}

}
