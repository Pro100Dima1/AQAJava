package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import generator.RandomData;
import models.AuthorizationRequest;
import models.CreateUserAccountsResponse;
import models.CreateUserByAdminRequest;
import models.GetUserInfoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.requesters.steps.AdminSteps;
import requests.skelethon.requesters.steps.CreateAccountsSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositeMoneyTest {
    @BeforeAll
    public static void setupSelenoid() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.baseUrl = "http://172.26.160.1:3000";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;

        Configuration.browserCapabilities.setCapability("selenoid:options",
                Map.of("enableVNC", true, "enableLog", true)
        );
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("positive test")
    public void userCanOpenDepositePage() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();

        Selenide.open("/");
        $(Selectors.byAttribute("placeholder", "Username")).setValue(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).setValue(user.getPassword());
        $("button").click();
        $(Selectors.byText("User Dashboard")).shouldBe(Condition.visible);

        $(Selectors.byText("\uD83D\uDCB0 Deposit Money")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();
        $(Selectors.byText("\uD83D\uDCB0 Deposit Money")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("positive test")
    public void userCanDepositMoney() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        String amount = String.valueOf(RandomData.getRandomBalance());
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);

        Selenide.open("/");
        $(Selectors.byAttribute("placeholder", "Username")).setValue(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).setValue(user.getPassword());
        $("button").click();
        $(Selectors.byText("User Dashboard")).shouldBe(Condition.visible);

        CreateUserAccountsResponse account = CreateAccountsSteps.createAccounts(user);

        $(Selectors.byText("\uD83D\uDCB0 Deposit Money")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();

        $("select.account-selector").selectOptionContainingText(account.getAccountNumber());

        $(Selectors.byAttribute("placeholder", "Enter amount")).shouldBe(Condition.clickable)
                .sendKeys(amount);

        $(Selectors.byText("\uD83D\uDCB5 Deposit")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();

        Alert depositAlert = switchTo().alert();
        assertEquals("✅ Successfully deposited $" + amount + " to account " + account.getAccountNumber() + "!", depositAlert.getText());
        depositAlert.accept();

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

        Selenide.open("/");
        $(Selectors.byAttribute("placeholder", "Username")).setValue(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).setValue(user.getPassword());
        $("button").click();
        $(Selectors.byText("User Dashboard")).shouldBe(Condition.visible);

        CreateUserAccountsResponse account = CreateAccountsSteps.createAccounts(user);

        $(Selectors.byText("\uD83D\uDCB0 Deposit Money")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();

        $("select.account-selector").selectOptionContainingText(account.getAccountNumber());

        $(Selectors.byAttribute("placeholder", "Enter amount")).shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(MAX_AMOUNT));

        $(Selectors.byText("\uD83D\uDCB5 Deposit")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();

        Alert depositAlert = switchTo().alert();
        assertEquals("✅ Successfully deposited $" + MAX_AMOUNT + " to account " + account.getAccountNumber() + "!", depositAlert.getText());
        depositAlert.accept();

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

        Selenide.open("/");
        $(Selectors.byAttribute("placeholder", "Username")).setValue(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).setValue(user.getPassword());
        $("button").click();
        $(Selectors.byText("User Dashboard")).shouldBe(Condition.visible);

        CreateUserAccountsResponse account = CreateAccountsSteps.createAccounts(user);

        $(Selectors.byText("\uD83D\uDCB0 Deposit Money")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();

        $("select.account-selector").selectOptionContainingText(account.getAccountNumber());

        $(Selectors.byAttribute("placeholder", "Enter amount")).shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(ABOVE_MAX_AMOUNT));

        $(Selectors.byText("\uD83D\uDCB5 Deposit")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();

        Alert depositAlert = switchTo().alert();
        assertEquals("❌ Please deposit less or equal to 5000$.", depositAlert.getText());
        depositAlert.accept();

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

        Selenide.open("/");
        $(Selectors.byAttribute("placeholder", "Username")).setValue(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).setValue(user.getPassword());
        $("button").click();
        $(Selectors.byText("User Dashboard")).shouldBe(Condition.visible);

        CreateUserAccountsResponse account = CreateAccountsSteps.createAccounts(user);

        $(Selectors.byText("\uD83D\uDCB0 Deposit Money")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();

        $(Selectors.byAttribute("placeholder", "Enter amount")).shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(amount));

        $(Selectors.byText("\uD83D\uDCB5 Deposit")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();

        Alert depositAlert = switchTo().alert();
        assertEquals("❌ Please select an account.", depositAlert.getText());
        depositAlert.accept();

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

        Selenide.open("/");
        $(Selectors.byAttribute("placeholder", "Username")).setValue(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).setValue(user.getPassword());
        $("button").click();
        $(Selectors.byText("User Dashboard")).shouldBe(Condition.visible);

        CreateUserAccountsResponse account = CreateAccountsSteps.createAccounts(user);

        $(Selectors.byText("\uD83D\uDCB0 Deposit Money")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();

        $("select.account-selector").selectOptionContainingText(account.getAccountNumber());

        $(Selectors.byText("\uD83D\uDCB5 Deposit")).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();

        Alert depositAlert = switchTo().alert();
        assertEquals("❌ Please enter a valid amount.", depositAlert.getText());
        depositAlert.accept();

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(), Endpoint.GET_INFO)
                .get();

        assertEquals(0.0, userInfo.getAccounts().get(0).getBalance());
    }
}
