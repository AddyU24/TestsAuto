package RegressionPagesObjetModel;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacturePage {
	
	

	private WebDriver driver;

    public FacturePage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Les differents elements sur la page Facture
    
    //By ChampsMontant = By.xpath("//input[@id='a2594298-ca69-4063-9f29-1201d4f8091d']");
    private By MessageErrorMontantInvalide = By.xpath("//span[normalize-space()='Le montant est invalide']");
    
    //By ChampsMontant = By.xpath("//input[@id='612f5a14-c7fd-42cc-bf2c-ffdcd298c591']");
  
    private By ChampsMontant = By.name("facture-u-ticketAmount");
    private By ChampsNom = By.name("facture-u-lastname");
    private By ChampsPrenom = By.name("facture-u-firstname");
    private By ErreurMessageChampsNom = By.xpath("//span[normalize-space()='Le nom est manquant']");
    private By ErreurMessageChampsPrenom  = By.xpath("//span[normalize-space()='Le prénom est manquant']");
    private By ThirdFieldsetOfForm = By.id("particulierFieldset");
 
    
    // Les methods et actions 
    
    public void InsererMontant(String montant) {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds((50)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ChampsMontant));
    	driver.findElement(ChampsMontant).sendKeys(montant);
    	
    }
    
    
    
    /*public boolean ErrorMontantInvalide () {
    	return driver.findElement(ErrorMontantInvalide).isDisplayed();
    	
    }   */
    
    // Cette fonction permet de verifier si les montants négatifs sont acceptés
   
    /*
    public boolean ErrorMontantInvalide() {
        try {
        	
            return driver.findElement(MessageErrorMontantInvalide).isDisplayed();
        } catch (NoSuchElementException e) {
            return false; // si l’élément n’est pas présent, c’est qu’il n’y a pas d’erreur, donc montant accepté
        }
        
    }  */
    
    public boolean ErrorMontantInvalide() {
        List<WebElement> errors = driver.findElements(MessageErrorMontantInvalide);
        return !errors.isEmpty() && errors.get(0).isDisplayed();
    }

    // Cette methode nous permet de cliquer à des éléments 
    
    public void Click(WebDriverWait wait,By locator) {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(locator));
    	driver.findElement(locator).click();
    } 
    
    public boolean AffichageErrorChampsNomPrenom() {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	try {
    		
    		wait.until(ExpectedConditions.elementToBeClickable(ChampsNom));
    		Click(wait,ChampsNom);
    		wait.until(ExpectedConditions.elementToBeClickable(ThirdFieldsetOfForm));
    		Click(wait,ThirdFieldsetOfForm);
    		wait.until(ExpectedConditions.elementToBeClickable(ChampsPrenom));
    		Click(wait,ChampsPrenom);
    		wait.until(ExpectedConditions.elementToBeClickable(ThirdFieldsetOfForm));
    		Click(wait,ThirdFieldsetOfForm);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(ErreurMessageChampsNom));
    		wait.until(ExpectedConditions.visibilityOfElementLocated(ErreurMessageChampsPrenom));
    		return true;
    		
    		
    	} catch (TimeoutException | NoSuchElementException e) {
    		
    		System.out.println("Les messages d'erreur ne s'affichent pas : " + e.getMessage());
	        return false;
    		
    	}
    	
    }
    
    
    /*
    void AcceptationMontantNegatif(String montant) {
    	InsererMontant(montant);
    	ErrorMontantInvalide ();
    }
    
	*/
	
	
}
