package ui.ui_pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import models.CreateUserAccountsResponse;
import models.CreateUserByAdminRequest;

import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage extends BasePage<TransferMoneyPage> {
    private SelenideElement makeTransferPage = $(Selectors.byText("\uD83D\uDD04 Make a Transfer"));
    private SelenideElement selectAccount = $("select.account-selector");
    private SelenideElement placeholderRecipientName = $(Selectors.byAttribute("placeholder", "Enter recipient name"));
    private SelenideElement placeholderRecipientAccountNumber = $(Selectors.byAttribute("placeholder", "Enter recipient account number"));
    private SelenideElement placeholderEnterAmount = $(Selectors.byAttribute("placeholder", "Enter amount"));
    private SelenideElement checkbox = $("#confirmCheck");
    private SelenideElement buttonSendTransfer = $(Selectors.byText("\uD83D\uDE80 Send Transfer"));

    @Override
    public String url() {
        return "/transfer";
    }

    public TransferMoneyPage waitLoadingTransferMoneyPage() {
        makeTransferPage.shouldBe(Condition.visible);
        return this;
    }

    public TransferMoneyPage enterTransferField(CreateUserAccountsResponse createUserAccountsResponse1, CreateUserByAdminRequest user, CreateUserAccountsResponse createUserAccountsResponse2, Float amount) {
        selectAccount.selectOptionContainingText(createUserAccountsResponse1.getAccountNumber());
        placeholderRecipientName.shouldBe(Condition.clickable)
                .sendKeys(user.getUsername());
        placeholderRecipientAccountNumber.shouldBe(Condition.clickable)
                .sendKeys(createUserAccountsResponse2.getAccountNumber());
        placeholderEnterAmount.shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(amount));
        return this;
    }

    public TransferMoneyPage acceptCheckbox() {
        checkbox.setSelected(true);
        return this;
    }

    public TransferMoneyPage clickSendTransferButton() {
        buttonSendTransfer.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.clickable).click();
        return this;
    }

    public TransferMoneyPage enterTransferFieldWithoutSelectedAccountNumber(CreateUserByAdminRequest user, CreateUserAccountsResponse createUserAccountsResponse2, Float amount) {
        placeholderRecipientName.shouldBe(Condition.clickable)
                .sendKeys(user.getUsername());
        placeholderRecipientAccountNumber.shouldBe(Condition.clickable)
                .sendKeys(createUserAccountsResponse2.getAccountNumber());
        placeholderEnterAmount.shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(amount));
        return this;
    }

    public TransferMoneyPage enterTransferFieldWithoutAccountNumber(CreateUserAccountsResponse createUserAccountsResponse1, CreateUserByAdminRequest user, Float amount) {
        selectAccount.selectOptionContainingText(createUserAccountsResponse1.getAccountNumber());
        placeholderRecipientName.shouldBe(Condition.clickable)
                .sendKeys(user.getUsername());
        placeholderEnterAmount.shouldBe(Condition.clickable)
                .sendKeys(String.valueOf(amount));
        return this;
    }
}
