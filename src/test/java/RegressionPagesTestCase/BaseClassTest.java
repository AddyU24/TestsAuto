package RegressionPagesTestCase;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import RegressionPagesObjetModel.AuthentificationPage;
import RegressionPagesObjetModel.HomeRecPage;
import RegressionPagesObjetModel.LoginPage;

public class BaseClassTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    public static WebDriver staticDriver; // 👈 Driver accessible globalement

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--user-agent=ALLOWUA");

        driver = new ChromeDriver(options);
        staticDriver = driver; // 👈 Affectation à la variable statique

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    public void loginToApp() throws InterruptedException {
        String username = "ufrfront";
        String passwordHttp = "KtBZ2Xuh8rkqpKZq9TLtT9vT";
        String authPagePass = "@Brazzaville24";
        String mail = "detticageino-1152@yopmail.fr";
        String mailPass = "@France24";

        wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        String baseUrl = "https://" + username + ":" + passwordHttp + "@www.rec1.magasins-u.com/";
        driver.get(baseUrl);

        AuthentificationPage authPage = new AuthentificationPage(driver);
        if (authPage.isAuthenticationPageDisplayed()) {
            authPage.login("aetoueli", authPagePass);
            System.out.println("Page d'authentification");
        }

        HomeRecPage homePage = new HomeRecPage(driver);
        homePage.openLoginPage();

        LoginPage login = new LoginPage(driver);
        login.login(mail, mailPass);

        Thread.sleep(3000);

        String redirectedUrl = driver.getCurrentUrl();
        String cleanedUrl = redirectedUrl.replace("https://", "");
        String finalUrl = "https://" + username + ":" + passwordHttp + "@" + cleanedUrl;
        driver.get(finalUrl);

        System.out.println("Connexion réussie, URL actuelle : " + driver.getCurrentUrl());

        wait.until(ExpectedConditions.urlContains(".connected"));
        System.out.println("URL actuelle : " + driver.getCurrentUrl());
        Assert.assertTrue(homePage.isUserLoggedInByUrl(), "Connexion échouée : pas de trace de '.connected' dans l'URL !");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Permet d'accéder au driver depuis ExtentReport ou ailleurs
    public static WebDriver getDriver() {
        return staticDriver;
    }
}
