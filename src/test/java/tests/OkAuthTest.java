package tests;

import pages.OkAuthPage;
import org.junit.jupiter.api.Test;

public class OkAuthTest {
    @Test
    public void testLoginWithWrongPassword() {
        var okAuthPage = new OkAuthPage();
        okAuthPage
                .open()
                .enterCredentials("Login", "Wrong password")
                .loginClick()
                .checkErrorMessage("Incorrect username and/or password");
    }

    @Test
    public void testChangeFocusEmptyPassword() {
        var okAuthPage = new OkAuthPage();
        okAuthPage
                .open()
                .enterCredentials("Login", "")
                .loginClick()
                .checkFocusOnPassword()
                .checkErrorMessage("Enter password");
    }

    @Test
    public void testInvalidLoginWarningMessage() {
        var okAuthPage = new OkAuthPage();

        okAuthPage.open();
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
        okAuthPage
                .open()
                .restoreAccessButtonClick()
                .contactSupportButtonClick()
                .checkContactSupportFormFields();
    }

    @Test
    public void testEmptyRecoveryEmailField() {
        var okAuthPage = new OkAuthPage();
        okAuthPage
                .open()
                .restoreAccessButtonClick()
                .emailButtonClick()
                .getCodeButtonClick()
                .checkRestoreEmailErrorMessage("Incorrect e-mail format");
    }
}