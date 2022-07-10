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

    @Before
    public void setUp() {
        userClient = new UserClient();
        ValidatableResponse createResponse = userClient.createUser(user);
        String accessToken = createResponse.extract().path("accessToken");
        String[] split = accessToken.split(" ");
        token = split[1];
    }

    @After
    public void teardown() {
        userClient.deleteUser(token);
    }

    @Test
    @DisplayName("Check enter from main page")
    public void checkEnterFromMainPage() {
        mainPage.clickEnterBtn();
        AuthPage authPage = open(AuthPage.URL, AuthPage.class);
        authPage.fillAuthInputEmail(user.getName());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.authUser();
        AfterAuthMainPage afterAuthMainPage = open(AfterAuthMainPage.URL, AfterAuthMainPage.class);
        assertTrue(afterAuthMainPage.isUserAuthorized());
    }

    @Test
    @DisplayName("Check enter from profile")
    public void checkEnterFromProfile() {
        HeaderPage headerPage = open(HeaderPage.URL, HeaderPage.class);
        headerPage.clickProfileBtn();
        AuthPage authPage = open(AuthPage.URL, AuthPage.class);
        authPage.fillAuthInputEmail(user.getName());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.authUser();
        AfterAuthMainPage afterAuthMainPage = open(AfterAuthMainPage.URL, AfterAuthMainPage.class);
        assertTrue(afterAuthMainPage.isUserAuthorized());
    }

    @Test
    @DisplayName("Chek enter from registration page")
    public void checkEnterFromRegPage() {
        mainPage.clickEnterBtn();
        AuthPage authPage = open(AuthPage.URL, AuthPage.class);
        authPage.clickRegLinkBtn();
        RegistrationPage regPage = open(RegistrationPage.URL, RegistrationPage.class);
        regPage.clickEnterLinkBtn();
        AuthPage authPage2 = open(AuthPage.URL, AuthPage.class);
        authPage.fillAuthInputEmail(user.getName());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.authUser();
        AfterAuthMainPage afterAuthMainPage = open(AfterAuthMainPage.URL, AfterAuthMainPage.class);
        assertTrue(afterAuthMainPage.isUserAuthorized());
    }

    @Test
    @DisplayName("Check enter from password refresh page")
    public void checkEnterFromPasswordRefreshPage() {
        mainPage.clickEnterBtn();
        AuthPage authPage = open(AuthPage.URL, AuthPage.class);
        authPage.clickRefreshLinkBtn();
        PasswordRefreshPage passwordRefreshPage = open(PasswordRefreshPage.URL, PasswordRefreshPage.class);
        passwordRefreshPage.clickAuthFromRefreshLinkBtn();
        AuthPage authPage2 = open(AuthPage.URL, AuthPage.class);
        authPage.fillAuthInputEmail(user.getName());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.authUser();
        AfterAuthMainPage afterAuthMainPage = open(AfterAuthMainPage.URL, AfterAuthMainPage.class);
        assertTrue(afterAuthMainPage.isUserAuthorized());
    }
}
