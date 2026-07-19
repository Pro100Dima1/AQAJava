package ui.ui_pages;

import lombok.Getter;

@Getter
public enum BankAlert {
    NAME_UPDATE_SUCCESSFULY("\u2705 Name updated successfully!"),
    NAME_UPDATE_FAILED("Name must contain two words with letters only"),
    DEPOSIT_SUCCSESSFULY("✅ Successfully deposited $%s to account %s!"),
    DEPOSIT_ABOVE_MAX_AMOUNT("❌ Please deposit less or equal to 5000$."),
    DEPOSIT_WITHOUT_SELECTED_ACCOUNT("❌ Please select an account."),
    DEPOSIT_WITHOUT_AMOUNT("❌ Please enter a valid amount."),
    TRANSFER_SUCCSESSFULY("✅ Successfully transferred $%s to account %s!"),
    TRANSFER_ABOVE_MAX_AMOUNT_FAILED("❌ Error: Transfer amount cannot exceed 10000"),
    TRANSFER_ABOVE_ACCOUNT_BALANCE_FAILED("❌ Error: Invalid transfer: insufficient funds or invalid accounts"),
    TRANSFER_WITHOUT_ALL_FIELD_CONFIRM_FAILED("❌ Please fill all fields and confirm.");

    private final String message;

    BankAlert(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
