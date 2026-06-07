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
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.requesters.steps.AdminSteps;
import requests.skelethon.requesters.steps.CreateAccountsSteps;
import requests.skelethon.requesters.steps.DepositeSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.List;
import java.util.stream.Stream;

public class DepositeMoneyByUser extends BaseTest{

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
        //Создание аккаунта юзеру
        CreateUserAccountsResponse createUserAccountsResponse = CreateAccountsSteps.createAccounts(createUserByAdminRequest);
        int idAccount = createUserAccountsResponse.getId();
        //Делаем депозит на аккаунт с конкретным id
        DepositeResponse depositeResponse = DepositeSteps.makeDeposite(balance, authorizationRequestUser, idAccount);

        softly.assertThat(depositeResponse.getBalance()).isEqualTo(balance);
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
        CreateUserAccountsResponse createUserAccountsResponse = CreateAccountsSteps.createAccounts(createUserByAdminRequest);
        int idAccount = createUserAccountsResponse.getId();
        //Проверяем ошибочный ответ
        DepositeSteps.failDeposite(balance, errorValue, authorizationRequestUser, idAccount);
    }
}
