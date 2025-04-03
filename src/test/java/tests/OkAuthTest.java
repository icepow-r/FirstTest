package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.Configuration;
import pages.OkAuthPage;
import org.junit.jupiter.api.Test;

public class OkAuthTest {

    @BeforeEach
    public void before() {
        Selenide.open("https://ok.ru");
    }

    @BeforeAll
    public static void setUp() {
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--lang=en_US");
        Configuration.browser = "chrome";
    }

    @Test
    public void testLoginWithWrongPassword() {
        var okAuthPage = new OkAuthPage();
        okAuthPage
                .enterCredentials("Login", "Wrong password")
                .loginClick()
                .checkErrorMessage("Incorrect username and/or password");
    }

    @Test
    public void testChangeFocusEmptyPassword() {
        var okAuthPage = new OkAuthPage();
        okAuthPage
                .enterCredentials("Login", "")
                .loginClick()
                .checkFocusOnPassword()
                .checkErrorMessage("Enter password");
    }

    @Test
    public void testInvalidLoginWarningMessage() {
        Selenide.clearBrowserCookies();
        var okAuthPage = new OkAuthPage();

        for (int i = 0; i < 3; i++) {
            okAuthPage
                    .enterCredentials("Login", "Wrong password")
                    .loginClick();
        }
        okAuthPage.checkRestoreAccessMessage();
    }

    @Test
    public void testCustomerSupportFormFields() {
        var okAuthPage = new OkAuthPage();
        var restoreAccessPage = okAuthPage
                .restoreAccessButtonClick();

        restoreAccessPage
                .contactSupportButtonClick()
                .checkContactSupportFormFields();
    }

    @Test
    public void testEmptyRecoveryEmailField() {
        var okAuthPage = new OkAuthPage();
        var restoreAccessPage = okAuthPage
                .restoreAccessButtonClick();
        restoreAccessPage.emailButtonClick()
                .getCodeButtonClick()
                .checkRestoreEmailErrorMessage("Incorrect e-mail format");
    }
}