package ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

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
}
