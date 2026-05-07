package requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.AuthorizationRequest;

import static io.restassured.RestAssured.given;

public class AdminAutharizationRequester extends Requests<AuthorizationRequest> {

    public AdminAutharizationRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse get(AuthorizationRequest model) {
        return null;
    }

    @Override
    public ValidatableResponse post(AuthorizationRequest model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .post("/api/v1/auth/login")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse put(AuthorizationRequest mmodel) {
        return null;
    }
}
