package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import generator.RandomData;
import models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrensferMoneyTest extends BaseUiTest{

    @Test
    @DisplayName("positive test")
    public void userCanOpenTransferPage() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();

        open("/");
        $(Selectors.byAttribute("placeholder", "Username")).setValue(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).setValue(user.getPassword());
        $("button").click();
        $(Selectors.byText("User Dashboard")).shouldBe(Condition.visible);

        $(Selectors.byText("\uD83D\uDD04 Make a Transfer")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();
        $(Selectors.byText("\uD83D\uDD04 Make a Transfer")).shouldBe(Condition.visible);
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

        Float amount = RandomData.getRandomBalance();
        DepositeSteps.makeDeposite(amount, authorizationRequestUser, createUserAccountsResponse1.getId());

        String authToken = new CrudRequester(
                RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.LOGIN)
                .post(AuthorizationRequest.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build())
                .extract()
                .header("Authorization");

        open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", authToken);
        open("/transfer");

        $("select.account-selector").selectOptionContainingText(createUserAccountsResponse1.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter recipient name"))
                .shouldBe(Condition.clickable)
                .sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .shouldBe(Condition.clickable)
                .sendKeys(createUserAccountsResponse2.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter amount"))
                .shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(amount));

        $("#confirmCheck").setSelected(true);

        $(Selectors.byText("\uD83D\uDE80 Send Transfer"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable).click();

        Alert alert = switchTo().alert();
        assertEquals("✅ Successfully transferred $" + amount + " to account " + createUserAccountsResponse2.getAccountNumber() + "!", alert.getText());
        alert.accept();

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

        final Float MAX_AMOUNT = 10000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        String authToken = new CrudRequester(
                RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.LOGIN)
                .post(AuthorizationRequest.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build())
                .extract()
                .header("Authorization");

        open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", authToken);
        open("/transfer");

        $("select.account-selector").selectOptionContainingText(createUserAccountsResponse1.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter recipient name"))
                .shouldBe(Condition.clickable)
                .sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .shouldBe(Condition.clickable)
                .sendKeys(createUserAccountsResponse2.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter amount"))
                .shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(MAX_AMOUNT));

        $("#confirmCheck").setSelected(true);

        $(Selectors.byText("\uD83D\uDE80 Send Transfer"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable).click();

        Alert alert = switchTo().alert();
        assertEquals("✅ Successfully transferred $" + MAX_AMOUNT + " to account " + createUserAccountsResponse2.getAccountNumber() + "!", alert.getText());
        alert.accept();

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

        final Float MAX_AMOUNT = 10001F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        String authToken = new CrudRequester(
                RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.LOGIN)
                .post(AuthorizationRequest.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build())
                .extract()
                .header("Authorization");

        open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", authToken);
        open("/transfer");

        $("select.account-selector").selectOptionContainingText(createUserAccountsResponse1.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter recipient name"))
                .shouldBe(Condition.clickable)
                .sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .shouldBe(Condition.clickable)
                .sendKeys(createUserAccountsResponse2.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter amount"))
                .shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(MAX_AMOUNT));

        $("#confirmCheck").setSelected(true);

        $(Selectors.byText("\uD83D\uDE80 Send Transfer"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable).click();

        Alert alert = switchTo().alert();
        assertEquals("❌ Error: Transfer amount cannot exceed 10000",alert.getText());
        alert.accept();

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

        final Float MAX_AMOUNT = 10000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        String authToken = new CrudRequester(
                RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.LOGIN)
                .post(AuthorizationRequest.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build())
                .extract()
                .header("Authorization");

        open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", authToken);
        open("/transfer");

        $("select.account-selector").selectOptionContainingText(createUserAccountsResponse1.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter recipient name"))
                .shouldBe(Condition.clickable)
                .sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .shouldBe(Condition.clickable)
                .sendKeys(createUserAccountsResponse2.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter amount"))
                .shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(MAX_AMOUNT));

        $("#confirmCheck").setSelected(true);

        $(Selectors.byText("\uD83D\uDE80 Send Transfer"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable).click();

        Alert alert = switchTo().alert();
        assertEquals("❌ Error: Invalid transfer: insufficient funds or invalid accounts",alert.getText());
        alert.accept();

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

        final Float TRANSFER_AMOUNT = 1000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        String authToken = new CrudRequester(
                RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.LOGIN)
                .post(AuthorizationRequest.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build())
                .extract()
                .header("Authorization");

        open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", authToken);
        open("/transfer");

        $(Selectors.byAttribute("placeholder", "Enter recipient name"))
                .shouldBe(Condition.clickable)
                .sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .shouldBe(Condition.clickable)
                .sendKeys(createUserAccountsResponse2.getAccountNumber());
                $(Selectors.byAttribute("placeholder", "Enter amount"))
                .shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(TRANSFER_AMOUNT));

        $("#confirmCheck").setSelected(true);

        $(Selectors.byText("\uD83D\uDE80 Send Transfer"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable).click();

        Alert alert = switchTo().alert();
        assertEquals("❌ Please fill all fields and confirm.",alert.getText());
        alert.accept();

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

        final Float TRANSFER_AMOUNT = 1000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        String authToken = new CrudRequester(
                RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.LOGIN)
                .post(AuthorizationRequest.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build())
                .extract()
                .header("Authorization");

        open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", authToken);
        open("/transfer");

        $("select.account-selector").selectOptionContainingText(createUserAccountsResponse1.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter recipient name"))
                .shouldBe(Condition.clickable)
                .sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Enter amount"))
                .shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(TRANSFER_AMOUNT));

        $("#confirmCheck").setSelected(true);

        $(Selectors.byText("\uD83D\uDE80 Send Transfer"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable).click();

        Alert alert = switchTo().alert();
        assertEquals("❌ Please fill all fields and confirm.", alert.getText());
        alert.accept();

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

        final Float MAX_AMOUNT = 10000F;
        final Float MAX_DEPOSIT_AMOUNT = 5000F;
        DepositeSteps.makeDeposite(MAX_DEPOSIT_AMOUNT, authorizationRequestUser, createUserAccountsResponse1.getId());

        String authToken = new CrudRequester(
                RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.LOGIN)
                .post(AuthorizationRequest.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build())
                .extract()
                .header("Authorization");

        open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", authToken);
        open("/transfer");

        $("select.account-selector").selectOptionContainingText(createUserAccountsResponse1.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter recipient name"))
                .shouldBe(Condition.clickable)
                .sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .shouldBe(Condition.clickable)
                .sendKeys(createUserAccountsResponse2.getAccountNumber());
        $(Selectors.byAttribute("placeholder", "Enter amount"))
                .shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(MAX_AMOUNT));

        $(Selectors.byText("\uD83D\uDE80 Send Transfer"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable).click();

        Alert alert = switchTo().alert();
        assertEquals("❌ Please fill all fields and confirm.",alert.getText());
        alert.accept();

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
