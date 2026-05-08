package requests;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.BaseModel;
import models.ChangeNameByUserRequest;
import org.apache.http.HttpStatus;
import specs.RequestSpecs;

import static io.restassured.RestAssured.given;

public class ChangeNameRequester extends Requests<ChangeNameByUserRequest> {
    public ChangeNameRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse get(ChangeNameByUserRequest model) {
        return null;
    }

    @Override
    public ValidatableResponse post(ChangeNameByUserRequest model) {
        return null;
    }

    @Override
    public ValidatableResponse put(ChangeNameByUserRequest mmodel) {
        return given()
                .spec(requestSpecification)
                .body(mmodel)
                .put("/api/v1/customer/profile")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }
}
