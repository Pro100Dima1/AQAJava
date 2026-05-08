package requests;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.GetUserInfoRequest;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class GetInfoUserRequester extends Requests<GetUserInfoRequest> {
    public GetInfoUserRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse get(GetUserInfoRequest model) {
         return given()
                .spec(requestSpecification)
                .get("/api/v1/customer/profile")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse post(GetUserInfoRequest model) {
        return null;
    }

    @Override
    public ValidatableResponse put(GetUserInfoRequest mmodel) {
        return null;
    }
}
