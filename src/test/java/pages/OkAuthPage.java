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
        loginInput.shouldBe(visible.because("Поле для ввода логина не отображается")).setValue(login);
        passwordInput.shouldBe(visible.because("Поле для ввода пароля не отображается")).setValue(password);
        return this;
    }

    public OkAuthPage loginClick() {
        loginButton.shouldBe(visible.because("Кнопка входа не отображается")).click();
        return this;
    }

    public OkAuthPage checkErrorMessage(String expectedError) {
        errorMessage.shouldBe(visible.because("Сообщение об ошибке не отображается"));
        errorMessage.shouldHave(text(expectedError).because("Текст сообщения об ошибке не соответствует ожидаемому: " + expectedError));
        return this;
    }

    public OkAuthPage checkFocusOnPassword() {
        passwordInput.shouldBe(focused.because("Фокус не находится на поле пароля"));
        return this;
    }

    public OkAuthPage checkRestoreAccessMessage() {
        restoreAccessMessage.shouldBe(visible.because("Сообщение о восстановлении доступа не отображается"));
        return this;
    }

    public RestoreAccessPage restoreAccessButtonClick() {
        restoreAccessButton.shouldBe(visible.because("Кнопка восстановления доступа не отображается")).click();
        return new RestoreAccessPage();
    }
}