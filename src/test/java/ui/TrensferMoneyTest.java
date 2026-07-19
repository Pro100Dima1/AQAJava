package ui;

import generator.RandomData;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.requesters.steps.AdminSteps;
import requests.skelethon.requesters.steps.CreateAccountsSteps;
import requests.skelethon.requesters.steps.DepositeSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;
import ui.annotations.UserSession;
import ui.storage.SessionStorage;
import ui.ui_pages.BankAlert;
import ui.ui_pages.TransferMoneyPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrensferMoneyTest extends BaseUiTest{

    @Test
    @DisplayName("positive test")
    public void userCanOpenTransferPage() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        authAsUser(user);

        new TransferMoneyPage().open().waitLoadingTransferMoneyPage();
    }

    @Test
    @UserSession
    @DisplayName("positive test")
    public void userCanTransferValidAmount() {
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(SessionStorage.getUser(1));
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();

        Float amount = RandomData.getRandomBalance();
        DepositeSteps.makeDeposite(amount, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferField(createUserAccountsResponse1, SessionStorage.getUser(1), createUserAccountsResponse2, amount)
                .acceptCheckbox()
                .clickSendTransferButton()
                .checkAlertMessageAndAccept(BankAlert.TRANSFER_SUCCSESSFULY.getMessage(amount, createUserAccountsResponse2.getAccountNumber()));

        GetUserInfoResponse getUserInfoResponse = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();
        List<Accounts> accountsList = getUserInfoResponse.getAccounts();
        Accounts account1 = accountsList.stream()
                .filter(acc -> accountNumber1.equals(acc.getAccountNumber()))
                .findFirst()
                .get();
        Accounts account2 = accountsList.stream()
                .filter(acc -> accountNumber2.equals(acc.getAccountNumber()))
                .findFirst()
                .get();

        assertEquals(account2.getBalance(), amount);
    }

    @Test
    @UserSession
    @DisplayName("positive test")
    public void userCanTransferMaxAmount() {
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(SessionStorage.getUser(1));
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();

        final Float MAX_AMOUNT = 10000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferField(createUserAccountsResponse1, SessionStorage.getUser(1), createUserAccountsResponse2, MAX_AMOUNT)
                .acceptCheckbox()
                .clickSendTransferButton()
                .checkAlertMessageAndAccept(BankAlert.TRANSFER_SUCCSESSFULY.getMessage(MAX_AMOUNT, createUserAccountsResponse2.getAccountNumber()));

        GetUserInfoResponse getUserInfoResponse = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();
        List<Accounts> accountsList = getUserInfoResponse.getAccounts();
        Accounts account1 = accountsList.stream()
                .filter(acc -> accountNumber1.equals(acc.getAccountNumber()))
                .findFirst()
                .get();
        Accounts account2 = accountsList.stream()
                .filter(acc -> accountNumber2.equals(acc.getAccountNumber()))
                .findFirst()
                .get();

        assertEquals(account2.getBalance(), MAX_AMOUNT);
    }

    @Test
    @UserSession
    @DisplayName("negative test")
    public void userCanNotTransferAboveMaxAmount() {
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(SessionStorage.getUser(1));
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();

        final Float MAX_AMOUNT = 10001F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferField(createUserAccountsResponse1, SessionStorage.getUser(1), createUserAccountsResponse2, MAX_AMOUNT)
                .acceptCheckbox()
                .clickSendTransferButton()
                .checkAlertMessageAndAccept(BankAlert.TRANSFER_ABOVE_MAX_AMOUNT_FAILED.getMessage());

        GetUserInfoResponse getUserInfoResponse = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();
        List<Accounts> accountsList = getUserInfoResponse.getAccounts();
        Accounts account1 = accountsList.stream()
                .filter(acc -> accountNumber1.equals(acc.getAccountNumber()))
                .findFirst()
                .get();
        Accounts account2 = accountsList.stream()
                .filter(acc -> accountNumber2.equals(acc.getAccountNumber()))
                .findFirst()
                .get();

        assertEquals(account2.getBalance(), 0);
    }

    @Test
    @UserSession
    @DisplayName("negative test")
    public void userCanNotTransferAmountAboveAccountBalance() {
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(SessionStorage.getUser(1));
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();

        final Float MAX_AMOUNT = 10000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferField(createUserAccountsResponse1, SessionStorage.getUser(1), createUserAccountsResponse2, MAX_AMOUNT)
                .acceptCheckbox()
                .clickSendTransferButton()
                .checkAlertMessageAndAccept(BankAlert.TRANSFER_ABOVE_ACCOUNT_BALANCE_FAILED.getMessage());

        GetUserInfoResponse getUserInfoResponse = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();
        List<Accounts> accountsList = getUserInfoResponse.getAccounts();
        Accounts account1 = accountsList.stream()
                .filter(acc -> accountNumber1.equals(acc.getAccountNumber()))
                .findFirst()
                .get();
        Accounts account2 = accountsList.stream()
                .filter(acc -> accountNumber2.equals(acc.getAccountNumber()))
                .findFirst()
                .get();

        assertEquals(account2.getBalance(), 0);
    }

    @Test
    @UserSession
    @DisplayName("negative test")
    public void userCanNotTransferAmountWithoutSelectedAccountNumber() {
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(SessionStorage.getUser(1));
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();

        final Float TRANSFER_AMOUNT = 1000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferFieldWithoutSelectedAccountNumber(SessionStorage.getUser(1), createUserAccountsResponse2, TRANSFER_AMOUNT)
                .acceptCheckbox()
                .clickSendTransferButton()
                .checkAlertMessageAndAccept(BankAlert.TRANSFER_WITHOUT_ALL_FIELD_CONFIRM_FAILED.getMessage());

        GetUserInfoResponse getUserInfoResponse = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();
        List<Accounts> accountsList = getUserInfoResponse.getAccounts();
        Accounts account1 = accountsList.stream()
                .filter(acc -> accountNumber1.equals(acc.getAccountNumber()))
                .findFirst()
                .get();
        Accounts account2 = accountsList.stream()
                .filter(acc -> accountNumber2.equals(acc.getAccountNumber()))
                .findFirst()
                .get();

        assertEquals(account2.getBalance(), 0);
    }

    @Test
    @UserSession
    @DisplayName("negative test")
    public void userCanNotTransferAmountWithoutAccountNumber() {
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(SessionStorage.getUser(1));
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();

        final Float TRANSFER_AMOUNT = 1000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferFieldWithoutAccountNumber(createUserAccountsResponse1, SessionStorage.getUser(1), TRANSFER_AMOUNT)
                .acceptCheckbox()
                .clickSendTransferButton()
                .checkAlertMessageAndAccept(BankAlert.TRANSFER_WITHOUT_ALL_FIELD_CONFIRM_FAILED.getMessage());

        GetUserInfoResponse getUserInfoResponse = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();
        List<Accounts> accountsList = getUserInfoResponse.getAccounts();
        Accounts account1 = accountsList.stream()
                .filter(acc -> accountNumber1.equals(acc.getAccountNumber()))
                .findFirst()
                .get();
        Accounts account2 = accountsList.stream()
                .filter(acc -> accountNumber2.equals(acc.getAccountNumber()))
                .findFirst()
                .get();

        assertEquals(account2.getBalance(), 0);
    }

    @Test
    @UserSession
    @DisplayName("negative test")
    public void userCanNotTransferAmountWithoutAcceptCheckbox() {
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(SessionStorage.getUser(1));
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(SessionStorage.getUser(1));
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();


        final Float MAX_AMOUNT = 10000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferField(createUserAccountsResponse1, SessionStorage.getUser(1), createUserAccountsResponse2, MAX_AMOUNT)
                .clickSendTransferButton()
                .checkAlertMessageAndAccept(BankAlert.TRANSFER_WITHOUT_ALL_FIELD_CONFIRM_FAILED.getMessage());

        GetUserInfoResponse getUserInfoResponse = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();
        List<Accounts> accountsList = getUserInfoResponse.getAccounts();
        Accounts account1 = accountsList.stream()
                .filter(acc -> accountNumber1.equals(acc.getAccountNumber()))
                .findFirst()
                .get();
        Accounts account2 = accountsList.stream()
                .filter(acc -> accountNumber2.equals(acc.getAccountNumber()))
                .findFirst()
                .get();

        assertEquals(account2.getBalance(), 0);
    }
}
