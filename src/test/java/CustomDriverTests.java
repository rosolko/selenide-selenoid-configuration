import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Custom Selenide driver tests")
class CustomDriverTests {
    @BeforeEach
    void setUp() {
        Configuration.driverManagerEnabled = false;
        Configuration.browser = CustomProvider.class.getName();
    }

    @Test
    @DisplayName("Able to run custom driver on Selenoid")
    void ableToRunCustomDriverOnSelenoid() {
        open("https://www.google.com");
        assertEquals(title(), "Google");
    }

    public static class CustomProvider implements WebDriverProvider {
        @Override
        public WebDriver createDriver(final DesiredCapabilities capabilities) {
            final FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setAcceptInsecureCerts(true);
            firefoxOptions.merge(capabilities);
            try {
                return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
            } catch (final MalformedURLException e) {
                throw new RuntimeException("Unable to create remote driver", e);
            }
        }
    }
}
