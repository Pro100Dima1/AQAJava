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
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class TransferUserMoney {

    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.filters(
                List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()));
    }

    public static Stream<Arguments> validTransferValue() {
        return Stream.of(
                Arguments.of(1, 2, 10000),
                Arguments.of(1, 2, 0),
                Arguments.of(1, 2, 5000),
                Arguments.of(1, 2, 9999.99),
                Arguments.of(1, 2, 0.01),
                Arguments.of(1, 2, 200.1)
        );

    }

    @MethodSource("validTransferValue")
    @DisplayName("Positive test")
    @ParameterizedTest
    public void transferUserMoney(int senderid, int receiverid, int amount) {
        String requestTransferBody = String.format(
                """   
                        {
                           "senderAccountId": 1,
                           "receiverAccountId": 2,
                           "amount": 10000
                         }
                        """, senderid, receiverid, amount);
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

        //Трансфер денежных средств на другой аккаунт
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .body(requestTransferBody)
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        //Получение баланса аккаунта после трансфера
        double accountBalanceAfterTransfer = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().path("balance");

        //Проверка, что баланс аккаунта изменился после трансфера
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("balance", Matchers.equalTo(accountBalanceAfterTransfer));
    }

    public static Stream<Arguments> inValidTransferValue() {
        return Stream.of(
                Arguments.of(1, 2, -100),
                Arguments.of(1, 2, 10000.01),
                Arguments.of(1, 2, 5348.999999999998)
        );
    }

    @MethodSource("inValidTransferValue")
    @DisplayName("Negative tests")
    @ParameterizedTest
    public void canNotTransferUserMoney(int senderid, int receiverid, int amount) {
        String requestTransferBody = String.format(
                """   
                        {
                           "senderAccountId": 1,
                           "receiverAccountId": 2,
                           "amount": 10000
                         }
                        """, senderid, receiverid, amount);
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

        //Трансфер денежных средств на другой аккаунт
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .body(requestTransferBody)
                .post("http://localhost:4111/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

        //Получение баланса аккаунта после трансфера
        double accountBalanceAfterTransfer = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().path("balance");

        //Проверка, что баланс аккаунта изменился после трансфера
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userAuthToken)
                .get("http://localhost:4111/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("balance", Matchers.not(Matchers.equalTo(accountBalanceAfterTransfer)));
    }
}
