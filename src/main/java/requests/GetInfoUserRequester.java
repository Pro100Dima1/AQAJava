package requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.BaseModel;
import models.GetUserInfoRequest;

import static io.restassured.RestAssured.given;

public class GetInfoUserRequester extends Requests {
    public GetInfoUserRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse get(BaseModel model) {
         return given()
                .spec(requestSpecification)
                .get("/api/v1/customer/profile")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    public ValidatableResponse get() {
        return given()
                .spec(requestSpecification)
                .get("/api/v1/customer/profile")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse post(BaseModel model ) {
        return null;
    }

    @Override
    public ValidatableResponse put(BaseModel model ) {
        return null;
    }
}
