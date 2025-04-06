package RegressionPagesObjetModel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagasinThouarePage {
	
	
	private WebDriver driver;

    public MagasinThouarePage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Identification des elements sur cette page
    private By BouttonNousContacter = By.xpath("//div[@class='contacter u-button button']//button[@type='button']");
    
    
    
    // Differentes methodes sur cette page 
    
    // Cette méthode permet de cliquer à des éléments
    public void Click(WebDriverWait wait , By locator) {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(locator));
    	driver.findElement(locator).click();
    }
    
    // Cette fonction n'est plus utilisée , elle a été remplacée par Click
    public void ClickSurBouttonNousContacter() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds((50)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BouttonNousContacter));
        driver.findElement(BouttonNousContacter).click();
    }
    
    public boolean VerifierAffichageDesChampsDuFormulaireNousContacter() {
    	
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    	    try {
    	    	// Clique sur le boutton Nous contacter
    	    	Click(wait, BouttonNousContacter);
    	        // Vérification individuelle de chaque élément du formulaire
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='dialogpopin-store-contact_label']")));
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))); // email
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastname"))); // nom
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname"))); // prénom
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phoneNumber"))); // téléphone
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("carteuNumber"))); // carte U
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("message"))); // message
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='dialogpopin-store-contact']//button[@class='u-btn u-btn--secondary']"))); // annuler
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='u-contact-store-form-submit-button']"))); // valider

    	        return true;

    	    } catch (TimeoutException | NoSuchElementException e) {
    	        System.out.println("Formulaire incomplet ou non chargé : " + e.getMessage());
    	        return false;
    	    }
    	
    }

}
