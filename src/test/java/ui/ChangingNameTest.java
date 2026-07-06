package ui;

import com.codeborne.selenide.*;
import generator.RandomData;
import models.AuthorizationRequest;
import models.CreateUserByAdminRequest;
import models.GetUserInfoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import requests.skelethon.interfaces.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.requesters.steps.AdminSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangingNameTest {
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
    @DisplayName("Happy path test")
    public void userCanChangeName() {
        CreateUserByAdminRequest user = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(user);
        String randomName = RandomData.getName();

        Selenide.open("/");
        $(Selectors.byAttribute("placeholder", "Username")).setValue(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).setValue(user.getPassword());
        $("button").click();
        $(Selectors.byText("User Dashboard")).shouldBe(Condition.visible);

        $(".user-username").shouldHave(Condition.text(user.getUsername())).click();

        $x("//h1[text()='\u270F\uFE0F Edit Profile']").shouldBe(Condition.visible);
        $(Selectors.byAttribute("placeholder", "Enter new name"))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .setValue(randomName)
                .shouldHave(Condition.value(randomName));

        $("button.btn.btn-primary.mt-3").shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.exactText("\uD83D\uDCBE Save Changes"))
                .click();

        Alert alert = switchTo().alert();
        assertEquals("\u2705 Name updated successfully!", alert.getText());
        alert.accept();

        $(".btn.btn-outline-primary.position-fixed").shouldBe(Condition.visible).click();

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(
                RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.GET_INFO)
                .get();

        $(Selectors.byText(userInfo.getName())).shouldBe(Condition.visible);
        assertEquals(randomName, userInfo.getName());
    }

    @Test
    @DisplayName("Negative test")
    public void userCanNotChangeName() {
        CreateUserByAdminRequest badUser = AdminSteps.createUserByAdmin();
        AuthorizationRequest authorizationRequestUser = AdminSteps.authorizationUser(badUser);
        String randomName = RandomData.getInvalidName();
        GetUserInfoResponse userInfoBeforeChange = new ValidatedCrudRequester<GetUserInfoResponse>(
                RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.GET_INFO)
                .get();

        String authToken = new CrudRequester(
                RequestSpecs.autharizationByUser(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.LOGIN)
                .post(AuthorizationRequest.builder()
                        .username(badUser.getUsername())
                        .password(badUser.getPassword())
                        .build())
                .extract()
                .header("Authorization");

        Selenide.open("/");
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", authToken);
        Selenide.open("/edit-profile");

        $(".user-username").shouldHave(Condition.text(badUser.getUsername())).click();
        $x("//h1[text()='\u270F\uFE0F Edit Profile']").shouldBe(Condition.visible);

        $(Selectors.byAttribute("placeholder", "Enter new name")).setValue(randomName);
        $("button.btn.btn-primary.mt-3")
                .shouldHave(Condition.exactText("\uD83D\uDCBE Save Changes"))
                .click();

        Alert alert = switchTo().alert();
        assertEquals("Name must contain two words with letters only", alert.getText());
        alert.accept();

        $(".btn.btn-outline-primary.position-fixed").shouldBe(Condition.visible).click();

        GetUserInfoResponse userInfo = new ValidatedCrudRequester<GetUserInfoResponse>(
                RequestSpecs.getUserInfo(authorizationRequestUser.getUsername(), authorizationRequestUser.getPassword()),
                ResponseSpecs.requestReturnStatusOK(),
                Endpoint.GET_INFO)
                .get();

        $(".user-username").shouldHave(Condition.text(badUser.getUsername()));
        assertEquals(userInfoBeforeChange.getName(), userInfo.getName());
    }
}
