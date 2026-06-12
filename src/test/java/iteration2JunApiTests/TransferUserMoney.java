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

public class TransferUserMoney extends BaseTest {

    public static Stream<Arguments> validTransferValue() {
        return Stream.of(
                Arguments.of(5000),
                Arguments.of(0.01F),
                Arguments.of(200.1F)
        );
    }

    @MethodSource("validTransferValue")
    @DisplayName("Positive test")
    @ParameterizedTest
    public void transferUserMoney(float amount) {

        CreateUserByAdminRequest createUserByAdminRequest = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(createUserByAdminRequest);

        //Создание аккаунта юзеру
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(createUserByAdminRequest);
        int idFirst = createUserAccountsResponse1.getId();
        //Создание второго аккаунта юзеру
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(createUserByAdminRequest);
        int idSecond = createUserAccountsResponse2.getId();
        //Депозит денег
        DepositeSteps.makeDeposite(amount, authorizationRequestUser, idFirst);

        // Трансфер денег с аккаунта на аккаунт
        TransferRequest transferRequest = TransferRequest.builder()
                .senderAccountId(idFirst)
                .receiverAccountId(idSecond)
                .amount(amount)
                .build();

        TransferResponse transferResponse = new ValidatedCrudRequester<TransferResponse>(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.ACCOUNTS_TRANSFER)
                .post(transferRequest);

        ModelAssertions.assertThatModels(transferRequest, transferResponse).match();
        softly.assertThat(transferResponse.getAmount()).isEqualTo(amount);
    }

    public static Stream<Arguments> inValidTransferValue() {
        return Stream.of(
                Arguments.of(-100F, "Transfer amount must be at least 0.01"),
                Arguments.of(10000.01F, "Transfer amount cannot exceed 10000"),
                Arguments.of(5348.999999999998F, "Invalid transfer: insufficient funds or invalid accounts")
        );
    }

    @MethodSource("inValidTransferValue")
    @DisplayName("Negative tests")
    @ParameterizedTest
    public void canNotTransferUserMoney(float amount, String errorValue) {
        CreateUserByAdminRequest createUserByAdminRequest = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(createUserByAdminRequest);

        //Создание аккаунта юзеру
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(createUserByAdminRequest);
        int idFirst = createUserAccountsResponse1.getId();
        //Создание второго аккаунта юзеру
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(createUserByAdminRequest);
        int idSecond = createUserAccountsResponse2.getId();
        //Депозит суммы на аккаунт
        DepositeSteps.makeDeposite(RandomData.getRandomBalance(), authorizationRequestUser, idFirst);
        // Трансфер денег с аккаунта на аккаунт
        TransferRequest transferRequest = TransferRequest.builder()
                .senderAccountId(idFirst)
                .receiverAccountId(idSecond)
                .amount(amount)
                .build();

        new CrudRequester(RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.userCanNotChangeNameBadRequest(errorValue), Endpoint.ACCOUNTS_TRANSFER)
                .post(transferRequest);
    }
}
