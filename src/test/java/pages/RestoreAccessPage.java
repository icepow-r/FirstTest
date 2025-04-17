package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RestoreAccessPage {
    private final SelenideElement contactSupportButton = $x(".//*[contains(@tsid,'support-link')]");
    private final SelenideElement recoveryEmailButton = $("a[data-l='t,email']");
    private final SelenideElement getCodeButton = $x(".//*[contains(@tsid,'recovery-start-email-verification-block')]");
    private final SelenideElement emailField = $("#field_email");
    private final SelenideElement restoreEmailErrorMessage = $("div[class='input-e']");


    public SupportPage contactSupportButtonClick() {
        contactSupportButton.shouldBe(visible).click();
        return new SupportPage();
    }

    public RestoreAccessPage setEmailValue(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public RestoreAccessPage emailButtonClick() {
        recoveryEmailButton.shouldBe(visible).click();
        return this;
    }

    public RestoreAccessPage getCodeButtonClick() {
        getCodeButton.shouldBe(visible).click();
        return this;
    }

    public RestoreAccessPage checkRestoreEmailErrorMessage(String expectedError) {
        restoreEmailErrorMessage.shouldBe(visible);
        restoreEmailErrorMessage.shouldHave(text(expectedError));
        return this;
    }
}
