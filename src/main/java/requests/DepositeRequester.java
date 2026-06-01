package requests;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.DepositeRequest;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class DepositeRequester extends Requests<DepositeRequest> {
    public DepositeRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse get(DepositeRequest model) {
        return null;
    }

    @Override
    public ValidatableResponse post(DepositeRequest model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .post("/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse put(DepositeRequest mmodel) {
        return null;
    }
}
