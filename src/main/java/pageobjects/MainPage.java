package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    public final static String URL = "https://stellarburgers.nomoreparties.site";

    //Локатор кнопки Войти в аккаунт на главной странице
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement enterBtn;

    //Локатор текста Соберите бургер
    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    public static SelenideElement constructBurgerText;

    //Локатор вкладки Булки
    @FindBy(how = How.XPATH, using = ".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']")
    public SelenideElement bunTab;

    //Локатор вкладки Соусы
    @FindBy(how = How.XPATH, using = ".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']")
    public SelenideElement sauceTab;

    //Локатор вкладки Начинки
    @FindBy(how = How.XPATH, using = ".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']")
    public SelenideElement fillingTab;

    //Локатор подзаголовка Булки
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    public SelenideElement bunSubHeader;

    //Локатор подзаголовка Соусы
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    public SelenideElement sauceSubHeader;

    //Локатор подзаголовка Начинки
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    public SelenideElement fillingSubHeader;


    //Метод клика по кнопке
    public AuthPage clickEnterBtn() {
        enterBtn.click();
        return page(AuthPage.class);
    }

    //Метод проверки отображения конструктора
    public boolean isConstHeaderVisible() {
        constructBurgerText.shouldBe(Condition.visible);
        return constructBurgerText.getText().equals("Соберите бургер");
    }

    //Метод проверки доступности подраздел Булки
    public boolean isBunSubHeaderVisible() {
        bunTab.click();
        bunSubHeader.shouldBe(Condition.visible);
        return bunSubHeader.getText().equals("Булки");
    }

    //Метод проверки доступности подраздел Соусы
    public boolean isSauceSubHeaderVisible() {
        sauceTab.click();
        sauceSubHeader.shouldBe(Condition.visible);
        return sauceSubHeader.getText().equals("Соусы");
    }

    //Метод проверки доступности подраздел Начинки
    public boolean isFillingSubHeaderVisible() {
        fillingTab.click();
        fillingSubHeader.shouldBe(Condition.visible);
        return fillingSubHeader.getText().equals("Начинки");
    }
}
