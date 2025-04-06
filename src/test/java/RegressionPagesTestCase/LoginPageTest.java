package RegressionPagesTestCase;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import RegressionPagesObjetModel.AuthentificationPage;
import RegressionPagesObjetModel.HomeRecPage;
import RegressionPagesObjetModel.LoginPage;

public class LoginPageTest extends BaseTest {
	
	 @Test
	    public void testDeConnexion() throws InterruptedException {
	        String username = "ufrfront";
	        String passwordHttp = "KtBZ2Xuh8rkqpKZq9TLtT9vT";
	        String authPagePass = "@Brazzaville24";
	        String mail = "detticageino-1152@yopmail.fr";
	        String mailPass = "@France24";
	     // Attendre que la redirection mène à une URL contenant "/accueil.connected"
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

	        LoginPage Login = new LoginPage(driver);
	        Login.login(mail, mailPass);

	        // Attendre la redirection
	        Thread.sleep(3000);

	        String redirectedUrl = driver.getCurrentUrl();
	        String cleanedUrl = redirectedUrl.replace("https://", "");
	        String finalUrl = "https://" + username + ":" + passwordHttp + "@" + cleanedUrl;
	        driver.get(finalUrl);

	        System.out.println("Connexion réussie, URL actuelle : " + driver.getCurrentUrl());
	        
	        wait.until(ExpectedConditions.urlContains(".connected"));

	        // C'est sur cette partie que la verification de la connexion a été faite
	        
	        Assert.assertTrue(homePage.isUserLoggedInByUrl(), "Connexion échouée : pas de trace de '.connected' dans l'URL !");


	    }
	
}
