import com.google.common.io.Files;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class homeTests {
    static WebDriver driver = new ChromeDriver ();
    LocalDateTime currentTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @BeforeEach
    void openPageAndClearCookies() {
        driver.get("https://myjniaforum.pl/");
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }

    @Test
    void shouldDisplayCorrectTitle() throws IOException {
        driver.manage().window().maximize();
        String title = driver.getTitle();
        String expectedTitle = "Myjnia Forum – Myjnia Samochodowa Forum Gdańsk";

        if (!title.equals(expectedTitle)) {
            String fileName = "C:\\CarWashTestsScr\\WrongPageTitle Actual " + title + " Expected " + expectedTitle + " " + currentTime.format(formatter) + ".png";
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(scrFile, new File(fileName));
        }
        Assertions.assertEquals(expectedTitle, title,
                "The title is different than expected");
    }

}
