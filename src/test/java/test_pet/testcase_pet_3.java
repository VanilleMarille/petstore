package test_pet;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import petstore.pet;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testcase_pet_3 {

    private pet petstore;

    //Test-Data
    int id = 255;
    String post_name = "Hund";
    String post_status = "pending";
    String update_name = "Katze";
    String update_status = "sold";

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
    @DisplayName("Update Pet Name and Status with form data")
    void update_pet_formData (){
        String output[] = petstore.post_pet_formData(id, update_name, update_status);
        int status = Integer.parseInt(output[1]);

        String return_message = output[0];


        System.out.println(status);
        System.out.println(return_message);
        Assertions.assertEquals(200, status);


    }

    @Test
    @Order(3)
    @DisplayName("Find Pet by Status and check if updated")
    void find_by_status () {

        String output[] = petstore.get_pet_byStatus(update_status);
        int status = Integer.parseInt(output[1]);
        String return_message = output[0];

        System.out.println(return_message);

        List<String> updated_pet = JsonPath.read(return_message, "$..[?(@.id==" + id + ")]");

        List<String> get_status = JsonPath.read(updated_pet, "$..status");
        String current_status = get_status.toString();
        Assertions.assertEquals("[\"" + update_status + "\"]", current_status );

        List<String> get_names = JsonPath.read(updated_pet, "$..name");
        String current_name = get_names.toString().substring(2).split("\"")[0];
        Assertions.assertEquals(update_name, current_name);

    }

    @Test
    @Order(4)
    @DisplayName("Delete Pet")
    void delete_pet(){
        String output[] = petstore.get_pet_byID(id);
        int status = Integer.parseInt(output[1]);

        Assertions.assertEquals(200, status);}

}
