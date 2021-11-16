package test_store;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.pet;
import petstore.store;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_store_1 {

    /////////////////////
    //Test-Data
    int ID = 6;
    int PET_ID = 221;
    int QUANTITY = 1;
    String PET_NAME = "Tom Arte";
    String PET_STATUS = ("Teststring_jjw72ikks23i1akhejkwehr23df");
    /////////////////////



    private store pet_store;
    private pet add_pet;

    @BeforeEach
    public void init() {
        pet_store = new store();
        add_pet = new pet();
    }

    @Test
    @Order(1)
    @DisplayName("Add new pet with unique Status")

    void add_pet() {
        String[] output = add_pet.add_pet(PET_ID, PET_NAME, PET_STATUS);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(2)
    @DisplayName("Get Store Inventory and check for unique Status")

    void get_store_inventory() {
        String[] output = pet_store.get_store_inventory();
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];


        int GET_STATUS = JsonPath.read(return_message, "$." + PET_STATUS);

        Assertions.assertEquals(1, GET_STATUS);
        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(3)
    @DisplayName("Place order for Pet")
    void get_order_by_id(){
        String[] output = pet_store.post_order_for_pet(ID, PET_ID, QUANTITY);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(4)
    @DisplayName("Delete order by ID")
    void delete_order_by_id(){
        String[] output = pet_store.delete_order_byID(ID);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(5)
    @DisplayName("Delete Pet")
    void delete_pet(){
        String[] output = add_pet.delete_pet(PET_ID);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }
}

