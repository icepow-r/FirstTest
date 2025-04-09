package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SupportPage {
    private final SelenideElement problemSelector = $("#categorynew");
    private final SelenideElement subjectSelector = $("#subcategory");
    private final SelenideElement categorySelector = $("#categoryValue");
    private final SelenideElement lookupSelector = $("#lookup");
    private final SelenideElement emailField = $("#field_e-mail");

    public SupportPage checkContactSupportFormFields() {
        problemSelector.shouldBe(visible);
        subjectSelector.shouldBe(visible);
        categorySelector.shouldBe(visible);
        lookupSelector.shouldBe(visible);
        emailField.shouldBe(visible);
        return this;
    }
}
