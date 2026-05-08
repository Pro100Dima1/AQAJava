package iteration2JunApiTests;

import generator.RandomData;
import io.restassured.http.ContentType;
import models.AuthorizationRequest;
import models.CreateUserByAdminRequest;
import models.UserRole;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import requests.AutharizationRequester;
import requests.AdminCreateUserRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class DepositeMoneyByUser {

    @BeforeAll
    public static void createUserByAdmin() {
        //Авторизация Админа для возможности осздать юзера :
        AuthorizationRequest authorizationRequest = AuthorizationRequest.builder()
                .username("admin")
                .password("admin")
                .build();

        new AutharizationRequester(RequestSpecs.autharizationByAdmin(), ResponseSpecs.requestReturnStatusOK())
                .post(authorizationRequest);

        //Создание юзера админом :
        CreateUserByAdminRequest createUserByAdminRequest = CreateUserByAdminRequest.builder()
                .username(RandomData.getName())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        new AdminCreateUserRequester(RequestSpecs.autharizationByAdmin(), ResponseSpecs.userWasCreatedByAdminSucssess())
                .post(createUserByAdminRequest);
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

        //Получение баланса аккаунта
        double accountBalance = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().path("balance");

        //Проверка, что баланс аккаунта совпадает с совершенным депозитом
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("balance", Matchers.equalTo(accountBalance));
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

        //Получение баланса аккаунта
        double accountBalance = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().path("balance");

        //Проверка, что баланс аккаунта совпадает с совершенным депозитом
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("balance", Matchers.not(Matchers.equalTo(accountBalance)));
    }
}
