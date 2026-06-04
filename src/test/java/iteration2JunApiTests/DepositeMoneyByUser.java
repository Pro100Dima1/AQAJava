package iteration2JunApiTests;

import generator.RandomData;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import requests.*;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.steps.AdminSteps;
import requests.skelethon.requesters.steps.CreateAccountsSteps;
import requests.skelethon.requesters.steps.DepositeSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.List;
import java.util.stream.Stream;

public class DepositeMoneyByUser {

    public static Stream<Arguments> validValueOfDeposite() {
        return Stream.of(
                Arguments.of(RandomData.getRandomBalance()),
                Arguments.of("0"),
                Arguments.of("4999.99F"),
                Arguments.of("5000F"),
                Arguments.of(RandomData.getRandomBalance()),
                Arguments.of("0.01F")
        );
    }

    @MethodSource("validValueOfDeposite")
    @DisplayName("Happy path tests")
    @ParameterizedTest
    public void makeSuccsessDepositeMoneyByUser(float balance) {
        //Авторизуемся
        CreateUserByAdminRequest createUserByAdminRequest = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(createUserByAdminRequest);
        //Создаем аккаунт юзеру
        CreateAccountsSteps.createAccounts(createUserByAdminRequest);
        //Делаем депозит на аккаунт с конкретным id
        CheckUserAccountsResponse checkUserAccountsResponses = DepositeSteps.makeDeposite(balance, authorizationRequestUser);
        //С помощью Get проверяем баланс аккаунта после депозиты
        new CrudRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()), ResponseSpecs.balanceMatches(checkUserAccountsResponses.getBalance(), authorizationRequestUser), Endpoint.CUSTOMER_PROFILE)
                .get();
    }

    public static Stream<Arguments> invalidValueOfDeposite() {
        return Stream.of(
                Arguments.of("-100", "Deposit amount must be at least 0.01"),
                Arguments.of("5000.01", "Deposit amount cannot exceed 5000"),
                Arguments.of("0.0001", "Deposit amount must be at least 0.01")
        );
    }

    @MethodSource("invalidValueOfDeposite")
    @DisplayName("Negative tests")
    @ParameterizedTest
    public void invalidDepositeValue(float balance, String errorValue) {
        //Авторизуемся
        CreateUserByAdminRequest createUserByAdminRequest = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(createUserByAdminRequest);
        //Создание аккаунта юзеру
        CreateAccountsSteps.createAccounts(createUserByAdminRequest);
        //Проверяем ошибочный ответ
        DepositeSteps.failDeposite(balance, errorValue, authorizationRequestUser);
    }
}
