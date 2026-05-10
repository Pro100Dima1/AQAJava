package iteration2JunApiTests;

import generator.RandomData;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import models.*;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import requests.*;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static java.lang.reflect.Array.getFloat;
import static javax.swing.UIManager.getInt;

public class TransferUserMoney extends BaseTest {

    public static Stream<Arguments> validTransferValue() {
        return Stream.of(
                Arguments.of( 5000),
                Arguments.of( 0.01F),
                Arguments.of( 200.1F)
        );
    }

    @MethodSource("validTransferValue")
    @DisplayName("Positive test")
    @ParameterizedTest
    public void transferUserMoney(float amount) {
        // Авторизация Админа
        AuthorizationRequest authorizationRequest = AuthorizationRequest.builder()
                .username("admin")
                .password("admin")
                .build();

        new AutharizationRequester(RequestSpecs.autharizationByAdmin(), ResponseSpecs.requestReturnStatusOK())
                .post(authorizationRequest);

        CreateUserByAdminRequest createUserByAdminRequest = CreateUserByAdminRequest.builder()
                .username(RandomData.getName())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();
        // Создание юзера админом
        new AdminCreateUserRequester(RequestSpecs.autharizationByAdmin(), ResponseSpecs.requestReturnStatusCreated())
                .post(createUserByAdminRequest);

        //Получение токена юзера при логине :
        AuthorizationRequest authorizationRequestUser = AuthorizationRequest.builder()
                .username(createUserByAdminRequest.getUsername())
                .password(createUserByAdminRequest.getPassword())
                .build();

        new AutharizationRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .post(authorizationRequestUser);

        //Создание аккаунта юзеру
        new CreateAccountRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusCreated())
                .post(null);
        //Создание второго аккаунта юзеру
        new CreateAccountRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusCreated())
                .post(null);
        //Депозит денег юзеру на аккаунт
        int idAccount = new GetInfoUserRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .get(null)
                .extract()
                .body().jsonPath().getInt("accounts[0].id");

        int idAccount2 = new GetInfoUserRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .get(null)
                .extract()
                .body().jsonPath().getInt("accounts[1].id");

        DepositeRequest depositeRequest = DepositeRequest.builder()
                .balance(amount)
                .id(idAccount)
                .build();

        new DepositeRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.balanceMatches(depositeRequest.getBalance()))
                .post(depositeRequest);
        // Трансфер денег с аккаунта на аккаунт
        TransferRequest transferRequest = TransferRequest.builder()
                .senderAccountId(idAccount)
                .receiverAccountId(idAccount2)
                .amount(amount)
                .build();

        TransferResponse transferResponse = new TransferRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .post(transferRequest)
                .extract()
                .as(TransferResponse.class);

        softly.assertThat(transferResponse.getAmount()).isEqualTo(amount);
    }


    public static Stream<Arguments> inValidTransferValue() {
        return Stream.of(
                Arguments.of( -100),
                Arguments.of( 10000.01),
                Arguments.of( 5348.999999999998)
        );
    }

    @MethodSource("inValidTransferValue")
    @DisplayName("Negative tests")
    @ParameterizedTest
    public void canNotTransferUserMoney(int senderid, int receiverid, int amount) {

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
