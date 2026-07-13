package specs;

import configs.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.AuthorizationRequest;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.CrudRequester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestSpecs {
    private static Map<String, String> authHeaders = new HashMap<>(Map.of("admin", "Basic YWRtaW46YWRtaW4="));

    private RequestSpecs() {
    } // Приватный конструктор делается, что б не могли создавать объекты этого класса

    // Дефолтный билдер из Rest Assured, в котором мы проставляем повторяющиеся моменты типа контент тайп и базовый УРЛ
    // Все методы статичны, что б вызывать их без создания объектов
    private static RequestSpecBuilder defaultRequestSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilters(List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()))
                .setBaseUri(Config.getProperty("server") + Config.getProperty("apiVersion"));
    }

    public static RequestSpecification autharizationByAdmin() {
        return RequestSpecs.defaultRequestSpec()
                .addHeader("Authorization", "Basic YWRtaW46YWRtaW4=")
                .build();
    }

    public static RequestSpecification userLogin() {
        return defaultRequestSpec().build();
    }

    public static RequestSpecification autharizationByUser(String username, String password) {
        String userAuthHeader;
//Здесь мы делаем проверку наличия токена в нашей МАПЕ. И если в МАПЕ его нет, то мы делаем запрос на логин, экстрактим токен и кладем его в нашу МАПУ
        if (!authHeaders.containsKey(username)) {
            userAuthHeader = new CrudRequester(
                    RequestSpecs.userLogin(),
                    ResponseSpecs.requestReturnStatusOK(),
                    Endpoint.LOGIN)
                    .post(AuthorizationRequest.builder().username(username).password(password).build())
                    .extract()
                    .header("Authorization");

            authHeaders.put(username, userAuthHeader);
        } else {
            userAuthHeader = authHeaders.get(username);
        }

        return defaultRequestSpec()
                .addHeader("Authorization", userAuthHeader)
                .build();
    }

    public static RequestSpecification getUserInfo(String username, String password) {
        String userAuthHeader;
//Здесь мы делаем проверку наличия токена в нашей МАПЕ. И если в МАПЕ его нет, то мы делаем запрос на логин, экстрактим токен и кладем его в нашу МАПУ
        if (!authHeaders.containsKey(username)) {
            userAuthHeader = new CrudRequester(
                    RequestSpecs.userLogin(),
                    ResponseSpecs.requestReturnStatusOK(),
                    Endpoint.LOGIN)
                    .post(AuthorizationRequest.builder().username(username).password(password).build())
                    .extract()
                    .header("Authorization");

            authHeaders.put(username, userAuthHeader);
        } else {
            userAuthHeader = authHeaders.get(username);
        }

        return defaultRequestSpec()
                .addHeader("Authorization", userAuthHeader)
                .build();
    }

    public static String getUserAuthHeader(String username, String password) {
        String userAuthHeader;

        if (!authHeaders.containsKey(username)) {
            userAuthHeader = new CrudRequester(
                    RequestSpecs.userLogin(),
                    ResponseSpecs.requestReturnStatusOK(),
                    Endpoint.LOGIN)
                    .post(AuthorizationRequest.builder().username(username).password(password).build())
                    .extract()
                    .header("Authorization");

            authHeaders.put(username, userAuthHeader);
        } else {
            userAuthHeader = authHeaders.get(username);
        }
        return userAuthHeader;
    }
}
