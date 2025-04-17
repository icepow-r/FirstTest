package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebElementCondition.*;
import static com.codeborne.selenide.Selenide.$;

public class SupportPage {
    private final SelenideElement problemDropdown = $("#categorynew");
    private final SelenideElement subjectDropdown = $("#subcategory");
    private final SelenideElement categoryDropdown = $("#categoryValue");
    private final SelenideElement lookupDropdown = $("#lookup");
    private final SelenideElement emailField = $("#field_e-mail");

    public SupportPage checkContactSupportFormFields() {
        problemDropdown.shouldBe(visible.because("Поле выбора проблемы не отображается"));
        subjectDropdown.shouldBe(visible.because("Поле выбора темы не отображается"));
        categoryDropdown.shouldBe(visible.because("Поле выбора категории не отображается"));
        lookupDropdown.shouldBe(visible.because("Поле выбора поиска не отображается"));
        emailField.shouldBe(visible.because("Поле email не отображается"));
        return this;
    }
}
