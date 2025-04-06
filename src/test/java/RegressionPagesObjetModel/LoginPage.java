package RegressionPagesObjetModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


// Cette page est dédié à la page de connexion une fois cliquer sur le bouton se connecter de rec1

public class LoginPage {
	
	
	private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.id("input-14");
    private By passwordField = By.id("input-16");
    private By continuerBtn = By.id("continuer");

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(continuerBtn).click();
    }

}
