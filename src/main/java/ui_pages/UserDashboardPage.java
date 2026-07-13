package ui_pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import models.CreateUserAccountsResponse;

import static com.codeborne.selenide.Selenide.$;

public class UserDashboardPage extends BasePage<UserDashboardPage> {
    private SelenideElement depositMonetTitle = $(Selectors.byText("\uD83D\uDCB0 Deposit Money"));
    private SelenideElement selectorAccountNumber = $("select.account-selector");
    private SelenideElement placeHolderEnterAmount = $(Selectors.byAttribute("placeholder", "Enter amount"));
    private SelenideElement depositMoneyButton =$(Selectors.byText("\uD83D\uDCB5 Deposit"));

    @Override
    public String url() {
        return "/deposit";
    }

    public UserDashboardPage waitLoadingDepositMoneyPage() {
        depositMonetTitle.shouldBe(Condition.visible);
        return this;
    }

    public UserDashboardPage depositMoney(CreateUserAccountsResponse account, String amount) {
        selectorAccountNumber.shouldBe(Condition.visible)
                .selectOptionContainingText(account.getAccountNumber());
        placeHolderEnterAmount.shouldBe(Condition.clickable)
                .sendKeys(amount);
        depositMoneyButton.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();
        return this;
    }

    public UserDashboardPage depositMoneyWithoutSelectedAccount(CreateUserAccountsResponse account, String amount) {
        placeHolderEnterAmount.shouldBe(Condition.clickable)
                .sendKeys(amount);
        depositMoneyButton.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();
        return this;
    }

    public UserDashboardPage depositMoneyWithoutAmount(CreateUserAccountsResponse account) {
        selectorAccountNumber.shouldBe(Condition.visible)
                .selectOptionContainingText(account.getAccountNumber());
        depositMoneyButton.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable)
                .click();
        return this;
    }
}


