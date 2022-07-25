import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class MainPageTest extends BaseTest {

    @Test
    @DisplayName("Check bun tab active")
    public void checkBunTabActive() throws Exception {
        boolean result = mainPage.isActiveBunTab();
        assertTrue(result);
    }

    @Test
    @DisplayName("Check sauce tab active")
    public void checkSauceTabActive() throws Exception {
        boolean result = mainPage.isActiveSauceTab();
        assertTrue(result);
    }

    @Test
    @DisplayName("Check filling tab active")
    public void checkFillingTabActive() throws Exception {
        boolean result = mainPage.isActiveFillingTab();
        assertTrue(result);
    }
}
