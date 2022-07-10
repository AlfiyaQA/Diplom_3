import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.After;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import pageobjects.AuthPage;
import pageobjects.RegistrationPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    private UserClient userClient;
    User user = User.getRandom();
    private String token;

    @Before
    public void setUp() {
        userClient = new UserClient();
    }

    @After
    public void teardown() {
        userClient.deleteUser(token);
    }

    @Test
    @DisplayName("Checking successful registration")
    public void checkSuccessRegistration() {
        mainPage.clickEnterBtn();
        AuthPage authPage = open(AuthPage.URL, AuthPage.class);
        authPage.clickRegLinkBtn();
        RegistrationPage regPage = open(RegistrationPage.URL, RegistrationPage.class);
        regPage.fillInputName(user.getName());
        regPage.fillInputEmail(user.getEmail());
        regPage.fillInputPassword(user.getPassword());
        boolean result = regPage.isSuccessRegistration();
        assertTrue(result);
    }

   @Test
    @DisplayName("Checking registration is failed")
    public void checkRegistrationFailed() {
        mainPage.clickEnterBtn();
        AuthPage authPage = open(AuthPage.URL, AuthPage.class);
        authPage.clickRegLinkBtn();
        RegistrationPage regPage = open(RegistrationPage.URL, RegistrationPage.class);
        regPage.fillInputName(user.getName());
        regPage.fillInputEmail(user.getEmail());
        regPage.fillInputPassword(RandomStringUtils.randomAlphanumeric(5));
        boolean result = regPage.isSuccessRegistration();
        assertTrue(result);
    }

    @Test
    @DisplayName("Checking password can not be null")
    public void checkPasswordCanNotBeNull() {
        mainPage.clickEnterBtn();
        AuthPage authPage = open(AuthPage.URL, AuthPage.class);
        authPage.clickRegLinkBtn();
        RegistrationPage regPage = open(RegistrationPage.URL, RegistrationPage.class);
        regPage.fillInputName(user.getName());
        regPage.fillInputEmail(user.getEmail());
        regPage.fillInputPassword("");
        boolean result = regPage.isSuccessRegistration();
        assertTrue(result);
    }
}
