package iteration2JunApiTests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class DepositeMoneyByUser {

    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.filters(
                List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()));
    }

    @BeforeAll
    public static void createUserByAdmin() {
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
                            "username": "Dima1001",
                            "password": "Qa934100!1",
                            "role": "USER"
                        }
                        """)
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);

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
    }

    public static Stream<Arguments> validValueOfDeposite() {
        return Stream.of(
                Arguments.of("1", "3000"),
                Arguments.of("1", "0"),
                Arguments.of("1", "4999.99"),
                Arguments.of("1", "5000"),
                Arguments.of("1", "5000"),
                Arguments.of("1", "0.01")
        );
    }

    @MethodSource("validValueOfDeposite")
    @DisplayName("Happy path tests")
    @ParameterizedTest
    public void makeSuccsessDepositeMoneyByUser(int id, int balance) {
        String requestBodyForDeposite = String.format(
                """
                        {
                          "id": 1,
                          "balance": 3000
                        }
                        """, id, balance);
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


        //Депозит денег на аккаунт юзера
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .body(requestBodyForDeposite)
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    public static Stream<Arguments> invalidValueOfDeposite() {
        return Stream.of(
                Arguments.of("1", "-100"),
                Arguments.of("1", "5000.01"),
                Arguments.of("1", "0.0001")
        );
    }

    @MethodSource("invalidValueOfDeposite")
    @DisplayName("Negative tests")
    @ParameterizedTest
    public void invalidDepositeValue(int id, int balance) {
        String requestBodyForDeposite = String.format(
                """
                        {
                          "id": 1,
                          "balance": 3000
                        }
                        """, id, balance);
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

        //Депозит денег на аккаунт юзера
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .body(requestBodyForDeposite)
                .post("http://localhost:4111/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
