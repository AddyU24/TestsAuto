package RegressionPagesObjetModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Cette n'est imoprtante que si lors de la connexion sur Rec1, le système nous renvoit vers une page d'authentification 

public class AuthentificationPage {
	
	private WebDriver driver;
	
	public AuthentificationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	// Localisation des différents éléments 
	
	    private By titre = By.xpath("//*[@id='app']/div/div/div[2]/h1");
	    private By usernameField = By.id("name-callback-1");
	    private By passwordField = By.id("password-callback-0");
	    private By centraleDropdown = By.id("select-code-centrale");
	    private By centraleOption = By.id("code-centrale-iris");
	    private By submitBtn = By.id("submit-continuer");
	    
	    
	// Actions et Methods 
	
	    public boolean isAuthenticationPageDisplayed() {
	        List<WebElement> titres = driver.findElements(titre);
	        return !titres.isEmpty() && titres.get(0).getText().contains("Veuillez vous authentifier");
	    }
	    
	   
	    public void login(String username, String password) {
	        driver.findElement(usernameField).sendKeys(username);
	        driver.findElement(passwordField).sendKeys(password);
	        driver.findElement(centraleDropdown).click();
	        driver.findElement(centraleOption).click();
	        driver.findElement(submitBtn).click();
	    }
	   
	

}
