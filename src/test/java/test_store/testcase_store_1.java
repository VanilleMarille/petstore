package test_store;

import org.junit.jupiter.api.*;
import petstore.store;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_store_1 {

    private store petstore;

    //Test-Data
    int id = 255;

    @BeforeEach
    public void init() {petstore = new store();}

    @Test
    @Order(1)
    @DisplayName("Get Store Inventory")

    void get_store_inventory() {
        String output[] = petstore.get_store_inventory();
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }


    @Test
    @Order(3)
    @DisplayName("Get order by ID")
    void get_order_by_id(){
        String output[] = petstore.get_order_by_id(id);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }

    @Test
    @Order(4)
    @DisplayName("Delete order by ID")
    void delete_order_by_id(){
        String output[] = petstore.delete_order_byID(id);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);
    }
}

