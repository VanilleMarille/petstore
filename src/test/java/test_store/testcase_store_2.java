package test_store;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.pet;
import petstore.store;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_store_2 {

    /////////////////////
    //Test-Data
    int ID = 3;
    int QUANTITY = 6;
    int PET_ID = 1;
    String PET_NAME = "Tom Arte";
    String PET_STATUS = "available";
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
    @DisplayName("Add new Pet")
    void add_pet(){
        String[] output = add_pet.add_pet(PET_ID, PET_NAME, PET_STATUS);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(2)
    @DisplayName("Place order for pet with Quantity")
    void place_order_for_pet(){
        String[] output = pet_store.post_order_for_pet(ID, PET_ID, QUANTITY);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        Assertions.assertEquals(200, status);
        System.out.println(return_message);
    }

    @Test
    @Order(3)
    @DisplayName("Get order by ID and check quantity")
    void get_order_by_id(){
        String[] output = pet_store.get_order_by_id(ID);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        int CHECK_QUANTITY = JsonPath.read(return_message, "$.quantity");

        Assertions.assertEquals(QUANTITY, CHECK_QUANTITY);
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
    @DisplayName("Check if order is deleted")
    void check_if_order_is_deleted(){
        String[] output = pet_store.get_order_by_id(ID);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(404, status);

    }

    @Test
    @Order(6)
    @DisplayName("Delete Pet")
    void delete_pet(){
        String[] output = add_pet.delete_pet(ID);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }
}

