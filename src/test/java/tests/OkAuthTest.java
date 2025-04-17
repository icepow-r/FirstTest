package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.Configuration;
import pages.OkAuthPage;

import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Тесты аутентификации OK.ru")
public class OkAuthTest {

    private final String correctLogin = "technopol52";
    private final String correctPassword = "technopolisPassword";
    private final String wrongPassword = "wrongPassword";
    private OkAuthPage okAuthPage;

    @BeforeEach
    public void setUp() {
        Selenide.open("https://ok.ru");
        okAuthPage = new OkAuthPage();
    }

    @BeforeAll
    public static void setUpBrowser() {
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--lang=en_US");
        Configuration.browser = "chrome";
    }

    @AfterEach
    public void tearDown() {
        Selenide.clearBrowserCookies();
    }

    @Test
    @DisplayName("Проверка ошибки при неверном пароле")
    @Tag("negative")
    @Timeout(value = 10)
    public void testLoginWithWrongPassword() {
        okAuthPage
                .enterCredentials("Login", "Wrong password")
                .loginClick()
                .checkErrorMessage("Incorrect username and/or password");
    }

    @Test
    @DisplayName("Проверка фокуса на пустом поле пароля")
    @Tag("negative")
    @Timeout(value = 10)
    public void testChangeFocusEmptyPassword() {
        assertAll(
            "Проверка сообщений об ошибке и фокуса при пустом пароле",
            () -> okAuthPage
                .enterCredentials("Login", "")
                .loginClick()
                .checkFocusOnPassword(),
            () -> okAuthPage.checkErrorMessage("Enter password")
        );
    }

    @Test
    @DisplayName("Проверка появления сообщения для восстановления доступа")
    @Tag("negative")
    @Timeout(value = 20)
    public void testInvalidLoginWarningMessage() {
        for (int i = 0; i < 3; i++) {
            okAuthPage
                    .enterCredentials("Login", "Wrong password")
                    .loginClick();
        }
        okAuthPage.checkRestoreAccessMessage();
    }

    @Nested
    @DisplayName("Тесты восстановления доступа")
    @Tag("restore-access")
    class RestoreAccessTests {

        @Test
        @DisplayName("Проверка полей формы поддержки")
        @Timeout(value = 15)
        public void testCustomerSupportFormFields() {
            okAuthPage
                    .restoreAccessButtonClick()
                    .contactSupportButtonClick()
                    .checkContactSupportFormFields();
        }

        @ParameterizedTest
        @DisplayName("Проверка сообщения об ошибке при пустом email")
        @ValueSource(strings = {"", " "})
        @Timeout(value = 15)
        public void testEmptyRecoveryEmailField(String email) {
            okAuthPage
                    .restoreAccessButtonClick()
                    .emailButtonClick()
                    .setEmailValue(email)
                    .getCodeButtonClick()
                    .checkRestoreEmailErrorMessage("Incorrect e-mail format");
        }
    }

    @TestFactory
    @DisplayName("Динамические тесты для проверки различных комбинаций логина/пароля")
    public DynamicTest[] testLoginWithDifferentCredentials() {
        return new DynamicTest[] {
            DynamicTest.dynamicTest("Пустой логин, правильный пароль", () -> {
                okAuthPage
                    .enterCredentials("", correctPassword)
                    .loginClick()
                    .checkErrorMessage("Enter your username");
            }),
            DynamicTest.dynamicTest("Правильный логин, неправильный пароль", () -> {
                okAuthPage
                    .enterCredentials(correctLogin, wrongPassword)
                    .loginClick()
                    .checkErrorMessage("Incorrect username and/or password");
            })
        };
    }
}