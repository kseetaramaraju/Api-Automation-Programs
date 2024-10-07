package org.example;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetStoreAutomation
{

    @Test
    public void create_user(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"username\": \"kseetaramaraju\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"kseetaramaraju\",\n" +
                        "  \"phone\": \"877875767\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}\n")
                .when()
                .post("https://petstore.swagger.io/v2/user");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void createWithArray(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 0,\n" +
                        "    \"username\": \"string\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"string\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .when().post("https://petstore.swagger.io/v2/user/createWithArray");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);

    }
    @Test
    public void logout(){
        Response response = given()
                .header("accept","application/json")
                .when().get("https://petstore.swagger.io/v2/user/logout");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void login(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .queryParam("username","KSeetaRamaRaju")
                .queryParam("password","Seeta@123")
                .get("https://petstore.swagger.io/v2/user/login?username=KSeetaRamaRaju&password=Seeta%40123");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void deleteUser() {
        Response response = given()
                .header("accept", "application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/user/KSeetaRamaRaju");
        response.prettyPrint();
        response.then().statusCode(404);
        Assert.assertEquals(response.statusCode(), 404);
    }
    @Test
    public void updatedUser() {
        Response response = given()
                .header("Content-Type","application/json")
                .header("accept", "application/json")
                .queryParam("username", "string")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"string\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/user/string");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }
    @Test
    public void getUserByUsername(){
        Response response = given()
                .header("accept", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/string");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }
    @Test
    public void creatUserWithGivenInputArray(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 0,\n" +
                        "    \"username\": \"string\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"string\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .when().post("https://petstore.swagger.io/v2/user/createWithList");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }


    @Test
    public void add_pet(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"puppy\"\n" +
                        "  },\n" +
                        "  \"name\": \"simba\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when().post("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    File imageFile = new File("images/dog_pets.jpg");
    @Test
    public void upload_pet_image(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","multipart/form-data")
                .formParam("additionalMetadata","good")
                .multiPart("file",imageFile)
                .when().post("https://petstore.swagger.io/v2/pet/1/uploadImage");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }


    @Test
    public void update_pet(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"puppy\"\n" +
                        "  },\n" +
                        "  \"name\": \"simba\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"un_available\"\n" +
                        "}\n")
                .when().post("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void get_pet_by_id(){
        Response response = given()
                .header("accept", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/1");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void find_pet_by_status(){
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type","application/json")
                .param("status","sold")
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=sold\n");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void update_pet_By_status(){
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type","application/json")
                .param("name","simba")
                .param("status","available")
                .when()
                .get("https://petstore.swagger.io/v2/pet/1?name=simba&status=available");
        response.prettyPrint();
        response.then().statusCode(404);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test
    public void place_order(){
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"petId\":101,\n" +
                        "  \"quantity\":1,\n" +
                        "  \"shipDate\": \"2024-09-18T08:43:58.937Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/store/order");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void get_order(){
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/store/order/1");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void get_pet_investers(){
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/store/inventory");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void delete_order(){
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type","application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/store/order/1");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }


}
