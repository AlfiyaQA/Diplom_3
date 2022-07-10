import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.AuthPage;
import pageobjects.HeaderPage;
import pageobjects.ProfilePage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest {

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
    @DisplayName("Check enter profile")
    public void checkEnterProfile() {
        mainPage.clickEnterBtn();
        AuthPage authPage = open(AuthPage.URL, AuthPage.class);
        authPage.fillAuthInputEmail(user.getName());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.authUser();
        HeaderPage headerPage = open(HeaderPage.URL, HeaderPage.class);
        headerPage.clickProfileBtn();
        ProfilePage profilePage = open(ProfilePage.URL, ProfilePage.class);
        assertTrue(profilePage.isUserProfileAvailable());
    }

    @Test
    @DisplayName("Check enter constructor from profile")
    public void checkEnterConstructorFromProfile() {
        mainPage.clickEnterBtn();
        AuthPage authPage = open(AuthPage.URL, AuthPage.class);
        authPage.fillAuthInputEmail(user.getName());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.authUser();
        HeaderPage headerPage = open(HeaderPage.URL, HeaderPage.class);
        headerPage.clickProfileBtn();
        ProfilePage profilePage = open(ProfilePage.URL, ProfilePage.class);
        assertTrue(profilePage.isUserProfileAvailable());
        headerPage.clickConstructorBtn();
        assertTrue(mainPage.isConstHeaderVisible());
    }

    @Test
    @DisplayName("Check log out")
    public void checkLogOut() {
        mainPage.clickEnterBtn();
        AuthPage authPage = open(AuthPage.URL, AuthPage.class);
        authPage.fillAuthInputEmail(user.getName());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.authUser();
        HeaderPage headerPage = open(HeaderPage.URL, HeaderPage.class);
        headerPage.clickProfileBtn();
        ProfilePage profilePage = open(ProfilePage.URL, ProfilePage.class);
        profilePage.logOut();
        assertTrue(authPage.isAuthPageAvailable());
    }
}
