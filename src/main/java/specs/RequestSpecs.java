package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.AuthorizationRequest;
import requests.AutharizationRequester;
import requests.GetInfoUserRequester;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestSpecs {
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
                .setBaseUri("http://localhost:4111");
    }

    public static RequestSpecification autharizationByAdmin() {
        return RequestSpecs.defaultRequestSpec()
                .addHeader("Authorization", "YWRtaW46YWRtaW4=")
                .build();
    }

    public static RequestSpecification userLogin() {
        return defaultRequestSpec().build();
    }

    public static RequestSpecification getInfo() {
        return defaultRequestSpec().build();
    }


    public static RequestSpecification autharizationByUser(String username, String password) {
        String userAuthToken = new AutharizationRequester(RequestSpecs.userLogin(), ResponseSpecs.requestReturnStatusOK())
                .post(AuthorizationRequest.builder().username(username).password(password).build())
                .extract()
                .header("Authorization");
        return defaultRequestSpec()
                .addHeader("Authorization", userAuthToken)
                .build();
    }

    public static RequestSpecification getUserInfo() {
        String nameUser = new GetInfoUserRequester(RequestSpecs.getInfo(), ResponseSpecs.requestReturnStatusOK())
                .get(null)
                .extract()
                .body().jsonPath().getString("name");
        return defaultRequestSpec()
                .build();
    }
}
