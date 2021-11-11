package test_store;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.store;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_store_2 {

    private store petstore;

    //Test-Data
    int id = 3;
    int quantity = 6;

    @BeforeEach
    public void init() {petstore = new store();}

    @Test
    @Order(1)
    @DisplayName("Place order for pet with Quantity")
    void place_order_for_pet(){
        String output[] = petstore.post_order_for_pet(id, quantity);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        Assertions.assertEquals(200, status);
        System.out.println(return_message);
    }


    @Test
    @Order(2)
    @DisplayName("Get order by ID and check quantity")
    void get_order_by_id(){
        String output[] = petstore.get_order_by_id(id);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        int check_quantity = JsonPath.read(return_message, "$.quantity");

        Assertions.assertEquals(quantity, check_quantity);
        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(3)
    @DisplayName("Delete order by ID")
    void delete_order_by_id(){
        String output[] = petstore.delete_order_byID(id);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(4)
    @DisplayName("Check if order is deleted")
    void check_if_order_is_deleted(){
        String output[] = petstore.get_order_by_id(id);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(404, status);

    }
}

