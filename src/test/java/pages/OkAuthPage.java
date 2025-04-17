package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OkAuthPage {
    private final SelenideElement loginInput = $("#field_email");
    private final SelenideElement passwordInput = $("#field_password");
    private final SelenideElement loginButton = $("input[data-l='t,sign_in']");
    private final SelenideElement errorMessage = $("[class='input-e login_error']");
    private final SelenideElement restoreAccessMessage = $("[class='stub']");
    private final SelenideElement restoreAccessButton = $("[data-l='t,restore']");

    public OkAuthPage enterCredentials(String login, String password) {
        loginInput.shouldBe(visible).setValue(login);
        passwordInput.shouldBe(visible).setValue(password);
        return this;
    }

    public OkAuthPage loginClick() {
        loginButton.shouldBe(visible).click();
        return this;
    }

    public OkAuthPage checkErrorMessage(String expectedError) {
        errorMessage.shouldBe(visible);
        errorMessage.shouldHave(text(expectedError));
        return this;
    }

    public OkAuthPage checkFocusOnPassword() {
        passwordInput.shouldBe(focused);
        return this;
    }

    public OkAuthPage checkRestoreAccessMessage() {
        restoreAccessMessage.shouldBe(visible);
        return this;
    }

    public RestoreAccessPage restoreAccessButtonClick() {
        restoreAccessButton.shouldBe(visible).click();
        return new RestoreAccessPage();
    }
}