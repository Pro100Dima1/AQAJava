package requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.CreateUserByAdminRequest;

import static io.restassured.RestAssured.given;

public class AdminCreateUserRequester extends Requests<CreateUserByAdminRequest>{
    public AdminCreateUserRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse get(CreateUserByAdminRequest createUserByAdminRequest) {
        return null;
    }

    @Override
    public ValidatableResponse post(CreateUserByAdminRequest createUserByAdminRequest) {
        return //Создание юзера админом :
                given()
                        .spec(requestSpecification)
                        .body(createUserByAdminRequest)
                        .post("/api/v1/admin/users")
                        .then()
                        .assertThat()
                        .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse put(CreateUserByAdminRequest createUserByAdminRequest) {
        return null;
    }
}
