package pageobjects;

import client.UserClient;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {

    public final static String URL = "https://stellarburgers.nomoreparties.site/register";

    private String password;
    private UserClient userClient;
    private String token;
    private User user;

    //Локатор заголовка Регистрация
    @FindBy(how = How.XPATH, using = ".//h2[text()='Регистрация']")
    private static SelenideElement regHeader;

    //Локатор поля Имя
    @FindBy(how = How.XPATH, using = "(.//*[contains(@class, 'input pr-6 pl-6')]/input)[1]")
    private SelenideElement inputName;

    //Локатор поля email
    @FindBy(how = How.XPATH, using = "(.//*[contains(@class, 'input pr-6 pl-6')]/input)[2]")
    private SelenideElement inputEmail;

    //Локатор поля Пароль
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement inputPassword;

    //Локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement regBtn;

    //Локатор ошибки поля Пароль
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement errorPassword;

    //Локатор линка Войти
    @FindBy(how = How.XPATH, using = ".//a[@href='/login'][text()='Войти']")
    private SelenideElement enterLinkBtn;

    //Метод заполнения поля Имя
    public RegistrationPage fillInputName(String value) {
        inputName.click();
        inputName.sendKeys(value);
        return page(RegistrationPage.class);
    }

    //Метод заполнения поля Email
    public RegistrationPage fillInputEmail(String value) {
        inputEmail.click();
        inputEmail.sendKeys(value);
        return page(RegistrationPage.class);
    }

    //Метод заполнения поля Пароль
    public RegistrationPage fillInputPassword(String value) {
        inputPassword.click();
        inputPassword.sendKeys(value);
        return page(RegistrationPage.class);
    }

    //Метод перехода по линку Войти
    public AuthPage clickEnterLinkBtn() {
        enterLinkBtn.click();
        return page(AuthPage.class);
    }

    //Проверка отображения страницы регистрации
    private static boolean has(Condition visible) {
        return regHeader.has(Condition.visible);
    }

    //Проверка получения токена
    public boolean isRegistered(String token) {
        ValidatableResponse createResponse = userClient.createUser(user);
        String accessToken = createResponse.extract().path("accessToken");
        String[] split = accessToken.split(" ");
        return token!=null && token.equals(split[1]);
    }

    //Проверка корректности регистрации юзера
    public boolean isSuccessRegistration() {
        regBtn.scrollIntoView(true).click();
        if (password == null) {
            return RegistrationPage.has(Condition.visible);
        } else if (password.length() >= 6){
            return isRegistered(token);
        } else {
            return errorPassword.has(Condition.visible);
        }
    }
}
