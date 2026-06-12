package requests.skelethon.requesters;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.BaseModel;
import requests.skelethon.HttpRequest;
import requests.skelethon.interfaces.CrudEndpointInterface;
import requests.skelethon.interfaces.Endpoint;

import static io.restassured.RestAssured.given;

public class CrudRequester extends HttpRequest implements CrudEndpointInterface {
    //Для негативных проверок. Ибо в негативных ответах не всегда есть тело ответа итд
    public CrudRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification, Endpoint endpoint) {
        super(requestSpecification, responseSpecification, endpoint);
    }

    @Override
    public ValidatableResponse post(BaseModel model) {
        var body = model == null ? "" : model; // проверка, что объект есть и не пустой
        return given()
                .spec(requestSpecification)
                .body(body)
                .post(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    public ValidatableResponse post() {
        return given()
                .spec(requestSpecification)
                .post(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse get() {
        return  given()
                .spec(requestSpecification)
                .get(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse put(BaseModel model) {
        var body = model == null ? "" : model;
        return given()
                .spec(requestSpecification)
                .body(body)
                .put(endpoint.getUrl())
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse delete(int id) {
        return given()
                .spec(requestSpecification)
                .delete(endpoint.getUrl() + "/" + id)
                .then()
                .spec(responseSpecification);
    }
}
