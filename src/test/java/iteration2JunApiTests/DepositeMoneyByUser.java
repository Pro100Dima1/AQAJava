package iteration2JunApiTests;


import generator.RandomData;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import requests.*;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.List;
import java.util.stream.Stream;

public class DepositeMoneyByUser {

    public static Stream<Arguments> validValueOfDeposite() {
        return Stream.of(
                Arguments.of( RandomData.getRandomBalance()),
                Arguments.of( "0"),
                Arguments.of( "4999.99F"),
                Arguments.of( "5000F"),
                Arguments.of( RandomData.getRandomBalance()),
                Arguments.of( "0.01F")
        );
    }

    @MethodSource("validValueOfDeposite")
    @DisplayName("Happy path tests")
    @ParameterizedTest
    public void makeSuccsessDepositeMoneyByUser(float balance) {
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
                .post();
        //Депозит денег на аккаунт юзера
        int idAccount = new GetInfoUserRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .get()
                .extract()
                .body().jsonPath().getInt("accounts[0].id");

        DepositeRequest depositeRequest = DepositeRequest.builder()
                .balance(balance)
                .id(idAccount)
                .build();

        new DepositeRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.balanceMatches(depositeRequest.getBalance()))
                .post(depositeRequest);

        List<CheckUserAccountsResponse> checkUserAccountsResponse = new GetInfoAccountsUserRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .get()
                .extract()
                .jsonPath().getList("", CheckUserAccountsResponse.class);

        new GetInfoAccountsUserRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.balanceMatches(checkUserAccountsResponse.get(0).getBalance(), authorizationRequestUser))
                .get();
    }

    public static Stream<Arguments> invalidValueOfDeposite() {
        return Stream.of(
                Arguments.of( "-100", "Deposit amount must be at least 0.01"),
                Arguments.of( "5000.01", "Deposit amount cannot exceed 5000"),
                Arguments.of( "0.0001", "Deposit amount must be at least 0.01")
        );
    }

    @MethodSource("invalidValueOfDeposite")
    @DisplayName("Negative tests")
    @ParameterizedTest
    public void invalidDepositeValue(float balance, String errorValue) {
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
                .post();
        //Депозит денег на аккаунт юзера
        int idAccount = new GetInfoUserRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.requestReturnStatusOK())
                .get()
                .extract()
                .body().jsonPath().getInt("accounts[0].id");

        DepositeRequest depositeRequest = DepositeRequest.builder()
                .balance(balance)
                .id(idAccount)
                .build();

        new DepositeRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.userCanNotChangeNameBadRequest(errorValue))
                .post(depositeRequest);
    }
}
