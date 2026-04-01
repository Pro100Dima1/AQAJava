package iteration2JunApiTests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class ChangingUserName {

    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.filters(
                List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()));
    }

    @BeforeAll
    public static void createUserByAdmin(){
        //Авторизация Админа для возможности осздать юзера :
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "username": "admin",
                          "password": "admin"
                        }
                        """)
                .post("http://localhost:4111/api/v1/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        //Создание юзера админом :
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                            "username": "Dima100",
                            "password": "Qa934100!",
                            "role": "USER"
                        }
                        """)
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @CsvSource({
            "Dima Orlow",
            "DDDD ORRLOOVV"
    })
    @ParameterizedTest
    @DisplayName("Happy path test")
    public void changingNameTwoWordsWithSpaceTest(String name) {
        String requestBody = String.format(
                """
                        {
                            "name" : "%s"
                        }
                        """, name);
        //Получение токена юзера при логине :
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

        //Изменение имени :
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .body(requestBody)
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        //Получение Имени юзера из тела ответа :
        String nameUser = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("name");

        //Проверка, что создался юзер с этим именем
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .body("name", Matchers.equalTo(nameUser));
    }

    public static Stream<Arguments> invalidNameUserTests() {
        return Stream.of(
                Arguments.of("Dima  Orlov", "Name must contain two words with letters only"),
                Arguments.of("DimaOrlov", "Name must contain two words with letters only"),
                Arguments.of("DimaOrlov ", "Name must contain two words with letters only"),
                Arguments.of(" ", "Name must contain two words with letters only"),
                Arguments.of("!@!@!@ !@!@!", "Name must contain two words with letters only"),
                Arguments.of("12345 6789", "Name must contain two words with letters only"),
                Arguments.of("123!!!45 67@@@89", "Name must contain two words with letters only"),
                Arguments.of("Dima Orlov Kerpich", "Name must contain two words with letters only"),
                Arguments.of("Dima 934435", "Name must contain two words with letters only"),
                Arguments.of("Дима Орлов", "Name must contain two words with letters only")
        );
    }

    @MethodSource("invalidNameUserTests")
    @DisplayName("Negative test")
    @ParameterizedTest
    public void negativeTestsChangingName(String name, String errorValue) {
        String requestBody = String.format(
                """
                        {
                            "name" : "%s"
                        }
                        """, name);

        //Получение токена юзера при логине :
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

        //Изменение имени на НЕ валидное :
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .body(requestBody)
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(Matchers.equalTo(errorValue));
    }
}
