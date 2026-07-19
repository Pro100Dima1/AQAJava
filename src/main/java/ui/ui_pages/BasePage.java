package ui.ui_pages;

import com.codeborne.selenide.Selenide;
import models.CreateUserByAdminRequest;
import org.openqa.selenium.Alert;
import specs.RequestSpecs;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class BasePage<T extends BasePage> {

    public abstract String url();

    public T open(){
        return Selenide.open(url(), (Class<T>) this.getClass());
    }

    public T checkAlertMessageAndAccept(String bankAlert){
        Alert alert = switchTo().alert();
        assertThat(alert.getText()).contains(bankAlert);
        alert.accept();
        return (T) this;
    }

    public static void authAsUser(String username, String password) {
        Selenide.open("/");
        String userAuthToken = RequestSpecs.getUserAuthHeader(username, password);
        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", userAuthToken);
    }

    public static void authAsUser(CreateUserByAdminRequest createUserRequest) {
        authAsUser(createUserRequest.getUsername(), createUserRequest.getPassword());
    }
}
