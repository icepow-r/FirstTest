package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OkAuthPage {
    private final SelenideElement cookieAcceptButton = $("button[tsid='cookie-banner_button_2e0c18']");
    private final SelenideElement loginInput = $("#field_email");
    private final SelenideElement passwordInput = $("#field_password");
    private final SelenideElement loginButton = $("input[data-l='t,sign_in']");
    private final SelenideElement errorMessage = $("div[class='input-e login_error']");
    private final SelenideElement restoreEmailErrorMessage = $("div[class='input-e']");
    private final SelenideElement restoreAccessMessage = $("div[class='stub']");

    public OkAuthPage open() {
        Selenide.open("https://ok.ru");

        if (cookieAcceptButton.exists()) {
            cookieAcceptButton.shouldBe(visible, enabled).click();
        }

        return this;
    }


    public OkAuthPage enterCredentials(String login, String password) {
        loginInput.shouldBe(visible);
        loginInput.setValue(login);
        passwordInput.setValue(password);
        return this;
    }

    public OkAuthPage loginClick() {
        loginButton.click();
        return this;
    }

    public OkAuthPage checkErrorMessage(String expectedError) {
        errorMessage.shouldBe(visible);
        errorMessage.shouldHave(text(expectedError));
        return this;
    }

    public OkAuthPage checkRestoreEmailErrorMessage(String expectedError) {
        restoreEmailErrorMessage.shouldBe(visible);
        restoreEmailErrorMessage.shouldHave(text(expectedError));
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

    public OkAuthPage checkContactSupportFormFields() {
        $("#categorynew").shouldBe(visible);
        $("#subcategory").shouldBe(visible);
        $("#categoryValue").shouldBe(visible);
        $("#lookup").shouldBe(visible);
        $("#field_e-mail").shouldBe(visible);
        return this;
    }

    public OkAuthPage restoreAccessButtonClick() {
        $("a[data-l='t,restore']").click();
        return this;
    }

    public OkAuthPage contactSupportButtonClick() {
        $("a[tsid='support-link_link_5998d4']").click();
        return this;
    }

    public OkAuthPage emailButtonClick() {
        $("a[data-l='t,email']").click();
        return this;
    }

    public OkAuthPage getCodeButtonClick() {
        $("input[tsid='recovery-start-email-verification-block_input_7f2bff']").click();
        return this;
    }
}