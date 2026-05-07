package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import requests.Requests;

import java.util.List;

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

    public static RequestSpecification autharizationByUser(){
        String userAuthToken = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "username": "Dima100",
                          "password": "Qa934100!"
                        }
                        """)
                .post("http://localhost:4111/api/v1/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .header("Authorization");
        return defaultRequestSpec()
                .addHeader("Authorization", userAuthToken)
                .build();
    }
}
