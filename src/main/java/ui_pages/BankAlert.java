package ui_pages;

import lombok.Getter;

@Getter
public enum BankAlert {
    NAME_UPDATE_SUCCESSFULY("\u2705 Name updated successfully!"),
    NAME_UPDATE_FAILED("Name must contain two words with letters only"),
    SUCCSESSFULY_DEPOSIT("✅ Successfully deposited $%s to account %s!");

    private final String message;

    BankAlert(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
