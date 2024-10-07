package org.example;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Json_Automation {

    private static final String BASE_URL = "http://localhost:3000/employees";

    @Test
    public void createEmployee() {
        String employeeJson = "{\n" +
                "  \"id\": \"212\",\n" +
                "  \"FIRST_NAME\": \"Ramkumar\",\n" +
                "  \"LAST_NAME\": \"Sankthi\",\n" +
                "  \"EMAIL\": \"SKING\",\n" +
                "  \"PHONE_NUMBER\": \"515.123.4567\",\n" +
                "  \"HIRE_DATE\": \"1987-06-17\",\n" +
                "  \"SALARY\": 54000,\n" +
                "  \"DEPARTMENT_ID\": 90,\n" +
                "  \"Image\": \"https://www.oracle.com/webfolder/technetwork/jet/Images/dvt/10.png\"\n" +
                "}";

        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(employeeJson)
                .when().post(BASE_URL);

        response.prettyPrint();
        Assert.assertEquals(201, response.statusCode());
    }

    @Test
    public void readEmployee() {
        Response response = given()
                .header("accept", "application/json")
                .when().get(BASE_URL + "/100");

        response.prettyPrint();
        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void read_All_Employee() {
        Response response = given()
                .header("accept", "application/json")
                .when().get(BASE_URL);

        response.prettyPrint();
        Assert.assertEquals(200, response.statusCode());
    }


    @Test
    public void updateEmployee() {
        String updatedEmployeeJson = "{\n" +
                "  \"id\": \"212\",\n" +
                "  \"FIRST_NAME\": \"Steven Updated\",\n" +
                "  \"LAST_NAME\": \"King\",\n" +
                "  \"EMAIL\": \"SKING\",\n" +
                "  \"PHONE_NUMBER\": \"515.123.4567\",\n" +
                "  \"HIRE_DATE\": \"1987-06-17\",\n" +
                "  \"SALARY\": 24000,\n" +
                "  \"DEPARTMENT_ID\": 90,\n" +
                "  \"Image\": \"https://www.oracle.com/webfolder/technetwork/jet/Images/dvt/10.png\"\n" +
                "}";

        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(updatedEmployeeJson)
                .when().put(BASE_URL + "/100");

        response.prettyPrint();
        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void deleteEmployee() {
        Response response = given()
                .header("accept", "application/json")
                .when().delete(BASE_URL + "/101");

        response.prettyPrint();
        Assert.assertEquals(200, response.statusCode());
    }

}
