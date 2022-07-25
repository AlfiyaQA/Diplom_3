
import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class AuthorizationTest extends BaseTest {

    private UserClient userClient;
    private String token;
    User user = User.getRandom();
    MainPage mainPage = open(MainPage.URL, MainPage.class);
    AuthPage authPage = open(AuthPage.URL, AuthPage.class);
    HeaderPage headerPage = open(HeaderPage.URL, HeaderPage.class);

    @Before
    public void setUp() {
        userClient = new UserClient();
        ValidatableResponse createResponse = userClient.createUser(user);
        token = userClient.getUserToken(createResponse);
    }

    @After
    public void teardown() {
        userClient.deleteUser(token);
    }

    @Test
    @DisplayName("Check enter from main page")
    public void checkEnterFromMainPage() throws Exception {
        mainPage.clickEnterBtn();
        authPage.fillAuthInputEmail(user.getEmail());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.clickAuthBtn();
        assertTrue(mainPage.isUserAuthorized());
    }

    @Test
    @DisplayName("Check enter from profile")
    public void checkEnterFromProfile() throws Exception {
        headerPage.clickProfileBtn();
        authPage.fillAuthInputEmail(user.getEmail());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.clickAuthBtn();
        assertTrue(mainPage.isUserAuthorized());
    }

    @Test
    @DisplayName("Check enter from registration page")
    public void checkEnterFromRegPage() throws Exception {
        RegistrationPage regPage = open(RegistrationPage.URL, RegistrationPage.class);
        regPage.clickEnterLinkBtn();
        authPage.fillAuthInputEmail(user.getEmail());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.clickAuthBtn();
        assertTrue(mainPage.isUserAuthorized());
    }

    @Test
    @DisplayName("Check enter from password refresh page")
    public void checkEnterFromPasswordRefreshPage() throws Exception {
        PasswordRefreshPage passwordRefreshPage = open(PasswordRefreshPage.URL, PasswordRefreshPage.class);
        passwordRefreshPage.clickAuthFromRefreshLinkBtn();
        authPage.fillAuthInputEmail(user.getEmail());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.clickAuthBtn();
        assertTrue(mainPage.isUserAuthorized());
    }
}

