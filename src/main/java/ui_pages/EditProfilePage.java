package ui_pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EditProfilePage extends BasePage<EditProfilePage> {
    private SelenideElement setName = $(Selectors.byAttribute("placeholder", "Enter new name"));
    private SelenideElement saveChangeButton = $("button.btn.btn-primary.mt-3");
    private SelenideElement userName = $(".user-username");
    private SelenideElement title = $$("h1").findBy(Condition.text("Edit Profile"));

    @Override
    public String url() {
        return "/edit-profile";
    }

    public EditProfilePage waitLoadingEditProfilePage(String name) {
        title.shouldHave(Condition.text("Edit Profile"));
        userName.shouldHave(Condition.text(name));
        setName.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable);
        return this;
    }

    public EditProfilePage setUsername(String name) {
        setName.setValue(name).shouldHave(Condition.value(name));
        return this;
    }

    public EditProfilePage clickOnSaveChangeButton() {
        saveChangeButton.shouldBe(Condition.enabled)
                .shouldHave(Condition.exactText("\uD83D\uDCBE Save Changes"))
                .click();
        return this;
    }
}
