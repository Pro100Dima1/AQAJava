package ui;

import generator.RandomData;
import models.AuthorizationRequest;
import models.CreateUserAccountsResponse;
import models.CreateUserByAdminRequest;
import models.GetUserInfoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.requesters.steps.AdminSteps;
import requests.skelethon.requesters.steps.CreateAccountsSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;
import ui_pages.BankAlert;
import ui_pages.UserDashboardPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositeMoneyTest extends BaseUiTest {

    @Test
    @DisplayName("positive test")
    public void userCanOpenDepositePage() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        authAsUser(user);

        new UserDashboardPage().open().waitLoadingDepositMoneyPage();
    }

    @Test
    @DisplayName("positive test")
    public void userCanDepositMoney() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        String amount = String.valueOf(RandomData.getRandomBalance());
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse account = CreateAccountsSteps.createAccounts(user);
        authAsUser(user);

        new UserDashboardPage().open()
                .waitLoadingDepositMoneyPage()
                .depositMoney(account, amount)
                .checkAlertMessageAndAccept(BankAlert.DEPOSIT_SUCCSESSFULY.getMessage(amount, account.getAccountNumber()));

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();

        assertEquals(Float.valueOf(amount), userInfo.getAccounts().get(0).getBalance());
    }

    @Test
    @DisplayName("positive test")
    public void userCanDepositMaxMoney() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        final float MAX_AMOUNT = 5000.00f;
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse account = CreateAccountsSteps.createAccounts(user);
        authAsUser(user);

        new UserDashboardPage().open()
                .waitLoadingDepositMoneyPage()
                .depositMoney(account, String.valueOf(MAX_AMOUNT))
                .checkAlertMessageAndAccept(BankAlert.DEPOSIT_SUCCSESSFULY.getMessage(MAX_AMOUNT, account.getAccountNumber()));

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();

        assertEquals(MAX_AMOUNT, userInfo.getAccounts().get(0).getBalance());
    }

    @Test
    @DisplayName("negative test")
    public void userCanNotDepositAboveMaxMoney() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        final float ABOVE_MAX_AMOUNT = 5500.00f;
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse account = CreateAccountsSteps.createAccounts(user);
        authAsUser(user);

        new UserDashboardPage().open()
                .waitLoadingDepositMoneyPage()
                .depositMoney(account, String.valueOf(ABOVE_MAX_AMOUNT))
                .checkAlertMessageAndAccept(BankAlert.DEPOSIT_ABOVE_MAX_AMOUNT.getMessage(ABOVE_MAX_AMOUNT, account.getAccountNumber()));

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();

        assertEquals(0.0, userInfo.getAccounts().get(0).getBalance());
    }

    @Test
    @DisplayName("negative test")
    public void userCanNotDepositMoneyWithoutSelectedAccount() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        String amount = String.valueOf(RandomData.getRandomBalance());
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse account = CreateAccountsSteps.createAccounts(user);
        authAsUser(user);

        new UserDashboardPage().open()
                .waitLoadingDepositMoneyPage()
                .depositMoneyWithoutSelectedAccount(account, amount)
                .checkAlertMessageAndAccept(BankAlert.DEPOSIT_WITHOUT_SELECTED_ACCOUNT.getMessage());

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();

        assertEquals(0.0, userInfo.getAccounts().get(0).getBalance());
    }

    @Test
    @DisplayName("negative test")
    public void userCanNotDepositMoneyWithoutAmount() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        CreateUserAccountsResponse account = CreateAccountsSteps.createAccounts(user);
        authAsUser(user);

        new UserDashboardPage().open()
                .waitLoadingDepositMoneyPage()
                .depositMoneyWithoutAmount(account)
                .checkAlertMessageAndAccept(BankAlert.DEPOSIT_WITHOUT_AMOUNT.getMessage());

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();

        assertEquals(0.0, userInfo.getAccounts().get(0).getBalance());
    }
}
