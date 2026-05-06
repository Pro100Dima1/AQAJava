package requests;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Requests {
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;

    public Requests(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        this.requestSpecification = requestSpecification;
        this.responseSpecification = responseSpecification;
    }
}
