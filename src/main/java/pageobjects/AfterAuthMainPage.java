package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AfterAuthMainPage {

    public final static String URL = "https://stellarburgers.nomoreparties.site";

    //Локатор кнопки Оформить заказ
    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']/button[text()='Оформить заказ']")
    private SelenideElement makeOrderBtn;

    //Метод проверки авторизации
    public boolean isUserAuthorized() {
        makeOrderBtn.shouldBe(Condition.visible);
        return makeOrderBtn.getText().equals("Оформить заказ");
    }
}
