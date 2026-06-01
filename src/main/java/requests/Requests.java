package requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.BaseModel;

public abstract class Requests<T extends BaseModel> {
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;

    public Requests(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        this.requestSpecification = requestSpecification;
        this.responseSpecification = responseSpecification;
    }

    public abstract ValidatableResponse get(T model);

    public abstract ValidatableResponse post(T model);

    public abstract ValidatableResponse put(T mmodel);
}
