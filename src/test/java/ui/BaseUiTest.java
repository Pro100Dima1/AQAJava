package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import configs.Config;
import models.CreateUserByAdminRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import specs.RequestSpecs;
import ui.extensions.UserSessionExtension;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.executeJavaScript;

@ExtendWith(UserSessionExtension.class)
public class BaseUiTest {
    @BeforeAll
    public static void setupSelenoid() {
        Configuration.remote = Config.getProperty("uiRemote");
        Configuration.baseUrl = Config.getProperty("uiBaseUrl");
        Configuration.browser = Config.getProperty("browser");
        Configuration.browserSize = Config.getProperty("browserSize");
        Configuration.timeout = 10000;

        Configuration.browserCapabilities.setCapability("selenoid:options",
                Map.of("enableVNC", true, "enableLog", true)
        );
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    public void authAsUser(String username, String password) {
        Selenide.open("/");
        String userAuthToken = RequestSpecs.getUserAuthHeader(username, password);
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", userAuthToken);
    }

    public void authAsUser(CreateUserByAdminRequest createUserRequest) {
        authAsUser(createUserRequest.getUsername(), createUserRequest.getPassword());
    }
}
