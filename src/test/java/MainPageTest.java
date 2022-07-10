import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class MainPageTest extends BaseTest {

    @Test
    @DisplayName("Is bun available")
    public void isBunsAvailable() {
        boolean result = mainPage.isBunSubHeaderVisible();
        assertTrue(result);
    }

    @Test
    @DisplayName("Is sauce available")
    public void isSauceAvailable() {
        boolean result = mainPage.isSauceSubHeaderVisible();
        assertTrue(result);
    }

    @Test
    @DisplayName("Is filling available")
    public void isFillingAvailable() {
        boolean result = mainPage.isFillingSubHeaderVisible();
        assertTrue(result);
    }
}
