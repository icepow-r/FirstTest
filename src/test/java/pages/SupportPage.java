package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SupportPage {
    private final SelenideElement problemDropdown = $("#categorynew");
    private final SelenideElement subjectDropdown = $("#subcategory");
    private final SelenideElement categoryDropdown = $("#categoryValue");
    private final SelenideElement lookupDropdown = $("#lookup");
    private final SelenideElement emailField = $("#field_e-mail");

    public SupportPage checkContactSupportFormFields() {
        problemDropdown.shouldBe(visible);
        subjectDropdown.shouldBe(visible);
        categoryDropdown.shouldBe(visible);
        lookupDropdown.shouldBe(visible);
        emailField.shouldBe(visible);
        return this;
    }
}
