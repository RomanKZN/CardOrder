package ru.netology.cardorder;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

class CardOrderTest {
    private WebDriver driver;
    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }
    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


    @Test
    void test1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79999998888");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected, actual);
    }
}

