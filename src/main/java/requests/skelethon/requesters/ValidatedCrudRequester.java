package requests.skelethon.requesters;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.BaseModel;
import requests.skelethon.HttpRequest;
import requests.skelethon.interfaces.CrudEndpointInterface;
import requests.skelethon.interfaces.Endpoint;

public class ValidatedCrudRequester<T extends BaseModel> extends HttpRequest implements CrudEndpointInterface {
    private CrudRequester crudRequester;

    // Для пзитивных тестов. Мы подразумеваем, что запрос прошел успешно и получили ответ 200 и дессериализуем ответ в объект. далее валидируем поля
    public ValidatedCrudRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification, Endpoint endpoint) {
        super(requestSpecification, responseSpecification, endpoint);
        this.crudRequester = new CrudRequester(requestSpecification, responseSpecification, endpoint);
    }

    @Override
    public T post(BaseModel model) {
        return (T)crudRequester.post(model).extract().as(endpoint.getResponseModel());
    }

    public T post() {
        return (T)crudRequester.post().extract().as(endpoint.getResponseModel());
    }

    @Override
    public T get() {
        return (T)crudRequester.post().extract().as(endpoint.getResponseModel());
    }

    @Override
    public T put(BaseModel model) {
        return (T)crudRequester.post(model).extract().as(endpoint.getResponseModel());
    }

    @Override
    public T delete(int id) {
        return (T)crudRequester.post().extract().as(endpoint.getResponseModel());
    }
}
