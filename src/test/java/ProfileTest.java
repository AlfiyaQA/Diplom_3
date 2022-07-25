import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.AuthPage;
import pageobjects.HeaderPage;
import pageobjects.MainPage;
import pageobjects.ProfilePage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest {

    private UserClient userClient;
    private String token;
    User user = User.getRandom();
    MainPage mainPage = open(MainPage.URL, MainPage.class);
    AuthPage authPage = open(AuthPage.URL, AuthPage.class);
    HeaderPage headerPage = open(HeaderPage.URL, HeaderPage.class);
    ProfilePage profilePage = open(ProfilePage.URL, ProfilePage.class);

    @Before
    public void setUp() throws Exception {
        userClient = new UserClient();
        ValidatableResponse createResponse = userClient.createUser(user);
        token = userClient.getUserToken(createResponse);

        mainPage.clickEnterBtn();
        authPage.fillAuthInputEmail(user.getEmail());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.clickAuthBtn();
    }

    @After
    public void teardown() {
        userClient.deleteUser(token);
    }

    @Test
    @DisplayName("Check enter profile")
    public void checkEnterProfile() {
        headerPage.clickProfileBtn();
        assertTrue(profilePage.isUserProfileVisible());
    }

    @Test
    @DisplayName("Check enter constructor from profile")
    public void checkEnterConstructorFromProfile() {
        headerPage.clickProfileBtn();
        assertTrue(profilePage.isUserProfileVisible());
        headerPage.clickConstructorBtn();
        assertTrue(mainPage.isConstHeaderVisible());
    }

    @Test
    @DisplayName("Check enter constructor from profile by clicking logo")
    public void checkEnterConstructorFromProfileClickLogo() {
        headerPage.clickProfileBtn();
        assertTrue(profilePage.isUserProfileVisible());
        headerPage.clickBurgerLogo();
        assertTrue(mainPage.isConstHeaderVisible());
    }

    @Test
    @DisplayName("Check log out")
    public void checkLogOut() {
        headerPage.clickProfileBtn();
        profilePage.logOut();
        assertTrue(authPage.isAuthPageVisible());
    }
}

