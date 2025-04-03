package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RestoreAccessPage {
    private final SelenideElement contactSupportButton = $("a[tsid='support-link_link_5998d4']");
    private final SelenideElement recoveryEmailButton = $("a[data-l='t,email']");
    private final SelenideElement getCodeButton = $("input[tsid='recovery-start-email-verification-block_input_7f2bff']");
    private final SelenideElement restoreEmailErrorMessage = $("div[class='input-e']");


    public SupportPage contactSupportButtonClick() {
        contactSupportButton.shouldBe(visible).click();
        return new SupportPage();
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
