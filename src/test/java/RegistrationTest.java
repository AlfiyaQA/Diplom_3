import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCredentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.*;
import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class RegistrationTest extends BaseTest {

    private UserClient userClient;
    private String token;
    User user = User.getRandom();
    MainPage mainPage = open(MainPage.URL, MainPage.class);
    AuthPage authPage = open(AuthPage.URL, AuthPage.class);
    RegistrationPage regPage = open(RegistrationPage.URL, RegistrationPage.class);

    @Before
    public void setUp() {
        userClient = new UserClient();

        mainPage.clickEnterBtn();
        authPage.clickRegLinkBtn();
    }

    @After
    public void teardown() {
        if (token != null) {
            userClient.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Checking successful registration")
    public void checkSuccessRegistration() {
        regPage.fillInputName(user.getName());
        regPage.fillInputEmail(user.getEmail());
        regPage.fillInputPassword(user.getPassword());
        regPage.clickRegBtn();
        assertTrue(authPage.isAuthPageVisible());

        UserCredentials creds = UserCredentials.from(user);
        ValidatableResponse response = userClient.loginUser(creds);
        token = userClient.getUserToken(response);
        int statusCode = response.extract().statusCode();
        assertEquals("Код статуса отличается от ожидаемого результата", SC_OK, statusCode);
    }

    @Test
    @DisplayName("Checking registration is failed")
    public void checkRegistrationFailed() {
        regPage.fillInputName(user.getName());
        regPage.fillInputEmail(user.getEmail());
        regPage.fillInputPassword(RandomStringUtils.randomAlphanumeric(5));
        regPage.clickRegBtn();
        assertTrue(regPage.isErrorPasswordVisible());

        UserCredentials creds = UserCredentials.from(user);
        ValidatableResponse response = userClient.loginUser(creds);
        token = userClient.getUserToken(response);
        assertNull(token);
    }

    @Test
    @DisplayName("Checking password can not be null")
    public void checkPasswordCanNotBeNull() {
        regPage.fillInputName(user.getName());
        regPage.fillInputEmail(user.getEmail());
        regPage.fillInputPassword("");
        regPage.clickRegBtn();
        assertTrue(regPage.isRegPageVisible());

        UserCredentials creds = UserCredentials.from(user);
        ValidatableResponse response = userClient.loginUser(creds);
        token = userClient.getUserToken(response);
        assertNull(token);
    }
}

