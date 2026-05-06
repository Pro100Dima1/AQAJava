package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class RequestSpecs {
    private RequestSpecs() {
    } // Приватный конструктор делается, что б не могли создавать объекты этого класса

    // Дефолтный билдер из Rest Assured, в котором мы проставляем повторяющиеся моменты типа контент тайп и базовый УРЛ
    // Все методы статичны, что б вызывать их без создания объектов
    public static RequestSpecBuilder defaultRequestSpec() {
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

}
