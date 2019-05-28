import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Default Selenide driver tests")
class DefaultDriverTests {
    @BeforeEach
    void setUp() {
        Configuration.driverManagerEnabled = false;
        Configuration.remote = "http://localhost:4444/wd/hub";
    }

    @Test
    @DisplayName("Able to run default driver on Selenoid")
    void ableToRunDefaultDriverOnSelenoid() {
        open("https://www.google.com");
        assertEquals(title(), "Google");
    }
}
