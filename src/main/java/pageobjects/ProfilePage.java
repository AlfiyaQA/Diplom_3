package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {

    public final static String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //Локатор надписи Профиль в личном кабинете
    @FindBy(how = How.XPATH, using = ".//*[@class='Account_text__fZAIn text text_type_main-default']")
    private SelenideElement profileText;

    //Локатор кнопки Выход
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement logOutBtn;

    //Метод проверки отображения кабинета авторизованного юзера
    public boolean isUserProfileAvailable() {
        profileText.shouldBe(Condition.visible);
        return profileText.getText().equals("В этом разделе вы можете изменить свои персональные данные");
    }

    //Метод выхода из аккаунта
    public AuthPage logOut() {
        logOutBtn.scrollIntoView(true).click();
        return page(AuthPage.class);
    }
}
