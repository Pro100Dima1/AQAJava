package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import generator.RandomData;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.requesters.steps.AdminSteps;
import requests.skelethon.requesters.steps.CreateAccountsSteps;
import requests.skelethon.requesters.steps.DepositeSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;
import ui_pages.BankAlert;
import ui_pages.TransferMoneyPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
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
    @DisplayName("positive test")
    public void userCanTransferValidAmount() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(user);
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(user);
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();
        authAsUser(user);

        Float amount = RandomData.getRandomBalance();
        DepositeSteps.makeDeposite(amount, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferField(createUserAccountsResponse1, user, createUserAccountsResponse2, amount)
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
    @DisplayName("positive test")
    public void userCanTransferMaxAmount() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(user);
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(user);
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();
        authAsUser(user);

        final Float MAX_AMOUNT = 10000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferField(createUserAccountsResponse1, user, createUserAccountsResponse2, MAX_AMOUNT)
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
    @DisplayName("negative test")
    public void userCanNotTransferAboveMaxAmount() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(user);
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(user);
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();
        authAsUser(user);

        final Float MAX_AMOUNT = 10001F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferField(createUserAccountsResponse1, user, createUserAccountsResponse2, MAX_AMOUNT)
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
    @DisplayName("negative test")
    public void userCanNotTransferAmountAboveAccountBalance() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(user);
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(user);
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();
        authAsUser(user);

        final Float MAX_AMOUNT = 10000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferField(createUserAccountsResponse1, user, createUserAccountsResponse2, MAX_AMOUNT)
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
    @DisplayName("negative test")
    public void userCanNotTransferAmountWithoutSelectedAccountNumber() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(user);
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(user);
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();
        authAsUser(user);

        final Float TRANSFER_AMOUNT = 1000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferFieldWithoutSelectedAccountNumber(user, createUserAccountsResponse2, TRANSFER_AMOUNT)
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
    @DisplayName("negative test")
    public void userCanNotTransferAmountWithoutAccountNumber() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(user);
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(user);
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();
        authAsUser(user);

        final Float TRANSFER_AMOUNT = 1000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferFieldWithoutAccountNumber(createUserAccountsResponse1, user, TRANSFER_AMOUNT)
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
    @DisplayName("negative test")
    public void userCanNotTransferAmountWithoutAcceptCheckbox() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse createUserAccountsResponse1 = CreateAccountsSteps.createAccounts(user);
        String accountNumber1 = createUserAccountsResponse1.getAccountNumber();
        CreateUserAccountsResponse createUserAccountsResponse2 = CreateAccountsSteps.createAccounts(user);
        String accountNumber2 = createUserAccountsResponse2.getAccountNumber();
        authAsUser(user);


        final Float MAX_AMOUNT = 10000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        new TransferMoneyPage().open()
                .waitLoadingTransferMoneyPage()
                .enterTransferField(createUserAccountsResponse1, user, createUserAccountsResponse2, MAX_AMOUNT)
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
