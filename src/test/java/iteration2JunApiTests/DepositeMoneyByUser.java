package iteration2JunApiTests;

import generator.RandomData;
import models.*;
import models.comparison.ModelAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.requesters.steps.AdminSteps;
import requests.skelethon.requesters.steps.CreateAccountsSteps;
import requests.skelethon.requesters.steps.DepositeSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.stream.Stream;

public class DepositeMoneyByUser extends BaseTest {

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

        ModelAssertions.assertThatModels(createUserAccountsResponse, depositeResponse).match();
        softly.assertThat(depositeResponse.getBalance()).isEqualTo(balance);

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();
        //Удаление юзера по id
        new CrudRequester(RequestSpecs.autharizationByAdmin(), ResponseSpecs.requestReturnStatusOK(), Endpoint.DELETE_USER)
                .delete(userInfo.getId());
        //Проверка, что юзер удалён
        new CrudRequester(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusUnauthorized(), Endpoint.GET_INFO)
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
        CreateUserAccountsResponse createUserAccountsResponse = CreateAccountsSteps.createAccounts(createUserByAdminRequest);
        int idAccount = createUserAccountsResponse.getId();
        //Проверяем ошибочный ответ
        DepositeSteps.failDeposite(balance, errorValue, authorizationRequestUser, idAccount);
    }
}
