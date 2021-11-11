# Petstore-Test
Exercise Petstore Test

Petstore-Test was written in JAVA with Intellij IDEA.

The Testcases are using the Junit testing framework.

External libraries used: com.jayway.jsonpath.JsonPath

Implementation of the petstore are stored under src/main/java/petstore

Implementation of the testcases are stored under src/test/java

## Testcases

### Testing /pet

testcase_pet_1:
* Post pet
* Get pet and Check pet
* Delete pet
* Check if pet is deleted
  
testcase_pet_2:
* Post pet
* Update pet name and status with PUT
* Check if Pet is updated
* Delete Pet
  
testcase_pet_3:
* Post pet
* Update pet name and status with form data
* Find pet by status and check if pet is updated
* Delete pet
  
### Testing /store

testcase_store_1:
* Get store inventory
* Get order by ID
* Delete order by ID
    
    
testcase_store_2:
* Place order for pet with quantity
* Get order by ID and check quantity
* Delete order by ID
  
### Testing /user
  
testcase_user_1:
* create user
* check if user is created
* delete user by username
* check if user is deleted
  
testcase_user_2:
* create user
* update user with put
* check if user is updated
* Delte updated user
  
  
### Not tested:
* /user/createWithArrey - Works the same way as /user/createWithList
* /user/login - Seams like a dummy. Allways return code 200. Not able to test.
* /user/logout - Same as /user/login
  
